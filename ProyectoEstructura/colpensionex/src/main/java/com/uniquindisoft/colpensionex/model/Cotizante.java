package com.uniquindisoft.colpensionex.model;

import java.time.LocalDate;

public class Cotizante extends BaseModel {
    // Constantes para nombres de atributos
    private static final String TIPO_DOC = "tipoDocumento";
    private static final String NUM_DOC = "numeroDocumento";
    private static final String NOMBRE = "nombreCompleto";
    private static final String FECHA_NAC = "fechaNacimiento";
    private static final String CIUDAD_NAC = "ciudadNacimiento";
    private static final String CIUDAD_RES = "ciudadResidencia";
    private static final String PRE_PENSIONADO = "prePensionado";
    private static final String SEMANAS_COTIZADAS = "semanasCotizadas";
    private static final String FONDO_ORIGEN = "fondoOrigen";
    private static final String INSTITUCION_PUBLICA = "institucionPublica";
    private static final String TIENE_CONDEORACION = "tieneCondecoracion";
    private static final String TIENE_HIJOS_INPEC = "tieneHijosInpec";
    private static final String FAMILIARES_POLICIA = "familiaresPolicia";
    private static final String OBS_DISCIPLINARIA = "observacionDisciplinaria";
    private static final String DECLARANTE_RENTA = "declaranteRenta";

    public Cotizante() {
        super();
    }

    // Getters y Setters básicos
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

    public LocalDate getFechaNacimiento() {
        return (LocalDate) getAtributo(FECHA_NAC);
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        setAtributo(FECHA_NAC, fechaNacimiento);
    }

    // Getters y Setters para atributos de localización
    public String getCiudadNacimiento() {
        return (String) getAtributo(CIUDAD_NAC);
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        setAtributo(CIUDAD_NAC, ciudadNacimiento);
    }

    public String getCiudadResidencia() {
        return (String) getAtributo(CIUDAD_RES);
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        setAtributo(CIUDAD_RES, ciudadResidencia);
    }

    // Getters y Setters para atributos de pensión
    public boolean isPrePensionado() {
        return (Boolean) getAtributo(PRE_PENSIONADO);
    }

    public void setPrePensionado(boolean prePensionado) {
        setAtributo(PRE_PENSIONADO, prePensionado);
    }

    public int getSemanasCotizadas() {
        return (Integer) getAtributo(SEMANAS_COTIZADAS);
    }

    public void setSemanasCotizadas(int semanasCotizadas) {
        setAtributo(SEMANAS_COTIZADAS, semanasCotizadas);
    }

    public String getFondoOrigen() {
        return (String) getAtributo(FONDO_ORIGEN);
    }

    public void setFondoOrigen(String fondoOrigen) {
        setAtributo(FONDO_ORIGEN, fondoOrigen);
    }

    // Getters y Setters para atributos institucionales
    public String getInstitucionPublica() {
        return (String) getAtributo(INSTITUCION_PUBLICA);
    }

    public void setInstitucionPublica(String institucionPublica) {
        setAtributo(INSTITUCION_PUBLICA, institucionPublica);
    }

    public boolean tieneCondecoracion() {
        return (Boolean) getAtributo(TIENE_CONDEORACION);
    }

    public void setTieneCondecoracion(boolean tieneCondecoracion) {
        setAtributo(TIENE_CONDEORACION, tieneCondecoracion);
    }

    // Getters y Setters para atributos familiares
    public boolean tieneHijosInpec() {
        return (Boolean) getAtributo(TIENE_HIJOS_INPEC);
    }

    public void setTieneHijosInpec(boolean tieneHijosInpec) {
        setAtributo(TIENE_HIJOS_INPEC, tieneHijosInpec);
    }

    public boolean tieneFamiliaresPolicia() {
        return (Boolean) getAtributo(FAMILIARES_POLICIA);
    }

    public void setTieneFamiliaresPolicia(boolean familiaresPolicia) {
        setAtributo(FAMILIARES_POLICIA, familiaresPolicia);
    }

    // Getters y Setters para atributos adicionales
    public boolean tieneObservacionDisciplinaria() {
        return (Boolean) getAtributo(OBS_DISCIPLINARIA);
    }

    public void setTieneObservacionDisciplinaria(boolean observacionDisciplinaria) {
        setAtributo(OBS_DISCIPLINARIA, observacionDisciplinaria);
    }

    public boolean isDeclaranteRenta() {
        return (Boolean) getAtributo(DECLARANTE_RENTA);
    }

    public void setDeclaranteRenta(boolean declaranteRenta) {
        setAtributo(DECLARANTE_RENTA, declaranteRenta);
    }
}