package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.TimetablePreviewDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.UnterhaltungEntryDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.UnterhaltungEntryPojo;
import ch.zkmf2024.server.util.DateUtil;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.UNTERHALTUNG_ENTRY;
import static ch.zkmf2024.server.jooq.generated.tables.TimetableEntry.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.util.DateUtil.now;

@Repository
public class UnterhaltungRepository {
    private final DSLContext jooqDsl;
    private final UnterhaltungEntryDao unterhaltungEntryDao;

    public UnterhaltungRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.unterhaltungEntryDao = new UnterhaltungEntryDao(jooqConfig);
    }

    public List<UnterhaltungEntryPojo> findAll() {
        return unterhaltungEntryDao.findAll();
    }

    public Optional<UnterhaltungEntryPojo> findByUnterhaltungIdentifier(String identifier) {
        return unterhaltungEntryDao.fetchByIdentifier(identifier).stream().findFirst();
    }

    public Optional<TimetablePreviewDTO> findCurrent(String locationIdentifier) {
        var now = DateUtil.currentTime();
        var today = DateUtil.today();
        return jooqDsl.select()
                      .from(UNTERHALTUNG_ENTRY)
                      .join(LOCATION).on(UNTERHALTUNG_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .where(UNTERHALTUNG_ENTRY.DATE.eq(today),
                             UNTERHALTUNG_ENTRY.START_TIME.lessOrEqual(now),
                             UNTERHALTUNG_ENTRY.END_TIME.greaterOrEqual(now),
                             LOCATION.IDENTIFIER.eq(locationIdentifier))
                      .fetchOptional(it -> new TimetablePreviewDTO(
                              it.get(UNTERHALTUNG_ENTRY.TITLE),
                              it.get(UNTERHALTUNG_ENTRY.SUBTITLE),
                              null,
                              LocationRepository.toDTO(it),
                              it.get(TIMETABLE_ENTRY.START_TIME),
                              it.get(TIMETABLE_ENTRY.END_TIME),
                              0
                      ));
    }

    public Optional<TimetablePreviewDTO> findNext(String locationIdentifier) {
        var query = jooqDsl.select()
                           .from(UNTERHALTUNG_ENTRY)
                           .join(LOCATION).on(UNTERHALTUNG_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                           .where(UNTERHALTUNG_ENTRY.DATE.eq(DateUtil.today()),
                                  LOCATION.IDENTIFIER.eq(locationIdentifier))
                           .orderBy(UNTERHALTUNG_ENTRY.DATE, UNTERHALTUNG_ENTRY.START_TIME);
        try (var stream = query.stream()) {
            return stream.filter(it -> LocalDateTime.of(it.get(UNTERHALTUNG_ENTRY.DATE), it.get(UNTERHALTUNG_ENTRY.START_TIME)).isAfter(now()))
                         .limit(1)
                         .map(it -> new TimetablePreviewDTO(
                                 it.get(UNTERHALTUNG_ENTRY.TITLE),
                                 it.get(UNTERHALTUNG_ENTRY.SUBTITLE),
                                 null,
                                 LocationRepository.toDTO(it),
                                 it.get(TIMETABLE_ENTRY.START_TIME),
                                 it.get(TIMETABLE_ENTRY.END_TIME),
                                 Duration.between(DateUtil.now(), LocalDateTime.of(it.get(UNTERHALTUNG_ENTRY.DATE), it.get(UNTERHALTUNG_ENTRY.START_TIME))).toMinutes()
                         ))
                         .findFirst();
        }
    }
}
