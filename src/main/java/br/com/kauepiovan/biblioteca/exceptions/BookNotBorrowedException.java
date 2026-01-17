package br.com.kauepiovan.biblioteca.exceptions;

public class BookNotBorrowedException extends Exception {
    public BookNotBorrowedException() {
        super("Exception: Book is not being borrow to this user.");
    }
}
