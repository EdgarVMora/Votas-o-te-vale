package com.finalproject.view;

// import java.io.File; // Ya no es necesario aquí

public interface CargarElectoresViewActions {
    void mostrarNombreArchivoSeleccionado(String nombreArchivo); // Para mostrar el nombre del archivo predefinido
    void mostrarMensajeEstado(String mensaje, boolean esError);
    void limpiarMensajeEstado();
    void operacionEnProgreso(boolean enProgreso);
    String obtenerNombreArchivoPredefinidoSeleccionado(); // Nuevo: para que el presentador sepa qué archivo de la lista se eligió
    void establecerNotificador(Object notificador);
}
