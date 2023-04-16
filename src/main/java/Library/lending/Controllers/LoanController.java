/*
package Library.lending.Controllers;

import Library.lending.entities.Dto.LoanDto;
import Library.lending.entities.Loan;
import Library.lending.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable Long id) {
        return loanRepository.getLoanById(id);
    }

    */
/*@PostMapping
    public Loan createLoan(@RequestBody LoanDto loanDto) {
        return loanRepository.createLoan();
    }*//*


    */
/*@PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody LoanDto loanDto) {
        return loanRepository.updateLoan(id, loanDto);
    }
*//*

    */
/*@DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanRepository.deleteLoan(id);
    }*//*


    @GetMapping("/reader/{Id}")
    public List<Loan> getLoansByReader(@PathVariable int readerId) {
        return loanRepository.getLoansByReader(readerId);
    }
}

*/
