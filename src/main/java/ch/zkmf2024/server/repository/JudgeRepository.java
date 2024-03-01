package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.JudgeRankingEntryDTO;
import ch.zkmf2024.server.dto.JudgeReportCategory;
import ch.zkmf2024.server.dto.JudgeReportCategoryRating;
import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.JudgeReportRatingDTO;
import ch.zkmf2024.server.dto.JudgeReportScoreDTO;
import ch.zkmf2024.server.dto.JudgeReportStatus;
import ch.zkmf2024.server.dto.JudgeReportSummaryDTO;
import ch.zkmf2024.server.dto.JudgeReportTitleDTO;
import ch.zkmf2024.server.dto.JudgeRole;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.ModulDSelection;
import ch.zkmf2024.server.dto.ModulDSelectionDTO;
import ch.zkmf2024.server.dto.TitelDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeReportCommentDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeReportDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeReportRatingDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgePojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportCommentPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportRatingPojo;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.dto.JudgeReportCategoryRating.NEUTRAL;
import static ch.zkmf2024.server.dto.JudgeReportStatus.DONE;
import static ch.zkmf2024.server.dto.Modul.D;
import static ch.zkmf2024.server.dto.ModulDSelection.TITEL_1;
import static ch.zkmf2024.server.dto.ModulDSelection.TITEL_2;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE_REPORT;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE_REPORT_COMMENT;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE_REPORT_RATING;
import static ch.zkmf2024.server.jooq.generated.Tables.KONTAKT;
import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.jooq.generated.Tables.TITEL;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM_TITEL;
import static java.math.RoundingMode.HALF_UP;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.defaultString;

@Repository
public class JudgeRepository {

    private final ProgrammVorgabenRepository programmVorgabenRepository;
    private final DSLContext jooqDsl;
    private final JudgeDao judgeDao;
    private final JudgeReportDao judgeReportDao;
    private final JudgeReportCommentDao judgeReportCommentDao;
    private final JudgeReportRatingDao judgeReportRatingDao;

    public JudgeRepository(ProgrammVorgabenRepository programmVorgabenRepository,
                           DSLContext jooqDsl,
                           DefaultConfiguration jooqConfig) {
        this.programmVorgabenRepository = programmVorgabenRepository;
        this.jooqDsl = jooqDsl;
        this.judgeDao = new JudgeDao(jooqConfig);
        this.judgeReportDao = new JudgeReportDao(jooqConfig);
        this.judgeReportCommentDao = new JudgeReportCommentDao(jooqConfig);
        this.judgeReportRatingDao = new JudgeReportRatingDao(jooqConfig);
    }

    public Optional<JudgePojo> findByEmail(String email) {
        return jooqDsl.selectFrom(JUDGE)
                      .where(JUDGE.EMAIL.equalIgnoreCase(email))
                      .fetchOptionalInto(JudgePojo.class);
    }

    public List<JudgeReportOverviewDTO> getReports(Long id) {
        return jooqDsl.select(
                              JUDGE_REPORT.ID,
                              JUDGE_REPORT.ROLE,
                              VEREIN.VEREINSNAME,
                              LOCATION.NAME,
                              LOCATION.ADDRESS,
                              VEREIN_PROGRAMM.MODUL,
                              VEREIN_PROGRAMM.KLASSE,
                              VEREIN_PROGRAMM.BESETZUNG,
                              TIMETABLE_ENTRY.DATE,
                              TIMETABLE_ENTRY.START_TIME,
                              TIMETABLE_ENTRY.END_TIME,
                              JUDGE_REPORT.STATUS
                      )
                      .from(JUDGE_REPORT)
                      .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .where(JUDGE_REPORT.FK_JUDGE.eq(id))
                      .orderBy(TIMETABLE_ENTRY.DATE.asc(), TIMETABLE_ENTRY.START_TIME.asc())
                      .fetch(it -> {
                          var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
                          var role = JudgeRole.valueOf(it.get(JUDGE_REPORT.ROLE));
                          return new JudgeReportOverviewDTO(
                                  it.get(JUDGE_REPORT.ID),
                                  it.get(VEREIN.VEREINSNAME),
                                  it.get(LOCATION.NAME),
                                  toGoogleMapsUrl(it.get(LOCATION.ADDRESS)),
                                  modul,
                                  modul.getFullDescription(),
                                  role,
                                  role.getDescription(),
                                  Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).orElse(null),
                                  Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).map(Besetzung::getDescription).orElse(null),
                                  LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.START_TIME)),
                                  LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.END_TIME)),
                                  JudgeReportStatus.valueOf(it.get(JUDGE_REPORT.STATUS))
                          );
                      });

    }

    private String toGoogleMapsUrl(String address) {
        return "https://www.google.com/maps/place/%s".formatted(URLEncoder.encode(address, UTF_8));
    }

    public Optional<JudgeReportDTO> getReport(Long judgeId, Long reportId) {
        return jooqDsl.select()
                      .from(JUDGE_REPORT)
                      .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .join(KONTAKT).on(VEREIN.DIREKTION_KONTAKT_ID.eq(KONTAKT.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .where(JUDGE_REPORT.FK_JUDGE.eq(judgeId),
                             JUDGE_REPORT.ID.eq(reportId))
                      .fetchOptional(it -> {
                          var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
                          var role = JudgeRole.valueOf(it.get(JUDGE_REPORT.ROLE));
                          var klasse = Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE));
                          var besetzung = Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG));

                          var minMaxDuration = programmVorgabenRepository.findMinMaxDuration(modul, klasse.orElse(null), besetzung.orElse(null));

                          return new JudgeReportDTO(
                                  it.get(JUDGE_REPORT.ID),
                                  modul,
                                  modul.getFullDescription(),
                                  role,
                                  role.getDescription(),
                                  klasse.map(Klasse::getDescription).orElse(null),
                                  besetzung.map(Besetzung::getDescription).orElse(null),
                                  it.get(LOCATION.NAME),
                                  it.get(VEREIN.VEREINSNAME),
                                  "%s %s".formatted(defaultString(it.get(KONTAKT.VORNAME)), defaultString(it.get(KONTAKT.NACHNAME))),
                                  it.get(VEREIN_PROGRAMM.TITEL),
                                  it.get(VEREIN_PROGRAMM.INFO_MODERATION),
                                  minMaxDuration.map(ProgrammVorgabenRepository.MinMaxDuration::minDurationInSeconds).orElse(null),
                                  minMaxDuration.map(ProgrammVorgabenRepository.MinMaxDuration::maxDurationInSeconds).orElse(null),
                                  it.get(JUDGE_REPORT.SCORE),
                                  it.get(JUDGE_REPORT.RATING_FIXED),
                                  JudgeReportStatus.valueOf(it.get(JUDGE_REPORT.STATUS)),
                                  findTitles(reportId, modul),
                                  findOverallRatings(reportId, modul, role)
                          );
                      });
    }

    private List<JudgeReportTitleDTO> findTitles(Long reportId, Modul modul) {
        if (modul == D) {
            var titel1Condition = VEREIN_PROGRAMM.MODUL_D_TITEL_1_ID.eq(TITEL.ID).and(VEREIN_PROGRAMM.MODUL_D_TITEL_SELECTION.eq(TITEL_1.name()));
            var titel2Condition = VEREIN_PROGRAMM.MODUL_D_TITEL_2_ID.eq(TITEL.ID).and(VEREIN_PROGRAMM.MODUL_D_TITEL_SELECTION.eq(TITEL_2.name()));
            return jooqDsl.select()
                          .from(JUDGE_REPORT)
                          .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                          .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                          .join(TITEL).on(titel1Condition.or(titel2Condition))
                          .leftJoin(JUDGE_REPORT_COMMENT).on(JUDGE_REPORT.ID.eq(JUDGE_REPORT_COMMENT.FK_JUDGE_REPORT),
                                                             TITEL.ID.eq(JUDGE_REPORT_COMMENT.FK_TITEL))
                          .where(JUDGE_REPORT.ID.eq(reportId))
                          .fetch(it -> toJudgeReportTitleDTO(modul, it));
        } else {
            return jooqDsl.select()
                          .from(JUDGE_REPORT)
                          .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                          .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                          .join(VEREIN_PROGRAMM_TITEL).on(VEREIN_PROGRAMM.ID.eq(VEREIN_PROGRAMM_TITEL.FK_PROGRAMM))
                          .join(TITEL).on(VEREIN_PROGRAMM_TITEL.FK_TITEL.eq(TITEL.ID))
                          .leftJoin(JUDGE_REPORT_COMMENT).on(JUDGE_REPORT.ID.eq(JUDGE_REPORT_COMMENT.FK_JUDGE_REPORT),
                                                             TITEL.ID.eq(JUDGE_REPORT_COMMENT.FK_TITEL))
                          .where(JUDGE_REPORT.ID.eq(reportId))
                          .orderBy(VEREIN_PROGRAMM_TITEL.POSITION)
                          .fetch(it -> toJudgeReportTitleDTO(modul, it));
        }

    }

    private JudgeReportTitleDTO toJudgeReportTitleDTO(Modul modul, Record it) {
        var role = JudgeRole.valueOf(it.get(JUDGE_REPORT.ROLE));
        return new JudgeReportTitleDTO(
                new TitelDTO(
                        it.get(TITEL.ID),
                        modul,
                        it.get(TITEL.TITEL_NAME),
                        it.get(TITEL.COMPOSER),
                        it.get(TITEL.ARRANGEUR),
                        it.get(TITEL.GRAD) != null ? it.get(TITEL.GRAD).floatValue() : null,
                        it.get(TITEL.SCHWIERIGKEITSGRAD),
                        it.get(TITEL.DURATION_IN_SECONDS),
                        it.get(TITEL.FK_VEREIN) == null,
                        it.get(TITEL.INFO_MODERATION)
                ),
                it.get(JUDGE_REPORT_COMMENT.COMMENT),
                findTitelRatings(it.get(JUDGE_REPORT.ID), it.get(TITEL.ID), modul, role)
        );
    }

    private List<JudgeReportRatingDTO> findOverallRatings(Long reportId, Modul modul, JudgeRole role) {
        var ratings = jooqDsl.select()
                             .from(JUDGE_REPORT_RATING)
                             .where(JUDGE_REPORT_RATING.FK_JUDGE_REPORT.eq(reportId),
                                    JUDGE_REPORT_RATING.FK_TITEL.isNull())
                             .fetch(this::toJudgeReportRatingDTO).stream()
                             .sorted(comparing(JudgeReportRatingDTO::category))
                             .toList();

        if (ratings.isEmpty()) {
            ratings = JudgeReportCategory.get(modul, role, true).stream()
                                         .map(this::toJudgeReportRatingDTO)
                                         .toList();
        }

        return ratings;
    }

    private JudgeReportRatingDTO toJudgeReportRatingDTO(JudgeReportCategory category) {
        return new JudgeReportRatingDTO(
                category,
                category.getDescription(),
                category.getGroup(),
                null,
                NEUTRAL
        );
    }

    private JudgeReportRatingDTO toJudgeReportRatingDTO(org.jooq.Record it) {
        var category = JudgeReportCategory.fromString(it.get(JUDGE_REPORT_RATING.CATEGORY)).orElseThrow();
        return new JudgeReportRatingDTO(
                category,
                category.getDescription(),
                category.getGroup(),
                it.get(JUDGE_REPORT_RATING.COMMENT),
                JudgeReportCategoryRating.fromString(it.get(JUDGE_REPORT_RATING.RATING)).orElseThrow()
        );
    }

    private List<JudgeReportRatingDTO> findTitelRatings(Long reportId, Long titelId, Modul modul, JudgeRole role) {
        var ratings = jooqDsl.select()
                             .from(JUDGE_REPORT_RATING)
                             .where(JUDGE_REPORT_RATING.FK_JUDGE_REPORT.eq(reportId),
                                    JUDGE_REPORT_RATING.FK_TITEL.eq(titelId))
                             .fetch(this::toJudgeReportRatingDTO).stream()
                             .sorted(comparing(JudgeReportRatingDTO::category))
                             .toList();

        if (ratings.isEmpty()) {
            ratings = JudgeReportCategory.get(modul, role, false).stream()
                                         .map(this::toJudgeReportRatingDTO)
                                         .toList();
        }

        return ratings;
    }

    public boolean exists(String email, Long reportId) {
        return findByEmail(email)
                .map(judge -> jooqDsl.fetchExists(JUDGE_REPORT,
                                                  JUDGE_REPORT.FK_JUDGE.eq(judge.getId()),
                                                  JUDGE_REPORT.ID.eq(reportId)))
                .orElse(false);
    }

    public JudgeReportPojo findReportById(Long reportId) {
        return judgeReportDao.findById(reportId);
    }

    public void update(JudgeReportPojo pojo) {
        judgeReportDao.update(pojo);
    }

    public void deleteReportCommentsByReportId(Long reportId) {
        jooqDsl.deleteFrom(JUDGE_REPORT_COMMENT)
               .where(JUDGE_REPORT_COMMENT.FK_JUDGE_REPORT.eq(reportId))
               .execute();
    }

    public void insert(JudgeReportCommentPojo comment) {
        judgeReportCommentDao.insert(comment);
    }

    public void deleteReportRatingsByReportId(Long reportId) {
        jooqDsl.deleteFrom(JUDGE_REPORT_RATING)
               .where(JUDGE_REPORT_RATING.FK_JUDGE_REPORT.eq(reportId))
               .execute();
    }

    public void insert(JudgeReportRatingPojo rating) {
        judgeReportRatingDao.insert(rating);
    }

    public void insert(JudgePojo judge) {
        judgeDao.insert(judge);
    }

    public List<JudgePojo> findAll() {
        return judgeDao.findAll();
    }

    public void insert(JudgeReportPojo report) {
        judgeReportDao.insert(report);
    }

    public List<JudgeReportSummaryDTO> findSummaries() {
        return jooqDsl.select()
                      .from(JUDGE_REPORT)
                      .join(TIMETABLE_ENTRY).on(TIMETABLE_ENTRY.ID.eq(JUDGE_REPORT.FK_TIMETABLE_ENTRY))
                      .join(VEREIN).on(VEREIN.ID.eq(TIMETABLE_ENTRY.FK_VEREIN))
                      .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                      .join(JUDGE).on(JUDGE.ID.eq(JUDGE_REPORT.FK_JUDGE))
                      .orderBy(TIMETABLE_ENTRY.DATE, TIMETABLE_ENTRY.START_TIME, TIMETABLE_ENTRY.END_TIME)
                      .stream()
                      .collect(groupingBy(it -> it.get(TIMETABLE_ENTRY.ID), toList()))
                      .values().stream()
                      .map(values -> {
                          var record1 = values.get(0);
                          var record2 = values.get(1);
                          var record3 = values.get(2);
                          var modul = Modul.valueOf(record1.get(VEREIN_PROGRAMM.MODUL));
                          var record4 = modul.isParademusik() ? values.get(3) : null;

                          return new JudgeReportSummaryDTO(
                                  record1.get(VEREIN_PROGRAMM.ID),
                                  modul.getFullDescription(),
                                  Klasse.fromString(record1.get(VEREIN_PROGRAMM.KLASSE)).map(Klasse::getDescription).orElse(null),
                                  Besetzung.fromString(record1.get(VEREIN_PROGRAMM.BESETZUNG)).map(Besetzung::getDescription).orElse(null),
                                  record1.get(VEREIN.VEREINSNAME),
                                  overallScore(record1, record2, record3, record4, modul),
                                  values.stream()
                                        .map(it -> new JudgeReportScoreDTO(
                                                it.get(JUDGE_REPORT.ID),
                                                it.get(JUDGE.NAME),
                                                JudgeRole.valueOf(it.get(JUDGE_REPORT.ROLE)).getDescription(),
                                                it.get(JUDGE_REPORT.SCORE),
                                                it.get(JUDGE_REPORT.RATING_FIXED),
                                                JudgeReportStatus.valueOf(it.get(JUDGE_REPORT.STATUS)) == DONE
                                        ))
                                        .toList(),
                                  isDone(record1, record2, record3),
                                  record1.get(VEREIN_PROGRAMM.SCORES_CONFIRMED_AT) != null,
                                  record1.get(VEREIN_PROGRAMM.SCORES_CONFIRMED_BY),
                                  record1.get(VEREIN_PROGRAMM.SCORES_CONFIRMED_AT)

                          );
                      })
                      .toList();

    }

    private boolean isDone(Record record1, Record record2, Record record3) {
        return JudgeReportStatus.valueOf(record1.get(JUDGE_REPORT.STATUS)) == DONE &&
                JudgeReportStatus.valueOf(record2.get(JUDGE_REPORT.STATUS)) == DONE &&
                JudgeReportStatus.valueOf(record3.get(JUDGE_REPORT.STATUS)) == DONE;
    }

    private BigDecimal overallScore(Record record1, Record record2, Record record3, Record record4, Modul modul) {
        var score1 = record1.get(JUDGE_REPORT.SCORE);
        var score2 = record2.get(JUDGE_REPORT.SCORE);
        var score3 = record3.get(JUDGE_REPORT.SCORE);
        if (modul.isParademusik()) {
            var score4 = record4.get(JUDGE_REPORT.SCORE);
            if (score1 != null && score2 != null && score3 != null && score4 != null) {
                return BigDecimal.valueOf(score1).add(BigDecimal.valueOf(score2)).add(BigDecimal.valueOf(score3)).add(BigDecimal.valueOf(score4))
                                 .divide(BigDecimal.valueOf(4), 2, HALF_UP);
            }
        } else {
            if (score1 != null && score2 != null && score3 != null) {
                return BigDecimal.valueOf(score1).add(BigDecimal.valueOf(score2)).add(BigDecimal.valueOf(score3))
                                 .divide(BigDecimal.valueOf(3), 2, HALF_UP);
            }
        }
        return null;
    }

    public List<JudgeRankingEntryDTO> getRanking(Long reportId) {
        var modulKlasseBesetzung = jooqDsl.select(VEREIN_PROGRAMM.MODUL,
                                                  VEREIN_PROGRAMM.KLASSE,
                                                  VEREIN_PROGRAMM.BESETZUNG)
                                          .from(JUDGE_REPORT)
                                          .join(TIMETABLE_ENTRY).on(TIMETABLE_ENTRY.ID.eq(JUDGE_REPORT.FK_TIMETABLE_ENTRY))
                                          .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                                          .where(JUDGE_REPORT.ID.eq(reportId))
                                          .fetchSingle();

        var klasse = modulKlasseBesetzung.get(VEREIN_PROGRAMM.KLASSE);
        var besetzung = modulKlasseBesetzung.get(VEREIN_PROGRAMM.BESETZUNG);

        return jooqDsl.select(TIMETABLE_ENTRY.ID,
                              VEREIN.VEREINSNAME,
                              VEREIN_PROGRAMM.MODUL,
                              JUDGE_REPORT.SCORE)
                      .from(JUDGE_REPORT)
                      .join(TIMETABLE_ENTRY).on(TIMETABLE_ENTRY.ID.eq(JUDGE_REPORT.FK_TIMETABLE_ENTRY))
                      .join(VEREIN).on(VEREIN.ID.eq(TIMETABLE_ENTRY.FK_VEREIN))
                      .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                      .where(VEREIN_PROGRAMM.MODUL.eq(modulKlasseBesetzung.get(VEREIN_PROGRAMM.MODUL)),
                             klasse != null ? VEREIN_PROGRAMM.KLASSE.eq(klasse) : VEREIN_PROGRAMM.KLASSE.isNull(),
                             besetzung != null ? VEREIN_PROGRAMM.BESETZUNG.eq(besetzung) : VEREIN_PROGRAMM.BESETZUNG.isNull())
                      .stream()
                      .collect(groupingBy(it -> it.get(TIMETABLE_ENTRY.ID), toList()))
                      .values().stream()
                      .map(values -> {
                          var record1 = values.get(0);
                          var record2 = values.get(1);
                          var record3 = values.get(2);
                          var modul = Modul.valueOf(record1.get(VEREIN_PROGRAMM.MODUL));
                          var record4 = modul.isParademusik() ? values.get(3) : null;
                          var overallScore = overallScore(record1, record2, record3, record4, modul);
                          return new JudgeRankingEntryDTO(record1.get(VEREIN.VEREINSNAME), overallScore);
                      })
                      .sorted(comparing(JudgeRankingEntryDTO::score, nullsLast(naturalOrder())).reversed())
                      .toList();
    }

    public List<ModulDSelectionDTO> getModulDSelection() {
        var t1 = TITEL.as("t1");
        var t2 = TITEL.as("t2");
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                      .join(VEREIN).on(VEREIN.ID.eq(VEREIN_PROGRAMM.FK_VEREIN))
                      .join(t1).on(t1.ID.eq(VEREIN_PROGRAMM.MODUL_D_TITEL_1_ID))
                      .join(t2).on(t2.ID.eq(VEREIN_PROGRAMM.MODUL_D_TITEL_2_ID))
                      .where(VEREIN_PROGRAMM.MODUL.eq(D.name()))
                      .orderBy(TIMETABLE_ENTRY.DATE, TIMETABLE_ENTRY.START_TIME)
                      .fetch(it -> new ModulDSelectionDTO(
                              it.get(VEREIN_PROGRAMM.ID),
                              it.get(VEREIN.VEREINSNAME),
                              "%s (%s)".formatted(it.get(t1.TITEL_NAME), it.get(t1.COMPOSER)),
                              "%s (%s)".formatted(it.get(t2.TITEL_NAME), it.get(t2.COMPOSER)),
                              ModulDSelection.valueOf(it.get(VEREIN_PROGRAMM.MODUL_D_TITEL_SELECTION)),
                              LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.START_TIME))
                      ));
    }
}
