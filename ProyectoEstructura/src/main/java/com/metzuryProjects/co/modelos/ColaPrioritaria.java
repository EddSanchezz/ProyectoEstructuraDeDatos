package com.metzuryProjects.co.modelos;

public class ColaPrioritaria {
    private Nodo<Cotizante> cabeza;
    private int tamano;


    private static class Nodo<Cotizante> {
        private Cotizante cotizante;
        private Nodo<Cotizante> siguiente;
        private Nodo<Cotizante> anterior;

        public Nodo(Cotizante cotizante) {
            this.cotizante = cotizante;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public ColaPrioritaria() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public void agregarPrioritaria(Cotizante cotizante) {
        this.cabeza = agregarPrioritariaRecursiva(cotizante, this.cabeza);
    }

    private Nodo<Cotizante> agregarPrioritariaRecursiva(Cotizante cotizante, Nodo<Cotizante> raiz) {
        if (raiz == null) {
            return new Nodo<>(cotizante);
        }
        if (cotizante.getEdad() > 35) {
            if (raiz.cotizante.getEdad() <= 35) {
                Nodo<Cotizante> nuevoNodo = new Nodo<>(cotizante);
                nuevoNodo.siguiente = raiz;
                return nuevoNodo;
            } else {
                raiz.siguiente = agregarPrioritariaRecursiva(cotizante, raiz.siguiente);
            }
        } else {
            raiz.siguiente = agregarPrioritariaRecursiva(cotizante, raiz.siguiente);
        }
        return raiz;
    }

}
