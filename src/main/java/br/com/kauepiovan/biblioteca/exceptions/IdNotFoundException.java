package br.com.kauepiovan.biblioteca.exceptions;

public class IdNotFoundException extends Exception {
    public IdNotFoundException() {
        super("Exception: Id can not be found or do not exist.");
    }
    
}
