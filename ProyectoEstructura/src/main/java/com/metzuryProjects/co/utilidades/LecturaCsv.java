package com.metzuryProjects.co.utilidades;

import java.io.*;
import java.util.ArrayList;

public class LecturaCsv {


    public static ArrayList<ArrayList<String>> leerCSV(String ruta) throws FileNotFoundException{
        FileReader archivo = new FileReader(ruta);
        String linea;
        ArrayList<ArrayList<String>> lista = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(archivo)){
            while((linea = lector.readLine()) != null){
                String[] array = linea.split(",");
                ArrayList<String> LS = new ArrayList<>();
                for(String a: array){
                    LS.add(a);
                }
                lista.add(LS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void  agregarCSV(String ruta, ArrayList<String> linea){
        FileWriter archivo = new FileWriter(ruta);
        try(BufferedWriter escritor = BufferedWriter(archivo)){
            escritor.write();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

