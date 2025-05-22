package com.finalproject.model;

public class Voto {
    private Elector elector;
    private Candidato candidato;
    private String fechaVoto;

    public Voto(Elector elector, Candidato candidato, String fechaVoto) {
        this.elector = elector;
        this.candidato = candidato;
        this.fechaVoto = fechaVoto;
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
} 