
package com.finalproject.exceptions;


public class ElectorException extends Exception {
   public ElectorException(String mensaje) {
       super(mensaje);
   }


   public ElectorException(String mensaje, Throwable causa) {
       super(mensaje, causa);
   }
}

