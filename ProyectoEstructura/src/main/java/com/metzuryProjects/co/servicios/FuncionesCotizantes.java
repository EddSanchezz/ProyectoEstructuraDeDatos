package com.metzuryProjects.co.servicios;

import com.metzuryProjects.co.modelos.Cotizante;
import com.metzuryProjects.co.modelos.ListaEnlazada;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FuncionesCotizantes {

    public static class ListaNegra extends ListaEnlazada<Cotizante> {
        // Método para obtener el iterador de cotizantes embargables
        public Iterator<Cotizante> iteradorEmbargables() {
            return new IteradorEmbargables(this.iterator());
        }

        // Método para obtener el iterador de cotizantes inhabilitados
        public Iterator<Cotizante> iteradorInhabilitados() {
            return new IteradorInhabilitados(this.iterator());
        }

        private static class IteradorEmbargables implements Iterator<Cotizante> {
            private final Iterator<Cotizante> iteradorGeneral;
            private Cotizante siguienteEmbargable;

            public IteradorEmbargables(Iterator<Cotizante> iteradorGeneral) {
                this.iteradorGeneral = iteradorGeneral;
                avanzarAlSiguienteEmbargable();
            }

            private void avanzarAlSiguienteEmbargable() {
                siguienteEmbargable = null;
                while (iteradorGeneral.hasNext()) {
                    Cotizante actual = iteradorGeneral.next(); // Avanza al siguiente cotizante
                    if ("EMBARGAR".equalsIgnoreCase(actual.getCaracterizacion())) { // Usar getCaracterizacion()
                        siguienteEmbargable = actual;
                        break; // Sale del bucle al encontrar un embargable
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return siguienteEmbargable != null;
            }

            @Override
            public Cotizante next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Cotizante embargable = siguienteEmbargable;
                avanzarAlSiguienteEmbargable();
                return embargable;
            }
        }

        private static class IteradorInhabilitados implements Iterator<Cotizante> {
            private final Iterator<Cotizante> iteradorGeneral;
            private Cotizante siguienteInhabilitado;

            public IteradorInhabilitados(Iterator<Cotizante> iteradorGeneral) {
                this.iteradorGeneral = iteradorGeneral;
                avanzarAlSiguienteInhabilitado();
            }

            private void avanzarAlSiguienteInhabilitado() {
                siguienteInhabilitado = null;
                while (iteradorGeneral.hasNext()) {
                    Cotizante actual = iteradorGeneral.next();
                    if (actual.isDesabilitado()) {
                        siguienteInhabilitado = actual;
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return siguienteInhabilitado != null;
            }

            @Override
            public Cotizante next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Cotizante inhabilitado = siguienteInhabilitado;
                avanzarAlSiguienteInhabilitado();
                return inhabilitado;
            }
        }
    }
}
