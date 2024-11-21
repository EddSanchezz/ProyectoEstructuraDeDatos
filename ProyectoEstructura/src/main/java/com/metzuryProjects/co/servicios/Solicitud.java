package com.metzuryProjects.co.servicios;

import lombok.Setter;

public class Solicitud {
    private String id;
    private String nombre;
    private int edad;
    private boolean declaraRenta;
    @Setter
    private boolean inhabilitado;
    @Setter
    private boolean embargado;
    @Setter
    private String estado; // "Aprobada" o "Rechazada"

    
    public Solicitud(String id, String nombre, int edad, boolean declaraRenta) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.declaraRenta = declaraRenta;
        this.estado = "Pendiente";
    }


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isDeclaraRenta() {
        return declaraRenta;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isInhabilitado() {
        return inhabilitado;
    }

    public boolean isEmbargado() {
        return embargado;
    }
}
