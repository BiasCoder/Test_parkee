package libraryrestapi.testparkee.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class BorrowBookRequestDTO implements Serializable {
    private Long ktp;
    private Integer isbn;
    private LocalDate returnDate;
}
