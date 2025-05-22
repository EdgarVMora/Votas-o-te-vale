package com.finalproject.presenter;

import com.finalproject.view.MostrarCandidatosViewActions;
import com.finalproject.view.NotificadorMostrarCandidatosAlPresentador;
import java.util.List;
import com.finalproject.model.Candidato;

public class MostrarCandidatosPresenter implements NotificadorMostrarCandidatosAlPresentador {
    private MostrarCandidatosViewActions vista;
    private AdminMenuNavegador navegador;

    public MostrarCandidatosPresenter(MostrarCandidatosViewActions vista, List<Candidato> candidatos, AdminMenuNavegador navegador) {
        this.vista = vista;
        this.navegador = navegador;
        this.vista.establecerNotificador(this);
    }

    @Override
    public void alPulsarBotonRegresar() {
        if (navegador != null) {
            navegador.regresarAlMenuAdmin();
        }
    }
} 