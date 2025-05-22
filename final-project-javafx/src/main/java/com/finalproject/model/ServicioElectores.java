package com.finalproject.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServicioElectores {

    public List<Elector> cargarElectoresDesdeArchivo(File archivo) throws IOException {
        List<Elector> listaElectores = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String lineaNombreCompleto;
            while ((lineaNombreCompleto = br.readLine()) != null) {
                String lineaCorreo = br.readLine();
                String lineaFechaStr = br.readLine();

                if (lineaCorreo == null || lineaFechaStr == null) {
                    System.err.println("Advertencia: Formato de archivo incorrecto o datos incompletos. Nombre leído: " + lineaNombreCompleto);
                    break; 
                }
                
                lineaNombreCompleto = lineaNombreCompleto.trim();
                lineaCorreo = lineaCorreo.trim();
                lineaFechaStr = lineaFechaStr.trim();

                if (lineaNombreCompleto.isEmpty() || lineaCorreo.isEmpty() || lineaFechaStr.isEmpty()) {
                    System.err.println("Advertencia: Se encontró una entrada de elector con campos vacíos. Se omitirá.");
                    continue;
                }

                StringTokenizer stNombre = new StringTokenizer(lineaNombreCompleto);
                String nombre = stNombre.hasMoreTokens() ? stNombre.nextToken() : "";
                String apellidoPaterno = stNombre.hasMoreTokens() ? stNombre.nextToken() : "";
                String apellidoMaterno = "";
                
                while (stNombre.hasMoreTokens()) {
                    if (!apellidoMaterno.isEmpty()) {
                        apellidoMaterno += " ";
                    }
                    apellidoMaterno += stNombre.nextToken();
                }

                String[] partesFecha = lineaFechaStr.split("/");
                Fecha fechaNacimiento = null;
                if (partesFecha.length == 3) {
                    try {
                        int dia = Integer.parseInt(partesFecha[0]);
                        int mes = Integer.parseInt(partesFecha[1]);
                        int anio = Integer.parseInt(partesFecha[2]);
                        fechaNacimiento = new Fecha(dia, mes, anio);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear la fecha: " + lineaFechaStr + " para el elector: " + lineaNombreCompleto);
                        continue;
                    }
                } else {
                    System.err.println("Formato de fecha incorrecto: " + lineaFechaStr + " para el elector: " + lineaNombreCompleto);
                    continue;
                }

                if (fechaNacimiento != null) {
                    Elector elector = new Elector(nombre, apellidoPaterno, apellidoMaterno, lineaCorreo, fechaNacimiento);
                    listaElectores.add(elector);
                }
            }
        }
        
        return listaElectores;
    }
}