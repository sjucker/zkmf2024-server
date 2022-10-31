package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.domain.Band;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BandRepository extends JpaRepository<Band, Long> {

    Optional<Band> findByEmail(String email);

}
