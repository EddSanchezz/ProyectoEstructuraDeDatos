// ProcessingSolicitudService.java
package com.uniquindisoft.colpensionex.service;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.logging.Logger;
import com.uniquindisoft.colpensionex.model.*;
import com.uniquindisoft.colpensionex.queue.ColaPriorizada;

public class ProcessingSolicitudService {
    private static final Logger logger = Logger.getLogger(ProcessingSolicitudService.class.getName());
    private final CaracterizacionService caracterizacionService;
    private final PriorizacionService priorizacionService;
    private final ExportacionService exportacionService;
    private final ListaNegra listaNegra;
    private final ColaPriorizada<Solicitud> colaSolicitudes;
    private final Path directorioBase;

    public ProcessingSolicitudService(Path directorioBase) {
        this.directorioBase = directorioBase;
        this.caracterizacionService = new CaracterizacionService();
        this.priorizacionService = new PriorizacionService();
        this.exportacionService = new ExportacionService(directorioBase.resolve("SolicitudesProcesadas"));
        this.listaNegra = new ListaNegra();
        this.colaSolicitudes = new ColaPriorizada<>();
    }

    public void procesarSolicitud(Solicitud solicitud) {
        try {
            LocalDate fechaActual = LocalDate.now();

            // Paso 1: Verificar caracterizaciones
            if (caracterizacionService.verificarCaracterizaciones(solicitud)) {
                // Paso 2: Verificar si está en lista negra
                if (listaNegra.esInhabilitado(
                        solicitud.getCotizante().getTipoDocumento(),
                        solicitud.getCotizante().getNumeroDocumento(),
                        fechaActual)) {
                    solicitud.setEstado(Solicitud.ESTADO_RECHAZADA);
                    logger.info("Solicitud rechazada por inhabilitación: " +
                            solicitud.getCotizante().getNumeroDocumento());
                    return;
                }

                // Paso 3: Verificar si es embargable
                if (listaNegra.esEmbargable(
                        solicitud.getCotizante().getTipoDocumento(),
                        solicitud.getCotizante().getNumeroDocumento())) {
                    solicitud.setEstado(Solicitud.ESTADO_APROBADA);
                    solicitud.setEmbargado(true);
                    logger.info("Solicitud aprobada con embargo: " +
                            solicitud.getCotizante().getNumeroDocumento());
                    return;
                }

                // Paso 4: Verificar políticas internas
                if (verificarPoliticasInternas(solicitud)) {
                    solicitud.setEstado(Solicitud.ESTADO_APROBADA);
                    // Paso 5: Priorizar y encolar
                    int prioridad = priorizacionService.calcularPrioridad(solicitud);
                    colaSolicitudes.encolar(solicitud, prioridad);
                    logger.info("Solicitud aprobada y encolada con prioridad " + prioridad + ": " +
                            solicitud.getCotizante().getNumeroDocumento());
                } else {
                    solicitud.setEstado(Solicitud.ESTADO_RECHAZADA);
                    logger.info("Solicitud rechazada por políticas internas: " +
                            solicitud.getCotizante().getNumeroDocumento());
                }
            } else {
                solicitud.setEstado(Solicitud.ESTADO_RECHAZADA);
                logger.info("Solicitud rechazada por caracterizaciones: " +
                        solicitud.getCotizante().getNumeroDocumento());
            }
        } catch (Exception e) {
            logger.severe("Error procesando solicitud: " + e.getMessage());
            solicitud.setEstado(Solicitud.ESTADO_RECHAZADA);
        }
    }

    private boolean verificarPoliticasInternas(Solicitud solicitud) {
        Cotizante cotizante = solicitud.getCotizante();

        // Verificar pre-pensión
        if (cotizante.isPrePensionado()) {
            logger.fine("Cotizante pre-pensionado: " + cotizante.getNumeroDocumento());
            return false;
        }

        // Verificar semanas cotizadas según fondo de origen
        String fondoOrigen = cotizante.getFondoOrigen();
        int semanasCotizadas = cotizante.getSemanasCotizadas();

        boolean cumpleRequisitos = switch (fondoOrigen.toLowerCase()) {
            case "porvenir" -> semanasCotizadas >= 800;
            case "proteccion" -> semanasCotizadas >= 590;
            case "colfondos" -> semanasCotizadas >= 300;
            case "old mutual" -> semanasCotizadas >= 100;
            default -> false;
        };

        if (!cumpleRequisitos) {
            logger.fine("No cumple requisitos de semanas cotizadas: " +
                    cotizante.getNumeroDocumento() +
                    " (Fondo: " + fondoOrigen +
                    ", Semanas: " + semanasCotizadas + ")");
        }

        return cumpleRequisitos;
    }
}