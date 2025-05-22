package com.finalproject.presenter;

public interface LoginNavegador {
    void navegarAPantallaAdmin();
    void mostrarMensajeLoginExitoso(String titulo, String mensaje);
    void mostrarMensajeLoginFallido(String titulo, String mensaje);
}
