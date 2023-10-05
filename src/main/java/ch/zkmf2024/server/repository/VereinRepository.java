package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.DoppelEinsatzDTO;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.KontaktDTO;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.PhaseStatus;
import ch.zkmf2024.server.dto.TambourenGrundlage;
import ch.zkmf2024.server.dto.TitelDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VereinProgrammDTO;
import ch.zkmf2024.server.dto.VereinProgrammTitelDTO;
import ch.zkmf2024.server.dto.VereinSelectionDTO;
import ch.zkmf2024.server.dto.VereinTeilnahmeDTO;
import ch.zkmf2024.server.dto.VereinsangabenDTO;
import ch.zkmf2024.server.dto.VereinsanmeldungDTO;
import ch.zkmf2024.server.dto.VereinsinfoDTO;
import ch.zkmf2024.server.dto.admin.VereinOverviewDTO;
import ch.zkmf2024.server.dto.admin.VereinProgrammSelectionDTO;
import ch.zkmf2024.server.jooq.generated.tables.Image;
import ch.zkmf2024.server.jooq.generated.tables.daos.KontaktDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.TitelDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinCommentDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinDoppeleinsatzDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinProgrammDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinProgrammTitelDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinStatusDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinCommentPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinDoppeleinsatzPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammTitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinStatusPojo;
import ch.zkmf2024.server.mapper.VereinMapper;
import ch.zkmf2024.server.repository.ProgrammVorgabenRepository.MinMaxDuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.tools.StopWatch;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import static ch.zkmf2024.server.dto.ImageType.VEREIN_BILD;
import static ch.zkmf2024.server.dto.ImageType.VEREIN_LOGO;
import static ch.zkmf2024.server.dto.PhaseStatus.DONE;
import static ch.zkmf2024.server.jooq.generated.Tables.IMAGE;
import static ch.zkmf2024.server.jooq.generated.Tables.KONTAKT;
import static ch.zkmf2024.server.jooq.generated.Tables.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.jooq.generated.Tables.TITEL;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_COMMENT;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_DOPPELEINSATZ;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM_TITEL;
import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;
import static ch.zkmf2024.server.jooq.generated.tables.VereinStatus.VEREIN_STATUS;
import static ch.zkmf2024.server.service.VereinService.calculateTotalDurationInSeconds;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.notExists;
import static org.jooq.impl.DSL.selectCount;
import static org.jooq.impl.DSL.selectOne;

@Repository
public class VereinRepository {

    private static final VereinMapper MAPPER = VereinMapper.INSTANCE;

    private final ProgrammVorgabenRepository programmVorgabenRepository;
    private final DSLContext jooqDsl;
    private final VereinDao vereinDao;
    private final KontaktDao kontaktDao;
    private final VereinStatusDao vereinStatusDao;
    private final TitelDao titelDao;
    private final VereinProgrammDao vereinProgrammDao;
    private final VereinProgrammTitelDao vereinProgrammTitelDao;
    private final VereinCommentDao vereinCommentDao;
    private final VereinDoppeleinsatzDao vereinDoppeleinsatzDao;

    public VereinRepository(ProgrammVorgabenRepository programmVorgabenRepository,
                            DSLContext jooqDsl,
                            DefaultConfiguration jooqConfig) {
        this.programmVorgabenRepository = programmVorgabenRepository;
        this.jooqDsl = jooqDsl;
        this.vereinDao = new VereinDao(jooqConfig);
        this.kontaktDao = new KontaktDao(jooqConfig);
        this.vereinStatusDao = new VereinStatusDao(jooqConfig);
        this.titelDao = new TitelDao(jooqConfig);
        this.vereinProgrammDao = new VereinProgrammDao(jooqConfig);
        this.vereinProgrammTitelDao = new VereinProgrammTitelDao(jooqConfig);
        this.vereinCommentDao = new VereinCommentDao(jooqConfig);
        this.vereinDoppeleinsatzDao = new VereinDoppeleinsatzDao(jooqConfig);
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
        return jooqDsl.select(
                              VEREIN.asterisk(),
                              VEREIN_STATUS.PHASE1,
                              VEREIN_STATUS.PHASE2,
                              field(selectCount().from(VEREIN_COMMENT).where(VEREIN_COMMENT.FK_VEREIN.eq(VEREIN.ID))).as("COMMENT_COUNT")
                      )
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
                              it.get(VEREIN.PHASE2_CONFIRMED_AT) != null,
                              PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE1)),
                              PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE2)),
                              it.get("COMMENT_COUNT", Integer.class) > 0
                      ));
    }

    public List<VereinSelectionDTO> findAllNotYetPlanned() {
        return jooqDsl.select()
                      .from(VEREIN)
                      .where(
                              VEREIN.CONFIRMED_AT.isNotNull(),
                              VEREIN.PHASE2_CONFIRMED_AT.isNotNull(),
                              DSL.notExists(selectOne().from(TIMETABLE_ENTRY).where(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID)))
                      )
                      .orderBy(VEREIN.VEREINSNAME)
                      .fetch(it -> new VereinSelectionDTO(it.get(VEREIN.ID), it.get(VEREIN.VEREINSNAME)));
    }

    public List<VereinDTO> findAllForExport() {
        var stopWatch = new StopWatch();
        var programmePerVereinId = findProgrammePerVereinId();
        var doppeleinsatzPerVereinId = findDoppeleinsatzPerVereinId();

        stopWatch.splitInfo("findProgrammePerVereinId");

        var praesident = KONTAKT.as("praesident");
        var direktion = KONTAKT.as("direktion");
        var result = jooqDsl.select()
                            .from(VEREIN)
                            .join(praesident).on(VEREIN.PRAESIDENT_KONTAKT_ID.eq(praesident.ID))
                            .join(direktion).on(VEREIN.DIREKTION_KONTAKT_ID.eq(direktion.ID))
                            .join(VEREIN_STATUS).on(VEREIN.ID.eq(VEREIN_STATUS.FK_VEREIN))
                            .orderBy(VEREIN.VEREINSNAME)
                            .fetch(it -> new VereinDTO(
                                    it.get(VEREIN.EMAIL),
                                    new VereinsangabenDTO(
                                            it.get(VEREIN.VEREINSNAME),
                                            it.get(VEREIN.ADRESSE),
                                            it.get(VEREIN.PLZ),
                                            it.get(VEREIN.ORT),
                                            it.get(VEREIN.HOMEPAGE),
                                            it.get(VEREIN.FACEBOOK),
                                            it.get(VEREIN.INSTAGRAM),
                                            it.get(VEREIN.IBAN),
                                            it.get(VEREIN.DIREKTION_DOPPELEINSATZ),
                                            it.get(VEREIN.DIREKTION_DOPPELEINSATZ_VEREIN),
                                            it.get(VEREIN.MITSPIELER_DOPPELEINSATZ)
                                    ),
                                    doppeleinsatzPerVereinId.getOrDefault(it.get(VEREIN.ID), new ArrayList<>()),
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
                                    programmePerVereinId.getOrDefault(it.get(VEREIN.ID), new ArrayList<>()),
                                    PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE1)) == DONE,
                                    PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE2)) == DONE,
                                    it.get(VEREIN.PHASE2_CONFIRMED_BY),
                                    it.get(VEREIN.PHASE2_CONFIRMED_AT),
                                    it.get(VEREIN.PROV_WETTSPIEL),
                                    it.get(VEREIN.PROV_PARADEMUSIK),
                                    it.get(VEREIN.PROV_PLATZKONZERT)
                            ));

        stopWatch.splitInfo("findAllForExport");
        return result;
    }

    private Map<Long, List<DoppelEinsatzDTO>> findDoppeleinsatzPerVereinId() {
        return jooqDsl.select()
                      .from(VEREIN_DOPPELEINSATZ)
                      .join(VEREIN).on(VEREIN_DOPPELEINSATZ.FK_OTHER_VEREIN.eq(VEREIN.ID))
                      .stream()
                      .collect(groupingBy(it -> it.get(VEREIN_DOPPELEINSATZ.FK_VEREIN),
                                          mapping(it -> new DoppelEinsatzDTO(
                                                  new VereinSelectionDTO(it.get(VEREIN.ID), it.get(VEREIN.VEREINSNAME)),
                                                  it.get(VEREIN_DOPPELEINSATZ.NAME)
                                          ), toList())));
    }

    public Map<Long, List<VereinProgrammDTO>> findProgrammePerVereinId() {
        Map<Long, Map<Modul, List<Record>>> rows = jooqDsl.select()
                                                          .from(VEREIN)
                                                          .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.FK_VEREIN.eq(VEREIN.ID))
                                                          .leftJoin(VEREIN_PROGRAMM_TITEL).on(VEREIN_PROGRAMM_TITEL.FK_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                                                          .leftJoin(TITEL).on(TITEL.ID.eq(VEREIN_PROGRAMM_TITEL.FK_TITEL))
                                                          .orderBy(VEREIN_PROGRAMM_TITEL.POSITION.asc())
                                                          .stream()
                                                          .collect(groupingBy(it -> it.get(VEREIN.ID),
                                                                              groupingBy(it -> Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)),
                                                                                         toList())));

        var result = new HashMap<Long, Map<Modul, VereinProgrammDTO>>();
        for (var entry : rows.entrySet()) {
            var vereinId = entry.getKey();
            var value = entry.getValue();

            var programmPerModul = result.computeIfAbsent(vereinId, key -> new HashMap<>());
            for (var recordEntry : value.entrySet()) {
                var records = recordEntry.getValue();

                for (var r : records) {

                    var vereinProgrammDTO = programmPerModul.computeIfAbsent(recordEntry.getKey(), key -> {
                        var modul = Modul.valueOf(r.get(VEREIN_PROGRAMM.MODUL));
                        return new VereinProgrammDTO(
                                r.get(VEREIN_PROGRAMM.ID),
                                modul,
                                modul.getFullDescription(),
                                Klasse.fromString(r.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).orElse(null),
                                Besetzung.fromString(r.get(VEREIN_PROGRAMM.BESETZUNG)).map(Besetzung::getDescription).orElse(null),
                                r.get(VEREIN_PROGRAMM.TITEL),
                                r.get(VEREIN_PROGRAMM.INFO_MODERATION),
                                0,
                                null,
                                null,
                                new ArrayList<>(),
                                modul == Modul.G && r.get(VEREIN.TAMBOUREN_KAT_A),
                                modul == Modul.G && r.get(VEREIN.TAMBOUREN_KAT_B),
                                modul == Modul.G && r.get(VEREIN.TAMBOUREN_KAT_C),
                                TambourenGrundlage.fromString(r.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_1)).orElse(null),
                                TambourenGrundlage.fromString(r.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_2)).orElse(null),
                                getTitelOrEmpty(r.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_1_ID), modul),
                                getTitelOrEmpty(r.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_2_ID), modul),
                                getTitelOrEmpty(r.get(VEREIN_PROGRAMM.MODUL_G_KAT_B_TITEL_ID), modul),
                                getTitelOrEmpty(r.get(VEREIN_PROGRAMM.MODUL_G_KAT_C_TITEL_ID), modul),
                                r.get(VEREIN_PROGRAMM.MODUL_B_PA),
                                r.get(VEREIN_PROGRAMM.MODUL_B_EGITARRE),
                                r.get(VEREIN_PROGRAMM.MODUL_B_EBASS),
                                r.get(VEREIN_PROGRAMM.MODUL_B_KEYBOARD),
                                r.get(VEREIN_PROGRAMM.MODUL_B_GESANG),
                                getTitelOrEmpty(r.get(VEREIN_PROGRAMM.MODUL_D_TITEL_1_ID), modul),
                                getTitelOrEmpty(r.get(VEREIN_PROGRAMM.MODUL_D_TITEL_2_ID), modul)
                        );
                    });

                    if (r.get(TITEL.ID) != null) {
                        vereinProgrammDTO.ablauf().add(toVereinProgrammTitelDTO(r));
                    }
                }
            }
        }

        return result.entrySet().stream()
                     .collect(toMap(Entry::getKey,
                                    entry -> entry.getValue().values().stream().toList()));
    }

    private VereinProgrammTitelDTO toVereinProgrammTitelDTO(Record it) {
        return new VereinProgrammTitelDTO(
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
        );
    }

    public List<Long> findAllVereinIds() {
        return jooqDsl.select()
                      .from(VEREIN)
                      .fetch(VEREIN.ID);
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

    public void insert(VereinCommentPojo vereinComment) {
        vereinCommentDao.insert(vereinComment);
    }

    public void insert(VereinDoppeleinsatzPojo doppeleinsatz) {
        vereinDoppeleinsatzDao.insert(doppeleinsatz);
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

    public List<VereinProgrammSelectionDTO> findProgrammeSelection(Long vereinId) {
        return jooqDsl.select()
                      .from(VEREIN_PROGRAMM)
                      .where(VEREIN_PROGRAMM.FK_VEREIN.eq(vereinId))
                      .and(notExists(selectOne().from(TIMETABLE_ENTRY).where(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))))
                      .fetch(it -> new VereinProgrammSelectionDTO(
                              it.get(VEREIN_PROGRAMM.ID),
                              Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)).getFullDescription()
                      ));
    }

    public List<VereinProgrammDTO> findProgramme(Long vereinId) {
        return jooqDsl.select()
                      .from(VEREIN_PROGRAMM)
                      .join(VEREIN).on(VEREIN_PROGRAMM.FK_VEREIN.eq(VEREIN.ID))
                      .where(VEREIN_PROGRAMM.FK_VEREIN.eq(vereinId))
                      .orderBy(VEREIN_PROGRAMM.MODUL.asc())
                      .fetch(it -> {
                          var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
                          var klasse = Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).orElse(null);
                          var besetzung = Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).orElse(null);

                          var minMaxDuration = programmVorgabenRepository.findMinMaxDuration(modul, klasse, besetzung);
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
        if (titelId == null) {
            return TitelDTO.empty(modul);
        }
        return titelDao.findOptionalById(titelId)
                       .map(MAPPER::toDTO)
                       .orElse(TitelDTO.empty(modul));
    }

    private List<VereinProgrammTitelDTO> getVereinProgrammTitel(Long programmId, Modul modul, Klasse klasse, Besetzung besetzung) {
        var ablauf = jooqDsl.select()
                            .from(VEREIN_PROGRAMM_TITEL)
                            .join(TITEL).on(VEREIN_PROGRAMM_TITEL.FK_TITEL.eq(TITEL.ID))
                            .where(VEREIN_PROGRAMM_TITEL.FK_PROGRAMM.eq(programmId))
                            .orderBy(VEREIN_PROGRAMM_TITEL.POSITION.asc())
                            .fetch(this::toVereinProgrammTitelDTO);

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

    public List<VereinCommentPojo> findCommentByVereinId(Long vereinId) {
        return vereinCommentDao.fetchByFkVerein(vereinId);
    }

    public void deleteDoppeleinsatzByVereinId(Long vereinId) {
        jooqDsl.deleteFrom(VEREIN_DOPPELEINSATZ)
               .where(VEREIN_DOPPELEINSATZ.FK_VEREIN.eq(vereinId))
               .execute();
    }

    public List<DoppelEinsatzDTO> findDoppeleinsatz(Long vereinId) {
        return jooqDsl.select()
                      .from(VEREIN_DOPPELEINSATZ)
                      .join(VEREIN).on(VEREIN_DOPPELEINSATZ.FK_OTHER_VEREIN.eq(VEREIN.ID))
                      .where(VEREIN_DOPPELEINSATZ.FK_VEREIN.eq(vereinId))
                      .fetch(it -> new DoppelEinsatzDTO(
                              new VereinSelectionDTO(
                                      it.get(VEREIN.ID),
                                      it.get(VEREIN.VEREINSNAME)
                              ),
                              it.get(VEREIN_DOPPELEINSATZ.NAME)
                      ));
    }

}
