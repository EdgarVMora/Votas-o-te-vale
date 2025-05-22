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

                // 1. Separar nombre completo
                StringTokenizer stNombre = new StringTokenizer(lineaNombreCompleto);
                String nombre = stNombre.hasMoreTokens() ? stNombre.nextToken() : "";
                String apellidoPaterno = stNombre.hasMoreTokens() ? stNombre.nextToken() : "";
                String apellidoMaterno = ""; // Inicializar
                // Concatenar el resto de tokens al apellido materno si existen más de 3 palabras
                while(stNombre.hasMoreTokens()){
                    apellidoMaterno += (apellidoMaterno.isEmpty() ? "" : " ") + stNombre.nextToken();
                }
                if (apellidoMaterno.isEmpty() && !apellidoPaterno.isEmpty() && stNombre.countTokens() == 0 && apellidoPaterno.equals(nombre)) { 
                    // Si solo se encontró un token como nombre y apellido paterno, y no hay más, es probable que solo sea un nombre.
                    // Esto es un caso simple, se podría mejorar la lógica de parseo de nombres.
                    // Si solo hay dos "palabras" en lineaNombreCompleto, y la segunda se asignó a apellidoPaterno,
                    // y si no hay más tokens, es posible que apellidoMaterno deba quedar vacío.
                    // Para el caso de "Nombre ApellidoPaterno ApellidoMaterno", esto funciona.
                    // Si es "Nombre ApellidoPaterno", apellidoMaterno quedará vacío.
                }


                // 2. Parsear la fecha
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
                        // Omitir este elector o manejar el error como prefieras
                        continue;
                    }
                } else {
                    System.err.println("Formato de fecha incorrecto: " + lineaFechaStr + " para el elector: " + lineaNombreCompleto);
                    // Omitir este elector
                    continue;
                }

                // 3. Crear el objeto Elector
                if (fechaNacimiento != null) { // Solo crear si la fecha fue parseada correctamente
                    Elector elector = new Elector(nombre, apellidoPaterno, apellidoMaterno, lineaCorreo, fechaNacimiento);
                    listaElectores.add(elector);
                }
            }
        }
        // La IOException se propagará si ocurre al abrir/leer el archivo
        
        return listaElectores;
    }
}