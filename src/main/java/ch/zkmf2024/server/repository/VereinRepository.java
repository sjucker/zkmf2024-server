package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.PhaseStatus;
import ch.zkmf2024.server.dto.TitelDTO;
import ch.zkmf2024.server.dto.VereinProgrammDTO;
import ch.zkmf2024.server.dto.VereinProgrammTitelDTO;
import ch.zkmf2024.server.dto.admin.VereinOverviewDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.KontaktDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.TitelDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinProgrammDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinProgrammTitelDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinStatusDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammTitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinStatusPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static ch.zkmf2024.server.jooq.generated.Tables.PROGRAMM_VORGABEN;
import static ch.zkmf2024.server.jooq.generated.Tables.TITEL;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM_TITEL;
import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;
import static ch.zkmf2024.server.jooq.generated.tables.VereinStatus.VEREIN_STATUS;
import static java.util.Comparator.comparing;

@Repository
public class VereinRepository {

    private final DSLContext jooqDsl;
    private final VereinDao vereinDao;
    private final KontaktDao kontaktDao;
    private final VereinStatusDao vereinStatusDao;
    private final TitelDao titelDao;
    private final VereinProgrammDao vereinProgrammDao;
    private final VereinProgrammTitelDao vereinProgrammTitelDao;

    public VereinRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.vereinDao = new VereinDao(jooqConfig);
        this.kontaktDao = new KontaktDao(jooqConfig);
        this.vereinStatusDao = new VereinStatusDao(jooqConfig);
        this.titelDao = new TitelDao(jooqConfig);
        this.vereinProgrammDao = new VereinProgrammDao(jooqConfig);
        this.vereinProgrammTitelDao = new VereinProgrammTitelDao(jooqConfig);
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
                              Klasse.fromString(record.get(VEREIN.KLASSE_MODULA)).orElse(null),
                              Klasse.fromString(record.get(VEREIN.KLASSE_MODULB)).orElse(null),
                              Klasse.fromString(record.get(VEREIN.KLASSE_MODULH)).orElse(null),
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

    public void insert(TitelPojo titel) {
        titelDao.insert(titel);
    }

    public void delete(TitelPojo titel) {
        titelDao.delete(titel);
    }

    public void update(TitelPojo titel) {
        titelDao.update(titel);
    }

    public VereinStatusPojo findStatusById(Long vereinId) {
        return vereinStatusDao.fetchOneByFkVerein(vereinId);
    }

    public List<TitelPojo> findTitelByVereinId(Long vereinId) {
        return jooqDsl.selectFrom(TITEL)
                      .where(TITEL.FK_VEREIN.eq(vereinId))
                      .fetchInto(TitelPojo.class);
    }

    public List<TitelDTO> findPflichtTitel(Modul modul, Klasse klasse, Besetzung besetzung) {
        if (modul == null || klasse == null || besetzung == null) {
            return List.of();
        }

        return jooqDsl.selectFrom(TITEL)
                      .where(
                              TITEL.MODUL.eq(modul.name()),
                              TITEL.KLASSE.eq(klasse.name()),
                              TITEL.BESETZUNG.eq(besetzung.name())
                      )
                      .fetch(record -> new TitelDTO(
                              record.get(TITEL.ID),
                              record.get(TITEL.TITEL_NAME),
                              record.get(TITEL.COMPOSER),
                              record.get(TITEL.ARRANGEUR),
                              null,
                              record.get(TITEL.DURATION_IN_SECONDS),
                              true
                      ));
    }

    public Optional<VereinProgrammPojo> findVereinProgramm(Long id) {
        return vereinProgrammDao.findOptionalById(id);
    }

    public List<VereinProgrammDTO> findProgramme(VereinPojo verein) {
        return jooqDsl.selectFrom(VEREIN_PROGRAMM)
                      .where(VEREIN_PROGRAMM.FK_VEREIN.eq(verein.getId()))
                      .orderBy(VEREIN_PROGRAMM.MODUL.asc())
                      .fetch(record -> {
                          var modul = Modul.valueOf(record.get(VEREIN_PROGRAMM.MODUL));
                          var klasse = modul.getRelevantKlasse(Klasse.fromString(verein.getKlasseModula()).orElse(null),
                                                               Klasse.fromString(verein.getKlasseModulb()).orElse(null),
                                                               Klasse.fromString(verein.getKlasseModulh()).orElse(null))
                                            .orElse(null);

                          var besetzung = modul.getRelevantBesetzung(verein.getHarmonie(), verein.getBrassBand(), verein.getFanfare());

                          var minMaxDuration = findMinMaxDuration(modul, klasse, besetzung);

                          return new VereinProgrammDTO(
                                  record.get(VEREIN_PROGRAMM.ID),
                                  modul.getFullDescription(),
                                  klasse != null ? klasse.getDescription() : null,
                                  besetzung != null ? besetzung.getDescription() : null,
                                  record.get(VEREIN_PROGRAMM.TITEL),
                                  record.get(VEREIN_PROGRAMM.INFO_MODERATION),
                                  record.get(VEREIN_PROGRAMM.TOTAL_DURATION_IN_SECONDS),
                                  minMaxDuration.map(MinMaxDuration::minDurationInSeconds).orElse(null),
                                  minMaxDuration.map(MinMaxDuration::maxDurationInSeconds).orElse(null),
                                  getAvailableTitel(verein.getId(), modul, klasse, besetzung),
                                  getVereinProgrammTitel(record.get(VEREIN_PROGRAMM.ID))
                          );
                      });
    }

    private List<TitelDTO> getAvailableTitel(Long vereinId, Modul modul, Klasse klasse, Besetzung besetzung) {
        var pflichtTitel = findPflichtTitel(modul, klasse, besetzung);

        var selbstwahlTitel = findTitelByVereinId(vereinId).stream()
                                                           .map(pojo -> new TitelDTO(
                                                                   pojo.getId(),
                                                                   pojo.getTitelName(),
                                                                   pojo.getComposer(),
                                                                   pojo.getArrangeur(),
                                                                   pojo.getGrad().floatValue(),
                                                                   pojo.getDurationInSeconds(),
                                                                   false
                                                           )).toList();

        return Stream.concat(pflichtTitel.stream(), selbstwahlTitel.stream())
                     .sorted(comparing(TitelDTO::titelName))
                     .toList();
    }

    private Optional<MinMaxDuration> findMinMaxDuration(Modul modul, Klasse klasse, Besetzung besetzung) {
        if (modul == null || klasse == null || besetzung == null) {
            return Optional.empty();
        }
        return jooqDsl.selectFrom(PROGRAMM_VORGABEN)
                      .where(
                              PROGRAMM_VORGABEN.MODUL.eq(modul.name()),
                              PROGRAMM_VORGABEN.KLASSE.eq(klasse.name()),
                              PROGRAMM_VORGABEN.BESETZUNG.eq(besetzung.name())
                      )
                      .fetchOptional()
                      .map(record -> new MinMaxDuration(record.get(PROGRAMM_VORGABEN.MIN_DURATION_IN_SECONDS), record.get(PROGRAMM_VORGABEN.MAX_DURATION_IN_SECONDS)));

    }

    private List<VereinProgrammTitelDTO> getVereinProgrammTitel(Long programmId) {
        var titel = jooqDsl.select()
                           .from(VEREIN_PROGRAMM_TITEL)
                           .join(TITEL).on(VEREIN_PROGRAMM_TITEL.FK_TITEL.eq(TITEL.ID))
                           .where(VEREIN_PROGRAMM_TITEL.FK_PROGRAMM.eq(programmId))
                           .orderBy(VEREIN_PROGRAMM_TITEL.POSITION.asc())
                           .fetch(record -> new VereinProgrammTitelDTO(
                                   new TitelDTO(
                                           record.get(TITEL.ID),
                                           record.get(TITEL.TITEL_NAME),
                                           record.get(TITEL.COMPOSER),
                                           null,
                                           null,
                                           record.get(TITEL.DURATION_IN_SECONDS),
                                           record.get(TITEL.FK_VEREIN) == null
                                   ),
                                   record.get(VEREIN_PROGRAMM_TITEL.APPLAUS_IN_SECONDS)
                           ));
        if (titel.isEmpty()) {
            // TODO add first dummy entry
        }

        return titel;
    }

    public void insert(VereinProgrammPojo vereinProgramm) {
        vereinProgrammDao.insert(vereinProgramm);
    }

    public void update(VereinProgrammPojo vereinProgramm) {
        vereinProgrammDao.update(vereinProgramm);
    }

    public void insert(VereinProgrammTitelPojo vereinProgrammTitelPojo) {
        vereinProgrammTitelDao.insert(vereinProgrammTitelPojo);
    }

    public void update(VereinProgrammTitelPojo vereinProgrammTitelPojo) {
        vereinProgrammTitelDao.update(vereinProgrammTitelPojo);
    }

    public List<VereinProgrammTitelPojo> findTitelByProgrammId(Long programmId) {
        return vereinProgrammTitelDao.fetchByFkProgramm(programmId);
    }

    public void delete(VereinProgrammTitelPojo pojo) {
        vereinProgrammTitelDao.delete(pojo);
    }

    private record MinMaxDuration(Integer minDurationInSeconds, Integer maxDurationInSeconds) {
    }
}
