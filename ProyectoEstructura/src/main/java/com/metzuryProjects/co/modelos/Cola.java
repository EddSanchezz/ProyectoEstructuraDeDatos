package com.metzuryProjects.co.modelos;

import java.util.NoSuchElementException;

public class Cola<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamano;

    public Cola() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
    
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo;
        } else {
            // Enlaza el último nodo actual (cola) al nuevo nodo
            this.cola.siguiente = nuevoNodo;
            // Actualiza la referencia de cola al nuevo nodo
            this.cola = nuevoNodo;
        }
        tamano++;
    }
    

    public T eliminar() {
    if (this.cabeza == null) {
        throw new NoSuchElementException("La cola está vacía");
    }
    T elemento = this.cabeza.dato;
    this.cabeza = this.cabeza.siguiente;
    if (this.cabeza == null) {
        this.cola = null;
    }
    this.tamano--;
    return elemento;
}

    public T eliminar(int indice){
        Nodo<T> actual = this.cabeza;
        for(int i = 0; i < indice; i++){
            actual = actual.siguiente;
        }
        T elemento = actual.dato;
        actual = null;
        this.tamano --;
        return elemento;
    }
}
