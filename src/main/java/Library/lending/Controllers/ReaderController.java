package Library.lending.Controllers;

import Library.lending.entities.Dto.ReaderDto;
import Library.lending.entities.Reader;
import Library.lending.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GetMapping("/{id}")
    public Optional<Reader> getReaderById(@PathVariable Long id) {
        return readerService.getReaderById(id);
    }

    /*@PostMapping
    public Reader createReader(@RequestBody ReaderDto readerDto) {
        return readerService.createReader(readerDto);
    }*/

    @PutMapping("/{id}")
    public Reader updateReader(@PathVariable Long id, @RequestBody ReaderDto readerDto) {
        return readerService.updateReader(id, readerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
    }
}
