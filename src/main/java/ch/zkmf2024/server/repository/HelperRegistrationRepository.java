package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.domain.HelperRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelperRegistrationRepository extends JpaRepository<HelperRegistration, String> {
}
