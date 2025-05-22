package com.finalproject.exceptions;


public class VotacionCerradaException extends RuntimeException {
   public VotacionCerradaException(String mensaje) {
       super(mensaje);
   }


   public VotacionCerradaException(String mensaje, Throwable causa) {
       super(mensaje, causa);
   }
}

