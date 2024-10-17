package com.metzuryProjects.co;

import com.metzuryProjects.co.modelos.LinkedList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear una lista enlazada de enteros
        LinkedList<Integer> lista = new LinkedList<>();

        // Agregar elementos a la lista
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        System.out.println("Lista original: ");
        imprimirLista(lista);

        // Agregar en una posición específica
        lista.agregar(1, 10);
        System.out.println("\nLista después de agregar 10 en la posición 1: ");
        imprimirLista(lista);

        // Obtener elemento en la posición 2
        System.out.println("\nElemento en la posición 2: " + lista.obtener(2));

        // Eliminar el último elemento
        lista.eliminar();
        System.out.println("\nLista después de eliminar el último elemento: ");
        imprimirLista(lista);

        // Eliminar el elemento en la posición 1
        lista.eliminar(1);
        System.out.println("\nLista después de eliminar el elemento en la posición 1: ");
        imprimirLista(lista);

        // Invertir la lista
        lista.reversar();
        System.out.println("\nLista después de reversar: ");
        imprimirLista(lista);

        // Reemplazar el elemento en la posición 0
        lista.set(0, 99);
        System.out.println("\nLista después de reemplazar el elemento en la posición 0 por 99: ");
        imprimirLista(lista);

        // Agregar una colección de elementos
        ArrayList<Integer> elementos = new ArrayList<>();
        elementos.add(20);
        elementos.add(30);
        elementos.add(40);
        lista.agregarTodos(elementos);
        System.out.println("\nLista después de agregar todos los elementos de una colección: ");
        imprimirLista(lista);
    }

    // Método auxiliar para imprimir la lista
    public static <T> void imprimirLista(LinkedList<T> lista) {
        for (T elemento : lista) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }
}
