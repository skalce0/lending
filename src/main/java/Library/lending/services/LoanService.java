package Library.lending.exceptions;

import Library.lending.entities.Book;
import Library.lending.entities.Loan;
import Library.lending.entities.Reader;
import Library.lending.repositories.BookRepository;
import Library.lending.repositories.LoanRepository;
import Library.lending.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public Loan createLoan(Loan loan) throws ReaderNotFoundException {
        Book book = bookRepository.findById(loan.getBook().getIsbn())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));



        Reader reader = readerRepository.findById(loan.getReader().getId())
                .orElseThrow(() -> new ReaderNotFoundException("Reader not found"));

        LocalDate today = LocalDate.now();
        loan.setLoanDate(today);
        loan.setDueDate(today.plusWeeks(2));


        return loanRepository.save(loan);
    }

    public void returnLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        Book book = loan.getBook();

        bookRepository.save(book);

        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));
    }

    public List<Loan> getLoansByLoanDate(LocalDate loanDate) {
        return loanRepository.findByLoanDate(loanDate);
    }

    public ServiceLoader<Object> getLoansByReader(Long readerId) throws ReaderNotFoundException {
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException("Reader not found"));
        return loanRepository.findByReader(reader);
    }

    public List<Loan> getLoansByBook(String isbn) {
        Book book = bookRepository.findById(Long.valueOf(isbn))
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        return loanRepository.findByBook(book);
    }

    public List<Loan> getCurrentLoans() {
        LocalDate today = LocalDate.now();
        return loanRepository.findByDueDateGreaterThan(today).stream()
                .filter(loan -> Objects.isNull(loan.getReturnDate()))
                .collect(Collectors.toList());
    }

    public List<Loan> getLoanHistory(Long readerId) throws ReaderNotFoundException {
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException("Reader not found"));
        return loanRepository.findByReader(reader).stream()
                .filter(loan -> loan.getReturnDate() != null)
                .collect(Collectors.toList());
    }
}
