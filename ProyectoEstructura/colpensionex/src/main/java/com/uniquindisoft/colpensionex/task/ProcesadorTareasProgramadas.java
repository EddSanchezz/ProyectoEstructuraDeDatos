package com.uniquindisoft.colpensionex.task;

import com.uniquindisoft.colpensionex.service.*;

import java.util.concurrent.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.nio.file.*;
import java.util.logging.Logger;

public class ProcesadorTareasProgramadas {
    private static final Logger logger = Logger.getLogger(ProcesadorTareasProgramadas.class.getName());
    private final ScheduledExecutorService scheduler;
    private final ProcessingSolicitudService processingSolicitudService;
    private final Path directorioBase;
    private final CompresionService compresionService;
    private static final int MAX_SOLICITUDES_POR_DIA = 100;

    public ProcesadorTareasProgramadas(Path directorioBase) {
        this.scheduler = Executors.newScheduledThreadPool(4);
        this.directorioBase = directorioBase;
        this.processingSolicitudService = new ProcessingSolicitudService(directorioBase);
        this.compresionService = new CompresionService();
        iniciarTareas();
    }

    private void iniciarTareas() {
        // Tarea cada hora para mover archivos
        scheduler.scheduleAtFixedRate(
                this::moverArchivosSolicitudes,
                calcularProximaHora(),
                TimeUnit.HOURS.toMillis(1),
                TimeUnit.MILLISECONDS
        );

        // Tarea diaria para procesar solicitudes (1:00 AM)
        scheduler.scheduleAtFixedRate(
                this::procesarSolicitudesDiarias,
                calcularProximaEjecucion (6,53),
                TimeUnit.DAYS.toMillis(1),
                TimeUnit.MILLISECONDS
        );

        // Tarea diaria para manejar caracterizaciones (00:30 AM)
        scheduler.scheduleAtFixedRate(
                this::procesarCaracterizaciones,
                calcularProximaEjecucion(6, 55),
                TimeUnit.DAYS.toMillis(1),
                TimeUnit.MILLISECONDS
        );

        logger.info("Tareas programadas iniciadas correctamenteeeaeaea");
    }

    private void moverArchivosSolicitudes() {//atencion a esta linnea
        try {
            Path origen = directorioBase.resolve("SolicitudesEntrantes");
            Path destino = directorioBase.resolve("SolicitudesEnProcesamiento");

            if (!Files.exists(destino)) {
                Files.createDirectories(destino);
            }

            Files.list(origen)
                    .filter(path -> path.toString().endsWith(".csv"))
                    .forEach(archivo -> {
                        try {
                            Files.move(archivo,
                                    destino.resolve(archivo.getFileName()),
                                    StandardCopyOption.REPLACE_EXISTING);
                            logger.info("Archivo movido: " + archivo.getFileName());
                        } catch (Exception e) {
                            logger.severe("Error moviendo archivo: " + e.getMessage());
                        }
                    });
        } catch (Exception e) {
            logger.severe("Error en tarea de mover archivos: " + e.getMessage());
        }
    }

    private void procesarSolicitudesDiarias() {
        try {
            // Crear carpeta del día
            String carpetaProcesados = String.format("SolicitudesProcesadas_%s",
                    LocalDate.now().toString().replace("-", "_"));
            Path destino = directorioBase.resolve(carpetaProcesados);
            Files.createDirectories(destino);

            // Comprimir carpeta del día anterior
            comprimirCarpetaAnterior();

            // Procesar máximo 100 solicitudes
            procesarLoteSolicitudes(MAX_SOLICITUDES_POR_DIA);

            logger.info("Procesamiento diario completado");
        } catch (Exception e) {
            logger.severe("Error en procesamiento diario: " + e.getMessage());
        }
    }

    private void procesarCaracterizaciones() {
        try {
            Path origen = directorioBase.resolve("CaracterizacionesEntrantes");
            Path destino = directorioBase.resolve("CaracterizacionesEnProcesamiento");

            if (!Files.exists(destino)) {
                Files.createDirectories(destino);
            }

            Files.list(origen)
                    .filter(path -> path.toString().endsWith(".csv"))
                    .forEach(archivo -> {
                        try {
                            Files.move(archivo,
                                    destino.resolve(archivo.getFileName()),
                                    StandardCopyOption.REPLACE_EXISTING);
                            logger.info("Caracterización movida: " + archivo.getFileName());
                        } catch (Exception e) {
                            logger.severe("Error moviendo caracterización: " + e.getMessage());
                        }
                    });
        } catch (Exception e) {
            logger.severe("Error procesando caracterizaciones: " + e.getMessage());
        }
    }

    private void procesarLoteSolicitudes(int cantidadMaxima) {
        try {
            Path dirProcesamiento = directorioBase.resolve("SolicitudesEnProcesamiento");
            int solicitudesProcesadas = 0;

            for (Path archivo : Files.newDirectoryStream(dirProcesamiento, "*.csv")) {
                // Procesar archivo
                try {
                    // Aquí iría el código para leer y procesar el archivo
                    // usando ProcessingSolicitudService

                    solicitudesProcesadas++;
                    if (solicitudesProcesadas >= cantidadMaxima) {
                        break;
                    }
                } catch (Exception e) {
                    logger.severe("Error procesando archivo " + archivo + ": " + e.getMessage());
                }
            }

            logger.info("Procesadas " + solicitudesProcesadas + " solicitudes");
        } catch (Exception e) {
            logger.severe("Error en procesamiento de lote: " + e.getMessage());
        }
    }

    private void comprimirCarpetaAnterior() {
        try {
            LocalDate ayer = LocalDate.now().minusDays(1);
            String carpetaAnterior = String.format("SolicitudesProcesadas_%s",
                    ayer.toString().replace("-", "_"));
            Path rutaCarpetaAnterior = directorioBase.resolve(carpetaAnterior);

            if (Files.exists(rutaCarpetaAnterior)) {
                compresionService.comprimirCarpetaProcesados(rutaCarpetaAnterior);
                logger.info("Carpeta anterior comprimida: " + carpetaAnterior);
            }
        } catch (Exception e) {
            logger.severe("Error comprimiendo carpeta anterior: " + e.getMessage());
        }
    }

    private long calcularProximaHora() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime proximaHora = ahora.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        return TimeUnit.SECONDS.toMillis(java.time.Duration.between(ahora, proximaHora).getSeconds());
    }

    private long calcularProximaEjecucion(int hora, int minuto) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime proximaEjecucion = ahora.withHour(hora).withMinute(minuto).withSecond(0).withNano(0);
        if (proximaEjecucion.isBefore(ahora)) {
            proximaEjecucion = proximaEjecucion.plusDays(1);
        }
        return TimeUnit.SECONDS.toMillis(java.time.Duration.between(ahora, proximaEjecucion).getSeconds());
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        logger.info("Procesador de tareas detenido");
    }
}