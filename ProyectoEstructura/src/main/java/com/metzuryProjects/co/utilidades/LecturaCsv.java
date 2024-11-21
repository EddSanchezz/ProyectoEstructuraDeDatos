package com.metzuryProjects.co.utilidades;

import java.io.*;
import java.util.ArrayList;
import java.io.BufferedWriter;


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

    /**
     * Metodo para agregar una linea de texto al csv seperandola por comas 
     * @param ruta
     * @param linea
     */
    public static void  agregarCSV(String ruta, ArrayList<String> linea){
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta,true))){
            for(int i=0; i<linea.size();i++){
                if(i < linea.size()-1){
                    escritor.append(linea.get(i) + ",");
                }else{
                    escritor.append(linea.get(i));
                }
            }
            escritor.newLine();
            System.out.println("información de "+ linea.get(2) + " guardada en CSV exitosamente");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

