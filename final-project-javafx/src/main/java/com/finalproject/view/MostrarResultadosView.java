package com.finalproject.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;
import com.finalproject.model.Candidato;
import java.util.Map;

public class MostrarResultadosView implements MostrarResultadosViewActions {
    private VBox panelPrincipal;
    private TableView<ResultadoVotacion> tablaResultados;
    private Text mensajeError;
    private Object notificador;

    public MostrarResultadosView() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelPrincipal = new VBox(15);
        panelPrincipal.setPadding(new Insets(20));

        Text titulo = new Text("Resultados de las Votaciones");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        tablaResultados = new TableView<>();

        
        TableColumn<ResultadoVotacion, String> columnaCandidato = new TableColumn<>("Candidato");
        columnaCandidato.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getNombreCandidato()));

        
        TableColumn<ResultadoVotacion, String> columnaPartido = new TableColumn<>("Partido");
        columnaPartido.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getPartido()));

        
        TableColumn<ResultadoVotacion, String> columnaVotos = new TableColumn<>("Votos");
        columnaVotos.setCellValueFactory(cellData -> 
            new SimpleStringProperty(String.valueOf(cellData.getValue().getVotos())));

        tablaResultados.getColumns().addAll(columnaCandidato, columnaPartido, columnaVotos);

        mensajeError = new Text();
        mensajeError.setStyle("-fx-fill: red;");

        Button botonRegresar = new Button("Regresar al Menú");
        botonRegresar.setOnAction(e -> {
            if (notificador instanceof NotificadorMostrarResultadosAlPresentador) {
                ((NotificadorMostrarResultadosAlPresentador) notificador).alPulsarBotonRegresar();
            }
        });

        panelPrincipal.getChildren().addAll(titulo, tablaResultados, mensajeError, botonRegresar);
    }

    @Override
    public Parent obtenerNodoVista() {
        return panelPrincipal;
    }

    @Override
    public void mostrarResultados(Map<Candidato, Integer> votosPorCandidato, int votosNulos) {
        tablaResultados.getItems().clear();
        
        
        for (Map.Entry<Candidato, Integer> entry : votosPorCandidato.entrySet()) {
            Candidato candidato = entry.getKey();
            ResultadoVotacion resultado = new ResultadoVotacion(
                candidato.getNombre() + " " + candidato.getApellidoPaterno() + " " + candidato.getApellidoMaterno(),
                candidato.getPartido(),
                entry.getValue()
            );
            tablaResultados.getItems().add(resultado);
        }

        
        ResultadoVotacion votosNulosResultado = new ResultadoVotacion(
            "Votos Nulos",
            "N/A",
            votosNulos
        );
        tablaResultados.getItems().add(votosNulosResultado);

        
        tablaResultados.getItems().sort((r1, r2) -> r2.getVotos() - r1.getVotos());
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        mensajeError.setText(mensaje);
    }

    @Override
    public void establecerNotificador(Object notificador) {
        this.notificador = notificador;
    }

    
    public static class ResultadoVotacion {
        private final String nombreCandidato;
        private final String partido;
        private final int votos;

        public ResultadoVotacion(String nombreCandidato, String partido, int votos) {
            this.nombreCandidato = nombreCandidato;
            this.partido = partido;
            this.votos = votos;
        }

        public String getNombreCandidato() {
            return nombreCandidato;
        }

        public String getPartido() {
            return partido;
        }

        public int getVotos() {
            return votos;
        }
    }
} 