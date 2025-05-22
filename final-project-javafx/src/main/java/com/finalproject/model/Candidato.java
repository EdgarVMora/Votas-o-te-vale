package com.finalproject.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidato candidato = (Candidato) o;
        return Objects.equals(nombre, candidato.nombre) &&
               Objects.equals(apellidoPaterno, candidato.apellidoPaterno) &&
               Objects.equals(apellidoMaterno, candidato.apellidoMaterno) &&
               Objects.equals(partido, candidato.partido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidoPaterno, apellidoMaterno, partido);
    }

    @Override
    public String toString() {
        return "Candidato: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno +
               "\nPartido: " + partido +
               "\nPropuesta: " + propuesta;
    }
} 