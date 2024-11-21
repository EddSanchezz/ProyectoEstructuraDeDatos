package com.uniquindisoft.colpensionex.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase base para todos los modelos que utilizan mapeo dinámico de atributos
 */
public abstract class BaseModel {
    protected Map<String, Object> atributos;
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public BaseModel() {
        this.atributos = new HashMap<>();
    }

    public void setAtributo(String nombre, Object valor) {
        atributos.put(nombre, valor);
    }

    public Object getAtributo(String nombre) {
        return atributos.get(nombre);
    }

    public Map<String, Object> getAtributos() {
        return new HashMap<>(atributos);
    }
}