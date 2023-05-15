package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgePojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE_REPORT;
import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN;
import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
public class JudgeRepository {

    private final DSLContext jooqDsl;
    private final JudgeDao judgeDao;

    public JudgeRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.judgeDao = new JudgeDao(jooqConfig);
    }

    public Optional<JudgePojo> findByEmail(String email) {
        return jooqDsl.selectFrom(JUDGE)
                      .where(JUDGE.EMAIL.equalIgnoreCase(email))
                      .fetchOptionalInto(JudgePojo.class);
    }

    public List<JudgeReportOverviewDTO> getReports(Long id) {
        return jooqDsl.select(
                              JUDGE_REPORT.ID,
                              VEREIN.VEREINSNAME,
                              LOCATION.NAME,
                              LOCATION.ADDRESS,
                              TIMETABLE_ENTRY.MODUL,
                              TIMETABLE_ENTRY.KLASSE,
                              TIMETABLE_ENTRY.BESETZUNG,
                              TIMETABLE_ENTRY.DATE,
                              TIMETABLE_ENTRY.START_TIME,
                              TIMETABLE_ENTRY.END_TIME
                      )
                      .from(JUDGE_REPORT)
                      .join(JUDGE).on(JUDGE_REPORT.FK_JUDGE.eq(JUDGE.ID))
                      .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .where(JUDGE.ID.eq(id))
                      .orderBy(TIMETABLE_ENTRY.DATE.asc(), TIMETABLE_ENTRY.START_TIME.asc())
                      .fetch(r -> new JudgeReportOverviewDTO(
                              r.get(JUDGE_REPORT.ID),
                              r.get(VEREIN.VEREINSNAME),
                              r.get(LOCATION.NAME),
                              toGoogleMapsUrl(r.get(LOCATION.ADDRESS)),
                              Modul.valueOf(r.get(TIMETABLE_ENTRY.MODUL)).getFullDescription(),
                              Klasse.fromString(r.get(TIMETABLE_ENTRY.KLASSE)).map(Klasse::getDescription).orElse(null),
                              Besetzung.fromString(r.get(TIMETABLE_ENTRY.BESETZUNG)).map(Besetzung::getDescription).orElse(null),
                              LocalDateTime.of(r.get(TIMETABLE_ENTRY.DATE), r.get(TIMETABLE_ENTRY.START_TIME)),
                              LocalDateTime.of(r.get(TIMETABLE_ENTRY.DATE), r.get(TIMETABLE_ENTRY.END_TIME))
                      ));

    }

    private String toGoogleMapsUrl(String address) {
        return "https://www.google.com/maps/place/%s".formatted(URLEncoder.encode(address, UTF_8));
    }
}
