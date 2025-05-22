package com.finalproject.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; //libreria para poder cambiar el formato de la fecha

public class Electores {
   private String  nombre;
   private String  apellidoP;
   private String  apellidoM;
   private String  correo;
   private LocalDate fechaN;

   public Electores(String nombre, String apellidoP, String apellidoM, String correo, String fechaNStr) {
    this.nombre = nombre;
    this.apellidoP = apellidoP;
    this.apellidoM = apellidoM;
    this.correo = correo;
    this.fechaN = LocalDate.parse(fechaNStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));//cambiar el formato de la fecha
   }
  
   public String getNombre() {
    return nombre;
   }

   public String getApellidoP() {
    return apellidoP;
   }

   
  
}
