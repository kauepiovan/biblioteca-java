package br.com.kauepiovan.biblioteca.exceptions;

public class LimitBookReachedException extends Exception {
    public LimitBookReachedException() {
        super("Exception: The limit of books that this user can have is already reach.");
    }
}
