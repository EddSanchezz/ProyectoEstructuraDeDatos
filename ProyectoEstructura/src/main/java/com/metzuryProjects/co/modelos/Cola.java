package com.metzuryProjects.co.modelos;

public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> atras;
    private int tamaño;

    private static class Nodo<T> {
        private T dato;
        private Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Cola() {
        this.frente = this.atras = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamaño() {
        return tamaño;
    }

    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (atras == null) {
            frente = atras = nuevoNodo;
        } else {
            atras.siguiente = nuevoNodo;
            atras = nuevoNodo;
        }
        tamaño++;
    }

    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            atras = null;
        }
        tamaño--;
        return dato;
    }

    public T verFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return frente.dato;
    }

    public void limpiar() {
        frente = atras = null;
        tamaño = 0;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.siguiente;
        }
        System.out.println();
    }
}

