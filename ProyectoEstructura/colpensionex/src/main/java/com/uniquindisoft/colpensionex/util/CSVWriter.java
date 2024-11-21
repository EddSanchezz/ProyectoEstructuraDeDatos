package com.uniquindisoft.colpensionex.util;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase base genérica para escritura de archivos CSV
 */
public abstract class CSVWriter<T> {
    private static final Logger logger = Logger.getLogger(CSVWriter.class.getName());
    protected Path filePath;
    protected String separator;
    protected String[] headers;

    public CSVWriter(Path filePath, String separator, String[] headers) {
        this.filePath = filePath;
        this.separator = separator;
        this.headers = headers;
    }

    /**
     * Escribe una lista de objetos en el archivo CSV
     */
    public void escribirDatos(List<T> datos) throws IOException {
        try {
            List<String> lineas = datos.stream()
                                     .map(this::objectToCSVLine)
                                     .collect(Collectors.toList());
            
            lineas.add(0, String.join(separator, headers));
            Files.write(filePath, lineas);
            logger.info("Archivo CSV escrito exitosamente: " + filePath);
        } catch (IOException e) {
            logger.severe("Error escribiendo archivo CSV: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Método abstracto que debe ser implementado para convertir objeto a línea CSV
     */
    protected abstract String objectToCSVLine(T objeto);
}