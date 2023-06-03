package libraryrestapi.testparkee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Borrowings")
@Data
@NoArgsConstructor
public class BorrowingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "burrower_id")
    private BorrowersEntity burrower;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BooksEntity book;

    @Column(name = "borrowed_date")
    private LocalDate borrowedDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "returned")
    private boolean returned;

    @Column(name = "on_time_returned")
    private boolean onTimeReturned;

}
