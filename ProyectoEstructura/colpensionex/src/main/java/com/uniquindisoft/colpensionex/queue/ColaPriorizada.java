package com.uniquindisoft.colpensionex.queue;

import java.util.PriorityQueue;
import java.util.Comparator;

public class ColaPriorizada<T> {
    private PriorityQueue<ElementoPriorizado<T>> cola;

    public ColaPriorizada() {
        this.cola = new PriorityQueue<>(Comparator.comparingInt(ElementoPriorizado::getPrioridad));//usar el metodo revers para obtener la prioridad
    }

    public void encolar(T elemento, int prioridad) {
        cola.offer(new ElementoPriorizado<>(elemento, prioridad));
    }

    public T desencolar() {
        ElementoPriorizado<T> elemento = cola.poll();
        return elemento != null ? elemento.getElemento() : null;
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int tamano() {
        return cola.size();
    }

}