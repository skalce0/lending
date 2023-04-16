package Loan.Loan;

import Book.Book;
import Reader.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.ServiceLoader;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    ServiceLoader<Object> findByReader(Reader reader);

    List<Loan> findByLoanDate(LocalDate loanDate);

    List<Loan> findByBook(Book book);

    ServiceLoader<Object> findByDueDateGreaterThan(LocalDate today);
}

