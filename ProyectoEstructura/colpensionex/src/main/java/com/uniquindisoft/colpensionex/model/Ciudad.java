package com.uniquindisoft.colpensionex.model;

/**
 * Modelo que representa una ciudad en el sistema.
 */
public class Ciudad extends BaseModel {
    private static final String ID = "id";
    private static final String NOMBRE = "nombre";
    private static final String DEPARTAMENTO_ID = "departamentoId";
    private static final String CODIGO = "codigo";

    public Ciudad() {
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

    public String getDepartamentoId() {
        return (String) getAtributo(DEPARTAMENTO_ID);
    }

    public void setDepartamentoId(String departamentoId) {
        setAtributo(DEPARTAMENTO_ID, departamentoId);
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
        if (!(o instanceof Ciudad)) return false;
        Ciudad ciudad = (Ciudad) o;
        return getId().equals(ciudad.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", departamentoId='" + getDepartamentoId() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                '}';
    }
}