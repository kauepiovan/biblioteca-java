package br.com.kauepiovan.biblioteca.exceptions;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException() {
        super("Exception: Email can not be found or do not exist.");
    }
}
