package com.uniquindisoft.colpensionex.queue;

public class ElementoPriorizado<T> {
    private final T elemento;
    private static int prioridad;

    public ElementoPriorizado(T elemento, int prioridad) {
        this.elemento = elemento;
        this.prioridad = prioridad;
    }

    public T getElemento() {
        return elemento;
    }

    // Cambiado de static a instancia
    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "ElementoPriorizado{" +
                "elemento=" + elemento +
                ", prioridad=" + prioridad +
                '}';
    }

}
