package com.finalproject.view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class CargarElectoresView implements CargarElectoresViewActions {

    private VBox panelPrincipal;
    private Button botonCargarArchivo;
    private ComboBox<String> comboBoxArchivosPredefinidos;
    private Text textoEstado;
    private NotificadorCargarElectoresAlPresentador notificador;

    private final List<String> NOMBRES_ARCHIVOS_PREDEFINIDOS = Arrays.asList(
            "lista1.txt", "lista2.txt", "lista3.txt", "lista4.txt", "lista5.txt"
    );

    public CargarElectoresView(/* Stage escenarioPrimario */) { 
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelPrincipal = new VBox(15);
        panelPrincipal.setPadding(new Insets(25));
        panelPrincipal.setAlignment(Pos.CENTER_LEFT);

        Text titulo = new Text("Cargar Electores desde Archivo Predefinido");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label etiquetaSeleccion = new Label("Selecciona una lista de electores:");
        comboBoxArchivosPredefinidos = new ComboBox<>(FXCollections.observableArrayList(NOMBRES_ARCHIVOS_PREDEFINIDOS));
        comboBoxArchivosPredefinidos.setPromptText("Elige un archivo");
        
        comboBoxArchivosPredefinidos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            botonCargarArchivo.setDisable(newVal == null);
            if (notificador != null && newVal != null) {
                mostrarNombreArchivoSeleccionado("Archivo seleccionado: " + newVal);
            }
        });

        botonCargarArchivo = new Button("Cargar Electores");
        botonCargarArchivo.setDisable(true); 

        textoEstado = new Text(); 

        botonCargarArchivo.setOnAction(e -> {
            if (notificador != null && comboBoxArchivosPredefinidos.getValue() != null) {
                notificador.alPulsarBotonCargarArchivo(); 
            }
        });

        Button botonRegresar = new Button("Regresar al Menú");
        botonRegresar.setOnAction(e -> {
            if (notificador != null) {
                notificador.alPulsarBotonRegresar();
            }
        });

        panelPrincipal.getChildren().addAll(titulo, etiquetaSeleccion, comboBoxArchivosPredefinidos, botonCargarArchivo, textoEstado, botonRegresar);
    }

    public Parent obtenerNodoVista() {
        return panelPrincipal;
    }

    @Override
    public void mostrarNombreArchivoSeleccionado(String nombreArchivo) {
        
        if (nombreArchivo.isEmpty()) {
            textoEstado.setText(""); 
        }
        
    }
    
    @Override
    public String obtenerNombreArchivoPredefinidoSeleccionado() {
        return comboBoxArchivosPredefinidos.getValue();
    }

    @Override
    public void mostrarMensajeEstado(String mensaje, boolean esError) {
        textoEstado.setText(mensaje);
        textoEstado.setFill(esError ? Color.RED : Color.GREEN);
    }

    @Override
    public void limpiarMensajeEstado() {
        textoEstado.setText("");
    }

    @Override
    public void operacionEnProgreso(boolean enProgreso) {
        comboBoxArchivosPredefinidos.setDisable(enProgreso);
        botonCargarArchivo.setDisable(enProgreso || comboBoxArchivosPredefinidos.getValue() == null);
    }

    @Override
    public void establecerNotificador(Object notificadorObj) {
        if (notificadorObj instanceof NotificadorCargarElectoresAlPresentador) {
            this.notificador = (NotificadorCargarElectoresAlPresentador) notificadorObj;
        } else {
            System.err.println("Error: El notificador para CargarElectoresView no es del tipo esperado.");
        }
    }
}
