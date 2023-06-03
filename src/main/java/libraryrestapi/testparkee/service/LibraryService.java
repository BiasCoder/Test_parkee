package libraryrestapi.testparkee.service;

import libraryrestapi.testparkee.dto.BooksDTO;
import libraryrestapi.testparkee.dto.BorrowerRequestDTO;
import libraryrestapi.testparkee.dto.BorrowerResponseDTO;

import java.util.List;

public interface LibraryService {
    BooksDTO addBooks(Integer isbn, BooksDTO booksDTO);
    BooksDTO getByIsbn(Integer isbn);
    List getAllBooks();
    void deleteBooks(Integer isbn);

    BorrowerRequestDTO addBurrowers(Long ktp, BorrowerRequestDTO borrowerRequestDTO);
    BorrowerResponseDTO getByIdBurrowers(Long ktp);
    List getAllBorrowers();

    void deleteBurrowers(Long ktp);


}
