package com.metzuryProjects.co.modelos;

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
        Nodo<T> actual = this.cabeza;
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
        }
        else{
            while(actual!=null){
                actual = actual.siguiente;
            }
            actual = nuevoNodo;
        }
        tamano ++;
    }

    public void eliminar() {
        Nodo<T> actual = this.cabeza;
        if(actual==null){
            System.out.println("El cola esta vacio");
        }
        else{
            while(actual.siguiente!=null){
                actual = actual.siguiente;
            }
            actual = null;
        }
        tamano --;
    }
    public T eliminar(int indice){
        Nodo<T> actual = this.cabeza;
        for(int i = 0; i < indice; i++){
            actual = actual.siguiente;
        }
        T elemento = actual.dato;
        actual = null;
        return elemento;
    }
}
