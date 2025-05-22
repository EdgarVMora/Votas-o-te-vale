package com.finalproject.exceptions;


public class VotoDuplicadoException extends RuntimeException {
   public VotoDuplicadoException(String mensaje) {
       super(mensaje);
   }


   public VotoDuplicadoException(String mensaje, Throwable causa) {
       super(mensaje, causa);
   }
}

