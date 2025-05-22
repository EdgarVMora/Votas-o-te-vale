package com.finalproject.view;

public interface LoginViewActions {
    String obtenerUsuario();
    String obtenerContrasena();
    void mostrarErrorUsuario(String mensaje);
    void mostrarErrorContrasena(String mensaje);
    void mostrarErrorLogin(String mensaje);
    void limpiarErrores();
    void limpiarCampos();
    void indicarNavegacionAPanelAdmin(); // Cambiado para indicar acción, no ejecutarla
    void establecerNotificadorPresentador(Object notificador); // Renombrado y tipo Object por ahora
}
