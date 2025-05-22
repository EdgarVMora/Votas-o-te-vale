package com.finalproject.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AdminMenuView implements AdminMenuViewActions {

    private VBox panelPrincipal;
    private NotificadorMenuAdminAlPresentador notificador;

    public AdminMenuView() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelPrincipal = new VBox(15); 
        panelPrincipal.setAlignment(Pos.CENTER);
        panelPrincipal.setPadding(new Insets(25));

        Text tituloMenu = new Text("Menú del Administrador");
        tituloMenu.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Button botonCargarElectores = new Button("Cargar electores");
        Button botonCargarCandidatos = new Button("Cargar candidatos");
        Button botonMostrarElectores = new Button("Mostrar electores");
        Button botonMostrarCandidatos = new Button("Mostrar candidatos");
        Button botonAbrirVotaciones = new Button("Abrir votaciones");
        Button botonCerrarVotaciones = new Button("Cerrar votaciones");
        Button botonImprimirResultados = new Button("Imprimir resultados");
        Button botonCerrarSesion = new Button("Cerrar Sesión");

        botonCargarElectores.setOnAction(e -> { if (notificador != null) notificador.alPulsarBotonCargarElectores(); });
        botonCargarCandidatos.setOnAction(e -> { if (notificador != null) notificador.alPulsarBotonCargarCandidatos(); });
        botonMostrarElectores.setOnAction(e -> { if (notificador != null) notificador.alPulsarBotonMostrarElectores(); });
        botonMostrarCandidatos.setOnAction(e -> { if (notificador != null) notificador.alPulsarBotonMostrarCandidatos(); });
        botonAbrirVotaciones.setOnAction(e -> { if (notificador != null) notificador.alPulsarBotonAbrirVotaciones(); });
        botonCerrarVotaciones.setOnAction(e -> { if (notificador != null) notificador.alPulsarBotonCerrarVotaciones(); });
        botonImprimirResultados.setOnAction(e -> { if (notificador != null) notificador.alPulsarBotonImprimirResultados(); });
        botonCerrarSesion.setOnAction(e -> { if (notificador != null) notificador.alPulsarBotonCerrarSesion(); });
        
        panelPrincipal.getChildren().addAll(
            tituloMenu,
            botonCargarElectores,
            botonCargarCandidatos,
            botonMostrarElectores,
            botonMostrarCandidatos,
            botonAbrirVotaciones,
            botonCerrarVotaciones,
            botonImprimirResultados,
            new Text(" "), 
            botonCerrarSesion
        );
    }

    public Parent obtenerNodoVista() {
        return panelPrincipal;
    }

    @Override
    public void establecerNotificador(Object notificadorObj) {
        if (notificadorObj instanceof NotificadorMenuAdminAlPresentador) {
            this.notificador = (NotificadorMenuAdminAlPresentador) notificadorObj;
        } else {
            System.err.println("Error: El notificador para AdminMenuView no es del tipo esperado.");
            
        }
    }
}
