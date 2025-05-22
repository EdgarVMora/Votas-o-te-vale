package com.finalproject;

import com.finalproject.presenter.AdminMenuNavegador;
import com.finalproject.presenter.AdminMenuPresenter;
import com.finalproject.view.AdminMenuView;


import com.finalproject.model.Elector; 
import com.finalproject.model.ServicioElectores; 
import com.finalproject.presenter.CargarElectoresPresenter;
import com.finalproject.presenter.NavegadorCargaElectores; 
import com.finalproject.view.CargarElectoresView;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList; 
import java.util.List;     
import javafx.scene.control.Alert;
import com.finalproject.presenter.LoginNavegador;
import com.finalproject.presenter.LoginPresenter;
import com.finalproject.view.LoginView;
import com.finalproject.view.MostrarElectoresView;
import com.finalproject.presenter.MostrarElectoresPresenter;
import com.finalproject.model.Candidato;
import com.finalproject.model.ServicioCandidatos;
import com.finalproject.presenter.CargarCandidatosPresenter;
import com.finalproject.presenter.NavegadorCargaCandidatos;
import com.finalproject.view.CargarCandidatosView;
import com.finalproject.view.MostrarCandidatosView;
import com.finalproject.presenter.MostrarCandidatosPresenter;
import com.finalproject.model.EstadoVotaciones;
import javafx.scene.control.ButtonType;
import com.finalproject.view.VotacionView;
import com.finalproject.presenter.VotacionPresenter;
import com.finalproject.presenter.VotacionNavegador;
import com.finalproject.view.MostrarResultadosView;
import com.finalproject.presenter.MostrarResultadosPresenter;

public class App extends Application implements LoginNavegador, AdminMenuNavegador, NavegadorCargaElectores, NavegadorCargaCandidatos, VotacionNavegador {

    private Stage escenarioPrincipal;
    private List<Elector> listaGlobalDeElectores; 
    private List<Candidato> listaGlobalDeCandidatos;
    private ServicioElectores servicioElectoresGlobal; 
    private ServicioCandidatos servicioCandidatosGlobal;

    @Override
    public void start(Stage primaryStage) {
        this.escenarioPrincipal = primaryStage;
        this.escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP");
        this.listaGlobalDeElectores = new ArrayList<>();
        this.listaGlobalDeCandidatos = new ArrayList<>();
        this.servicioElectoresGlobal = new ServicioElectores();
        this.servicioCandidatosGlobal = ServicioCandidatos.obtenerInstancia();
        mostrarLogin();
    }

    private void mostrarLogin() {
        LoginView vistaLogin = new LoginView();
        new LoginPresenter(vistaLogin, this, this.listaGlobalDeElectores);
        Scene escena = new Scene(vistaLogin.obtenerNodoVista(), 400, 300);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    private void mostrarMenuAdmin() {
        AdminMenuView vistaMenuAdmin = new AdminMenuView();
        new AdminMenuPresenter(vistaMenuAdmin, this);
        Scene escenaMenuAdmin = new Scene(vistaMenuAdmin.obtenerNodoVista(), 450, 550);
        escenarioPrincipal.setScene(escenaMenuAdmin);
        escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP - Panel de Administrador");
    }
    
    private void mostrarPantallaCargarElectores() {
        
        CargarElectoresView vistaCarga = new CargarElectoresView();
       
        new CargarElectoresPresenter(vistaCarga, servicioElectoresGlobal, this);
        
        Scene escenaCarga = new Scene(vistaCarga.obtenerNodoVista(), 500, 400); 
        escenarioPrincipal.setScene(escenaCarga);
        escenarioPrincipal.setTitle("Cargar Electores");
    }

    private void mostrarPantallaCargarCandidatos() {
        CargarCandidatosView vistaCarga = new CargarCandidatosView();
        new CargarCandidatosPresenter(vistaCarga, servicioCandidatosGlobal, this);
        
        Scene escenaCarga = new Scene(vistaCarga.obtenerNodoVista(), 500, 400);
        escenarioPrincipal.setScene(escenaCarga);
        escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP - Cargar Candidatos");
    }

    
    @Override
    public void navegarAPantallaAdmin() {
        mostrarMenuAdmin(); 
    }
    @Override
    public void mostrarMensajeLoginExitoso(String titulo, String mensaje) {
        mostrarAlerta(Alert.AlertType.INFORMATION, titulo, mensaje);
    }
    @Override
    public void mostrarMensajeLoginFallido(String titulo, String mensaje) {
        mostrarAlerta(Alert.AlertType.ERROR, titulo, mensaje);
    }

    
    @Override
    public void navegarACargarElectores() {
        mostrarPantallaCargarElectores(); 
    }
    @Override
    public void navegarACargarCandidatos() {
        mostrarPantallaCargarCandidatos();
    }
    @Override
    public void navegarAMostrarElectores() {
        MostrarElectoresView vistaMostrar = new MostrarElectoresView();
        new MostrarElectoresPresenter(vistaMostrar, this.listaGlobalDeElectores, this);
        
        Scene escenaMostrar = new Scene(vistaMostrar.obtenerNodoVista(), 1000, 600);
        escenarioPrincipal.setScene(escenaMostrar);
        escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP - Lista de Electores");
    }
    @Override
    public void navegarAMostrarCandidatos() {
        MostrarCandidatosView vistaMostrar = new MostrarCandidatosView(this.listaGlobalDeCandidatos);
        new MostrarCandidatosPresenter(vistaMostrar, this.listaGlobalDeCandidatos, this);
        
        Scene escenaMostrar = new Scene(vistaMostrar.obtenerNodoVista(), 800, 600);
        escenarioPrincipal.setScene(escenaMostrar);
        escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP - Lista de Candidatos");
    }
    @Override
    public void regresarAlMenuAdmin() {
        mostrarMenuAdmin();
    }
    @Override
    public void navegarAAbrirVotaciones() {
        EstadoVotaciones estadoVotaciones = EstadoVotaciones.obtenerInstancia();
        
        
        if (estadoVotaciones.estanIniciadas()) {
            mostrarAlerta(Alert.AlertType.WARNING, 
                "Votaciones en Progreso", 
                "Las votaciones ya están iniciadas.");
            return;
        }

        
        if (listaGlobalDeCandidatos.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, 
                "Error al Iniciar Votaciones", 
                "No hay candidatos registrados. Por favor, cargue candidatos antes de iniciar las votaciones.");
            return;
        }

        if (listaGlobalDeElectores.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, 
                "Error al Iniciar Votaciones", 
                "No hay electores registrados. Por favor, cargue electores antes de iniciar las votaciones.");
            return;
        }

        
        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacion.setTitle("Iniciar Votaciones");
        alertaConfirmacion.setHeaderText(null);
        alertaConfirmacion.setContentText(
            "¿Está seguro de iniciar las votaciones?\n\n" +
            "Candidatos registrados: " + listaGlobalDeCandidatos.size() + "\n" +
            "Electores registrados: " + listaGlobalDeElectores.size() + "\n\n" +
            "Una vez iniciadas, los electores podrán acceder al sistema de votación."
        );

        alertaConfirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                estadoVotaciones.iniciarVotaciones();
                mostrarAlerta(Alert.AlertType.INFORMATION, 
                    "Votaciones Iniciadas", 
                    "Las votaciones han sido iniciadas exitosamente.\n" +
                    "Los electores ahora pueden acceder al sistema de votación con sus credenciales.");
            }
        });
    }

    @Override
    public void navegarACerrarVotaciones() {
        EstadoVotaciones estadoVotaciones = EstadoVotaciones.obtenerInstancia();
        
        if (!estadoVotaciones.estanIniciadas()) {
            mostrarAlerta(Alert.AlertType.WARNING, 
                "Votaciones no Iniciadas", 
                "Las votaciones no están iniciadas.");
            return;
        }

        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacion.setTitle("Cerrar Votaciones");
        alertaConfirmacion.setHeaderText(null);
        alertaConfirmacion.setContentText(
            "¿Está seguro de cerrar las votaciones?\n\n" +
            "Esta acción no se puede deshacer."
        );

        alertaConfirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                estadoVotaciones.cerrarVotaciones();
                mostrarAlerta(Alert.AlertType.INFORMATION, 
                    "Votaciones Cerradas", 
                    "Las votaciones han sido cerradas exitosamente.");
            }
        });
    }

    @Override
    public void alPulsarBotonCerrarSesion() {
        mostrarLogin();
    }

    
    @Override
    public void cargaDeElectoresCompletada(List<Elector> electoresCargados, String mensajeExito) {
        this.listaGlobalDeElectores.clear(); 
        this.listaGlobalDeElectores.addAll(electoresCargados); 
        mostrarAlerta(Alert.AlertType.INFORMATION, "Carga Exitosa", 
            mensajeExito + "\nTotal en memoria: " + this.listaGlobalDeElectores.size());
        
        mostrarMenuAdmin();
    }

    @Override
    public void cargaDeElectoresFallida(String mensajeError) {
        mostrarAlerta(Alert.AlertType.ERROR, "Error de Carga", mensajeError);
        
    }
    
    @Override
    public void solicitarRegresoAlMenuAdmin() {
        
        mostrarMenuAdmin();
    }

    
    @Override
    public void cargaDeCandidatosCompletada(List<Candidato> candidatosCargados, String mensajeExito) {
        this.listaGlobalDeCandidatos = this.servicioCandidatosGlobal.obtenerCandidatosRegistrados();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Carga Exitosa", 
            mensajeExito + "\nTotal de candidatos en memoria: " + this.listaGlobalDeCandidatos.size());
        mostrarMenuAdmin();
    }

    @Override
    public void cargaDeCandidatosFallida(String mensajeError) {
        mostrarAlerta(Alert.AlertType.ERROR, "Error de Carga", mensajeError);
    }

   
    private void mostrarAlerta(Alert.AlertType tipoAlerta, String titulo, String mensaje) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @Override
    public void navegarAPantallaElector(Elector elector) {
        VotacionView vistaVotacion = new VotacionView(elector);
        new VotacionPresenter(vistaVotacion, elector, this.listaGlobalDeCandidatos, this);
        
        Scene escenaVotacion = new Scene(vistaVotacion.obtenerNodoVista(), 500, 400);
        escenarioPrincipal.setScene(escenaVotacion);
        escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP - Sistema de Votación");
    }

    
    @Override
    public void cerrarSesion() {
        mostrarLogin();
    }

    private void mostrarResultados() {
        MostrarResultadosView vistaResultados = new MostrarResultadosView();
        new MostrarResultadosPresenter(vistaResultados, this);
        
        Scene escenaResultados = new Scene(vistaResultados.obtenerNodoVista(), 800, 600);
        escenarioPrincipal.setScene(escenaResultados);
        escenarioPrincipal.setTitle("VOTACIONES FFC - BUAP - Resultados de la Votación");
    }

    @Override
    public void navegarAMostrarResultados() {
        mostrarResultados();
    }

    public static void main(String[] args) {
        launch(args);
    }
}