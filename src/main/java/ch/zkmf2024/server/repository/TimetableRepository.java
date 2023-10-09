package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.TimetableEntryType;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.TimetableEntryDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.tables.TimetableEntry.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.jooq.generated.tables.Verein.VEREIN;

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
                      .fetch(it -> {
                          // TODO
//                          var judgeNames = jooqDsl.select()
//                                                  .from(JUDGE_REPORT)
//                                                  .join(JUDGE).on(JUDGE_REPORT.FK_JUDGE.eq(JUDGE.ID))
//                                                  .where(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(it.get(TIMETABLE_ENTRY.ID)))
//                                                  .fetch(r -> r.get(JUDGE.NAME));

                          return new TimetableEntryDTO(
                                  it.get(TIMETABLE_ENTRY.ID),
                                  Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)).getFullDescription(),
                                  Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).orElse(null),
                                  Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).map(Besetzung::getDescription).orElse(null),
                                  it.get(LOCATION.NAME),
                                  it.get(VEREIN.VEREINSNAME),
                                  it.get(TIMETABLE_ENTRY.DATE),
                                  it.get(TIMETABLE_ENTRY.START_TIME),
                                  it.get(TIMETABLE_ENTRY.END_TIME),
                                  TimetableEntryType.valueOf(it.get(TIMETABLE_ENTRY.ENTRY_TYPE).getLiteral()),
                                  null,
                                  null,
                                  null
//                                  judgeNames.get(0),
//                                  judgeNames.get(1),
//                                  judgeNames.get(2)
                          );
                      });
    }

    public void insert(TimetableEntryPojo pojo) {
        timetableEntryDao.insert(pojo);
    }

    public List<TimetableEntryPojo> findAll() {
        return timetableEntryDao.findAll();
    }
}
