package com.finalproject.presenter;

import com.finalproject.view.MostrarElectoresViewActions;
import com.finalproject.view.NotificadorMostrarElectoresAlPresentador;
import com.finalproject.model.Elector;
import java.util.List;

public class MostrarElectoresPresenter implements NotificadorMostrarElectoresAlPresentador {
    private MostrarElectoresViewActions vista;
    private List<Elector> listaElectores;
    private AdminMenuNavegador navegador;

    public MostrarElectoresPresenter(MostrarElectoresViewActions vista, List<Elector> listaElectores, AdminMenuNavegador navegador) {
        this.vista = vista;
        this.listaElectores = listaElectores;
        this.navegador = navegador;
        this.vista.establecerNotificador(this);
        
        if (listaElectores == null || listaElectores.isEmpty()) {
            vista.mostrarMensajeError("No hay electores registrados.");
        } else {
            vista.mostrarElectores(listaElectores);
        }
    }

    @Override
    public void alPulsarBotonRegresar() {
        if (navegador != null) {
            navegador.regresarAlMenuAdmin(); 
        }
    }
} 