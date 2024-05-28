package libraryrestapi.testparkee.controller;

import libraryrestapi.testparkee.dto.*;
import libraryrestapi.testparkee.entity.BooksEntity;
import libraryrestapi.testparkee.entity.BorrowersEntity;
import libraryrestapi.testparkee.service.BorrowBookService;
import libraryrestapi.testparkee.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;
    private final BorrowBookService borrowBookService;

    @GetMapping("/testController")
    public ResponseEntity<ResponseDTO<String>> testController() {
        return ResponseEntity.ok(ResponseDTO.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Test Controller")
                .data("Test Controller")
                .build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO<BooksDTO>> addBooks(Integer isbn, @RequestBody BooksDTO booksDTO) {
        libraryService.addBooks(isbn, booksDTO);
        return new ResponseEntity<>(ResponseDTO.<BooksDTO>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Books Added")
                .data(booksDTO)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ResponseDTO<BooksDTO>> getBooksById(@PathVariable Integer isbn) {
        BooksDTO booksDTO = libraryService.getByIsbn(isbn);
        return new ResponseEntity<>(ResponseDTO.<BooksDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Found By ISBN")
                .data(booksDTO)
                .build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<BooksDTO>>> getAllBooks() {
        List<BooksEntity> booksEntities = libraryService.getAllBooks();
        List<BooksDTO> booksDTOs = new ArrayList<>();

        for (BooksEntity booksEntity : booksEntities) {
            BooksDTO booksDTO = new BooksDTO();
            booksDTO.setIsbn(booksEntity.getIsbn());
            booksDTO.setJudul_buku(booksEntity.getJudul_buku());
            booksDTO.setStock(booksEntity.getStock());
            booksDTOs.add(booksDTO);
        }

        return ResponseEntity.ok(ResponseDTO.<List<BooksDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get All Books")
                .data(booksDTOs)
                .build());
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<ResponseDTO> deleteBooks(@PathVariable Integer isbn) {
        libraryService.deleteBooks(isbn);
        return new ResponseEntity<>(ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Deleted")
                .data(null)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/add/borrowers")
    public ResponseEntity<ResponseDTO<BorrowerResponseDTO>> addBurrowers(Long ktp, @RequestBody BorrowerRequestDTO borrowerRequestDTO) {
        BorrowerRequestDTO addBurrowers = libraryService.addBurrowers(ktp, borrowerRequestDTO);
        BorrowerResponseDTO borrowerResponseDTO = new BorrowerResponseDTO();
        borrowerResponseDTO.setKtp(addBurrowers.getKtp());
        borrowerResponseDTO.setName(addBurrowers.getName());
        borrowerResponseDTO.setEmail(addBurrowers.getEmail());

        return new ResponseEntity<>(ResponseDTO.<BorrowerResponseDTO>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Burrowers Added")
                .data(borrowerResponseDTO)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/borrowers/{ktp}")
    public ResponseEntity<ResponseDTO<BorrowerResponseDTO>> getBurrowersById(@PathVariable Long ktp) {
        BorrowerResponseDTO borrowerResponseDTO = libraryService.getByIdBurrowers(ktp);
        return new ResponseEntity<>(ResponseDTO.<BorrowerResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Found By KTP")
                .data(borrowerResponseDTO)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/borrowers")
    public ResponseEntity<ResponseDTO<List<BorrowerResponseDTO>>> getAllBorrowers() {
        List<BorrowersEntity> burrowersEntities = libraryService.getAllBorrowers();
        List<BorrowerResponseDTO> borrowerResponseDTOS = new ArrayList<>();
        for (BorrowersEntity burrowers : burrowersEntities) {
            BorrowerResponseDTO borrowerResponseDTO = new BorrowerResponseDTO();
            borrowerResponseDTO.setKtp(burrowers.getKtp());
            borrowerResponseDTO.setName(burrowers.getName());
            borrowerResponseDTO.setEmail(burrowers.getEmail());
            borrowerResponseDTOS.add(borrowerResponseDTO);
        }
        return ResponseEntity.ok(ResponseDTO.<List<BorrowerResponseDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get All Burrowers")
                .data(borrowerResponseDTOS)
                .build());
    }

    @DeleteMapping("/borrowers/{ktp}")
    public ResponseEntity<ResponseDTO> deleteBurrowers(@PathVariable Long ktp) {
        libraryService.deleteBurrowers(ktp);
        return new ResponseEntity<>(ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Deleted")
                .data(null)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/borrow")
    public ResponseEntity<ResponseDTO<Void>> borrowBook(@RequestBody BorrowBookRequestDTO requestDTO) {
        Long ktp = requestDTO.getKtp();
        Integer isbn = requestDTO.getIsbn();
        LocalDate returnDate = requestDTO.getReturnDate();

        // Panggil method borrowBook dari service
        borrowBookService.borrowBook(ktp, isbn, returnDate);

        // Mengembalikan response sukses
        return ResponseEntity.ok(ResponseDTO.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Book successfully borrowed")
                .build());
    }

    @PostMapping("/return")
    public ResponseEntity<ResponseDTO<BorrowingResponseDTO>> returnBook(@RequestBody ReturnBookRequestDTO requestDTO) {
        Long ktp = requestDTO.getKtp();
        Integer isbn = requestDTO.getIsbn();

        // Panggil method returnBook dari service
        borrowBookService.returnBook(ktp, isbn);

        // Mengembalikan response sukses
        return ResponseEntity.ok(ResponseDTO.<BorrowingResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Book successfully returned")
                .build());
    }

    @GetMapping("/borrowings")
    public ResponseEntity<ResponseDTO<List<BorrowingResponseDTO>>> getAllBorrowings() {
        List<BorrowingResponseDTO> borrowingList = borrowBookService.getAllBorrowingData();

        // Mengembalikan response sukses
        return ResponseEntity.ok(ResponseDTO.<List<BorrowingResponseDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("All borrowing data retrieved successfully")
                .data(borrowingList)
                .build());
    }






}
