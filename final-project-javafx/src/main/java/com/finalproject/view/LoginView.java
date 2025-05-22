package com.finalproject.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginView implements LoginViewActions {

    private GridPane panelCuadricula;
    private TextField campoUsuario;
    private PasswordField campoContrasena;
    private Text textoEstado;
    private NotificadorLoginAlPresentador notificadorPresentador;

    public LoginView() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelCuadricula = new GridPane();
        panelCuadricula.setAlignment(Pos.CENTER);
        panelCuadricula.setHgap(10);
        panelCuadricula.setVgap(10);
        panelCuadricula.setPadding(new Insets(25, 25, 25, 25));

        Text tituloEscena = new Text("Sistema de Votaciones FFC - BUAP");
        tituloEscena.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        panelCuadricula.add(tituloEscena, 0, 0, 2, 1);

        Label etiquetaUsuario = new Label("Usuario:");
        panelCuadricula.add(etiquetaUsuario, 0, 1);

        campoUsuario = new TextField();
        campoUsuario.setPromptText("Ingrese su usuario");
        panelCuadricula.add(campoUsuario, 1, 1);

        Label etiquetaContrasena = new Label("Contraseña:");
        panelCuadricula.add(etiquetaContrasena, 0, 2);

        campoContrasena = new PasswordField();
        campoContrasena.setPromptText("Ingrese su contraseña");
        panelCuadricula.add(campoContrasena, 1, 2);

        Button botonLogin = new Button("Iniciar Sesión");
        panelCuadricula.add(botonLogin, 1, 4);

        textoEstado = new Text();
        panelCuadricula.add(textoEstado, 0, 5, 2, 1);

        Text textoInfo = new Text("Ingrese sus credenciales para acceder al sistema");
        textoInfo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        textoInfo.setFill(Color.GRAY);
        panelCuadricula.add(textoInfo, 0, 6, 2, 1);

        botonLogin.setOnAction(e -> {
            if (notificadorPresentador != null) {
                notificadorPresentador.alPulsarBotonLogin();
            }
        });
    }

    public Parent obtenerNodoVista() {
        return panelCuadricula;
    }

    @Override
    public String obtenerUsuario() {
        return campoUsuario.getText();
    }

    @Override
    public String obtenerContrasena() {
        return campoContrasena.getText();
    }

    @Override
    public void mostrarErrorUsuario(String mensaje) {
        textoEstado.setFill(Color.FIREBRICK);
        textoEstado.setText("Usuario: " + mensaje);
    }

    @Override
    public void mostrarErrorContrasena(String mensaje) {
        textoEstado.setFill(Color.FIREBRICK);
        textoEstado.setText("Contraseña: " + mensaje);
    }
    
    @Override
    public void mostrarErrorLogin(String mensaje) {
        textoEstado.setFill(Color.FIREBRICK);
        textoEstado.setText(mensaje);
    }

    @Override
    public void limpiarErrores() {
        textoEstado.setText("");
    }

    @Override
    public void limpiarCampos() {
        campoUsuario.clear();
        campoContrasena.clear();
    }

    @Override
    public void indicarNavegacionAPanelAdmin() {
        System.out.println("LoginView: El presentador indicó que el login fue exitoso y se procederá a navegar.");
    }

    @Override
    public void establecerNotificadorPresentador(Object notificador) {
        if (notificador instanceof NotificadorLoginAlPresentador) {
            this.notificadorPresentador = (NotificadorLoginAlPresentador) notificador;
        } else {
            System.err.println("Error: El notificador proporcionado no es del tipo NotificadorLoginAlPresentador.");
        }
    }
}
