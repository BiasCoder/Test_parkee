package libraryrestapi.testparkee.repository;

import libraryrestapi.testparkee.entity.BooksEntity;
import libraryrestapi.testparkee.entity.BorrowingsEntity;
import libraryrestapi.testparkee.entity.BorrowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingsRepository extends JpaRepository<BorrowingsEntity, Long> {

    boolean existsByBurrowerAndReturnedFalse(BorrowersEntity burrower);

    Optional<BorrowingsEntity> findByBurrowerAndBookAndReturnedFalse(BorrowersEntity burrower, BooksEntity book);
}