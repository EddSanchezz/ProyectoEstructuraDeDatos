package com.metzuryProjects.co.modelos;

import java.util.Collection;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private Nodo<T> cabeza;
    private int tamano;

    // Constructor por defecto
    public LinkedList() {
        this.cabeza = null;
        this.tamano = 0;
    }

    // Método para agregar al final de la lista
    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
        } else {
            Nodo<T> aux = this.cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevoNodo;
        }
        this.tamano++;
    }

    // Método para agregar en una posición específica
    public void agregar(int indice, T elemento) {
        if (indice < 0 || indice > this.tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (indice == 0) {  // Agregar al principio
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
        this.tamano++;
    }

    // Método para agregar todos los elementos de una colección
    public void agregarTodos(Collection<? extends T> elementos) {
        for (T elemento : elementos) {
            agregar(elemento);
        }
    }

    // Método para eliminar el último elemento
    public void eliminar() {
        if (this.cabeza == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        if (this.cabeza.siguiente == null) {
            this.cabeza = null;
        } else {
            Nodo<T> aux = this.cabeza;
            while (aux.siguiente.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = null;
        }
        this.tamano--;
    }

    // Método para eliminar en una posición específica
    public void eliminar(int indice) {
        if (indice < 0 || indice >= this.tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (indice == 0) {
            this.cabeza = cabeza.siguiente;
        } else {
            Nodo<T> actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            actual.siguiente = actual.siguiente.siguiente;
        }
        this.tamano--;
    }

    // Método para obtener el valor en un índice específico
    public T obtener(int indice) {
        if (indice < 0 || indice >= this.tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    // Método para obtener el tamaño de la lista
    public int obtenerTamano() {
        return this.tamano;
    }

    // Método para invertir la lista
    public void reversar() {
        Nodo<T> anterior = null;
        Nodo<T> actual = this.cabeza;
        Nodo<T> siguiente = null;

        while (actual != null) {
            siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }
        this.cabeza = anterior;
    }

    // Método para reemplazar un elemento en un índice específico
    public void set(int indice, T elemento) {
        if (indice < 0 || indice >= this.tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        actual.dato = elemento;
    }

    // Implementación del iterador
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }
}
