package br.com.kauepiovan.biblioteca.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Exception: User can not be found or do not exist.");
    }    
}
