package com.finalproject.exceptions;


public class CredencialesInvalidasException extends ElectorException {
   public CredencialesInvalidasException(String mensaje) {
       super(mensaje);
   }


   public CredencialesInvalidasException(String mensaje, Throwable causa) {
       super(mensaje, causa);
   }
}

