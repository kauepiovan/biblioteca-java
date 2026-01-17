package br.com.kauepiovan.biblioteca.exceptions;

public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("Exception: Book can not be found or do not exist.");
    }
}
