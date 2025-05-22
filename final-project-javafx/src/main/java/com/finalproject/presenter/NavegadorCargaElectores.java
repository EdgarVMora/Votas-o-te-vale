package com.finalproject.presenter;

import com.finalproject.model.Elector; // Asegúrate de importar tu clase Elector
import java.util.List;
 
public interface NavegadorCargaElectores {
    void cargaDeElectoresCompletada(List<Elector> electoresCargados, String mensajeExito);
    void cargaDeElectoresFallida(String mensajeError);
    void solicitarRegresoAlMenuAdmin(); // Renombrado para mejor claridad
}