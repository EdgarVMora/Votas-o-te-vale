package com.finalproject.view;

// import java.io.File; // El archivo se resolverá en el presentador a partir del nombre

public interface NotificadorCargarElectoresAlPresentador {
    // alPulsarBotonSeleccionarArchivo(); // Ya no es necesario si usamos ComboBox o similar
    void alPulsarBotonCargarArchivo(); // Ya no pasamos el File desde aquí
    void alPulsarBotonRegresar(); // Nuevo: para el botón de regreso
}
