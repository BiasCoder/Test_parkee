package libraryrestapi.testparkee.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class BorrowerRequestDTO implements Serializable {
    private Long ktp;
    private String name;
    private String email;
}
