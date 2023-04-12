package Library.lending.Controllers;

import Library.lending.entities.Book;
import Library.lending.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Retrieve all books
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Retrieve a book by ISBN
    @GetMapping("/{isbn}")
    public Book getBookByISBN(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn);
    }

    // Create a new book
    @PostMapping("")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // Update an existing book
    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book book) {
        return bookService.updateBook(isbn, book);
    }

    // Delete a book
    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }
}
