package Library.lending.exceptions;

import Library.lending.entities.Book;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(Book book) {
        super("Book " + book.getTitle() + " is not available.");
    }
}

