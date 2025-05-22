package com.finalproject.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCandidatos {
    
    public List<Candidato> cargarCandidatoDesdeArchivo(File archivo) throws IOException {
        List<Candidato> candidatos = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String lineaNombreCompleto;
            while ((lineaNombreCompleto = br.readLine()) != null) {
                String lineaPartido = br.readLine();
                
                if (lineaPartido == null) {
                    System.err.println("Advertencia: Formato de archivo incorrecto. Nombre leído: " + lineaNombreCompleto);
                    break;
                }
                
                lineaNombreCompleto = lineaNombreCompleto.trim();
                lineaPartido = lineaPartido.trim();

                if (lineaNombreCompleto.isEmpty() || lineaPartido.isEmpty()) {
                    System.err.println("Advertencia: Se encontró una entrada de candidato con campos vacíos. Se omitirá.");
                    continue;
                }

                // Separar nombre completo
                String[] partesNombre = lineaNombreCompleto.split("\\s+");
                String nombre = partesNombre[0];
                String apellidoPaterno = partesNombre.length > 1 ? partesNombre[1] : "";
                String apellidoMaterno = partesNombre.length > 2 ? partesNombre[2] : "";

                // Usar el partido leído del archivo
                candidatos.add(new Candidato(nombre, apellidoPaterno, apellidoMaterno, lineaPartido, ""));
            }
        }
        
        return candidatos;
    }
} 