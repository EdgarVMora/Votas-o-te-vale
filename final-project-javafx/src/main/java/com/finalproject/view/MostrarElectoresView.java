package com.finalproject.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import com.finalproject.model.Elector;
import java.util.List;

public class MostrarElectoresView implements MostrarElectoresViewActions {
    private VBox panelPrincipal;
    private TableView<Elector> tablaElectores;
    private Text mensajeError;
    private Object notificador;

    public MostrarElectoresView() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelPrincipal = new VBox(10);
        panelPrincipal.setPadding(new Insets(20));

        Text titulo = new Text("Lista de Electores Registrados");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Crear la tabla
        tablaElectores = new TableView<>();
        
        // Columnas de la tabla
        TableColumn<Elector, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getNombre()));

        TableColumn<Elector, String> columnaApellidoPaterno = new TableColumn<>("Apellido Paterno");
        columnaApellidoPaterno.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getApellidoPaterno()));

        TableColumn<Elector, String> columnaApellidoMaterno = new TableColumn<>("Apellido Materno");
        columnaApellidoMaterno.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getApellidoMaterno()));

        TableColumn<Elector, String> columnaCorreo = new TableColumn<>("Correo");
        columnaCorreo.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getCorreo()));

        TableColumn<Elector, String> columnaUsuario = new TableColumn<>("Usuario");
        columnaUsuario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUsuario()));

        // Añadir columnas a la tabla
        tablaElectores.getColumns().addAll(
            columnaNombre, 
            columnaApellidoPaterno, 
            columnaApellidoMaterno, 
            columnaCorreo,
            columnaUsuario
        );

        // Mensaje de error
        mensajeError = new Text();
        mensajeError.setStyle("-fx-fill: red;");

        // Botón para regresar
        Button botonRegresar = new Button("Regresar al Menú");
        botonRegresar.setOnAction(e -> {
            if (notificador instanceof NotificadorMostrarElectoresAlPresentador) {
                ((NotificadorMostrarElectoresAlPresentador) notificador).alPulsarBotonRegresar();
            }
        });

        panelPrincipal.getChildren().addAll(titulo, tablaElectores, mensajeError, botonRegresar);
    }

    @Override
    public Parent obtenerNodoVista() {
        return panelPrincipal;
    }

    @Override
    public void mostrarElectores(List<Elector> electores) {
        ObservableList<Elector> datos = FXCollections.observableArrayList(electores);
        tablaElectores.setItems(datos);
        mensajeError.setText("");
    }

    @Override
    public void establecerNotificador(Object notificador) {
        this.notificador = notificador;
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        mensajeError.setText(mensaje);
    }
} 