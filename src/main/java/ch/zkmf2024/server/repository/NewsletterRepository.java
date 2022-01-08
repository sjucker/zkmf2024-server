package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.domain.NewsletterRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends JpaRepository<NewsletterRecipient, String> {
}
