package com.finalproject.view;

import javafx.scene.Parent;
import com.finalproject.model.Candidato;
import java.util.Map;

public interface MostrarResultadosViewActions {
    Parent obtenerNodoVista();
    void mostrarResultados(Map<Candidato, Integer> votosPorCandidato, int votosNulos);
    void mostrarMensajeError(String mensaje);
    void establecerNotificador(Object notificador);
} 