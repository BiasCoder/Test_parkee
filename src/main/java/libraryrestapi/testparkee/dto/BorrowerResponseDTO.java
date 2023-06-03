package libraryrestapi.testparkee.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class BorrowerResponseDTO implements Serializable {
    private Long ktp;
    private String name;
    private String email;
}
