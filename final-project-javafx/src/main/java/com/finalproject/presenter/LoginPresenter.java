package com.finalproject.presenter;

import com.finalproject.view.LoginViewActions;
import com.finalproject.view.NotificadorLoginAlPresentador;
import com.finalproject.model.Elector;
import java.util.List;

public class LoginPresenter implements NotificadorLoginAlPresentador {

    private LoginViewActions vista;
    private LoginNavegador navegador;
    private List<Elector> listaElectores;

    private final String USUARIO_VALIDO = "admin";
    private final String CONTRASENA_VALIDA = "12345";

    public LoginPresenter(LoginViewActions vista, LoginNavegador navegador, List<Elector> listaElectores) {
        this.vista = vista;
        this.navegador = navegador;
        this.listaElectores = listaElectores;
        this.vista.establecerNotificadorPresentador(this);
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

        // Primero verificar si es administrador
        if (USUARIO_VALIDO.equals(usuario) && CONTRASENA_VALIDA.equals(contrasena)) {
            vista.limpiarCampos();
            if (navegador != null) {
                navegador.mostrarMensajeLoginExitoso("Inicio Exitoso", "Bienvenido Administrador!");
                navegador.navegarAPantallaAdmin();
            }
            vista.indicarNavegacionAPanelAdmin();
            return;
        }

        // Si no es administrador, verificar si es elector
        if (listaElectores != null) {
            for (Elector elector : listaElectores) {
                if (elector.getUsuario().equals(usuario) && elector.getContrasena().equals(contrasena)) {
                    vista.limpiarCampos();
                    if (navegador != null) {
                        navegador.mostrarMensajeLoginExitoso("Inicio Exitoso", 
                            "Bienvenido " + elector.getNombre() + " " + elector.getApellidoPaterno() + "!");
                        navegador.navegarAPantallaElector(elector);
                    }
                    return;
                }
            }
        }

        // Si no es ni administrador ni elector válido
        vista.mostrarErrorLogin("Usuario o contraseña incorrectos.");
        if (navegador != null) {
            navegador.mostrarMensajeLoginFallido("Error de Inicio", "Usuario o contraseña incorrectos.");
        }
    }
}
