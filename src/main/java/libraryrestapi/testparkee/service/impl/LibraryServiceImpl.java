package libraryrestapi.testparkee.service.impl;

import libraryrestapi.testparkee.dto.BooksDTO;
import libraryrestapi.testparkee.dto.BorrowerRequestDTO;
import libraryrestapi.testparkee.dto.BorrowerResponseDTO;
import libraryrestapi.testparkee.entity.BooksEntity;
import libraryrestapi.testparkee.entity.BorrowersEntity;
import libraryrestapi.testparkee.repository.BooksRepository;
import libraryrestapi.testparkee.repository.BorrowersRepository;
import libraryrestapi.testparkee.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final BooksRepository booksRepository;

    private final BorrowersRepository borrowersRepository;
    private final ModelMapper modelMapper;

    @Override
    public BooksDTO addBooks(Integer isbn, BooksDTO booksDTO) {
        Optional<BooksEntity> existingBook = booksRepository.findByIsbn(isbn);
        if (existingBook.isPresent()) {
            throw new RuntimeException("Buku dengan nomor ISBN tersebut sudah pernah didaftarkan.");
        }
        BooksEntity books1 = modelMapper.map(booksDTO, BooksEntity.class);
        books1 = booksRepository.save(books1);
        booksDTO.setIsbn(books1.getIsbn());
        return booksDTO;
    }

    @Override
    public BooksDTO getByIsbn(Integer isbn) {
        Optional<BooksEntity> books = booksRepository.findByIsbn(isbn);
        if (!books.isPresent()){
            throw new RuntimeException("Not Found");
        }
        BooksDTO booksDTO = modelMapper.map(books.get(), BooksDTO.class);
        return booksDTO;
    }

    @Override
    public List getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public void deleteBooks(Integer isbn) {
        Optional<BooksEntity> books = booksRepository.findByIsbn(isbn);
        if (!books.isPresent()){
            throw new RuntimeException("Not Found");
        }
        booksRepository.deleteByIsbn(isbn);
    }

    @Override
    public BorrowerRequestDTO addBurrowers(Long ktp, BorrowerRequestDTO borrowerRequestDTO) {
        Optional<BorrowersEntity> burrowers = borrowersRepository.findByKtp(ktp);
        if (burrowers.isPresent()) {
            throw new RuntimeException("Peminjam dengan nomor KTP tersebut sudah terdaftar");
        }
        BorrowersEntity burrowers1 = modelMapper.map(borrowerRequestDTO, BorrowersEntity.class);
        burrowers1 = borrowersRepository.save(burrowers1);
        borrowerRequestDTO.setKtp(burrowers1.getKtp());
        return borrowerRequestDTO;
    }

    @Override
    public BorrowerResponseDTO getByIdBurrowers(Long ktp) {
        Optional<BorrowersEntity> burrowers = borrowersRepository.findByKtp(ktp);
        if (!burrowers.isPresent()){
            throw new RuntimeException("Burrowers Not Found");
        }
        BorrowerResponseDTO borrowerResponseDTO = modelMapper.map(burrowers.get(), BorrowerResponseDTO.class);
        return borrowerResponseDTO;
    }

    @Override
    public List getAllBorrowers() {
        return borrowersRepository.findAll();
    }

    @Override
    public void deleteBurrowers(Long ktp) {
        Optional<BorrowersEntity> burrowers = borrowersRepository.findByKtp(ktp);
            if (!burrowers.isPresent()){
                throw new RuntimeException("Not Found");
            }
            borrowersRepository.deleteByKtp(ktp);
    }
}
