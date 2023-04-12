package Library.lending.services;

// BookService.java
import Library.lending.exceptions.BookNotFoundException;
import Library.lending.entities.Book;
import Library.lending.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(String isbn, Book book) {
        Book bookToUpdate = bookRepository.findByIsbn(isbn);
        if (bookToUpdate != null) {
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setPublisher(book.getPublisher());
            bookToUpdate.setEdition(book.getEdition());
            return bookRepository.save(bookToUpdate);
        } else {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
    }

    public void deleteBook(String isbn) {
        Book bookToDelete = bookRepository.findByIsbn(isbn);
        if (bookToDelete != null) {
            bookRepository.delete(bookToDelete);
        } else {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
    }

}

