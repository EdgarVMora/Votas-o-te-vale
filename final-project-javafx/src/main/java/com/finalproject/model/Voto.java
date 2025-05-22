package com.finalproject.model;

public class Voto {
    private Elector elector;
    private Candidato candidato;
    private String fechaVoto;
    private boolean esNulo;

    public Voto(Elector elector, Candidato candidato, String fechaVoto, boolean esNulo) {
        this.elector = elector;
        this.candidato = candidato;
        this.fechaVoto = fechaVoto;
        this.esNulo = esNulo;
    }

    public Elector getElector() {
        return elector;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public String getFechaVoto() {
        return fechaVoto;
    }

    public boolean esNulo() {
        return esNulo;
    }
} 