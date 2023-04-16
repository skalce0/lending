package Library.lending.repositories;



import Library.lending.entities.Book;
import Library.lending.entities.Dto.LoanDto;
import Library.lending.entities.Loan;
import Library.lending.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Loan getLoanById(Long Id);
    ServiceLoader<Object> findByReader(Reader reader);

    List<Loan> findByLoanDate(LocalDate loanDate);

    List<Loan> findByBook(Book book);

    ServiceLoader<Object> findByDueDateGreaterThan(LocalDate today);

 @Transactional
    @Modifying
    @Query(value = "INSERT INTO loan (book_id, borrower_id, loan_date, return_date) " +
            "VALUES (:bookId, :borrowerId, :loanDate, :returnDate)", nativeQuery = true)
    void createLoan(@Param("bookId") Long bookId,
                    @Param("borrowerId") Long borrowerId,
                    @Param("loanDate") LocalDate loanDate,
                    @Param("returnDate") LocalDate returnDate);

    default Loan createLoan(LoanDto loanDto, Book book, Reader reader) {
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setReader(reader);
        loan.setLoanDate(loanDto.getLoanDate());
        //loan.setReturnDate(loanDto.getReturnDate());
        //createLoan(book.getId(), borrower.getId(), loan.getLoanDate(), loan.getReturnDate());
        return loan;
    }

    void deleteLoan(Long id);

Loan updateLoan(Long id, LoanDto loanDto);


    List<Loan> getLoansByReader(int readerId);


}

