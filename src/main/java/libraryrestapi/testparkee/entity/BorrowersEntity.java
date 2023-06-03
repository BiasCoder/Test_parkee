package libraryrestapi.testparkee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Borrowers")
@Data
@NoArgsConstructor
public class BorrowersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true)
    private Long ktp;

    @Column
    private String name;

    @Column
    private String email;
}
