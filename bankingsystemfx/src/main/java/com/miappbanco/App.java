// Ejemplo: package com.miappbanco;
package com.miappbanco; // CAMBIA ESTO para que coincida con tu paquete

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application { // El nombre de la clase debe ser 'App' o el que definiste en pom.xml

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mi Aplicación JavaFX con Maven!");

        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();
        nameField.setPromptText("Introduce tu nombre");

        Label messageLabel = new Label("Mensaje:");
        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Escribe tu mensaje aquí");
        messageArea.setPrefRowCount(5);

        Button submitButton = new Button("Enviar");
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String message = messageArea.getText();
            if (name.isEmpty() || message.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Entrada Inválida", "Por favor, introduce nombre y mensaje.");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Información Enviada",
                        "Nombre: " + name + "\nMensaje: " + message);
                nameField.clear();
                messageArea.clear();
            }
        });

        Separator separator = new Separator();

        VBox vbox = new VBox(10); // Espaciado de 10px
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(nameLabel, nameField, messageLabel, messageArea, separator, submitButton);

        Scene scene = new Scene(vbox, 400, 350);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}