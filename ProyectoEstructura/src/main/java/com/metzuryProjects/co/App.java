package com.metzuryProjects.co;

import java.util.ArrayList;

import com.metzuryProjects.co.modelos.ColaPrioritaria;
import com.metzuryProjects.co.modelos.Cotizante;
import com.metzuryProjects.co.utilidades.LecturaCsv;

/**
 * Hello world!
 *XD
 */
public class App {
    public static void main( String[] args ){
        ColaPrioritaria cola = new ColaPrioritaria();
        Cotizante cotizante = new Cotizante(1231231,1002311,"Skibidi",45,"Perúlandia",20000,false,false,"pensionesViejitos","pendiente","sura","no sé XD");
        cola.agregarPrioritaria(cotizante);
    }
}
