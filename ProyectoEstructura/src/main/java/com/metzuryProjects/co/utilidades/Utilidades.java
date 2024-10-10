package com.metzuryProjects.co.utilidades;

public class Utilidades {

    /**
     * Método genérico que intercambia dos elementos de un array
     * @param array
     * @param i
     * @param j
     * @param <T>
     */
    public static <T> void intercambiar(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}

