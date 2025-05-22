package com.finalproject.presenter;

import com.finalproject.view.CargarCandidatosViewActions;
import com.finalproject.view.NotificadorCargarCandidatosAlPresentador;
import com.finalproject.model.Candidato;
import com.finalproject.model.ServicioCandidatos;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class CargarCandidatosPresenter implements NotificadorCargarCandidatosAlPresentador {
    private CargarCandidatosViewActions vista;
    private ServicioCandidatos servicioCandidatos;
    private NavegadorCargaCandidatos navegador;

    public CargarCandidatosPresenter(CargarCandidatosViewActions vista, ServicioCandidatos servicio, NavegadorCargaCandidatos navegador) {
        this.vista = vista;
        this.servicioCandidatos = servicio;
        this.navegador = navegador;
        this.vista.establecerNotificador(this);
        this.vista.limpiarMensajeEstado();
    }

    @Override
    public void alPulsarBotonCargarArchivo(String partido) {
        String nombreArchivo = vista.obtenerNombreArchivoSeleccionado(partido);
        if (nombreArchivo == null || nombreArchivo.isEmpty()) {
            vista.mostrarMensajeEstado("Por favor, seleccione un archivo para el partido " + partido, true);
            return;
        }

        vista.operacionEnProgreso(true);
        vista.limpiarMensajeEstado();

        String rutaRecurso = "/listas_candidatos/" + nombreArchivo;
        
        File archivoTemporal = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream(rutaRecurso);
            if (inputStream == null) {
                throw new IOException("No se pudo encontrar el recurso: " + rutaRecurso);
            }

            Path tempFile = Files.createTempFile("candidato_temp_", ".txt");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            archivoTemporal = tempFile.toFile();
            inputStream.close();

            List<Candidato> candidatosCargados = servicioCandidatos.cargarCandidatoDesdeArchivo(archivoTemporal);
            String mensaje = candidatosCargados.size() + " candidato(s) cargado(s) exitosamente";
            vista.mostrarMensajeEstado(mensaje, false);
            
            if (navegador != null) {
                navegador.cargaDeCandidatosCompletada(candidatosCargados, mensaje);
            }

        } catch (IOException e) {
            String errorMsg = "Error al leer el archivo '" + nombreArchivo + "': " + e.getMessage();
            vista.mostrarMensajeEstado(errorMsg, true);
            if (navegador != null) {
                navegador.cargaDeCandidatosFallida(errorMsg);
            }
        } finally {
            vista.operacionEnProgreso(false);
            if (archivoTemporal != null && archivoTemporal.exists()) {
                archivoTemporal.delete();
            }
        }
    }

    @Override
    public void alPulsarBotonRegresar() {
        if (navegador != null) {
            navegador.solicitarRegresoAlMenuAdmin();
        }
    }
} 