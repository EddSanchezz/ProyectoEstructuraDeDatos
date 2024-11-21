package com.metzuryProjects.co.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Cotizante {
    private int idCotizante;
    private int cedula;
    private String nombre;
    private int edad;
    private String ciudad;
    private int semanasCotizadas;
    private boolean desabilitado, embargado;
    private String fondoDePensiones, estadoSolicitud;
    private String entidadPublica;
    private String caracterizacion;

    public Cotizante() {}

    public Cotizante(int idCotizante, int cedula, String nombre, int edad, String ciudad, int semanasCotizadas, boolean desabilitado, boolean embargado, String fondoDePensiones, String estadoSolicitud, String entidadPublica, String caracterizacion) {
        this.idCotizante = idCotizante;
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
        this.semanasCotizadas = semanasCotizadas;
        this.desabilitado = desabilitado;
        this.embargado = embargado;
        this.fondoDePensiones = fondoDePensiones;
        this.estadoSolicitud = "pendiente";
        this.entidadPublica = entidadPublica;
        this.caracterizacion = caracterizacion;
    }

    public int getIdCotizante() {
        return idCotizante;
    }

    public void setIdCotizante(int idCotizante) {
        this.idCotizante = idCotizante;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getSemanasCotizadas() {
        return semanasCotizadas;
    }

    public void setSemanasCotizadas(int semanasCotizadas) {
        this.semanasCotizadas = semanasCotizadas;
    }

    public boolean isDesabilitado() {
        return desabilitado;
    }

    public void setDesabilitado(boolean desabilitado) {
        this.desabilitado = desabilitado;
    }

    public boolean isEmbargado() {
        return embargado;
    }

    public void setEmbargado(boolean embargado) {
        this.embargado = embargado;
    }

    public String getFondoDePensiones() {
        return fondoDePensiones;
    }

    public void setFondoDePensiones(String fondoDePensiones) {
        this.fondoDePensiones = fondoDePensiones;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getEntidadPublica() {
        return entidadPublica;
    }

    public void setEntidadPublica(String entidadPublica) {
        this.entidadPublica = entidadPublica;
    }

    public String getCaracterizacion() {
        return caracterizacion;
    }

    public void setCaracterizacion(String caracterizacion) {
        this.caracterizacion = caracterizacion;
    }
}
