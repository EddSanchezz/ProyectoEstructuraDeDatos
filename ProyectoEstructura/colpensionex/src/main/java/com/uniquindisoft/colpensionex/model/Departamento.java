package com.uniquindisoft.colpensionex.model;

/**
 * Modelo que representa un departamento en el sistema.
 */
public class Departamento extends BaseModel {
    private static final String ID = "id";
    private static final String NOMBRE = "nombre";
    private static final String PAIS_ID = "paisId";
    private static final String CODIGO = "codigo";

    public Departamento() {
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

    public String getPaisId() {
        return (String) getAtributo(PAIS_ID);
    }

    public void setPaisId(String paisId) {
        setAtributo(PAIS_ID, paisId);
    }

    public String getCodigo() {
        return (String) getAtributo(CODIGO);
    }

    public void setCodigo(String codigo) {
        setAtributo(CODIGO, codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento departamento = (Departamento) o;
        return getId().equals(departamento.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", paisId='" + getPaisId() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                '}';
    }
}