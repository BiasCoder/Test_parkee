package libraryrestapi.testparkee.service.impl;

import libraryrestapi.testparkee.entity.BooksEntity;
import libraryrestapi.testparkee.entity.BorrowersEntity;
import libraryrestapi.testparkee.entity.BorrowingsEntity;
import libraryrestapi.testparkee.repository.BooksRepository;
import libraryrestapi.testparkee.repository.BorrowersRepository;
import libraryrestapi.testparkee.repository.BorrowingsRepository;
import libraryrestapi.testparkee.service.BorrowBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowBookServiceImpl implements BorrowBookService {
    private final BorrowingsRepository borrowingsRepository;
    private final BorrowersRepository borrowersRepository;

    private final BooksRepository booksRepository;

    @Override
    public void borrowBook(Long ktp, Integer isbn, LocalDate returnDate) {
        Optional<BorrowersEntity> burrower = borrowersRepository.findByKtp(ktp);
        if (!burrower.isPresent()) {
            throw new RuntimeException("Peminjam dengan nomor KTP tersebut tidak terdaftar");
        }

        Optional<BooksEntity> book = booksRepository.findByIsbn(isbn);
        if (!book.isPresent()) {
            throw new RuntimeException("Buku dengan nomor ISBN tersebut tidak tersedia");
        }

        if (borrowingsRepository.existsByBurrowerAndReturnedFalse(burrower.get())) {
            throw new RuntimeException("Peminjam sedang meminjam buku lain");
        }

        if (book.get().getStock() <= 0) {
            throw new RuntimeException("Stok buku tidak tersedia");
        }

        LocalDate today = LocalDate.now();
        if (returnDate.isBefore(today) || returnDate.isEqual(today) || returnDate.isAfter(today.plusDays(30))) {
            throw new RuntimeException("Tanggal pengembalian tidak lebih dari 30 Hari");
        }

        BorrowingsEntity borrowing = new BorrowingsEntity();
        borrowing.setBurrower(burrower.get());
        borrowing.setBook(book.get());
        borrowing.setBorrowedDate(today);
        borrowing.setReturnDate(returnDate);

        book.get().setStock(book.get().getStock() - 1);

        borrowingsRepository.save(borrowing);
    }

    @Override
    public void returnBook(Long ktp, Integer isbn) {
        Optional<BorrowersEntity> borrower = borrowersRepository.findByKtp(ktp);
        if (!borrower.isPresent()) {
            throw new RuntimeException("Peminjam dengan nomor KTP tersebut tidak terdaftar");
        }

        Optional<BooksEntity> book = booksRepository.findByIsbn(isbn);
        if (!book.isPresent()) {
            throw new RuntimeException("Buku dengan nomor ISBN tersebut tidak tersedia");
        }

        Optional<BorrowingsEntity> borrowing = borrowingsRepository.findByBurrowerAndBookAndReturnedFalse(borrower.get(), book.get());
        if (!borrowing.isPresent()) {
            throw new RuntimeException("Peminjam tidak sedang meminjam buku tersebut");
        }

        LocalDate today = LocalDate.now();
        boolean onTimeReturned = today.isBefore(borrowing.get().getReturnDate()) || today.isEqual(borrowing.get().getReturnDate());

        borrowing.get().setReturned(true);
        borrowing.get().setReturnDate(today);
        borrowing.get().setOnTimeReturned(onTimeReturned);
        book.get().setStock(book.get().getStock() + 1);

        borrowingsRepository.save(borrowing.get());
    }

    @Override
    public List getAllBorrowingData() {
        return borrowingsRepository.findAll();
    }
}
