package com.finalproject.view;

public interface AdminMenuViewActions {
    // Podríamos añadir métodos si el presentador necesita modificar la vista del menú,
    // por ejemplo, para habilitar/deshabilitar botones basados en algún estado.
    // Por ahora, puede estar vacía o tener un método genérico.
    void establecerNotificador(Object notificador); // Para pasar el notificador del presentador a la vista
}