package br.com.kauepiovan.biblioteca.exceptions;

public class EmailAlreadyExistException extends Exception {
    public EmailAlreadyExistException() {
        super("Exception: This email is already in use.");
    }
}
