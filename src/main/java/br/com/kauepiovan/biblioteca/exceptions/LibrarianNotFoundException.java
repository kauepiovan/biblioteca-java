package br.com.kauepiovan.biblioteca.exceptions;

public class LibrarianNotFoundException extends Exception {
    public LibrarianNotFoundException() {
        super("Exception: Librarian can not be found or do not exist.");
    }
}
