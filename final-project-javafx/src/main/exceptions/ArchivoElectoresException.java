package com.finalproject.exceptions;


public class ArchivoElectoresException extends ElectorException {
   public ArchivoElectoresException(String mensaje) {
       super(mensaje);
   }


   public ArchivoElectoresException(String mensaje, Throwable causa) {
       super(mensaje, causa);
   }
}
