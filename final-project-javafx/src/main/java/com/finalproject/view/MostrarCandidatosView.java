package com.finalproject.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;
import com.finalproject.model.Candidato;
import java.util.List;

public class MostrarCandidatosView implements MostrarCandidatosViewActions {
    private VBox panelPrincipal;
    private TableView<Candidato> tablaCandidatos;
    private Text mensajeError;
    private Object notificador;

    public MostrarCandidatosView(List<Candidato> candidatos) {
        inicializarComponentes(candidatos);
    }

    private void inicializarComponentes(List<Candidato> candidatos) {
        panelPrincipal = new VBox(15);
        panelPrincipal.setPadding(new Insets(20));

        Text titulo = new Text("Lista de Candidatos Registrados");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        tablaCandidatos = new TableView<>();
        tablaCandidatos.setItems(FXCollections.observableArrayList(candidatos));

        // Columna Nombre
        TableColumn<Candidato, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getNombre()));

        // Columna Apellido Paterno
        TableColumn<Candidato, String> columnaApellidoPaterno = new TableColumn<>("Apellido Paterno");
        columnaApellidoPaterno.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getApellidoPaterno()));

        // Columna Apellido Materno
        TableColumn<Candidato, String> columnaApellidoMaterno = new TableColumn<>("Apellido Materno");
        columnaApellidoMaterno.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getApellidoMaterno()));

        // Columna Partido
        TableColumn<Candidato, String> columnaPartido = new TableColumn<>("Partido");
        columnaPartido.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getPartido()));

        tablaCandidatos.getColumns().addAll(
            columnaNombre,
            columnaApellidoPaterno,
            columnaApellidoMaterno,
            columnaPartido
        );

        mensajeError = new Text();
        mensajeError.setStyle("-fx-fill: red;");

        Button botonRegresar = new Button("Regresar al Menú");
        botonRegresar.setOnAction(e -> {
            if (notificador instanceof NotificadorMostrarCandidatosAlPresentador) {
                ((NotificadorMostrarCandidatosAlPresentador) notificador).alPulsarBotonRegresar();
            }
        });

        panelPrincipal.getChildren().addAll(titulo, tablaCandidatos, mensajeError, botonRegresar);
    }

    @Override
    public Parent obtenerNodoVista() {
        return panelPrincipal;
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        mensajeError.setText(mensaje);
    }

    @Override
    public void establecerNotificador(Object notificador) {
        this.notificador = notificador;
    }
} 