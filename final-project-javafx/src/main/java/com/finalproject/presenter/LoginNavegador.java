package com.finalproject.presenter;

import com.finalproject.model.Elector;

public interface LoginNavegador {
    void navegarAPantallaAdmin();
    void navegarAPantallaElector(Elector elector);
    void mostrarMensajeLoginExitoso(String titulo, String mensaje);
    void mostrarMensajeLoginFallido(String titulo, String mensaje);
}
