package libraryrestapi.testparkee.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class BorrowingResponseDTO implements Serializable {
    private Long id;
    private Long borrowerId;
    private String borrowerName;
    private Integer bookId;
    private String bookTitle;
    private LocalDate borrowedDate;
    private LocalDate returnDate;
    private boolean onTimeReturned;
}
