package com.finalproject.model; // Paquete corregido

public class Fecha {
    private int dia; // Atributos en minúscula por convención
    private int mes;
    private int anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    @Override
    public String toString() {
        // Formato dd/MM/yyyy para consistencia, como se espera en archivos.
        return String.format("%02d/%02d/%04d", dia, mes, anio);
    }
}
