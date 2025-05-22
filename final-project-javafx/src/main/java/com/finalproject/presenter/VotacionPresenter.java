package com.finalproject.presenter;

import com.finalproject.view.VotacionViewActions;
import com.finalproject.view.NotificadorVotacionAlPresentador;
import com.finalproject.model.Candidato;
import com.finalproject.model.Elector;
import com.finalproject.model.ServicioVotos;
import com.finalproject.model.EstadoVotaciones;
import java.util.List;

public class VotacionPresenter implements NotificadorVotacionAlPresentador {
    private VotacionViewActions vista;
    private VotacionNavegador navegador;
    private Elector elector;
    private List<Candidato> candidatos;
    private ServicioVotos servicioVotos;

    public VotacionPresenter(VotacionViewActions vista, Elector elector, List<Candidato> candidatos, VotacionNavegador navegador) {
        this.vista = vista;
        this.elector = elector;
        this.candidatos = candidatos;
        this.navegador = navegador;
        this.servicioVotos = ServicioVotos.obtenerInstancia();
        
        this.vista.establecerNotificador(this);
        this.vista.establecerCandidatos(candidatos);

       
        if (servicioVotos.electorYaVoto(elector)) {
            vista.mostrarMensajeError("Usted ya ha emitido su voto.");
            vista.deshabilitarVotacion();
        }

        
        if (!EstadoVotaciones.obtenerInstancia().estanIniciadas()) {
            vista.mostrarMensajeError("Las votaciones no están abiertas en este momento.");
            vista.deshabilitarVotacion();
        }
    }

    @Override
    public void alPulsarBotonVotar() {
        Candidato candidatoSeleccionado = vista.obtenerCandidatoSeleccionado();
        
        if (candidatoSeleccionado == null) {
            vista.mostrarMensajeError("Por favor, seleccione un candidato.");
            return;
        }

        if (servicioVotos.electorYaVoto(elector)) {
            vista.mostrarMensajeError("Usted ya ha emitido su voto.");
            return;
        }

        if (!EstadoVotaciones.obtenerInstancia().estanIniciadas()) {
            vista.mostrarMensajeError("Las votaciones no están abiertas en este momento.");
            return;
        }

        boolean votoRegistrado = servicioVotos.registrarVoto(elector, candidatoSeleccionado, false);
        
        if (votoRegistrado) {
            vista.mostrarMensajeExito("¡Su voto ha sido registrado exitosamente!");
            vista.deshabilitarVotacion();
        } else {
            vista.mostrarMensajeError("No se pudo registrar su voto. Por favor, intente nuevamente.");
        }
    }

    @Override
    public void alPulsarBotonVotoNulo() {
        if (servicioVotos.electorYaVoto(elector)) {
            vista.mostrarMensajeError("Usted ya ha emitido su voto.");
            return;
        }

        if (!EstadoVotaciones.obtenerInstancia().estanIniciadas()) {
            vista.mostrarMensajeError("Las votaciones no están abiertas en este momento.");
            return;
        }

        
        boolean votoRegistrado = servicioVotos.registrarVoto(elector, null, true);
        
        if (votoRegistrado) {
            vista.mostrarMensajeExito("¡Su voto nulo ha sido registrado exitosamente!");
            vista.deshabilitarVotacion();
        } else {
            vista.mostrarMensajeError("No se pudo registrar su voto nulo. Por favor, intente nuevamente.");
        }
    }

    @Override
    public void alPulsarBotonCerrarSesion() {
        if (navegador != null) {
            navegador.cerrarSesion();
        }
    }
} 