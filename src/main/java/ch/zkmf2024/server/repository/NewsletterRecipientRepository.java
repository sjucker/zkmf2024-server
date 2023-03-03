package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.NewsletterRecipientDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.NewsletterRecipientPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NewsletterRecipientRepository {

    private final DSLContext jooqDsl;
    private final NewsletterRecipientDao newsletterRecipientDao;

    public NewsletterRecipientRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.newsletterRecipientDao = new NewsletterRecipientDao(jooqConfig);
    }

    public boolean existsById(String email) {
        return newsletterRecipientDao.existsById(email);
    }

    public void insert(NewsletterRecipientPojo newsletterRecipient) {
        newsletterRecipientDao.insert(newsletterRecipient);
    }

    public List<NewsletterRecipientPojo> findAll() {
        return newsletterRecipientDao.findAll();
    }

    public void deleteById(String email) {
        newsletterRecipientDao.deleteById(email);
    }

    public Optional<NewsletterRecipientPojo> findById(String email) {
        return newsletterRecipientDao.findOptionalById(email);
    }

    public void update(NewsletterRecipientPojo newsletterRecipient) {
        newsletterRecipientDao.update(newsletterRecipient);
    }
}
