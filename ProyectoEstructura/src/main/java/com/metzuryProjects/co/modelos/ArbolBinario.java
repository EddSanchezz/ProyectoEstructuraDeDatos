package com.metzuryProjects.co.modelos;

import lombok.Getter;
import lombok.Setter;

public class ArbolBinario<T> {
    private Nodo<T> raiz;
    private int tamano;

    @Getter
    @Setter
    private static class Nodo<T> {
      private int valor;
      private Nodo<T> derecho;
      private Nodo<T> izquierdo;

      public Nodo(int valor) {
          this.valor = valor;
          this.derecho = null;
          this.izquierdo = null;
      }
    }

    public ArbolBinario(){
      this.raiz = null;
    }

    public void insertar(int dato){
      Nodo<T> nuevoNodo = new Nodo<>(dato);
      if(this.raiz == null){
        this.raiz = nuevoNodo;
        return;
      }

      Nodo<T> nivel = this.raiz;
      Nodo<T> padreTemporal = null;
      while(true){
        if(dato < nivel.getValor()){
          padreTemporal = nivel;
          nivel = nivel.getIzquierdo();
          if(nivel == null){
            padreTemporal.setIzquierdo(nuevoNodo);
            return;
          }
        }else if(dato > nivel.getValor()){
          nivel = nivel.getDerecho();
          if(nivel == null){
            padreTemporal.setDerecho(nuevoNodo);
            return;
          }
        }else{
          return;
        }
      }
    }

    public int getTamano(){
          return this.tamano;
    }
}
