package com.finalproject.view;

import com.finalproject.model.Candidato;
import com.finalproject.model.Elector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.List;

public class VotacionView implements VotacionViewActions {
    private VBox panelPrincipal;
    private ComboBox<Candidato> comboBoxCandidatos;
    private Button botonVotar;
    private Button botonCerrarSesion;
    private Text mensajeEstado;
    private NotificadorVotacionAlPresentador notificador;
    private Elector electorActual;

    public VotacionView(Elector elector) {
        this.electorActual = elector;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelPrincipal = new VBox(15);
        panelPrincipal.setAlignment(Pos.CENTER);
        panelPrincipal.setPadding(new Insets(25));

        Text titulo = new Text("Sistema de Votación");
        titulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Text bienvenida = new Text("Bienvenido " + electorActual.getNombre() + " " + 
                                 electorActual.getApellidoPaterno());
        bienvenida.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        Label instrucciones = new Label("Seleccione el candidato por el que desea votar:");
        comboBoxCandidatos = new ComboBox<>();
        comboBoxCandidatos.setPromptText("Seleccione un candidato");

        botonVotar = new Button("Registrar Voto");
        botonVotar.setOnAction(e -> {
            if (notificador != null) {
                notificador.alPulsarBotonVotar();
            }
        });

        botonCerrarSesion = new Button("Cerrar Sesión");
        botonCerrarSesion.setOnAction(e -> {
            if (notificador != null) {
                notificador.alPulsarBotonCerrarSesion();
            }
        });

        mensajeEstado = new Text();
        mensajeEstado.setStyle("-fx-fill: red;");

        panelPrincipal.getChildren().addAll(
            titulo,
            bienvenida,
            new Separator(),
            instrucciones,
            comboBoxCandidatos,
            botonVotar,
            mensajeEstado,
            new Separator(),
            botonCerrarSesion
        );
    }

    @Override
    public Parent obtenerNodoVista() {
        return panelPrincipal;
    }

    @Override
    public void establecerCandidatos(List<Candidato> candidatos) {
        comboBoxCandidatos.getItems().clear();
        comboBoxCandidatos.getItems().addAll(candidatos);
    }

    @Override
    public Candidato obtenerCandidatoSeleccionado() {
        return comboBoxCandidatos.getValue();
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        mensajeEstado.setText(mensaje);
    }

    @Override
    public void mostrarMensajeExito(String mensaje) {
        mensajeEstado.setStyle("-fx-fill: green;");
        mensajeEstado.setText(mensaje);
    }

    @Override
    public void establecerNotificador(Object notificador) {
        if (notificador instanceof NotificadorVotacionAlPresentador) {
            this.notificador = (NotificadorVotacionAlPresentador) notificador;
        }
    }

    @Override
    public void deshabilitarVotacion() {
        comboBoxCandidatos.setDisable(true);
        botonVotar.setDisable(true);
    }
} 