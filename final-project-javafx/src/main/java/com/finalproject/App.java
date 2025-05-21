package com.finalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField; // Importante para contraseñas
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane; // Usaremos GridPane para organizar mejor
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage; // Guardar referencia al Stage principal

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("VOTACIONES FFC - BUAP");

        showLoginScreen();
    }

    private void showLoginScreen() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10); // Espacio horizontal entre columnas
        grid.setVgap(10); // Espacio vertical entre filas
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Inicio de Sesión - Administrador");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1); // Columna 0, Fila 0, Ocupa 2 columnas, Ocupa 1 fila

        Label userNameLabel = new Label("Usuario:");
        grid.add(userNameLabel, 0, 1); // Columna 0, Fila 1

        TextField userTextField = new TextField();
        userTextField.setPromptText("admin"); // Pista para el usuario
        grid.add(userTextField, 1, 1); // Columna 1, Fila 1

        Label passwordLabel = new Label("Contraseña:");
        grid.add(passwordLabel, 0, 2); // Columna 0, Fila 2

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("12345"); // Pista para la contraseña
        grid.add(passwordField, 1, 2); // Columna 1, Fila 2

        Button loginButton = new Button("Iniciar Sesión");
        grid.add(loginButton, 1, 4); // Columna 1, Fila 4 (dejamos un espacio)

        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();

            if ("admin".equals(username) && "12345".equals(password)) {
                showAlert(Alert.AlertType.INFORMATION, "Inicio Exitoso", "Bienvenido Administrador!");
                showAdminMenu(); // Navegar al menú de administrador
            } else {
                showAlert(Alert.AlertType.ERROR, "Error de Inicio", "Usuario o contraseña incorrectos.");
            }
        });

        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAdminMenu() {
        VBox adminMenuLayout = new VBox(15); // Espaciado entre botones
        adminMenuLayout.setAlignment(Pos.CENTER);
        adminMenuLayout.setPadding(new Insets(25));

        Text menuTitle = new Text("Menú del Administrador");
        menuTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Button loadElectorsButton = new Button("Cargar electores");
        Button loadCandidatesButton = new Button("Cargar candidatos");
        Button showElectorsButton = new Button("Mostrar electores");
        Button showCandidatesButton = new Button("Mostrar candidatos");
        Button openVotingButton = new Button("Abrir votaciones");
        Button closeVotingButton = new Button("Cerrar votaciones");
        Button printResultsButton = new Button("Imprimir resultados");
        Button registrationButton = new Button("Registro"); // Asumo que es "Registro de algo más" o "Configuración"

        // Acciones placeholder para los botones del menú
        loadElectorsButton.setOnAction(e -> System.out.println("Acción: Cargar electores"));
        loadCandidatesButton.setOnAction(e -> System.out.println("Acción: Cargar candidatos"));
        showElectorsButton.setOnAction(e -> System.out.println("Acción: Mostrar electores"));
        showCandidatesButton.setOnAction(e -> System.out.println("Acción: Mostrar candidatos"));
        openVotingButton.setOnAction(e -> System.out.println("Acción: Abrir votaciones"));
        closeVotingButton.setOnAction(e -> System.out.println("Acción: Cerrar votaciones"));
        printResultsButton.setOnAction(e -> System.out.println("Acción: Imprimir resultados"));
        registrationButton.setOnAction(e -> System.out.println("Acción: Registro"));

        // Botón para regresar al login (opcional, pero útil para desarrollo)
        Button logoutButton = new Button("Cerrar Sesión");
        logoutButton.setOnAction(e -> showLoginScreen());


        adminMenuLayout.getChildren().addAll(
            menuTitle,
            loadElectorsButton,
            loadCandidatesButton,
            showElectorsButton,
            showCandidatesButton,
            openVotingButton,
            closeVotingButton,
            printResultsButton,
            registrationButton,
            new Label(" "), // Un pequeño separador visual
            logoutButton
        );

        Scene adminMenuScene = new Scene(adminMenuLayout, 450, 550);
        primaryStage.setScene(adminMenuScene);
        primaryStage.setTitle("VOTACIONES FFC - BUAP - Panel de Administrador");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // No necesitamos un texto de cabecera
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
