package com.metzuryProjects.co.modelos;

public class Solicitante {
    private int idCotizante;
    private int cedula;
    private String nombre;
    private int edad;
    private String genero,ciudad;
    private int semanasCotizadas;
    private boolean esPrePensionado,declaraRenta;
    private String fondoDePensiones,estadoSolicitud;
    private String entidadPublica;

    // Constructor
    public Solicitante(int idCotizante, int cedula, String nombre, int edad, String genero, String ciudad, 
            int semanasCotizadas, boolean declaraRenta,boolean esPrePensionado, String fondoDePensiones,String entidadPublica) {
        this.idCotizante = idCotizante;
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.semanasCotizadas = semanasCotizadas;
        this.esPrePensionado = esPrePensionado;
        this.declaraRenta = declaraRenta;
        this.fondoDePensiones = fondoDePensiones;
        this.estadoSolicitud = "pendiente";
        this.entidadPublica = entidadPublica;
    }

    // Getters y Setters
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public boolean isEsPrePensionado() {
        return esPrePensionado;
    }

    public void setEsPrePensionado(boolean esPrePensionado) {
        this.esPrePensionado = esPrePensionado;
    }

    public boolean isDeclaraRenta() {
        return declaraRenta;
    }

    public void setDeclaraRenta(boolean declaraRenta) {
        this.declaraRenta = declaraRenta;
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

//    @Override
//    public String toString() {
//        return "Solicitante{" + "cedula=" + cedula + ", nombre=" + nombre + ", " 
//                + "edad=" + edad + ", genero=" + genero + ", ciudad=" + ciudad + ", semanas=" + semanasCotizadas + ", "
//                + "PrePensionado=" + esPrePensionado + ", declaraRenta=" + declaraRenta + ", "
//                + "fondoDePensiones=" + fondoDePensiones + ", estado=" + estadoSolicitud + '}';
//    }

    @Override
    public String toString() {
        return "Solicitante{" + "idCotizante=" + idCotizante + ", cedula=" + cedula + ", nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + ", ciudad=" + ciudad + ", semanasCotizadas=" + semanasCotizadas + ", esPrePensionado=" + esPrePensionado + ", declaraRenta=" + declaraRenta + ", fondoDePensiones=" + fondoDePensiones + ", estadoSolicitud=" + estadoSolicitud + ", entidadPublica=" + entidadPublica + '}';
    }

}
