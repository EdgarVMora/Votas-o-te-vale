package com.finalproject.view;


public interface CargarElectoresViewActions {
    void mostrarNombreArchivoSeleccionado(String nombreArchivo); 
    void mostrarMensajeEstado(String mensaje, boolean esError);
    void limpiarMensajeEstado();
    void operacionEnProgreso(boolean enProgreso);
    String obtenerNombreArchivoPredefinidoSeleccionado(); 
    void establecerNotificador(Object notificador);
}
