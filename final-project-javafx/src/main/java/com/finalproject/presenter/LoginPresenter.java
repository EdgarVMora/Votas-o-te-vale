package com.finalproject.presenter;

import com.finalproject.view.LoginViewActions;
import com.finalproject.view.NotificadorLoginAlPresentador; // Importa la interfaz pública

public class LoginPresenter implements NotificadorLoginAlPresentador { // Implementa la interfaz correcta

    private LoginViewActions vista;
    private LoginNavegador navegador;

    private final String USUARIO_VALIDO = "admin";
    private final String CONTRASENA_VALIDA = "12345";

    public LoginPresenter(LoginViewActions vista, LoginNavegador navegador) {
        this.vista = vista;
        this.navegador = navegador;
        this.vista.establecerNotificadorPresentador(this); // La vista establece este presentador como su notificador
    }

    @Override
    public void alPulsarBotonLogin() {
        vista.limpiarErrores();
        String usuario = vista.obtenerUsuario();
        String contrasena = vista.obtenerContrasena();

        boolean esValido = true;
        if (usuario == null || usuario.trim().isEmpty()) {
            vista.mostrarErrorUsuario("El usuario no puede estar vacío.");
            esValido = false;
        }

        if (contrasena == null || contrasena.isEmpty()) {
            vista.mostrarErrorContrasena("La contraseña no puede estar vacía.");
            esValido = false;
        }
        
        if (!esValido) {
            return;
        }

        if (USUARIO_VALIDO.equals(usuario) && CONTRASENA_VALIDA.equals(contrasena)) {
            vista.limpiarCampos();
            if (navegador != null) {
                navegador.mostrarMensajeLoginExitoso("Inicio Exitoso", "Bienvenido Administrador!");
                navegador.navegarAPantallaAdmin();
            }
            vista.indicarNavegacionAPanelAdmin(); // Indica a la vista que la acción fue exitosa
        } else {
            vista.mostrarErrorLogin("Usuario o contraseña incorrectos.");
             if (navegador != null) {
                navegador.mostrarMensajeLoginFallido("Error de Inicio", "Usuario o contraseña incorrectos.");
            }
        }
    }
}
