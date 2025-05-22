package com.finalproject.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServicioCandidatos {
    private static ServicioCandidatos instancia;
    private Set<Candidato> candidatosRegistrados;

    private ServicioCandidatos() {
        this.candidatosRegistrados = new HashSet<>();
    }

    public static ServicioCandidatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new ServicioCandidatos();
        }
        return instancia;
    }
    
    public List<Candidato> cargarCandidatoDesdeArchivo(File archivo) throws IOException {
        List<Candidato> candidatosNuevos = new ArrayList<>();
        List<Candidato> candidatosDuplicados = new ArrayList<>();
        
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

                // Crear el candidato
                Candidato candidato = new Candidato(nombre, apellidoPaterno, apellidoMaterno, lineaPartido, "");

                // Verificar si el candidato ya está registrado
                if (candidatosRegistrados.contains(candidato)) {
                    candidatosDuplicados.add(candidato);
                    System.err.println("Advertencia: Candidato duplicado encontrado: " + candidato.toString());
                } else {
                    candidatosRegistrados.add(candidato);
                    candidatosNuevos.add(candidato);
                }
            }
        }
        
        if (!candidatosDuplicados.isEmpty()) {
            throw new IOException("Se encontraron candidatos duplicados en el archivo. Por favor, revise la lista de candidatos.");
        }
        
        return candidatosNuevos;
    }

    public List<Candidato> obtenerCandidatosRegistrados() {
        return new ArrayList<>(candidatosRegistrados);
    }

    public void reiniciarCandidatos() {
        candidatosRegistrados.clear();
    }
} 