package com.finalproject.presenter;

import com.finalproject.view.AdminMenuViewActions;
import com.finalproject.view.NotificadorMenuAdminAlPresentador;

public class AdminMenuPresenter implements NotificadorMenuAdminAlPresentador {

    private AdminMenuViewActions vista; // Aunque no la usemos mucho ahora, es bueno tenerla
    private AdminMenuNavegador navegador;

    public AdminMenuPresenter(AdminMenuViewActions vista, AdminMenuNavegador navegador) {
        this.vista = vista;
        this.navegador = navegador;
        this.vista.establecerNotificador(this); // El presentador se establece como notificador en la vista
    }

    @Override
    public void alPulsarBotonCargarElectores() {
        System.out.println("Presentador: Botón Cargar Electores pulsado.");
        if (navegador != null) navegador.navegarACargarElectores();
    }

    @Override
    public void alPulsarBotonCargarCandidatos() {
        System.out.println("Presentador: Botón Cargar Candidatos pulsado.");
        if (navegador != null) navegador.navegarACargarCandidatos();
    }

    @Override
    public void alPulsarBotonMostrarElectores() {
        System.out.println("Presentador: Botón Mostrar Electores pulsado.");
        if (navegador != null) navegador.navegarAMostrarElectores();
    }

    @Override
    public void alPulsarBotonMostrarCandidatos() {
        System.out.println("Presentador: Botón Mostrar Candidatos pulsado.");
        if (navegador != null) navegador.navegarAMostrarCandidatos();
    }

    @Override
    public void alPulsarBotonAbrirVotaciones() {
        System.out.println("Presentador: Botón Abrir Votaciones pulsado.");
        if (navegador != null) navegador.navegarAAbrirVotaciones();
    }

    @Override
    public void alPulsarBotonCerrarVotaciones() {
        System.out.println("Presentador: Botón Cerrar Votaciones pulsado.");
        if (navegador != null) navegador.navegarACerrarVotaciones();
    }

    @Override
    public void alPulsarBotonImprimirResultados() {
        System.out.println("Presentador: Botón Mostrar Resultados pulsado.");
        if (navegador != null) navegador.navegarAMostrarResultados();
    }

    @Override
    public void alPulsarBotonCerrarSesion() {
        System.out.println("Presentador: Botón Cerrar Sesión pulsado.");
        if (navegador != null) navegador.alPulsarBotonCerrarSesion();
    }
}
