package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.KontaktDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;

@Repository
public class VereinRepository {

    private final DSLContext jooqDsl;
    private final VereinDao vereinDao;
    private final KontaktDao kontaktDao;

    public VereinRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.vereinDao = new VereinDao(jooqConfig);
        this.kontaktDao = new KontaktDao(jooqConfig);
    }

    public Optional<VereinPojo> findByEmail(String email) {
        return jooqDsl.selectFrom(VEREIN)
                      .where(VEREIN.EMAIL.equalIgnoreCase(email))
                      .fetchOptionalInto(VereinPojo.class);
    }

    public void insert(VereinPojo verein) {
        vereinDao.insert(verein);
    }

    public void update(VereinPojo verein) {
        vereinDao.update(verein);
    }

    public KontaktPojo findKontaktById(Long id) {
        return kontaktDao.findOptionalById(id).orElseThrow();
    }

    public void insert(KontaktPojo kontakt) {
        kontaktDao.insert(kontakt);
    }

    public void update(KontaktPojo kontakt) {
        kontaktDao.update(kontakt);
    }
}
