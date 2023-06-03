package libraryrestapi.testparkee.dto;

import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class ReturnBookRequestDTO implements Serializable {
    private Long ktp;
    private Integer isbn;
}
