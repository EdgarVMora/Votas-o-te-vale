package com.finalproject.model;

public class Elector {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private Fecha fechaNacimiento;
    private boolean haVotado;

    public Elector(String nombre, String apellidoPaterno, String apellidoMaterno, 
                   String correo, Fecha fechaNacimiento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.haVotado = false;
    }

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

    public void setHaVotado(boolean haVotado) {
        this.haVotado = haVotado;
    }

    public String getUsuario() {
        if (this.correo == null || this.correo.isEmpty() || !this.correo.contains("@")) {
            return this.correo != null ? this.correo.split("@")[0] : "";
        }
        return this.correo.substring(0, this.correo.indexOf("@"));
    }

    public String getContrasena() {
        if (this.nombre == null || this.apellidoPaterno == null || this.apellidoMaterno == null || 
            this.fechaNacimiento == null || this.nombre.isEmpty() || 
            this.apellidoPaterno.isEmpty() || this.apellidoMaterno.isEmpty()) {
            return "DATOSINCOMPLETOS";
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
               "\nUsuario: " + getUsuario() +
               "\nContraseña: " + getContrasena() +
               "\nHa votado: " + (haVotado ? "Sí" : "No");
    }
}
