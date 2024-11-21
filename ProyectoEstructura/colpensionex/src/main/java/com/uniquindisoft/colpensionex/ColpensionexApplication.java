package com.uniquindisoft.colpensionex;

import com.uniquindisoft.colpensionex.task.ProcesadorTareasProgramadas;
import com.uniquindisoft.colpensionex.service.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class ColpensionexApplication {
    private static final Logger logger = Logger.getLogger(ColpensionexApplication.class.getName());

    public static void main(String[] args) {
        try {
            Path solicitudes = Paths.get("data\\SolicitudesEntrantes");
            GeneradorArchivosService servicio = new GeneradorArchivosService(solicitudes);
            servicio.generarArchivosCaracterizacion();
            servicio.generarArchivosSolicitudes(2,234);
            logger.info("Iniciando sistema de procesamiento Colpensionex");

            Path directorioBase = Paths.get(System.getProperty("user.dir"), "data");
            
            CargaDatosBaseService cargaDatosService = new CargaDatosBaseService(directorioBase);
            if (!cargaDatosService.cargarDatosBase()) {
                logger.severe("Error cargando datos base. Abortando inicio del sistema.");
                return;
            }

            ProcesadorTareasProgramadas procesador = new ProcesadorTareasProgramadas(directorioBase);
            

            if (args.length > 0 && args[0].equals("--generate-test-data")) {
                GeneradorArchivosService generador = new GeneradorArchivosService(
                    directorioBase.resolve("SolicitudesEntrantes")
                );
                generador.generarArchivosSolicitudes(10000, 100);
            }

            logger.info("Sistema iniciado correctamente");
        } catch (Exception e) {
            logger.severe("Error iniciando el sistema: " + e.getMessage());
            System.exit(1);
        }
    }
}