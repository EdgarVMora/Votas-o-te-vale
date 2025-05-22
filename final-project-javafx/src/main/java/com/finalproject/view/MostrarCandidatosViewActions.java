package com.finalproject.view;

import javafx.scene.Parent;

public interface MostrarCandidatosViewActions {
    Parent obtenerNodoVista();
    void mostrarMensajeError(String mensaje);
    void establecerNotificador(Object notificador);
} 