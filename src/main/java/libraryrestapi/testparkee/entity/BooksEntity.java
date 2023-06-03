package libraryrestapi.testparkee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Books")
@Data
@NoArgsConstructor
public class BooksEntity {
    @Id
    @GeneratedValue
    private  Integer id;

    @Column(unique = true)
    private Integer isbn;

    @Column
    private String judul_buku;

    @Column
    private Integer stock;
}
