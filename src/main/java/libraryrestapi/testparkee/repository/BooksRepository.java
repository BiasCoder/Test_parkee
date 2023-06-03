package libraryrestapi.testparkee.repository;

import libraryrestapi.testparkee.dto.BooksDTO;
import libraryrestapi.testparkee.entity.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository <BooksEntity, Integer> {
    Optional<BooksEntity> findByIsbn(Integer isbn);

    void deleteByIsbn(Integer isbn);
}
