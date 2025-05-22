package com.finalproject.model;

public class Candidato {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String partido;
    private String propuesta;

    public Candidato(String nombre, String apellidoPaterno, String apellidoMaterno, String partido, String propuesta) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.partido = partido;
        this.propuesta = propuesta;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public String getPartido() { return partido; }
    public String getPropuesta() { return propuesta; }

    @Override
    public String toString() {
        return "Candidato: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno +
               "\nPartido: " + partido +
               "\nPropuesta: " + propuesta;
    }
} 