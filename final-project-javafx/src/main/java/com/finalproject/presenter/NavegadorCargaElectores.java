package com.finalproject.presenter;

import com.finalproject.model.Elector;
import java.util.List;
 
public interface NavegadorCargaElectores {
    void cargaDeElectoresCompletada(List<Elector> electoresCargados, String mensajeExito);
    void cargaDeElectoresFallida(String mensajeError);
    void solicitarRegresoAlMenuAdmin(); 
}