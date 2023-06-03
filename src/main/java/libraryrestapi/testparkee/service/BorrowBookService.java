package libraryrestapi.testparkee.service;

import java.time.LocalDate;
import java.util.List;

public interface BorrowBookService {
    void borrowBook(Long ktp, Integer isbn, LocalDate returnDate);
    void returnBook(Long ktp, Integer isbn);
    List getAllBorrowingData();
}
