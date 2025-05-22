package com.finalproject.presenter;

import com.finalproject.model.Elector;
import com.finalproject.model.ServicioElectores; // Asumiendo que ServicioElectores sigue igual
import com.finalproject.view.CargarElectoresViewActions;
import com.finalproject.view.NotificadorCargarElectoresAlPresentador;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class CargarElectoresPresenter implements NotificadorCargarElectoresAlPresentador {

    private CargarElectoresViewActions vista;
    private ServicioElectores servicioElectores;
    private NavegadorCargaElectores navegador;
    // private List<Elector> electoresCargadosTemporalmente; // Ya no es necesario aquí si se pasa directo al navegador

    public CargarElectoresPresenter(CargarElectoresViewActions vista, ServicioElectores servicio, NavegadorCargaElectores navegador) {
        this.vista = vista;
        this.servicioElectores = servicio;
        this.navegador = navegador;
        this.vista.establecerNotificador(this);
        this.vista.limpiarMensajeEstado();
    }

    // alPulsarBotonSeleccionarArchivo(); // Ya no existe en el notificador

    @Override
    public void alPulsarBotonCargarArchivo() {
        String nombreArchivoPredefinido = vista.obtenerNombreArchivoPredefinidoSeleccionado();
        if (nombreArchivoPredefinido == null || nombreArchivoPredefinido.isEmpty()) {
            vista.mostrarMensajeEstado("Por favor, seleccione un archivo de la lista.", true);
            return;
        }

        vista.operacionEnProgreso(true);
        vista.limpiarMensajeEstado();

        // Construir la ruta al recurso
        String rutaRecurso = "/listas_electores/" + nombreArchivoPredefinido; // Asumiendo que están en la raíz de resources/listas_electores
        
        File archivoTemporal = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream(rutaRecurso);
            if (inputStream == null) {
                throw new IOException("No se pudo encontrar el recurso: " + rutaRecurso + ". Asegúrate que esté en src/main/resources/listas_electores/");
            }

            // Crear un archivo temporal para pasarlo a ServicioElectores si este espera un File
            // Si ServicioElectores pudiera tomar un InputStream, sería más directo.
            Path tempFile = Files.createTempFile("electores_temp_", ".txt");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            archivoTemporal = tempFile.toFile();
            inputStream.close(); // Cerrar el stream

            List<Elector> electoresCargados = servicioElectores.cargarElectoresDesdeArchivo(archivoTemporal);
            String mensaje = electoresCargados.size() + " electores cargados exitosamente desde " + nombreArchivoPredefinido;
            vista.mostrarMensajeEstado(mensaje, false);
            
            if (navegador != null) {
                navegador.cargaDeElectoresCompletada(electoresCargados, mensaje);
            }

        } catch (IOException e) {
            String errorMsg = "Error al leer el archivo de recurso '" + nombreArchivoPredefinido + "': " + e.getMessage();
            vista.mostrarMensajeEstado(errorMsg, true);
            System.err.println(errorMsg);
            e.printStackTrace();
            if (navegador != null) {
                navegador.cargaDeElectoresFallida(errorMsg);
            }
        } finally {
            vista.operacionEnProgreso(false);
            if (archivoTemporal != null) {
                archivoTemporal.delete(); // Limpiar el archivo temporal
            }
        }
    }
    
    @Override
    public void alPulsarBotonRegresar() {
        vista.limpiarMensajeEstado();
        if (navegador != null) {
            navegador.solicitarRegresoAlMenuAdmin();
        }
    }
}
