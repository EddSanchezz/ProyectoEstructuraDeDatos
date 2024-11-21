package com.metzuryProjects.co.modelos;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEnlazada<T> implements Collections<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamaño;

    static class Nodo<T> {
        private T dato;
        private Nodo<T> siguiente;
        private Nodo<T> anterior;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public ListaEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.tamaño = 0;
    }

    // Metodo para agregar al inicio
    public void agregarAlInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = cola = nuevoNodo;
        } else {
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
            cabeza = nuevoNodo;
        }
        tamaño++;
    }

    // Metodo para agregar al final
    public void agregarAlFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cola == null) {
            cabeza = cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            nuevoNodo.anterior = cola;
            cola = nuevoNodo;
        }
        tamaño++;
    }

    // Metodo para agregar en un indice especifico
    public void agregarEnIndice(int indice, T dato) {
        if (indice < 0 || indice > tamaño) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }

        if (indice == 0) {
            agregarAlInicio(dato);
        } else if (indice == tamaño) {
            agregarAlFinal(dato);
        } else {
            Nodo<T> nuevoNodo = new Nodo<>(dato);
            Nodo<T> actual = cabeza;

            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }

            nuevoNodo.siguiente = actual.siguiente;
            nuevoNodo.anterior = actual;
            actual.siguiente.anterior = nuevoNodo;
            actual.siguiente = nuevoNodo;

            tamaño++;
        }
    }

    // Metodo para eliminar el primer elemento
    public T eliminarPrimero() {
        if (cabeza == null) {
            throw new IllegalStateException("La lista esta vacia");
        }

        T dato = cabeza.dato;
        cabeza = cabeza.siguiente;

        if (cabeza != null) {
            cabeza.anterior = null;
        } else {
            cola = null;
        }

        tamaño--;
        return dato;
    }

    // Metodo para eliminar el ultimo elemento
    public T eliminarUltimo() {
        if (cola == null) {
            throw new IllegalStateException("La lista esta vacia");
        }

        T dato = cola.dato;
        cola = cola.anterior;

        if (cola != null) {
            cola.siguiente = null;
        } else {
            cabeza = null;
        }

        tamaño--;
        return dato;
    }

    // Metodo para eliminar en un indice especifico
    public T eliminarEnIndice(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }

        if (indice == 0) {
            return eliminarPrimero();
        } else if (indice == tamaño - 1) {
            return eliminarUltimo();
        } else {
            Nodo<T> actual = cabeza;

            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }

            T dato = actual.dato;
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;

            tamaño--;
            return dato;
        }
    }

    // Metodo para obtener un elemento en un indice especifico
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    // Metodo para obtener el tamaño de la lista
    public int tamaño() {
        return tamaño;
    }

    // Metodo para mostrar todos los elementos de la lista
    public void mostrar() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    // Metodo para implementar un iterador
    @Override
    public Iterator<T> iterator() {
        return new IteradorLista<>(cabeza);
    }

    // Clase interna para el iterador
    private static class IteradorLista<T> implements Iterator<T> {
        private Nodo<T> actual;

        public IteradorLista(Nodo<T> cabeza) {
            this.actual = cabeza;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            if (actual == null) {
                throw new NoSuchElementException();
            }
            T dato = actual.dato;
            actual = actual.siguiente;
            return dato;
        }
    }

}
