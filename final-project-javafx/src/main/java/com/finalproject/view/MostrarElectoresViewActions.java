package com.finalproject.view;

import javafx.scene.Parent;
import com.finalproject.model.Elector;
import java.util.List;

public interface MostrarElectoresViewActions {
    Parent obtenerNodoVista();
    void mostrarElectores(List<Elector> electores);
    void establecerNotificador(Object notificador);
    void mostrarMensajeError(String mensaje);
} 