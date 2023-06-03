package libraryrestapi.testparkee.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class BooksDTO implements Serializable {
    private Integer isbn;
    private String judul_buku;
    private Integer stock;
}
