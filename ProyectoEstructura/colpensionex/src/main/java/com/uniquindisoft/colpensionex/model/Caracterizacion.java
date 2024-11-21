package com.uniquindisoft.colpensionex.model;

import java.time.LocalDate;

public class Caracterizacion extends BaseModel {
    private static final String TIPO_DOC = "tipoDocumento";
    private static final String NUM_DOC = "numeroDocumento";
    private static final String NOMBRE = "nombreCompleto";
    private static final String TIPO_CARACTERIZACION = "tipoCaracterizacion";
    private static final String ENTIDAD = "entidad";
    private static final String FECHA_CARACTERIZACION = "fechaCaracterizacion";

    public enum TipoCaracterizacion {
        INHABILITAR,
        EMBARGAR
    }

    public enum Entidad {
        FISCALIA,
        PROCURADURIA,
        CONTRALORIA
    }

    public Caracterizacion() {
        super();
        setFechaCaracterizacion(LocalDate.now());
    }

    public String getTipoDocumento() {
        return (String) getAtributo(TIPO_DOC);
    }

    public void setTipoDocumento(String tipoDocumento) {
        setAtributo(TIPO_DOC, tipoDocumento);
    }

    public String getNumeroDocumento() {
        return (String) getAtributo(NUM_DOC);
    }

    public void setNumeroDocumento(String numeroDocumento) {
        setAtributo(NUM_DOC, numeroDocumento);
    }

    public String getNombreCompleto() {
        return (String) getAtributo(NOMBRE);
    }

    public void setNombreCompleto(String nombreCompleto) {
        setAtributo(NOMBRE, nombreCompleto);
    }

    public TipoCaracterizacion getTipoCaracterizacion() {
        return (TipoCaracterizacion) getAtributo(TIPO_CARACTERIZACION);
    }

    public void setTipoCaracterizacion(TipoCaracterizacion tipo) {
        setAtributo(TIPO_CARACTERIZACION, tipo);
    }

    public Entidad getEntidad() {
        return (Entidad) getAtributo(ENTIDAD);
    }

    public void setEntidad(Entidad entidad) {
        setAtributo(ENTIDAD, entidad);
    }

    public LocalDate getFechaCaracterizacion() {
        return (LocalDate) getAtributo(FECHA_CARACTERIZACION);
    }

    public void setFechaCaracterizacion(LocalDate fecha) {
        setAtributo(FECHA_CARACTERIZACION, fecha);
    }
}