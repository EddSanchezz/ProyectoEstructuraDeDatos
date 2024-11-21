package com.metzuryProjects.co;

import java.util.ArrayList;

import com.metzuryProjects.co.utilidades.LecturaCsv;

/**
 * Hello world!
 *XD
 */
public class App {
    public static void main( String[] args ){
        ArrayList<String> texto = new ArrayList<>();
        texto.add("12312312312");
        texto.add("sura");
        texto.add("pedro");
        LecturaCsv.agregarCSV("ProyectoEstructura\\src\\main\\java\\com\\metzuryProjects\\co\\ArchivosCSV\\cotizantes.csv", texto);
    }
}
