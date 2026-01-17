package br.com.kauepiovan.biblioteca.exceptions;

public class BookAlreadyBorrowedException extends Exception {
    public BookAlreadyBorrowedException() {
        super("Exception: This book is already been borrow");
    }
}
