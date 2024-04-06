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
import ch.zkmf2024.server.dto.VereinPresentationDTO;
import ch.zkmf2024.server.dto.VereinProgrammDTO;
import ch.zkmf2024.server.dto.VereinProgrammTitelDTO;
import ch.zkmf2024.server.dto.VereinSelectionDTO;
import ch.zkmf2024.server.dto.VereinTeilnahmeDTO;
import ch.zkmf2024.server.dto.VereinTimetableEntryDTO;
import ch.zkmf2024.server.dto.VereinsangabenDTO;
import ch.zkmf2024.server.dto.VereinsanmeldungDTO;
import ch.zkmf2024.server.dto.VereinsanmeldungDetailDTO;
import ch.zkmf2024.server.dto.VereinsinfoDTO;
import ch.zkmf2024.server.dto.admin.VereinOverviewDTO;
import ch.zkmf2024.server.dto.admin.VereinProgrammSelectionDTO;
import ch.zkmf2024.server.jooq.generated.tables.Image;
import ch.zkmf2024.server.jooq.generated.tables.daos.KontaktDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.TitelDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinAnmeldungAdhocOrchesterDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinAnmeldungDetailDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinAnmeldungNichtmitgliederDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinCommentDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinDoppeleinsatzDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinMessageDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinProgrammDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinProgrammTitelDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.VereinStatusDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinAnmeldungAdhocOrchesterPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinAnmeldungDetailPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinAnmeldungNichtmitgliederPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinCommentPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinDoppeleinsatzPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinMessagePojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammTitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinStatusPojo;
import ch.zkmf2024.server.mapper.VereinMapper;
import ch.zkmf2024.server.repository.ProgrammVorgabenRepository.MinMaxDuration;
import ch.zkmf2024.server.util.DateUtil;
import ch.zkmf2024.server.util.FormatUtil;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.tools.StopWatch;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.StringJoiner;

import static ch.zkmf2024.server.dto.ImageType.VEREIN_BILD;
import static ch.zkmf2024.server.dto.ImageType.VEREIN_LOGO;
import static ch.zkmf2024.server.dto.Modul.A;
import static ch.zkmf2024.server.dto.Modul.B;
import static ch.zkmf2024.server.dto.Modul.D;
import static ch.zkmf2024.server.dto.Modul.H;
import static ch.zkmf2024.server.dto.PhaseStatus.DONE;
import static ch.zkmf2024.server.jooq.generated.Tables.IMAGE;
import static ch.zkmf2024.server.jooq.generated.Tables.KONTAKT;
import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.jooq.generated.Tables.TITEL;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_ANMELDUNG_DETAIL;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_COMMENT;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_DOPPELEINSATZ;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_MESSAGE;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM_TITEL;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.MARSCHMUSIK;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.PLATZKONZERT;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.WETTSPIEL;
import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;
import static ch.zkmf2024.server.jooq.generated.tables.VereinStatus.VEREIN_STATUS;
import static ch.zkmf2024.server.service.VereinService.calculateTotalDurationInSeconds;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
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
    private final VereinMessageDao vereinMessageDao;
    private final VereinDoppeleinsatzDao vereinDoppeleinsatzDao;
    private final VereinAnmeldungDetailDao vereinAnmeldungDetailDao;
    private final VereinAnmeldungAdhocOrchesterDao vereinAnmeldungAdhocOrchesterDao;
    private final VereinAnmeldungNichtmitgliederDao vereinAnmeldungNichtmitgliederDao;

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
        this.vereinMessageDao = new VereinMessageDao(jooqConfig);
        this.vereinDoppeleinsatzDao = new VereinDoppeleinsatzDao(jooqConfig);
        this.vereinAnmeldungDetailDao = new VereinAnmeldungDetailDao(jooqConfig);
        this.vereinAnmeldungAdhocOrchesterDao = new VereinAnmeldungAdhocOrchesterDao(jooqConfig);
        this.vereinAnmeldungNichtmitgliederDao = new VereinAnmeldungNichtmitgliederDao(jooqConfig);
    }

    public List<VereinTeilnahmeDTO> findAllConfirmed() {
        Image vereinLogo = IMAGE.as("i2");
        Image vereinBild = IMAGE.as("i1");
        return jooqDsl.select(
                              VEREIN.ID,
                              VEREIN.IDENTIFIER,
                              VEREIN.VEREINSNAME,
                              VEREIN.WEBSITE_TEXT,
                              VEREIN.HOMEPAGE,
                              VEREIN.FACEBOOK,
                              VEREIN.INSTAGRAM,
                              vereinLogo.CLOUDFLARE_ID,
                              vereinBild.CLOUDFLARE_ID
                      )
                      .from(VEREIN)
                      .leftJoin(vereinLogo).on(vereinLogo.FOREIGN_KEY.eq(VEREIN.ID).and(vereinLogo.TYPE.eq(VEREIN_LOGO.name())))
                      .leftJoin(vereinBild).on(vereinBild.FOREIGN_KEY.eq(VEREIN.ID).and(vereinBild.TYPE.eq(VEREIN_BILD.name())))
                      .where(VEREIN.CONFIRMED_AT.isNotNull())
                      .orderBy(VEREIN.VEREINSNAME.asc())
                      .fetch(it -> new VereinTeilnahmeDTO(
                              it.get(VEREIN.ID),
                              it.get(VEREIN.IDENTIFIER),
                              it.get(VEREIN.VEREINSNAME),
                              it.get(vereinLogo.CLOUDFLARE_ID),
                              it.get(vereinBild.CLOUDFLARE_ID),
                              it.get(VEREIN.HOMEPAGE),
                              it.get(VEREIN.FACEBOOK),
                              it.get(VEREIN.INSTAGRAM),
                              it.get(VEREIN.WEBSITE_TEXT)
                      ));
    }

    public Optional<VereinPresentationDTO> findPresentationByIdentifier(String identifier) {
        Image vereinLogo = IMAGE.as("i2");
        Image vereinBild = IMAGE.as("i1");
        return jooqDsl.select(
                              VEREIN.ID,
                              VEREIN.VEREINSNAME,
                              VEREIN.WEBSITE_TEXT,
                              VEREIN.HOMEPAGE,
                              VEREIN.FACEBOOK,
                              VEREIN.INSTAGRAM,
                              vereinLogo.CLOUDFLARE_ID,
                              vereinBild.CLOUDFLARE_ID,
                              KONTAKT.VORNAME,
                              KONTAKT.NACHNAME
                      )
                      .from(VEREIN)
                      .join(KONTAKT).on(KONTAKT.ID.eq(VEREIN.DIREKTION_KONTAKT_ID))
                      .leftJoin(vereinLogo).on(vereinLogo.FOREIGN_KEY.eq(VEREIN.ID).and(vereinLogo.TYPE.eq(VEREIN_LOGO.name())))
                      .leftJoin(vereinBild).on(vereinBild.FOREIGN_KEY.eq(VEREIN.ID).and(vereinBild.TYPE.eq(VEREIN_BILD.name())))
                      .where(
                              VEREIN.IDENTIFIER.eq(identifier),
                              VEREIN.CONFIRMED_AT.isNotNull()
                      )
                      .fetchOptional(it -> new VereinPresentationDTO(
                              it.get(VEREIN.ID),
                              it.get(VEREIN.VEREINSNAME),
                              getDirektionName(it).orElse(null),
                              it.get(vereinLogo.CLOUDFLARE_ID),
                              it.get(vereinBild.CLOUDFLARE_ID),
                              it.get(VEREIN.HOMEPAGE),
                              it.get(VEREIN.FACEBOOK),
                              it.get(VEREIN.INSTAGRAM),
                              it.get(VEREIN.WEBSITE_TEXT),
                              findTimetableEntriesByVereinId(it.get(VEREIN.ID))
                      ));
    }

    private Optional<String> getDirektionName(Record it) {
        var vorname = it.get(KONTAKT.VORNAME);
        var nachname = it.get(KONTAKT.NACHNAME);
        if (isNotBlank(vorname) && isNotBlank(nachname)) {
            return Optional.of("%s %s".formatted(vorname, nachname));
        }
        return Optional.empty();
    }

    private List<VereinTimetableEntryDTO> findTimetableEntriesByVereinId(Long id) {
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .where(
                              TIMETABLE_ENTRY.FK_VEREIN.eq(id),
                              TIMETABLE_ENTRY.ENTRY_TYPE.in(WETTSPIEL, PLATZKONZERT, MARSCHMUSIK)
                      )
                      .orderBy(TIMETABLE_ENTRY.DATE, TIMETABLE_ENTRY.START_TIME, TIMETABLE_ENTRY.END_TIME)
                      .fetch(it -> new VereinTimetableEntryDTO(
                              Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)),
                              getCompetition(it),
                              LocationRepository.toDTO(it),
                              getDateTime(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.START_TIME), it.get(TIMETABLE_ENTRY.END_TIME)),
                              it.get(VEREIN_PROGRAMM.TITEL),
                              it.get(VEREIN_PROGRAMM.INFO_MODERATION),
                              getProgramm(it)
                      ));
    }

    private List<TitelDTO> getProgramm(Record it) {
        var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
        if (modul.isParademusik()) {
            return jooqDsl.select()
                          .from(TITEL)
                          .join(VEREIN).on(VEREIN.ID.eq(it.get(VEREIN_PROGRAMM.FK_VEREIN)))
                          .where(TITEL.ID.in(it.get(VEREIN_PROGRAMM.MODUL_D_TITEL_1_ID), it.get(VEREIN_PROGRAMM.MODUL_D_TITEL_2_ID)),
                                 VEREIN.PHASE2_CONFIRMED_AT.isNotNull())
                          .fetch(this::toTitelDTO);
        } else {
            return jooqDsl.select()
                          .from(VEREIN_PROGRAMM_TITEL)
                          .join(TITEL).on(VEREIN_PROGRAMM_TITEL.FK_TITEL.eq(TITEL.ID))
                          .join(VEREIN).on(VEREIN.ID.eq(it.get(VEREIN_PROGRAMM.FK_VEREIN)))
                          .where(VEREIN_PROGRAMM_TITEL.FK_PROGRAMM.eq(it.get(VEREIN_PROGRAMM.ID)),
                                 VEREIN.PHASE2_CONFIRMED_AT.isNotNull())
                          .orderBy(VEREIN_PROGRAMM_TITEL.POSITION.asc())
                          .fetch(this::toTitelDTO);
        }
    }

    public static String getCompetition(Record it) {
        var joiner = new StringJoiner(", ");
        joiner.add(Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)).getDescription());
        Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).ifPresent(joiner::add);
        Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).flatMap(Besetzung::getRelevantDescription).ifPresent(joiner::add);
        return joiner.toString();
    }

    private String getDateTime(LocalDate localDate, LocalTime start, LocalTime end) {
        return "%s, %s - %s".formatted(
                FormatUtil.formatDate(localDate, true),
                FormatUtil.formatTime(start),
                FormatUtil.formatTime(end)
        );
    }

    public List<VereinOverviewDTO> findAllOverview() {
        var commentCount = field(selectCount().from(VEREIN_COMMENT).where(VEREIN_COMMENT.FK_VEREIN.eq(VEREIN.ID))).as("COMMENT_COUNT");
        var messageCount = field(selectCount().from(VEREIN_MESSAGE).where(VEREIN_MESSAGE.FK_VEREIN.eq(VEREIN.ID))).as("MESSAGE_COUNT");
        return jooqDsl.select(
                              VEREIN.asterisk(),
                              VEREIN_STATUS.PHASE1,
                              VEREIN_STATUS.PHASE2,
                              VEREIN_STATUS.PHASE4,
                              commentCount,
                              messageCount
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
                              it.get(VEREIN.PHASE4_CONFIRMED_AT) != null,
                              PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE1)),
                              PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE2)),
                              PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE4)),
                              it.get(commentCount) > 0,
                              it.get(messageCount) > 0,
                              it.get(VEREIN.PROGRAMM_LAST_UPDATED)
                      ));
    }

    public List<VereinSelectionDTO> findAllNotYetPlanned() {
        return jooqDsl.select()
                      .from(VEREIN)
                      .where(
                              VEREIN.CONFIRMED_AT.isNotNull(),
                              DSL.notExists(selectOne().from(TIMETABLE_ENTRY).where(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID)))
                      )
                      .orderBy(VEREIN.VEREINSNAME)
                      .fetch(it -> new VereinSelectionDTO(it.get(VEREIN.ID), it.get(VEREIN.VEREINSNAME)));
    }

    public List<VereinDTO> findAllForExport() {
        var stopWatch = new StopWatch();
        var programmePerVereinId = findProgrammePerVereinId();
        var detailsPerVereinId = findAnmeldungDetailsPerVereinId();
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
                                    new VereinsinfoDTO(null, null, null, null, ""),
                                    it.get(VEREIN.CONFIRMED_AT) != null,
                                    programmePerVereinId.getOrDefault(it.get(VEREIN.ID), new ArrayList<>()),
                                    detailsPerVereinId.get(it.get(VEREIN.ID)),
                                    PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE1)) == DONE,
                                    PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE2)) == DONE,
                                    PhaseStatus.valueOf(it.get(VEREIN_STATUS.PHASE4)) == DONE,
                                    it.get(VEREIN.PHASE2_CONFIRMED_BY),
                                    it.get(VEREIN.PHASE2_CONFIRMED_AT),
                                    it.get(VEREIN.PHASE4_CONFIRMED_AT),
                                    List.of(),
                                    List.of(),
                                    List.of(),
                                    it.get(VEREIN.LUNCH_TIME),
                                    false
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
                toTitelDTO(it),
                it.get(VEREIN_PROGRAMM_TITEL.APPLAUS_IN_SECONDS)
        );
    }

    private TitelDTO toTitelDTO(Record it) {
        return new TitelDTO(
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

    public Optional<VereinAnmeldungDetailPojo> findAnmeldungDetailById(Long vereinId) {
        return vereinAnmeldungDetailDao.fetchByFkVerein(vereinId).stream().findFirst();
    }

    public Optional<VereinAnmeldungDetailPojo> findAnmeldungDetailByEmail(String email) {
        return jooqDsl.select(VEREIN_ANMELDUNG_DETAIL.asterisk())
                      .from(VEREIN_ANMELDUNG_DETAIL)
                      .join(VEREIN).on(VEREIN.ID.eq(VEREIN_ANMELDUNG_DETAIL.FK_VEREIN))
                      .where(VEREIN.EMAIL.equalIgnoreCase(email))
                      .fetchOptionalInto(VereinAnmeldungDetailPojo.class);
    }

    public String findRelevantLocationIdentifierForStageSetup(Long vereinId) {
        return jooqDsl.select()
                      .from(VEREIN_PROGRAMM)
                      .join(TIMETABLE_ENTRY).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(LOCATION.ID.eq(TIMETABLE_ENTRY.FK_LOCATION))
                      .where(VEREIN_PROGRAMM.FK_VEREIN.eq(vereinId),
                             // only those modules need a stage setup
                             VEREIN_PROGRAMM.MODUL.in(A.name(), B.name(), H.name()),
                             TIMETABLE_ENTRY.ENTRY_TYPE.eq(WETTSPIEL))
                      .fetchSingle(it -> it.get(LOCATION.IDENTIFIER));
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

    public void insert(VereinMessagePojo vereinMessagePojo) {
        vereinMessageDao.insert(vereinMessagePojo);
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
                      .fetch(it -> {
                          var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
                          var klasse = Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).orElse(null);
                          var besetzung = Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).map(Besetzung::getDescription).orElse(null);

                          return new VereinProgrammSelectionDTO(
                                  it.get(VEREIN_PROGRAMM.ID),
                                  modul,
                                  modul.getFullDescription(),
                                  klasse,
                                  besetzung);
                      });
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

    public List<VereinCommentPojo> findCommentsByVereinId(Long vereinId) {
        return vereinCommentDao.fetchByFkVerein(vereinId);
    }

    public List<VereinMessagePojo> findMessagesByVereinId(Long vereinId) {
        return vereinMessageDao.fetchByFkVerein(vereinId);
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

    public void confirmScores(String username, Long programmId) {
        jooqDsl.update(VEREIN_PROGRAMM)
               .set(VEREIN_PROGRAMM.SCORES_CONFIRMED_AT, DateUtil.now())
               .set(VEREIN_PROGRAMM.SCORES_CONFIRMED_BY, username)
               .where(VEREIN_PROGRAMM.ID.eq(programmId))
               .execute();
    }

    public Optional<String> getEmailByProgrammId(Long programmId) {
        return jooqDsl.select()
                      .from(VEREIN_PROGRAMM)
                      .join(VEREIN).on(VEREIN_PROGRAMM.FK_VEREIN.eq(VEREIN.ID))
                      .where(VEREIN_PROGRAMM.ID.eq(programmId))
                      .fetchOptional(VEREIN.EMAIL);
    }

    public Optional<VereinAnmeldungDetailPojo> findAnmeldungDetail(Long vereinId) {
        return vereinAnmeldungDetailDao.fetchByFkVerein(vereinId).stream().findFirst();
    }

    public List<VereinAnmeldungAdhocOrchesterPojo> findAdhocOrchester(Long vereinId) {
        return vereinAnmeldungAdhocOrchesterDao.fetchByFkVerein(vereinId);
    }

    public void deleteAdhocOrchester(Long vereinId) {
        vereinAnmeldungAdhocOrchesterDao.delete(findAdhocOrchester(vereinId));
    }

    public void insertAdhocOrchester(List<VereinAnmeldungAdhocOrchesterPojo> pojos) {
        vereinAnmeldungAdhocOrchesterDao.insert(pojos);
    }

    public List<VereinAnmeldungNichtmitgliederPojo> findNichtmitglieder(Long vereinId) {
        return vereinAnmeldungNichtmitgliederDao.fetchByFkVerein(vereinId);
    }

    public void deleteNichtmitglieder(Long vereinId) {
        vereinAnmeldungNichtmitgliederDao.delete(findNichtmitglieder(vereinId));
    }

    public void insertNichtmitglieder(List<VereinAnmeldungNichtmitgliederPojo> pojos) {
        vereinAnmeldungNichtmitgliederDao.insert(pojos);
    }

    public VereinsanmeldungDetailDTO getAnmeldungDetail(Long vereinId, boolean hasPartituren) {
        return vereinAnmeldungDetailDao.fetchByFkVerein(vereinId).stream().findFirst()
                                       .map(pojo -> MAPPER.toAnmeldungDetailDto(pojo,
                                                                                vereinAnmeldungAdhocOrchesterDao.fetchByFkVerein(pojo.getFkVerein()),
                                                                                vereinAnmeldungNichtmitgliederDao.fetchByFkVerein(pojo.getFkVerein()),
                                                                                hasPartituren))
                                       .orElseThrow(() -> new NoSuchElementException("no VereinAnmeldungDetail found for vereinId: " + vereinId));
    }

    public Map<Long, VereinsanmeldungDetailDTO> findAnmeldungDetailsPerVereinId() {
        var adhocPerVerein = vereinAnmeldungAdhocOrchesterDao.findAll().stream()
                                                             .sorted(comparing(VereinAnmeldungAdhocOrchesterPojo::getId))
                                                             .collect(groupingBy(VereinAnmeldungAdhocOrchesterPojo::getFkVerein, toList()));

        var nichtmitgliederPerVerein = vereinAnmeldungNichtmitgliederDao.findAll().stream()
                                                                        .sorted(comparing(VereinAnmeldungNichtmitgliederPojo::getId))
                                                                        .collect(groupingBy(VereinAnmeldungNichtmitgliederPojo::getFkVerein, toList()));

        return vereinAnmeldungDetailDao.findAll().stream()
                                       .collect(toMap(VereinAnmeldungDetailPojo::getFkVerein,
                                                      pojo -> MAPPER.toAnmeldungDetailDto(pojo,
                                                                                          adhocPerVerein.getOrDefault(pojo.getFkVerein(), List.of()),
                                                                                          nichtmitgliederPerVerein.getOrDefault(pojo.getFkVerein(), List.of()),
                                                                                          true)));
    }

    public void insert(VereinAnmeldungDetailPojo anmeldungDetail) {
        vereinAnmeldungDetailDao.insert(anmeldungDetail);
    }

    public void update(VereinAnmeldungDetailPojo anmeldungDetail) {
        vereinAnmeldungDetailDao.update(anmeldungDetail);
    }

    public List<VereinProgrammPojo> findAllModulDProgramme() {
        return vereinProgrammDao.fetchByModul(D.name());
    }
}
