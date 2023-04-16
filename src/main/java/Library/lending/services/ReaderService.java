package Library.lending.services;



import Library.lending.entities.Dto.ReaderDto;
import Library.lending.entities.Reader;
import Library.lending.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }

    /*public Reader createReader(ReaderDto reader) {
        return readerRepository.save(reader);
    }*/

    public Reader updateReader(Long id, ReaderDto reader) {
        Optional<Reader> existingReader = readerRepository.findById(id);

        if (existingReader.isPresent()) {
            Reader updatedReader = existingReader.get();
            updatedReader.setLastName(reader.getLastName());
            updatedReader.setFirstName(reader.getFirstName());
            updatedReader.setBirthDate(reader.getBirthDate());
            updatedReader.setAddress(reader.getAddress());
            updatedReader.setGender(reader.getGender());
            return readerRepository.save(updatedReader);
        } else {
            throw new RuntimeException("Reader not found with id: " + id);
        }
    }

    public void deleteReader(Long id) {
        Optional<Reader> reader = readerRepository.findById(id);

        if (reader.isPresent()) {
            readerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Reader not found with id: " + id);
        }
    }
}
