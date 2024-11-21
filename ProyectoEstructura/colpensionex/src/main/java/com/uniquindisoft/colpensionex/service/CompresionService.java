package com.uniquindisoft.colpensionex.service;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;
import java.util.logging.Logger;
import java.time.LocalDate;

public class CompresionService {
    private static final Logger logger = Logger.getLogger(CompresionService.class.getName());

    public void comprimirCarpetaProcesados(Path carpeta) {
        try {
            String nombreZip = carpeta.getFileName().toString() + ".zip";
            Path archivoZip = carpeta.getParent().resolve(nombreZip);

            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(archivoZip.toFile()))) {
                Files.walk(carpeta)
                     .filter(path -> !Files.isDirectory(path))
                     .forEach(path -> {
                         ZipEntry zipEntry = new ZipEntry(carpeta.relativize(path).toString());
                         try {
                             zos.putNextEntry(zipEntry);
                             Files.copy(path, zos);
                             zos.closeEntry();
                         } catch (IOException e) {
                             logger.severe("Error comprimiendo archivo: " + e.getMessage());
                         }
                     });
            }

            // Eliminar carpeta original después de comprimir
            Files.walk(carpeta)
                 .sorted((p1, p2) -> -p1.compareTo(p2))
                 .forEach(path -> {
                     try {
                         Files.delete(path);
                     } catch (IOException e) {
                         logger.severe("Error eliminando archivo/carpeta: " + e.getMessage());
                     }
                 });

            logger.info("Carpeta comprimida exitosamente: " + nombreZip);
        } catch (Exception e) {
            logger.severe("Error en proceso de compresión: " + e.getMessage());
            throw new RuntimeException("Error comprimiendo carpeta", e);
        }
    }
}