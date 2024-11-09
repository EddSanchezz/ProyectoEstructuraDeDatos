package com.metzuryProjects.co.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.metzuryProjects.co.modelos.LinkedList;

public class LectorArchivosCSV {
     // TODO Consultar el separador csv desde un archivo de propiedades.
    public static final String SEPARADOR_CSV = ";;";

    public static String[] leerPrimeraLineaCsv(String rutaArchivo)
            throws IOException
    {
        File archivo = new File(rutaArchivo);

        try(BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))
        ) {
            String primeraLinea = lector.readLine();

            if(primeraLinea != null) {
                return primeraLinea.split(LectorArchivosCSV.SEPARADOR_CSV);
            }
        }

        return null;
    }

    public static LinkedList<String[]> leerTodasLasLineasCsv(String rutaArchivo)//usamos la LinkedList creada por nosotros.
            throws IOException {
        return leerTodasLasLineasCsv(rutaArchivo,true);
    }

    public static LinkedList<String[]> leerTodasLasLineasCsv(
            String rutaArchivo, boolean esSaltarPrimera
    ) throws IOException {

        File archivo = new File(rutaArchivo);
        LinkedList<String[]> lineas = new LinkedList<>();

        try(BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))
        ) {
            String linea;
            boolean esPrimeraLinea = (esSaltarPrimera) ? true: false;
            while( (linea = lector.readLine()) != null  ) {

                if(esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }

                String[] arreglo = linea.split(LectorArchivosCSV.SEPARADOR_CSV);
                lineas.agregar(arreglo);//uso del metodo agregar de linkedlist personal.
            }
        }

        return lineas;
    }
}
