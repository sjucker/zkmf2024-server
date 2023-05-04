package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.PhaseStatus;
import ch.zkmf2024.server.dto.admin.VereinOverviewDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.KontaktDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinStatusDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinStatusPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;
import static ch.zkmf2024.server.jooq.generated.tables.VereinStatus.VEREIN_STATUS;

@Repository
public class VereinRepository {

    private final DSLContext jooqDsl;
    private final VereinDao vereinDao;
    private final KontaktDao kontaktDao;
    private final VereinStatusDao vereinStatusDao;

    public VereinRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.vereinDao = new VereinDao(jooqConfig);
        this.kontaktDao = new KontaktDao(jooqConfig);
        this.vereinStatusDao = new VereinStatusDao(jooqConfig);
    }

    public List<VereinPojo> findAll() {
        return vereinDao.findAll();
    }

    public List<VereinOverviewDTO> findAllOverview() {
        return jooqDsl.select()
                      .from(VEREIN)
                      .join(VEREIN_STATUS).on(VEREIN.ID.eq(VEREIN_STATUS.FK_VEREIN))
                      .orderBy(VEREIN.VEREINSNAME)
                      .fetch(record -> new VereinOverviewDTO(
                              record.get(VEREIN.ID),
                              record.get(VEREIN.VEREINSNAME),
                              record.get(VEREIN.MODULA),
                              record.get(VEREIN.MODULB),
                              record.get(VEREIN.MODULC),
                              record.get(VEREIN.MODULD),
                              record.get(VEREIN.MODULE),
                              record.get(VEREIN.MODULF),
                              record.get(VEREIN.MODULG),
                              record.get(VEREIN.MODULH),
                              record.get(VEREIN.KLASSE_MODULA) != null ? Klasse.valueOf(record.get(VEREIN.KLASSE_MODULA)) : null,
                              record.get(VEREIN.KLASSE_MODULB) != null ? Klasse.valueOf(record.get(VEREIN.KLASSE_MODULB)) : null,
                              record.get(VEREIN.KLASSE_MODULH) != null ? Klasse.valueOf(record.get(VEREIN.KLASSE_MODULH)) : null,
                              record.get(VEREIN.HARMONIE),
                              record.get(VEREIN.BRASS_BAND),
                              record.get(VEREIN.FANFARE),
                              record.get(VEREIN.TAMBOUREN),
                              record.get(VEREIN.PERKUSSIONSENSEMBLE),
                              record.get(VEREIN.CONFIRMED_AT) != null,
                              PhaseStatus.valueOf(record.get(VEREIN_STATUS.PHASE1)),
                              PhaseStatus.valueOf(record.get(VEREIN_STATUS.PHASE2))
                      ));
    }

    public Optional<VereinPojo> findByEmail(String email) {
        return jooqDsl.selectFrom(VEREIN)
                      .where(VEREIN.EMAIL.equalIgnoreCase(email))
                      .fetchOptionalInto(VereinPojo.class);
    }

    public Optional<VereinPojo> findById(Long id) {
        return jooqDsl.selectFrom(VEREIN)
                      .where(VEREIN.ID.eq(id))
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

    public void insert(VereinStatusPojo status) {
        vereinStatusDao.insert(status);
    }

    public void update(VereinStatusPojo status) {
        vereinStatusDao.update(status);
    }

    public VereinStatusPojo findStatusById(Long vereinId) {
        return vereinStatusDao.fetchOneByFkVerein(vereinId);
    }
}
