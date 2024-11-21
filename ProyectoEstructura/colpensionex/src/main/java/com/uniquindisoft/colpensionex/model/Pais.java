package com.uniquindisoft.colpensionex.model;

/**
 * Modelo que representa un país en el sistema.
 */
public class Pais extends BaseModel {
    private static final String ID = "id";
    private static final String NOMBRE = "nombre";
    private static final String CODIGO = "codigo";
    private static final String CODIGO_ISO = "codigoIso";

    public Pais() {
        super();
    }

    public String getId() {
        return (String) getAtributo(ID);
    }

    public void setId(String id) {
        setAtributo(ID, id);
    }

    public String getNombre() {
        return (String) getAtributo(NOMBRE);
    }

    public void setNombre(String nombre) {
        setAtributo(NOMBRE, nombre);
    }

    public String getCodigo() {
        return (String) getAtributo(CODIGO);
    }

    public void setCodigo(String codigo) {
        setAtributo(CODIGO, codigo);
    }

    public String getCodigoIso() {
        return (String) getAtributo(CODIGO_ISO);
    }

    public void setCodigoIso(String codigoIso) {
        setAtributo(CODIGO_ISO, codigoIso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pais)) return false;
        Pais pais = (Pais) o;
        return getId().equals(pais.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", codigoIso='" + getCodigoIso() + '\'' +
                '}';
    }
}