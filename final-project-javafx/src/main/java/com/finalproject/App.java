package com.finalproject;

// Asegúrate que este import sea el correcto para la interfaz LoginNavegador
import com.finalproject.presenter.LoginNavegador; 
import com.finalproject.presenter.LoginPresenter;
import com.finalproject.view.LoginView;
import com.finalproject.view.LoginViewActions; // Necesario para instanciar LoginPresenter

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label; // No se usa directamente aquí pero es bueno tenerlo si se expande el placeholder
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application implements LoginNavegador {

    private Stage escenarioPrincipal;

    @Override
    public void start(Stage primaryStage) {
        this.escenarioPrincipal = primaryStage;
        this.escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP");
        mostrarLogin();
    }

    private void mostrarLogin() {
        // La vista se crea primero
        LoginView vistaLogin = new LoginView();
        
        // El presentador se crea con la vista (como LoginViewActions) y el navegador (this)
        new LoginPresenter(vistaLogin, this); 

        Scene escena = new Scene(vistaLogin.obtenerNodoVista(), 400, 300);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    @Override
    public void navegarAPantallaAdmin() {
        mostrarMenuAdminTemporal();
    }
    
    @Override
    public void mostrarMensajeLoginExitoso(String titulo, String mensaje) {
        mostrarAlerta(Alert.AlertType.INFORMATION, titulo, mensaje);
    }

    @Override
    public void mostrarMensajeLoginFallido(String titulo, String mensaje) {
        mostrarAlerta(Alert.AlertType.ERROR, titulo, mensaje);
    }

    private void mostrarMenuAdminTemporal() {
        VBox panelMenuAdmin = new VBox(15);
        panelMenuAdmin.setAlignment(Pos.CENTER);
        panelMenuAdmin.setPadding(new Insets(25));

        Text tituloMenu = new Text("Menú del Administrador (MVP)");
        tituloMenu.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Button botonPlaceholder = new Button("Funcionalidad Admin Placeholder");
        botonPlaceholder.setOnAction(e -> System.out.println("Acción del menú admin placeholder"));
        
        Button botonCerrarSesion = new Button("Cerrar Sesión");
        botonCerrarSesion.setOnAction(e -> mostrarLogin());

        panelMenuAdmin.getChildren().addAll(tituloMenu, botonPlaceholder, botonCerrarSesion);

        Scene escenaMenuAdmin = new Scene(panelMenuAdmin, 450, 550);
        escenarioPrincipal.setScene(escenaMenuAdmin);
        escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP - Panel de Administrador");
    }

    private void mostrarAlerta(Alert.AlertType tipoAlerta, String titulo, String mensaje) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}