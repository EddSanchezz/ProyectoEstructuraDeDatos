package com.uniquindisoft.colpensionex.service;

import com.uniquindisoft.colpensionex.model.*;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.time.LocalDate;

public class ExportacionService {
    private static final Logger logger = Logger.getLogger(ExportacionService.class.getName());
    private final Path directorioExportacion;

    public ExportacionService(Path directorioExportacion) {
        this.directorioExportacion = directorioExportacion;
    }

    public void exportarCotizantesProcesados(List<Cotizante> cotizantes) {
        List<String> lineas = new ArrayList<>();
        lineas.add("tipoDocumento,numeroDocumento,nombreCompleto,ciudad,fondoOrigen,estado,fechaProcesamiento");

        for (Cotizante cotizante : cotizantes) {
            String linea = String.format("%s,%s,%s,%s,%s,%s,%s",
                cotizante.getTipoDocumento(),
                cotizante.getNumeroDocumento(),
                cotizante.getNombreCompleto(),
                cotizante.getAtributo("ciudad"),
                cotizante.getFondoOrigen(),
                cotizante.getAtributo("estado"),
                LocalDate.now().format(DateTimeFormatter.ISO_DATE)
            );
            lineas.add(linea);
        }

        try {
            String nombreArchivo = "cotizantes_procesados_" + 
                                 LocalDate.now().toString() + ".csv";
            Path archivoSalida = directorioExportacion.resolve(nombreArchivo);
            Files.write(archivoSalida, lineas);
            logger.info("Archivo de cotizantes procesados generado: " + archivoSalida);
        } catch (Exception e) {
            logger.severe("Error exportando cotizantes: " + e.getMessage());
            throw new RuntimeException("Error exportando cotizantes", e);
        }
    }

    public void moverArchivoProcesado(Path archivo) {
        try {
            Path carpetaProcesados = directorioExportacion.resolve(
                "SolicitudesProcesadas_" + LocalDate.now().toString().replace("-", "_")
            );
            
            if (!Files.exists(carpetaProcesados)) {
                Files.createDirectories(carpetaProcesados);
            }

            Files.move(archivo, 
                      carpetaProcesados.resolve(archivo.getFileName()),
                      StandardCopyOption.REPLACE_EXISTING);
            
            logger.info("Archivo movido a procesados: " + archivo.getFileName());
        } catch (Exception e) {
            logger.severe("Error moviendo archivo procesado: " + e.getMessage());
            throw new RuntimeException("Error moviendo archivo procesado", e);
        }
    }
}