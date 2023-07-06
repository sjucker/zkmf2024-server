package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.KontaktDTO;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.PhaseStatus;
import ch.zkmf2024.server.dto.TambourenGrundlage;
import ch.zkmf2024.server.dto.TitelDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VereinProgrammDTO;
import ch.zkmf2024.server.dto.VereinProgrammTitelDTO;
import ch.zkmf2024.server.dto.VereinTeilnahmeDTO;
import ch.zkmf2024.server.dto.VereinsangabenDTO;
import ch.zkmf2024.server.dto.VereinsanmeldungDTO;
import ch.zkmf2024.server.dto.VereinsinfoDTO;
import ch.zkmf2024.server.dto.admin.VereinOverviewDTO;
import ch.zkmf2024.server.jooq.generated.tables.Image;
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
import ch.zkmf2024.server.mapper.VereinMapper;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.dto.ImageType.VEREIN_BILD;
import static ch.zkmf2024.server.dto.ImageType.VEREIN_LOGO;
import static ch.zkmf2024.server.jooq.generated.Tables.IMAGE;
import static ch.zkmf2024.server.jooq.generated.Tables.KONTAKT;
import static ch.zkmf2024.server.jooq.generated.Tables.PROGRAMM_VORGABEN;
import static ch.zkmf2024.server.jooq.generated.Tables.TITEL;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM_TITEL;
import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;
import static ch.zkmf2024.server.jooq.generated.tables.VereinStatus.VEREIN_STATUS;
import static ch.zkmf2024.server.service.VereinService.calculateTotalDurationInSeconds;

@Repository
public class VereinRepository {

    private static final VereinMapper MAPPER = VereinMapper.INSTANCE;

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

    public List<VereinTeilnahmeDTO> findAllConfirmed() {
        Image vereinLogo = IMAGE.as("i2");
        Image vereinBild = IMAGE.as("i1");
        return jooqDsl.select(
                              VEREIN.VEREINSNAME,
                              VEREIN.WEBSITE_TEXT,
                              VEREIN.HOMEPAGE,
                              vereinLogo.ID,
                              vereinBild.ID
                      )
                      .from(VEREIN)
                      .leftJoin(vereinLogo).on(vereinLogo.FOREIGN_KEY.eq(VEREIN.ID).and(vereinLogo.TYPE.eq(VEREIN_LOGO.name())))
                      .leftJoin(vereinBild).on(vereinBild.FOREIGN_KEY.eq(VEREIN.ID).and(vereinBild.TYPE.eq(VEREIN_BILD.name())))
                      .where(VEREIN.CONFIRMED_AT.isNotNull())
                      .orderBy(VEREIN.VEREINSNAME.asc())
                      .fetch(it -> new VereinTeilnahmeDTO(
                              it.get(VEREIN.VEREINSNAME),
                              it.get(vereinLogo.ID),
                              it.get(vereinBild.ID),
                              it.get(VEREIN.HOMEPAGE),
                              it.get(VEREIN.WEBSITE_TEXT)
                      ));

    }

    public List<VereinOverviewDTO> findAllOverview() {
        return jooqDsl.select()
                      .from(VEREIN)
                      .join(VEREIN_STATUS).on(VEREIN.ID.eq(VEREIN_STATUS.FK_VEREIN))
                      .orderBy(VEREIN.VEREINSNAME)
                      .fetch(it -> new VereinOverviewDTO(
                              it.get(VEREIN.ID),
                              it.get(VEREIN.VEREINSNAME),
                              it.get(VEREIN.MODULA),
                              it.get(VEREIN.MODULB),
                              it.get(VEREIN.MODULC),
                              it.get(VEREIN.MODULD),
                              it.get(VEREIN.MODULE),
                              it.get(VEREIN.MODULF),
                              it.get(VEREIN.MODULG),
                              it.get(VEREIN.MODULH),
                              Klasse.fromString(it.get(VEREIN.KLASSE_MODULA)).orElse(null),
                              Klasse.fromString(it.get(VEREIN.KLASSE_MODULB)).orElse(null),
                              Klasse.fromString(it.get(VEREIN.KLASSE_MODULH)).orElse(null),
                              it.get(VEREIN.TAMBOUREN_KAT_A),
                              it.get(VEREIN.TAMBOUREN_KAT_B),
                              it.get(VEREIN.TAMBOUREN_KAT_C),
                              it.get(VEREIN.HARMONIE),
                              it.get(VEREIN.BRASS_BAND),
                              it.get(VEREIN.FANFARE),
                              it.get(VEREIN.TAMBOUREN),
                              it.get(VEREIN.PERKUSSIONSENSEMBLE),
                              it.get(VEREIN.CONFIRMED_AT) != null,
                              PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE1)),
                              PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE2))
                      ));
    }

    public List<VereinDTO> findAllForExport() {
        var praesident = KONTAKT.as("praesident");
        var direktion = KONTAKT.as("direktion");
        return jooqDsl.select()
                      .from(VEREIN)
                      .join(praesident).on(VEREIN.PRAESIDENT_KONTAKT_ID.eq(praesident.ID))
                      .join(direktion).on(VEREIN.DIREKTION_KONTAKT_ID.eq(direktion.ID))
                      .orderBy(VEREIN.VEREINSNAME)
                      .fetch(it -> new VereinDTO(
                              it.get(VEREIN.EMAIL),
                              new VereinsangabenDTO(
                                      it.get(VEREIN.VEREINSNAME),
                                      it.get(VEREIN.ADRESSE),
                                      it.get(VEREIN.PLZ),
                                      it.get(VEREIN.ORT),
                                      it.get(VEREIN.HOMEPAGE),
                                      it.get(VEREIN.IBAN),
                                      it.get(VEREIN.DIREKTION_DOPPELEINSATZ),
                                      it.get(VEREIN.DIREKTION_DOPPELEINSATZ_VEREIN),
                                      it.get(VEREIN.MITSPIELER_DOPPELEINSATZ)
                              ),
                              new KontaktDTO(
                                      it.get(praesident.VORNAME),
                                      it.get(praesident.NACHNAME),
                                      it.get(praesident.ADRESSE),
                                      it.get(praesident.PLZ),
                                      it.get(praesident.ORT),
                                      it.get(praesident.EMAIL),
                                      it.get(praesident.TELEFON_PRIVAT),
                                      it.get(praesident.TELEFON_MOBILE)
                              ),
                              new KontaktDTO(
                                      it.get(direktion.VORNAME),
                                      it.get(direktion.NACHNAME),
                                      it.get(direktion.ADRESSE),
                                      it.get(direktion.PLZ),
                                      it.get(direktion.ORT),
                                      it.get(direktion.EMAIL),
                                      it.get(direktion.TELEFON_PRIVAT),
                                      it.get(direktion.TELEFON_MOBILE)
                              ),
                              new VereinsanmeldungDTO(
                                      it.get(VEREIN.MODULA),
                                      it.get(VEREIN.MODULB),
                                      it.get(VEREIN.MODULC),
                                      it.get(VEREIN.MODULD),
                                      it.get(VEREIN.MODULE),
                                      it.get(VEREIN.MODULF),
                                      it.get(VEREIN.MODULG),
                                      it.get(VEREIN.MODULH),
                                      Klasse.fromString(it.get(VEREIN.KLASSE_MODULA)).orElse(null),
                                      Klasse.fromString(it.get(VEREIN.KLASSE_MODULB)).orElse(null),
                                      Klasse.fromString(it.get(VEREIN.KLASSE_MODULH)).orElse(null),
                                      it.get(VEREIN.TAMBOUREN_KAT_A),
                                      it.get(VEREIN.TAMBOUREN_KAT_B),
                                      it.get(VEREIN.TAMBOUREN_KAT_C),
                                      it.get(VEREIN.HARMONIE),
                                      it.get(VEREIN.BRASS_BAND),
                                      it.get(VEREIN.FANFARE),
                                      it.get(VEREIN.TAMBOUREN),
                                      it.get(VEREIN.PERKUSSIONSENSEMBLE)
                              ),
                              new VereinsinfoDTO(null, null, ""),
                              it.get(VEREIN.CONFIRMED_AT) != null,
                              List.of()
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

    public List<VereinPojo> findAll() {
        return vereinDao.findAll();
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

    public TitelPojo findTitelById(Long id) {
        return titelDao.findById(id);
    }

    public VereinStatusPojo findStatusById(Long vereinId) {
        return vereinStatusDao.fetchOneByFkVerein(vereinId);
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
                      .fetch(it -> new TitelDTO(
                              it.get(TITEL.ID),
                              Modul.valueOf(it.get(TITEL.MODUL)),
                              it.get(TITEL.TITEL_NAME),
                              it.get(TITEL.COMPOSER),
                              it.get(TITEL.ARRANGEUR),
                              null,
                              null,
                              it.get(TITEL.DURATION_IN_SECONDS),
                              true,
                              it.get(TITEL.INFO_MODERATION)
                      ));
    }

    public Optional<VereinProgrammPojo> findVereinProgramm(Long id) {
        return vereinProgrammDao.findOptionalById(id);
    }

    public List<VereinProgrammDTO> findProgramme(VereinPojo verein) {
        return jooqDsl.select()
                      .from(VEREIN_PROGRAMM)
                      .join(VEREIN).on(VEREIN_PROGRAMM.FK_VEREIN.eq(VEREIN.ID))
                      .where(VEREIN_PROGRAMM.FK_VEREIN.eq(verein.getId()))
                      .orderBy(VEREIN_PROGRAMM.MODUL.asc())
                      .fetch(it -> {
                          var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
                          var klasse = Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).orElse(null);
                          var besetzung = Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).orElse(null);

                          var minMaxDuration = findMinMaxDuration(modul, klasse, besetzung);
                          var ablauf = getVereinProgrammTitel(it.get(VEREIN_PROGRAMM.ID), modul, klasse, besetzung);

                          return new VereinProgrammDTO(
                                  it.get(VEREIN_PROGRAMM.ID),
                                  modul,
                                  modul.getFullDescription(),
                                  klasse != null ? klasse.getDescription() : null,
                                  besetzung != null ? besetzung.getDescription() : null,
                                  it.get(VEREIN_PROGRAMM.TITEL),
                                  it.get(VEREIN_PROGRAMM.INFO_MODERATION),
                                  calculateTotalDurationInSeconds(ablauf),
                                  minMaxDuration.map(MinMaxDuration::minDurationInSeconds).orElse(null),
                                  minMaxDuration.map(MinMaxDuration::maxDurationInSeconds).orElse(null),
                                  ablauf,
                                  modul == Modul.G && it.get(VEREIN.TAMBOUREN_KAT_A),
                                  modul == Modul.G && it.get(VEREIN.TAMBOUREN_KAT_B),
                                  modul == Modul.G && it.get(VEREIN.TAMBOUREN_KAT_C),
                                  TambourenGrundlage.fromString(it.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_1)).orElse(null),
                                  TambourenGrundlage.fromString(it.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_2)).orElse(null),
                                  getTitelOrEmpty(it.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_1_ID), modul),
                                  getTitelOrEmpty(it.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_2_ID), modul),
                                  getTitelOrEmpty(it.get(VEREIN_PROGRAMM.MODUL_G_KAT_B_TITEL_ID), modul),
                                  getTitelOrEmpty(it.get(VEREIN_PROGRAMM.MODUL_G_KAT_C_TITEL_ID), modul),
                                  it.get(VEREIN_PROGRAMM.MODUL_B_PA),
                                  it.get(VEREIN_PROGRAMM.MODUL_B_EGITARRE),
                                  it.get(VEREIN_PROGRAMM.MODUL_B_EBASS),
                                  it.get(VEREIN_PROGRAMM.MODUL_B_KEYBOARD),
                                  it.get(VEREIN_PROGRAMM.MODUL_B_GESANG),
                                  getTitelOrEmpty(it.get(VEREIN_PROGRAMM.MODUL_D_TITEL_1_ID), modul),
                                  getTitelOrEmpty(it.get(VEREIN_PROGRAMM.MODUL_D_TITEL_2_ID), modul)
                          );
                      });
    }

    private TitelDTO getTitelOrEmpty(Long titelId, Modul modul) {
        return titelDao.findOptionalById(titelId)
                       .map(MAPPER::toDTO)
                       .orElse(new TitelDTO(null, modul, null, null, null, null, null, 0, false, null));
    }

    private Optional<MinMaxDuration> findMinMaxDuration(Modul modul, Klasse klasse, Besetzung besetzung) {
        if (klasse != null && besetzung != null) {
            return jooqDsl.selectFrom(PROGRAMM_VORGABEN)
                          .where(
                                  PROGRAMM_VORGABEN.MODUL.eq(modul.name()),
                                  PROGRAMM_VORGABEN.KLASSE.eq(klasse.name()),
                                  PROGRAMM_VORGABEN.BESETZUNG.eq(besetzung.name())
                          )
                          .fetchOptional()
                          .map(it -> new MinMaxDuration(it.get(PROGRAMM_VORGABEN.MIN_DURATION_IN_SECONDS),
                                                        it.get(PROGRAMM_VORGABEN.MAX_DURATION_IN_SECONDS)));
        } else {
            return switch (modul) {
                case C -> Optional.of(new MinMaxDuration(30 * 60, 45 * 60));
                case E, F -> Optional.of(new MinMaxDuration(8 * 60, 10 * 60));
                default -> Optional.empty();
            };
        }

    }

    private List<VereinProgrammTitelDTO> getVereinProgrammTitel(Long programmId, Modul modul, Klasse klasse, Besetzung besetzung) {
        var ablauf = jooqDsl.select()
                            .from(VEREIN_PROGRAMM_TITEL)
                            .join(TITEL).on(VEREIN_PROGRAMM_TITEL.FK_TITEL.eq(TITEL.ID))
                            .where(VEREIN_PROGRAMM_TITEL.FK_PROGRAMM.eq(programmId))
                            .orderBy(VEREIN_PROGRAMM_TITEL.POSITION.asc())
                            .fetch(it -> new VereinProgrammTitelDTO(
                                    new TitelDTO(
                                            it.get(TITEL.ID),
                                            Modul.valueOf(it.get(TITEL.MODUL)),
                                            it.get(TITEL.TITEL_NAME),
                                            it.get(TITEL.COMPOSER),
                                            it.get(TITEL.ARRANGEUR),
                                            it.get(TITEL.GRAD) != null ? it.get(TITEL.GRAD).floatValue() : null,
                                            it.get(TITEL.SCHWIERIGKEITSGRAD),
                                            it.get(TITEL.DURATION_IN_SECONDS),
                                            it.get(TITEL.FK_VEREIN) == null,
                                            it.get(TITEL.INFO_MODERATION)
                                    ),
                                    it.get(VEREIN_PROGRAMM_TITEL.APPLAUS_IN_SECONDS)
                            ));

        // first time loading the Programm, automatically add the Pflichtitel (which cannot be deleted)
        if (ablauf.isEmpty()) {
            var pflichtTitel = findPflichtTitel(modul, klasse, besetzung);
            if (!pflichtTitel.isEmpty()) {
                ablauf = pflichtTitel.stream()
                                     .map(titel -> new VereinProgrammTitelDTO(titel, null))
                                     .toList();
            }
        }

        return ablauf;
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
