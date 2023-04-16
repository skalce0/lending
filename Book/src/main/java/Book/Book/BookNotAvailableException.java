package Book.Book;


public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(Book book) {
        super("Book " + book.getTitle() + " is not available.");
    }
}

