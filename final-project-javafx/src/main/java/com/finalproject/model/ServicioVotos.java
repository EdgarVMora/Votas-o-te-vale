package com.finalproject.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioVotos {
    private static ServicioVotos instancia;
    private List<Voto> votos;
    private Map<Candidato, Integer> conteoVotos;
    private int votosNulos;

    private ServicioVotos() {
        this.votos = new ArrayList<>();
        this.conteoVotos = new HashMap<>();
        this.votosNulos = 0;
    }

    public static ServicioVotos obtenerInstancia() {
        if (instancia == null) {
            instancia = new ServicioVotos();
        }
        return instancia;
    }

    public boolean registrarVoto(Elector elector, Candidato candidato, boolean esNulo) {
        // Verificar si el elector ya votó
        for (Voto voto : votos) {
            if (voto.getElector().equals(elector)) {
                return false; // El elector ya votó
            }
        }

        // Registrar el voto
        String fechaVoto = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Voto nuevoVoto = new Voto(elector, candidato, fechaVoto, esNulo);
        votos.add(nuevoVoto);

        // Actualizar conteo
        if (esNulo) {
            votosNulos++;
        } else {
            conteoVotos.put(candidato, conteoVotos.getOrDefault(candidato, 0) + 1);
        }

        return true;
    }

    public boolean electorYaVoto(Elector elector) {
        for (Voto voto : votos) {
            if (voto.getElector().equals(elector)) {
                return true;
            }
        }
        return false;
    }

    public Map<Candidato, Integer> obtenerConteoVotos() {
        return new HashMap<>(conteoVotos);
    }

    public int obtenerVotosNulos() {
        return votosNulos;
    }

    public List<Voto> obtenerTodosLosVotos() {
        return new ArrayList<>(votos);
    }

    public void reiniciarVotos() {
        votos.clear();
        conteoVotos.clear();
        votosNulos = 0;
    }
} 