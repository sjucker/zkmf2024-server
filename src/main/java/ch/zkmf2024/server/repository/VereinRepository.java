package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.domain.Verein;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VereinRepository extends JpaRepository<Verein, Long> {

    Optional<Verein> findByEmail(String email);

}
