package com.finalproject.presenter;

import com.finalproject.model.Candidato;
import java.util.List;
 
public interface NavegadorCargaCandidatos {
    void cargaDeCandidatosCompletada(List<Candidato> candidatosCargados, String mensajeExito);
    void cargaDeCandidatosFallida(String mensajeError);
    void solicitarRegresoAlMenuAdmin();
} 