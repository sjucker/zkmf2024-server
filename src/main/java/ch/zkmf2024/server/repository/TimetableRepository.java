package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.TimetableEntryType;
import ch.zkmf2024.server.dto.TimetableOverviewEntryDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.TimetableEntryDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import ch.zkmf2024.server.util.FormatUtil;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.MARSCHMUSIK;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.PLATZKONZERT;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.WETTSPIEL;
import static ch.zkmf2024.server.jooq.generated.tables.TimetableEntry.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;
import static ch.zkmf2024.server.repository.VereinRepository.getCompetition;

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
                      .fetch(TimetableRepository::toDTO);
    }

    private static TimetableEntryDTO toDTO(org.jooq.Record it) {
        return new TimetableEntryDTO(
                it.get(TIMETABLE_ENTRY.ID),
                Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)).getFullDescription(),
                Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).orElse(null),
                Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).map(Besetzung::getDescription).orElse(null),
                it.get(LOCATION.ID),
                it.get(LOCATION.NAME),
                it.get(VEREIN.VEREINSNAME),
                it.get(TIMETABLE_ENTRY.DATE),
                it.get(TIMETABLE_ENTRY.START_TIME),
                it.get(TIMETABLE_ENTRY.END_TIME),
                TimetableEntryType.from(it.get(TIMETABLE_ENTRY.ENTRY_TYPE)),
                null,
                null,
                null
        );
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

        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .where(TIMETABLE_ENTRY.ENTRY_TYPE.in(MARSCHMUSIK, PLATZKONZERT, WETTSPIEL))
                      .orderBy(TIMETABLE_ENTRY.DATE, TIMETABLE_ENTRY.START_TIME)
                      .fetch(TimetableRepository::toOverviewDTO);

    }

    private static TimetableOverviewEntryDTO toOverviewDTO(org.jooq.Record it) {
        return new TimetableOverviewEntryDTO(
                it.get(VEREIN.ID),
                it.get(VEREIN.IDENTIFIER),
                it.get(VEREIN.VEREINSNAME),
                Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)).getDescription(),
                getCompetition(it),
                TimetableEntryType.from(it.get(TIMETABLE_ENTRY.ENTRY_TYPE)).getDescription(),
                LocationRepository.toDTO(it),
                it.get(TIMETABLE_ENTRY.DATE),
                it.get(TIMETABLE_ENTRY.START_TIME),
                it.get(TIMETABLE_ENTRY.END_TIME),
                "%s - %s".formatted(
                        FormatUtil.formatTime(it.get(TIMETABLE_ENTRY.START_TIME)),
                        FormatUtil.formatTime(it.get(TIMETABLE_ENTRY.END_TIME)))
        );
    }

    public void delete(Long id) {
        timetableEntryDao.deleteById(id);
    }

    public Optional<TimetableEntryPojo> find(Long id) {
        return timetableEntryDao.findOptionalById(id);
    }

    public void update(TimetableEntryPojo entry) {
        timetableEntryDao.update(entry);
    }
}
