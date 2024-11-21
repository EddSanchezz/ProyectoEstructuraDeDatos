package com.uniquindisoft.colpensionex.model;

import java.time.LocalDateTime;

public class Solicitud extends BaseModel {
    // Constantes de estado
    public static final String ESTADO_GENERADA = "Generada";
    public static final String ESTADO_APROBADA = "Aprobada";
    public static final String ESTADO_RECHAZADA = "Rechazada";

    // Nombres de atributos
    private static final String COTIZANTE = "cotizante";
    private static final String ESTADO = "estado";
    private static final String FECHA_SOLICITUD = "fechaSolicitud";
    private static final String FECHA_PROCESAMIENTO = "fechaProcesamiento";
    private static final String EMBARGADO = "embargado";
    private static final String PRIORIDAD = "prioridad";

    public Solicitud() {
        super();
        setEstado(ESTADO_GENERADA);
        setFechaSolicitud(LocalDateTime.now());
    }

    public Cotizante getCotizante() {
        return (Cotizante) getAtributo(COTIZANTE);
    }

    public void setCotizante(Cotizante cotizante) {
        setAtributo(COTIZANTE, cotizante);
    }

    public String getEstado() {
        return (String) getAtributo(ESTADO);
    }

    public void setEstado(String estado) {
        setAtributo(ESTADO, estado);
    }

    public LocalDateTime getFechaSolicitud() {
        return (LocalDateTime) getAtributo(FECHA_SOLICITUD);
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        setAtributo(FECHA_SOLICITUD, fechaSolicitud);
    }

    public LocalDateTime getFechaProcesamiento() {
        return (LocalDateTime) getAtributo(FECHA_PROCESAMIENTO);
    }

    public void setFechaProcesamiento(LocalDateTime fechaProcesamiento) {
        setAtributo(FECHA_PROCESAMIENTO, fechaProcesamiento);
    }

    public boolean isEmbargado() {
        return (Boolean) getAtributo(EMBARGADO);
    }

    public void setEmbargado(boolean embargado) {
        setAtributo(EMBARGADO, embargado);
    }

    public int getPrioridad() {
        return (Integer) getAtributo(PRIORIDAD);
    }

    public void setPrioridad(int prioridad) {
        setAtributo(PRIORIDAD, prioridad);
    }
}   