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
// import javafx.stage.Stage; // Ya no es necesario si no hay FileChooser desde la vista

import java.util.Arrays;
import java.util.List;

public class CargarElectoresView implements CargarElectoresViewActions {

    private VBox panelPrincipal;
    private Button botonCargarArchivo;
    private ComboBox<String> comboBoxArchivosPredefinidos;
    private Text textoEstado;
    private NotificadorCargarElectoresAlPresentador notificador;

    // Lista de los nombres de tus archivos precargados (sin la ruta, solo el nombre)
    private final List<String> NOMBRES_ARCHIVOS_PREDEFINIDOS = Arrays.asList(
            "lista1.txt", "lista2.txt", "lista3.txt", "lista4.txt", "lista5.txt"
    );

    public CargarElectoresView(/* Stage escenarioPrimario */) { // Ya no necesitamos el Stage aquí
        // this.escenarioPrimario = escenarioPrimario;
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
        
        // Opcional: mostrar el nombre del archivo seleccionado si es necesario
        // comboBoxArchivosPredefinidos.setOnAction(e -> {
        //     if (notificador != null) {
        //         // Podríamos notificar la selección si el presentador necesitara reaccionar a esto
        //     }
        // });

        botonCargarArchivo = new Button("Cargar Electores");
        botonCargarArchivo.setDisable(true); // Habilitar cuando se seleccione algo

        comboBoxArchivosPredefinidos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            botonCargarArchivo.setDisable(newVal == null);
            if (newVal != null) {
                mostrarNombreArchivoSeleccionado("Seleccionado: " + newVal);
            } else {
                mostrarNombreArchivoSeleccionado("");
            }
        });
        
        textoEstado = new Text(); // Para mensajes de estado

        botonCargarArchivo.setOnAction(e -> {
            if (notificador != null && comboBoxArchivosPredefinidos.getValue() != null) {
                notificador.alPulsarBotonCargarArchivo(); // Ya no pasamos el File
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
        // Podríamos usar una Label separada o actualizar el textoEstado temporalmente
        // Por ahora, no tenemos una label dedicada para la ruta, ya que es predefinido.
        // Si quieres mostrarlo, añade una Label y actualízala aquí.
        if (nombreArchivo.isEmpty()) {
            textoEstado.setText(""); // Limpiar si no hay nada seleccionado
        }
        // No hacemos mucho con esto por ahora, el ComboBox muestra la selección.
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
        // El botón regresar no se deshabilita generalmente
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
