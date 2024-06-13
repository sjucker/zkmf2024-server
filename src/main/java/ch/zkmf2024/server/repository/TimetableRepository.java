package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.TimetableCurrentDTO;
import ch.zkmf2024.server.dto.TimetableEntryType;
import ch.zkmf2024.server.dto.TimetableOverviewEntryDTO;
import ch.zkmf2024.server.dto.TimetablePreviewDTO;
import ch.zkmf2024.server.dto.UpcomingVereinDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.TimetableEntryDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import ch.zkmf2024.server.util.FormatUtil;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.CURRENTLY_PLAYING;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE_REPORT;
import static ch.zkmf2024.server.jooq.generated.Tables.KONTAKT;
import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.TITEL;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM_TITEL;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.MARSCHMUSIK;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.PLATZKONZERT;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.WETTSPIEL;
import static ch.zkmf2024.server.jooq.generated.tables.TimetableEntry.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;
import static ch.zkmf2024.server.repository.VereinRepository.getCompetition;
import static ch.zkmf2024.server.util.DateUtil.currentTime;
import static ch.zkmf2024.server.util.DateUtil.now;
import static ch.zkmf2024.server.util.DateUtil.today;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.defaultString;

@Repository
public class TimetableRepository {

    private final DSLContext jooqDsl;
    private final TimetableEntryDao timetableEntryDao;

    public TimetableRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.timetableEntryDao = new TimetableEntryDao(jooqConfig);
    }

    public List<TimetableEntryDTO> findAllDTOs() {
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .fetch(this::toDTO);
    }

    private TimetableEntryDTO toDTO(org.jooq.Record it) {
        var judgeNames = jooqDsl.select()
                                .from(JUDGE_REPORT)
                                .join(JUDGE).on(JUDGE_REPORT.FK_JUDGE.eq(JUDGE.ID))
                                .where(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(it.get(TIMETABLE_ENTRY.ID)))
                                .orderBy(JUDGE_REPORT.ID)
                                .fetch(r -> r.get(JUDGE.NAME));

        var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));

        return new TimetableEntryDTO(
                it.get(TIMETABLE_ENTRY.ID),
                modul,
                modul.getFullDescription(),
                Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).orElse(null),
                Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).map(Besetzung::getDescription).orElse(null),
                it.get(LOCATION.ID),
                it.get(LOCATION.NAME),
                it.get(VEREIN.VEREINSNAME),
                it.get(TIMETABLE_ENTRY.DATE),
                it.get(TIMETABLE_ENTRY.START_TIME),
                it.get(TIMETABLE_ENTRY.END_TIME),
                TimetableEntryType.from(it.get(TIMETABLE_ENTRY.ENTRY_TYPE)),
                findElementAt(judgeNames, 0).orElse(null),
                findElementAt(judgeNames, 1).orElse(null),
                findElementAt(judgeNames, 2).orElse(null),
                findElementAt(judgeNames, 3).orElse(null)
        );
    }

    private Optional<String> findElementAt(List<String> judgeNames, int i) {
        return judgeNames.size() > i ? Optional.of(judgeNames.get(i)) : Optional.empty();
    }

    public void insert(TimetableEntryPojo pojo) {
        timetableEntryDao.insert(pojo);
    }

    public void insertAll(List<TimetableEntryPojo> pojos) {
        timetableEntryDao.insert(pojos);
    }

    public List<TimetableEntryPojo> findAll() {
        return timetableEntryDao.findAll();
    }

    public List<TimetableOverviewEntryDTO> findAllByVereinId(Long vereinId) {
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .where(VEREIN.ID.eq(vereinId))
                      .orderBy(TIMETABLE_ENTRY.DATE.asc(), TIMETABLE_ENTRY.START_TIME.asc())
                      .fetch(TimetableRepository::toOverviewDTO);
    }

    public List<TimetableOverviewEntryDTO> findAllForPublic() {
        return findAllByTypes(List.of(MARSCHMUSIK, PLATZKONZERT, WETTSPIEL));
    }

    public List<TimetableOverviewEntryDTO> findAllPlatzkonzzerte() {
        return findAllByTypes(List.of(PLATZKONZERT));
    }

    private List<TimetableOverviewEntryDTO> findAllByTypes(Collection<ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType> types) {
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .where(TIMETABLE_ENTRY.ENTRY_TYPE.in(types))
                      .orderBy(TIMETABLE_ENTRY.DATE, TIMETABLE_ENTRY.START_TIME)
                      .fetch(TimetableRepository::toOverviewDTO);
    }

    private static TimetableOverviewEntryDTO toOverviewDTO(org.jooq.Record it) {
        return new TimetableOverviewEntryDTO(
                it.get(VEREIN.ID),
                it.get(VEREIN.IDENTIFIER),
                it.get(VEREIN.VEREINSNAME),
                Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)).getDescription(),
                Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).orElse(null),
                Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).map(Besetzung::getDescription).orElse(null),
                getCompetition(it),
                TimetableEntryType.from(it.get(TIMETABLE_ENTRY.ENTRY_TYPE)),
                TimetableEntryType.from(it.get(TIMETABLE_ENTRY.ENTRY_TYPE)).getDescription(),
                LocationRepository.toDTO(it),
                it.get(TIMETABLE_ENTRY.DATE),
                it.get(TIMETABLE_ENTRY.START_TIME),
                it.get(TIMETABLE_ENTRY.END_TIME),
                "%s - %s".formatted(
                        FormatUtil.formatTime(it.get(TIMETABLE_ENTRY.START_TIME)),
                        FormatUtil.formatTime(it.get(TIMETABLE_ENTRY.END_TIME))),
                now().isAfter(LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.END_TIME)))
        );
    }

    public void delete(Long id) {
        timetableEntryDao.deleteById(id);
    }

    public Optional<TimetableEntryPojo> find(Long id) {
        return timetableEntryDao.findOptionalById(id);
    }

    public Optional<TimetableEntryPojo> findWettspielByProgrammId(Long programmId) {
        return timetableEntryDao.fetchByFkVereinProgramm(programmId).stream()
                                .filter(pojo -> pojo.getEntryType().equals(WETTSPIEL))
                                .findFirst();
    }

    public List<TimetableOverviewEntryDTO> find(List<ModulKlasseBesetzung> modulKlasseBesetzungen) {
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .where(
                              DSL.or(modulKlasseBesetzungen.stream()
                                                           .map(modulKlasseBesetzung -> DSL.and(
                                                                   VEREIN_PROGRAMM.MODUL.eq(modulKlasseBesetzung.modul().name()),
                                                                   VEREIN_PROGRAMM.KLASSE.eq(modulKlasseBesetzung.klasse().name()),
                                                                   VEREIN_PROGRAMM.BESETZUNG.eq(modulKlasseBesetzung.besetzung().name())
                                                           ))
                                                           .toList()),
                              TIMETABLE_ENTRY.ENTRY_TYPE.eq(WETTSPIEL)
                      )
                      .fetch(TimetableRepository::toOverviewDTO);
    }

    public void update(TimetableEntryPojo entry) {
        timetableEntryDao.update(entry);
    }

    public Optional<TimetableCurrentDTO> findCurrent(String locationIdentifier) {
        return jooqDsl.select(VEREIN.VEREINSNAME,
                              KONTAKT.VORNAME,
                              KONTAKT.NACHNAME,
                              VEREIN_PROGRAMM.MODUL,
                              VEREIN_PROGRAMM.KLASSE,
                              VEREIN_PROGRAMM.BESETZUNG,
                              VEREIN_PROGRAMM.TITEL,
                              VEREIN_PROGRAMM.ID,
                              VEREIN_PROGRAMM.MODUL)
                      .from(TIMETABLE_ENTRY)
                      .join(CURRENTLY_PLAYING).on(TIMETABLE_ENTRY.ID.eq(CURRENTLY_PLAYING.FK_TIMETABLE_ENTRY))
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .join(KONTAKT).on(VEREIN.DIREKTION_KONTAKT_ID.eq(KONTAKT.ID))
                      .where(CURRENTLY_PLAYING.STARTED_AT.isNotNull(),
                             CURRENTLY_PLAYING.ENDED_AT.isNull(),
                             LOCATION.IDENTIFIER.eq(locationIdentifier))
                      .orderBy(CURRENTLY_PLAYING.STARTED_AT.desc())
                      .limit(1)
                      .fetchOptional(it -> {
                          var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
                          var titles = getTitles(it.get(VEREIN_PROGRAMM.ID), modul);
                          return new TimetableCurrentDTO(
                                  it.get(VEREIN.VEREINSNAME),
                                  "%s %s".formatted(defaultString(it.get(KONTAKT.VORNAME)), defaultString(it.get(KONTAKT.NACHNAME))),
                                  getCompetition(it),
                                  defaultIfBlank(it.get(VEREIN_PROGRAMM.TITEL), getTitel(titles, modul)),
                                  titles
                          );
                      });
    }

    private static String getTitel(List<String> titles, Modul modul) {
        if (titles.isEmpty()) {
            return "";
        }
        if (modul.isTambouren()) {
            return titles.size() > 1 ? "Kompositionen" : "Komposition";
        }
        return "Programm";
    }

    private List<String> getTitles(Long programmId, Modul modul) {
        if (modul.isParademusik() || modul.isPlatzkonzert()) {
            return List.of();
        } else if (modul.isTambouren()) {
            return jooqDsl.select(TITEL.TITEL_NAME,
                                  TITEL.FK_VEREIN,
                                  TITEL.COMPOSER)
                          .from(TITEL)
                          .join(VEREIN_PROGRAMM).on(
                            DSL.or(VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_1_ID.eq(TITEL.ID),
                                   VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_2_ID.eq(TITEL.ID),
                                   VEREIN_PROGRAMM.MODUL_G_KAT_B_TITEL_ID.eq(TITEL.ID),
                                   VEREIN_PROGRAMM.MODUL_G_KAT_C_TITEL_ID.eq(TITEL.ID)))
                          .join(VEREIN).on(VEREIN.ID.eq(VEREIN_PROGRAMM.FK_VEREIN))
                          .where(VEREIN_PROGRAMM.ID.eq(programmId),
                                 VEREIN.PHASE2_CONFIRMED_AT.isNotNull())
                          .fetch(it -> "%s%s (%s)".formatted(it.get(TITEL.TITEL_NAME),
                                                             it.get(TITEL.FK_VEREIN) == null ? "*" : "",
                                                             it.get(TITEL.COMPOSER)));
        } else {
            return jooqDsl.select(TITEL.TITEL_NAME,
                                  TITEL.FK_VEREIN,
                                  TITEL.COMPOSER)
                          .from(TITEL)
                          .join(VEREIN_PROGRAMM_TITEL).on(VEREIN_PROGRAMM_TITEL.FK_TITEL.eq(TITEL.ID))
                          .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM_TITEL.FK_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                          .join(VEREIN).on(VEREIN.ID.eq(VEREIN_PROGRAMM.FK_VEREIN))
                          .where(VEREIN_PROGRAMM.ID.eq(programmId),
                                 VEREIN.PHASE2_CONFIRMED_AT.isNotNull())
                          .orderBy(VEREIN_PROGRAMM_TITEL.POSITION)
                          .fetch(it -> "%s%s (%s)".formatted(it.get(TITEL.TITEL_NAME),
                                                             it.get(TITEL.FK_VEREIN) == null ? "*" : "",
                                                             it.get(TITEL.COMPOSER)));
        }

    }

    public Optional<TimetablePreviewDTO> findNext(String locationIdentifier) {
        var query = jooqDsl.select()
                           .from(TIMETABLE_ENTRY)
                           .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                           .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                           .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                           .join(KONTAKT).on(VEREIN.DIREKTION_KONTAKT_ID.eq(KONTAKT.ID))
                           .where(TIMETABLE_ENTRY.DATE.greaterOrEqual(today()),
                                  LOCATION.IDENTIFIER.eq(locationIdentifier),
                                  TIMETABLE_ENTRY.ENTRY_TYPE.eq(WETTSPIEL))
                           .orderBy(TIMETABLE_ENTRY.DATE, TIMETABLE_ENTRY.START_TIME);
        try (var stream = query.stream()) {
            return stream.filter(it -> LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.START_TIME)).isAfter(now()))
                         .limit(1)
                         .map(TimetableRepository::toTimetablePreviewDTO)
                         .findFirst();
        }
    }

    private static TimetablePreviewDTO toTimetablePreviewDTO(org.jooq.Record it) {
        return new TimetablePreviewDTO(
                it.get(VEREIN.VEREINSNAME),
                getCompetition(it),
                null,
                LocationRepository.toDTO(it),
                it.get(TIMETABLE_ENTRY.DATE),
                it.get(TIMETABLE_ENTRY.START_TIME),
                it.get(TIMETABLE_ENTRY.END_TIME),
                getMinutesUntilStart(it)
        );
    }

    private static long getMinutesUntilStart(org.jooq.Record it) {
        return Duration.between(now(), LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.START_TIME))).toMinutes();
    }

    public List<UpcomingVereinDTO> findAllUpcomingIn(long amount, TemporalUnit unit) {
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .where(TIMETABLE_ENTRY.DATE.eq(today()),
                             TIMETABLE_ENTRY.START_TIME.lessOrEqual(currentTime().plus(amount, unit)),
                             TIMETABLE_ENTRY.START_TIME.greaterThan(currentTime()),
                             TIMETABLE_ENTRY.ENTRY_TYPE.eq(WETTSPIEL))
                      .fetch(it -> new UpcomingVereinDTO(
                              it.get(TIMETABLE_ENTRY.ID),
                              it.get(VEREIN.IDENTIFIER),
                              it.get(VEREIN.VEREINSNAME),
                              it.get(LOCATION.NAME),
                              it.get(TIMETABLE_ENTRY.START_TIME),
                              getMinutesUntilStart(it)
                      ));
    }

    public record ModulKlasseBesetzung(Modul modul, Klasse klasse, Besetzung besetzung) {
    }
}
