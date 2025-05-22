package com.finalproject.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CargarCandidatosView implements CargarCandidatosViewActions {
    private VBox panelPrincipal;
    private Map<String, ComboBox<String>> combosPorPartido;
    private Text mensajeEstado;
    private Object notificador;

    private final Map<String, String[]> ARCHIVOS_POR_PARTIDO = new HashMap<String, String[]>() {{
        put("Movimiento del Wifi gratis", new String[]{"candidatoUno.txt"});
        put("Alianza por los descansos eternos", new String[]{"candidatoDos.txt"});
        put("Anti Lunes Revolucionario", new String[]{"candidatoTres.txt"});
    }};

    public CargarCandidatosView() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelPrincipal = new VBox(15);
        panelPrincipal.setPadding(new Insets(20));

        Text titulo = new Text("Cargar Candidatos por Partido");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        combosPorPartido = new HashMap<>();
        
        // Crear sección para cada partido
        for (String partido : ARCHIVOS_POR_PARTIDO.keySet()) {
            VBox seccionPartido = new VBox(5);
            
            Text nombrePartido = new Text(partido);
            nombrePartido.setStyle("-fx-font-weight: bold;");
            
            ComboBox<String> comboArchivos = new ComboBox<>(
                FXCollections.observableArrayList(ARCHIVOS_POR_PARTIDO.get(partido))
            );
            comboArchivos.setPromptText("Selecciona un archivo");
            
            Button botonCargar = new Button("Cargar Candidato");
            botonCargar.setDisable(true);
            
            comboArchivos.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                botonCargar.setDisable(newVal == null);
            });
            
            botonCargar.setOnAction(e -> {
                if (notificador instanceof NotificadorCargarCandidatosAlPresentador) {
                    ((NotificadorCargarCandidatosAlPresentador) notificador)
                        .alPulsarBotonCargarArchivo(partido);
                }
            });
            
            seccionPartido.getChildren().addAll(nombrePartido, comboArchivos, botonCargar);
            combosPorPartido.put(partido, comboArchivos);
            panelPrincipal.getChildren().add(seccionPartido);
        }

        mensajeEstado = new Text();
        mensajeEstado.setStyle("-fx-fill: red;");

        Button botonRegresar = new Button("Regresar al Menú");
        botonRegresar.setOnAction(e -> {
            if (notificador instanceof NotificadorCargarCandidatosAlPresentador) {
                ((NotificadorCargarCandidatosAlPresentador) notificador).alPulsarBotonRegresar();
            }
        });

        panelPrincipal.getChildren().addAll(mensajeEstado, botonRegresar);
    }

    @Override
    public Parent obtenerNodoVista() {
        return panelPrincipal;
    }

    @Override
    public void mostrarMensajeEstado(String mensaje, boolean esError) {
        mensajeEstado.setText(mensaje);
        mensajeEstado.setStyle(esError ? "-fx-fill: red;" : "-fx-fill: green;");
    }

    @Override
    public void limpiarMensajeEstado() {
        mensajeEstado.setText("");
    }

    @Override
    public void operacionEnProgreso(boolean enProgreso) {
        for (ComboBox<String> combo : combosPorPartido.values()) {
            combo.setDisable(enProgreso);
        }
    }

    @Override
    public String obtenerNombreArchivoSeleccionado(String partido) {
        ComboBox<String> combo = combosPorPartido.get(partido);
        return combo != null ? combo.getValue() : null;
    }

    @Override
    public void establecerNotificador(Object notificador) {
        this.notificador = notificador;
    }
} 