package com.finalproject.view;

import javafx.scene.Parent;

public interface CargarCandidatosViewActions {
    Parent obtenerNodoVista();
    void mostrarMensajeEstado(String mensaje, boolean esError);
    void limpiarMensajeEstado();
    void operacionEnProgreso(boolean enProgreso);
    String obtenerNombreArchivoSeleccionado(String partido);
    void establecerNotificador(Object notificador);
} 