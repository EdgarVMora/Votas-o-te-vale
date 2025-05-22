package com.finalproject.presenter;

import com.finalproject.view.MostrarResultadosViewActions;
import com.finalproject.view.NotificadorMostrarResultadosAlPresentador;
import com.finalproject.model.ServicioVotos;
import com.finalproject.model.EstadoVotaciones;
import com.finalproject.model.Candidato;
import java.util.Map;

public class MostrarResultadosPresenter implements NotificadorMostrarResultadosAlPresentador {
    private MostrarResultadosViewActions vista;
    private AdminMenuNavegador navegador;
    private ServicioVotos servicioVotos;

    public MostrarResultadosPresenter(MostrarResultadosViewActions vista, AdminMenuNavegador navegador) {
        this.vista = vista;
        this.navegador = navegador;
        this.servicioVotos = ServicioVotos.obtenerInstancia();
        
        this.vista.establecerNotificador(this);
        mostrarResultados();
    }

    private void mostrarResultados() {
        if (!EstadoVotaciones.obtenerInstancia().estanCerradas()) {
            vista.mostrarMensajeError("Las votaciones aún no han sido cerradas. No se pueden mostrar los resultados.");
            return;
        }

        Map<Candidato, Integer> votosPorCandidato = servicioVotos.obtenerConteoVotos();
        int votosNulos = servicioVotos.obtenerVotosNulos();

        if (votosPorCandidato.isEmpty() && votosNulos == 0) {
            vista.mostrarMensajeError("No hay votos registrados.");
            return;
        }

        vista.mostrarResultados(votosPorCandidato, votosNulos);
    }

    @Override
    public void alPulsarBotonRegresar() {
        if (navegador != null) {
            navegador.regresarAlMenuAdmin();
        }
    }
} 