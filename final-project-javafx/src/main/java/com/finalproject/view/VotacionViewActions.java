package com.finalproject.view;

import com.finalproject.model.Candidato;
import javafx.scene.Parent;
import java.util.List;

public interface VotacionViewActions {
    Parent obtenerNodoVista();
    void establecerCandidatos(List<Candidato> candidatos);
    Candidato obtenerCandidatoSeleccionado();
    void mostrarMensajeError(String mensaje);
    void mostrarMensajeExito(String mensaje);
    void establecerNotificador(Object notificador);
    void deshabilitarVotacion();
} 