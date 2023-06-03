package libraryrestapi.testparkee.repository;

import libraryrestapi.testparkee.entity.BorrowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowersRepository extends JpaRepository<BorrowersEntity, Integer> {
    Optional<BorrowersEntity> findByKtp (Long ktp);

    void deleteByKtp (Long ktp);
}
