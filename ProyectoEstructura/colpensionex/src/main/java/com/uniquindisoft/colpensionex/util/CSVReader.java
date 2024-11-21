package com.uniquindisoft.colpensionex.util;

import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Clase base genérica para lectura de archivos CSV
 */
public abstract class CSVReader<T> {
    private static final Logger logger = Logger.getLogger(CSVReader.class.getName());
    protected Path filePath;
    protected String separator;

    public CSVReader(Path filePath, String separator) {
        this.filePath = filePath;
        this.separator = separator;
    }

    /**
     * Lee todas las líneas del archivo CSV y las convierte en objetos
     */
    public LinkedList<T> leerTodasLasFilas() throws IOException {
        LinkedList<T> resultado = new LinkedList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                T objeto = parseLine(line);
                if (objeto != null) {
                    resultado.add(objeto);
                }
            }
        } catch (IOException e) {
            logger.severe("Error leyendo archivo CSV: " + e.getMessage());
            throw e;
        }
        return resultado;
    }

    /**
     * Método abstracto que debe ser implementado para parsear cada línea
     */
    protected abstract T parseLine(String line);
}