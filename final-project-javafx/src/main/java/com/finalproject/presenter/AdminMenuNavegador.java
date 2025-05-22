package com.finalproject.presenter;

public interface AdminMenuNavegador {
    void navegarACargarElectores();
    void navegarACargarCandidatos();
    void navegarAMostrarElectores();
    void navegarAMostrarCandidatos();
    void navegarAAbrirVotaciones();
    void navegarACerrarVotaciones();
    void navegarAImprimirResultados();
    void navegarARegistro(); // O la pantalla que corresponda a "Registro"
    void cerrarSesionYMostrarLogin();
    void regresarAlMenuAdmin(); // Nuevo método para regresar al menú
}
