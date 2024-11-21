package com.metzuryProjects.co.modelos;

import java.util.NoSuchElementException;

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

//HEAD
    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
    
        if (this.frente == null) {
            this.frente = nuevoNodo;
            this.atras = nuevoNodo;
        } else {
            // Enlaza el último nodo actual (cola) al nuevo nodo
            this.atras.siguiente = nuevoNodo;
            // Actualiza la referencia de cola al nuevo nodo
            this.atras = nuevoNodo;
        }
        this.tamaño++;
    }
    public boolean estaVacia() {
        return frente == null;
//>>>>>>> 3762c80e7358f7ddd5360e573c589bedce200383
    }
    

//<<<<<<< HEAD
    public T eliminar() {
    if (this.frente == null) {
        throw new NoSuchElementException("La cola está vacía");
    }
    T elemento = this.frente.dato;
    this.frente = this.frente.siguiente;
    if (this.frente == null) {
        this.atras = null;
    }
    this.tamaño--;
    return elemento;
}

    public void eliminar(int indice){
        Nodo<T> actual = this.frente;
        for(int i = 0; i < indice; i++){
            actual = actual.siguiente;
        }
    }        
//=======
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
//>>>>>>> 3762c80e7358f7ddd5360e573c589bedce200383
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

