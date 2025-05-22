package com.finalproject.model; // Paquete corregido

public class Elector {
    private String nombre;
    private String apellidoPaterno; // Usaremos nombres más descriptivos consistentemente
    private String apellidoMaterno;
    private String correo;
    private Fecha fechaNacimiento; // Usaremos nombres más descriptivos consistentemente
    private boolean haVotado;

    // Constructor directo como el que proporcionaste, pero con nombres de parámetros ajustados
    public Elector(String nombre, String apellidoPaterno, String apellidoMaterno, 
                   String correo, Fecha fechaNacimiento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.haVotado = false; // Valor inicial por defecto
    }

    // Getters para los campos básicos
    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public boolean haVotado() {
        return haVotado;
    }

    // Setter para haVotado
    public void setHaVotado(boolean haVotado) {
        this.haVotado = haVotado;
    }

    // Método getUsuario (corregido según instrucción del proyecto)
    public String getUsuario() {
        if (this.correo == null || this.correo.isEmpty() || !this.correo.contains("@")) {
            // Si el correo es inválido, no se puede generar usuario según la regla.
            // Podríamos devolver un valor por defecto o lanzar una excepción.
            // Por simplicidad, devolvemos el correo o una cadena vacía.
            // O podríamos usar la lógica que tenías antes como fallback:
            // return (nombre + "." + apellidoPaterno).toLowerCase();
            return this.correo != null ? this.correo.split("@")[0] : "";
        }
        return this.correo.substring(0, this.correo.indexOf("@"));
    }

    // Método getContrasena (tomado de tu Elector.java original, usando los nombres de campo actualizados)
    public String getContrasena() {
        if (this.nombre == null || this.apellidoPaterno == null || this.apellidoMaterno == null || 
            this.fechaNacimiento == null || this.nombre.isEmpty() || 
            this.apellidoPaterno.isEmpty() || this.apellidoMaterno.isEmpty()) {
            return "DATOSINCOMPLETOS"; // O algún manejo de error
        }

        String contrasenaCalculada = "";
        contrasenaCalculada += nombre.charAt(0);
        contrasenaCalculada += (fechaNacimiento.getDia() < 10 ? "0" : "") + fechaNacimiento.getDia();
        contrasenaCalculada += apellidoPaterno.charAt(0);
        contrasenaCalculada += (fechaNacimiento.getMes() < 10 ? "0" : "") + fechaNacimiento.getMes();
        contrasenaCalculada += apellidoMaterno.charAt(0);
        int dosDigitosAnio = fechaNacimiento.getAnio() % 100;
        contrasenaCalculada += (dosDigitosAnio < 10 ? "0" : "") + dosDigitosAnio;
        
        return contrasenaCalculada.toUpperCase();
    }

    @Override
    public String toString() {
        return "Nombre completo: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno +
               "\nCorreo: " + correo +
               "\nFecha de nacimiento: " + (fechaNacimiento != null ? fechaNacimiento.toString() : "N/A") +
               "\nUsuario: " + getUsuario() +      // Se calcula al llamar
               "\nContraseña: " + getContrasena() + // Se calcula al llamar
               "\nHa votado: " + (haVotado ? "Sí" : "No");
    }
}
