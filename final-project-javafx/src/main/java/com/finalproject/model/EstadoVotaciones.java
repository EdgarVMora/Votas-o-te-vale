package com.finalproject.model;

public class EstadoVotaciones {
    private static EstadoVotaciones instancia;
    private boolean votacionesIniciadas;
    private boolean votacionesCerradas;

    private EstadoVotaciones() {
        this.votacionesIniciadas = false;
        this.votacionesCerradas = false;
    }

    public static EstadoVotaciones obtenerInstancia() {
        if (instancia == null) {
            instancia = new EstadoVotaciones();
        }
        return instancia;
    }

    public boolean estanIniciadas() {
        return votacionesIniciadas;
    }

    public boolean estanCerradas() {
        return votacionesCerradas;
    }

    public void iniciarVotaciones() {
        this.votacionesIniciadas = true;
        this.votacionesCerradas = false;
    }

    public void cerrarVotaciones() {
        this.votacionesIniciadas = false;
        this.votacionesCerradas = true;
    }

    public void reiniciarEstado() {
        this.votacionesIniciadas = false;
        this.votacionesCerradas = false;
    }
} 