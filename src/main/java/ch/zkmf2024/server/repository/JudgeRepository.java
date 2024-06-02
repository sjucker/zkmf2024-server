package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.JudgeRankingEntryDTO;
import ch.zkmf2024.server.dto.JudgeReportCategory;
import ch.zkmf2024.server.dto.JudgeReportCategoryRating;
import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportModulCategory;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.JudgeReportRatingDTO;
import ch.zkmf2024.server.dto.JudgeReportScoreDTO;
import ch.zkmf2024.server.dto.JudgeReportStatus;
import ch.zkmf2024.server.dto.JudgeReportSummaryDTO;
import ch.zkmf2024.server.dto.JudgeReportTitleDTO;
import ch.zkmf2024.server.dto.JudgeReportViewDTO;
import ch.zkmf2024.server.dto.JudgeRole;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.ModulDSelection;
import ch.zkmf2024.server.dto.ModulDSelectionDTO;
import ch.zkmf2024.server.dto.TambourenGrundlage;
import ch.zkmf2024.server.dto.TitelDTO;
import ch.zkmf2024.server.dto.VereinPlayingDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeReportCommentDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeReportDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.JudgeReportRatingDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgePojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportCommentPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportRatingPojo;
import ch.zkmf2024.server.service.JudgeService.JudgeTeamIdentifier;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static ch.zkmf2024.server.dto.JudgeReportCategoryRating.NEUTRAL;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_A;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_B;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_C;
import static ch.zkmf2024.server.dto.JudgeReportStatus.DONE;
import static ch.zkmf2024.server.dto.Modul.D;
import static ch.zkmf2024.server.dto.Modul.G;
import static ch.zkmf2024.server.dto.ModulDSelection.TITEL_1;
import static ch.zkmf2024.server.dto.ModulDSelection.TITEL_2;
import static ch.zkmf2024.server.jooq.generated.Tables.CURRENTLY_PLAYING;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE_REPORT;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE_REPORT_COMMENT;
import static ch.zkmf2024.server.jooq.generated.Tables.JUDGE_REPORT_RATING;
import static ch.zkmf2024.server.jooq.generated.Tables.KONTAKT;
import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.RANKING_PENALTY;
import static ch.zkmf2024.server.jooq.generated.Tables.TIMETABLE_ENTRY;
import static ch.zkmf2024.server.jooq.generated.Tables.TITEL;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM_TITEL;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.WETTSPIEL;
import static java.math.RoundingMode.HALF_UP;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.nullsLast;
import static java.util.Objects.requireNonNull;
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
                              JUDGE_REPORT.STATUS,
                              JUDGE_REPORT.CATEGORY
                      )
                      .from(JUDGE_REPORT)
                      .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .where(JUDGE_REPORT.FK_JUDGE.eq(id))
                      .orderBy(TIMETABLE_ENTRY.DATE.asc(), TIMETABLE_ENTRY.START_TIME.asc(), JUDGE_REPORT.CATEGORY)
                      .fetch(it -> {
                          var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
                          var role = JudgeRole.valueOf(it.get(JUDGE_REPORT.ROLE));
                          var modulCategory = JudgeReportModulCategory.fromString(it.get(JUDGE_REPORT.CATEGORY));
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
                                  modulCategory.orElse(null),
                                  modulCategory.map(JudgeReportModulCategory::getDescription).orElse(null),
                                  LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.START_TIME)),
                                  LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.END_TIME)),
                                  JudgeReportStatus.valueOf(it.get(JUDGE_REPORT.STATUS))
                          );
                      });

    }

    private String toGoogleMapsUrl(String address) {
        return "https://www.google.com/maps/place/%s".formatted(URLEncoder.encode(address, UTF_8));
    }

    public List<JudgeReportDTO> getAllReports() {
        return jooqDsl.select()
                      .from(JUDGE_REPORT)
                      .join(JUDGE).on(JUDGE_REPORT.FK_JUDGE.eq(JUDGE.ID))
                      .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .join(KONTAKT).on(VEREIN.DIREKTION_KONTAKT_ID.eq(KONTAKT.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .fetch(this::toDTO);
    }

    public Optional<JudgeReportDTO> getReport(Long judgeId, Long reportId) {
        return jooqDsl.select()
                      .from(JUDGE_REPORT)
                      .join(JUDGE).on(JUDGE_REPORT.FK_JUDGE.eq(JUDGE.ID))
                      .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(LOCATION).on(TIMETABLE_ENTRY.FK_LOCATION.eq(LOCATION.ID))
                      .join(KONTAKT).on(VEREIN.DIREKTION_KONTAKT_ID.eq(KONTAKT.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .where(JUDGE_REPORT.FK_JUDGE.eq(judgeId),
                             JUDGE_REPORT.ID.eq(reportId))
                      .fetchOptional(this::toDTO);
    }

    private JudgeReportDTO toDTO(Record it) {
        var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
        var category = JudgeReportModulCategory.fromString(it.get(JUDGE_REPORT.CATEGORY));
        var role = JudgeRole.valueOf(it.get(JUDGE_REPORT.ROLE));
        var klasse = Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE));
        var besetzung = Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG));

        var minMaxDuration = programmVorgabenRepository.findMinMaxDuration(modul, klasse.orElse(null), besetzung.orElse(null));

        return new JudgeReportDTO(
                it.get(JUDGE_REPORT.ID),
                modul,
                modul.getFullDescription(),
                "%s %s".formatted(it.get(JUDGE.FIRST_NAME), it.get(JUDGE.NAME)),
                role,
                role.getDescription(),
                klasse.map(Klasse::getDescription).orElse(null),
                besetzung.map(Besetzung::getDescription).orElse(null),
                category.orElse(null),
                category.map(JudgeReportModulCategory::getDescription).orElse(null),
                LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.START_TIME)),
                LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.END_TIME)),
                it.get(LOCATION.NAME),
                it.get(VEREIN.VEREINSNAME),
                "%s %s".formatted(defaultString(it.get(KONTAKT.VORNAME)), defaultString(it.get(KONTAKT.NACHNAME))),
                it.get(VEREIN_PROGRAMM.TITEL),
                it.get(VEREIN_PROGRAMM.INFO_MODERATION),
                minMaxDuration.map(ProgrammVorgabenRepository.MinMaxDuration::minDurationInSeconds).orElse(null),
                minMaxDuration.map(ProgrammVorgabenRepository.MinMaxDuration::maxDurationInSeconds).orElse(null),
                TambourenGrundlage.fromString(it.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_1)).map(TambourenGrundlage::getDescription).orElse(null),
                TambourenGrundlage.fromString(it.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_2)).map(TambourenGrundlage::getDescription).orElse(null),
                it.get(JUDGE_REPORT.SCORE),
                it.get(JUDGE_REPORT.RATING_FIXED),
                JudgeReportStatus.valueOf(it.get(JUDGE_REPORT.STATUS)),
                findTitles(it.get(JUDGE_REPORT.ID), modul, category.orElse(null)),
                findOverallRatings(it.get(JUDGE_REPORT.ID), modul, category.orElse(null), role)
        );
    }

    public Optional<JudgeReportViewDTO> getReport(Long reportId) {
        return jooqDsl.select()
                      .from(JUDGE_REPORT)
                      .join(JUDGE).on(JUDGE_REPORT.FK_JUDGE.eq(JUDGE.ID))
                      .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .join(VEREIN).on(TIMETABLE_ENTRY.FK_VEREIN.eq(VEREIN.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .where(JUDGE_REPORT.ID.eq(reportId))
                      .fetchOptional(it -> {
                          var modul = Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL));
                          var category = JudgeReportModulCategory.fromString(it.get(JUDGE_REPORT.CATEGORY));
                          var role = JudgeRole.valueOf(it.get(JUDGE_REPORT.ROLE));
                          var klasse = Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE));
                          var besetzung = Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG));

                          return new JudgeReportViewDTO(
                                  "%s %s".formatted(it.get(JUDGE.FIRST_NAME), it.get(JUDGE.NAME)),
                                  modul,
                                  modul.getFullDescription(),
                                  klasse.map(Klasse::getDescription).orElse(null),
                                  besetzung.map(Besetzung::getDescription).orElse(null),
                                  category.orElse(null),
                                  category.map(JudgeReportModulCategory::getDescription).orElse(null),
                                  it.get(VEREIN.VEREINSNAME),
                                  it.get(JUDGE_REPORT.SCORE),
                                  JudgeReportStatus.valueOf(it.get(JUDGE_REPORT.STATUS)),
                                  findTitles(reportId, modul, category.orElse(null)),
                                  findOverallRatings(reportId, modul, category.orElse(null), role)
                          );
                      });
    }

    private List<JudgeReportTitleDTO> findTitles(Long reportId, Modul modul, JudgeReportModulCategory category) {
        if (modul == D) {
            var titel1Condition = VEREIN_PROGRAMM.MODUL_D_TITEL_1_ID.eq(TITEL.ID).and(VEREIN_PROGRAMM.MODUL_D_TITEL_SELECTION.eq(TITEL_1.name()));
            var titel2Condition = VEREIN_PROGRAMM.MODUL_D_TITEL_2_ID.eq(TITEL.ID).and(VEREIN_PROGRAMM.MODUL_D_TITEL_SELECTION.eq(TITEL_2.name()));
            return fetchTitel(reportId, modul, category, titel1Condition.or(titel2Condition));
        } else if (modul == G) {
            requireNonNull(category, "category must be set for modul G!");

            return switch (category) {
                case MODUL_G_KAT_A -> Stream.concat(fetchTitel(reportId, modul, category, VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_1_ID.eq(TITEL.ID)).stream(),
                                                    fetchTitel(reportId, modul, category, VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_2_ID.eq(TITEL.ID)).stream())
                                            .toList();
                case MODUL_G_KAT_B -> fetchTitel(reportId, modul, category, VEREIN_PROGRAMM.MODUL_G_KAT_B_TITEL_ID.eq(TITEL.ID));
                case MODUL_G_KAT_C -> fetchTitel(reportId, modul, category, VEREIN_PROGRAMM.MODUL_G_KAT_C_TITEL_ID.eq(TITEL.ID));
            };
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
                          .fetch(it -> toJudgeReportTitleDTO(modul, category, it));
        }

    }

    private List<JudgeReportTitleDTO> fetchTitel(Long reportId, Modul modul, JudgeReportModulCategory category, Condition condition) {
        return jooqDsl.select()
                      .from(JUDGE_REPORT)
                      .join(TIMETABLE_ENTRY).on(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .join(VEREIN_PROGRAMM).on(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM.eq(VEREIN_PROGRAMM.ID))
                      .join(TITEL).on(condition)
                      .leftJoin(JUDGE_REPORT_COMMENT).on(JUDGE_REPORT.ID.eq(JUDGE_REPORT_COMMENT.FK_JUDGE_REPORT),
                                                         TITEL.ID.eq(JUDGE_REPORT_COMMENT.FK_TITEL))
                      .where(JUDGE_REPORT.ID.eq(reportId))
                      .fetch(it -> toJudgeReportTitleDTO(modul, category, it));
    }

    private JudgeReportTitleDTO toJudgeReportTitleDTO(Modul modul, JudgeReportModulCategory category, Record it) {
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
                findTitelRatings(it.get(JUDGE_REPORT.ID), it.get(TITEL.ID), modul, category, role)
        );
    }

    private List<JudgeReportRatingDTO> findOverallRatings(Long reportId, Modul modul, JudgeReportModulCategory category, JudgeRole role) {
        var ratings = jooqDsl.select()
                             .from(JUDGE_REPORT_RATING)
                             .where(JUDGE_REPORT_RATING.FK_JUDGE_REPORT.eq(reportId),
                                    JUDGE_REPORT_RATING.FK_TITEL.isNull())
                             .fetch(this::toJudgeReportRatingDTO).stream()
                             .sorted(comparing(JudgeReportRatingDTO::category))
                             .toList();

        if (ratings.isEmpty()) {
            ratings = JudgeReportCategory.get(modul, category, role, true).stream()
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
                category.getRatingDescriptions(),
                null,
                NEUTRAL,
                null
        );
    }

    private JudgeReportRatingDTO toJudgeReportRatingDTO(org.jooq.Record it) {
        var category = JudgeReportCategory.fromString(it.get(JUDGE_REPORT_RATING.CATEGORY)).orElseThrow();
        return new JudgeReportRatingDTO(
                category,
                category.getDescription(),
                category.getGroup(),
                category.getRatingDescriptions(),
                it.get(JUDGE_REPORT_RATING.COMMENT),
                JudgeReportCategoryRating.fromString(it.get(JUDGE_REPORT_RATING.RATING)).orElseThrow(),
                it.get(JUDGE_REPORT_RATING.SCORE)
        );
    }

    private List<JudgeReportRatingDTO> findTitelRatings(Long reportId, Long titelId, Modul modul, JudgeReportModulCategory category, JudgeRole role) {
        var ratings = jooqDsl.select()
                             .from(JUDGE_REPORT_RATING)
                             .where(JUDGE_REPORT_RATING.FK_JUDGE_REPORT.eq(reportId),
                                    JUDGE_REPORT_RATING.FK_TITEL.eq(titelId))
                             .fetch(this::toJudgeReportRatingDTO).stream()
                             .sorted(comparing(JudgeReportRatingDTO::category))
                             .toList();

        if (ratings.isEmpty()) {
            ratings = JudgeReportCategory.get(modul, category, role, false).stream()
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
        var query = jooqDsl.select()
                           .from(JUDGE_REPORT)
                           .join(TIMETABLE_ENTRY).on(TIMETABLE_ENTRY.ID.eq(JUDGE_REPORT.FK_TIMETABLE_ENTRY))
                           .join(VEREIN).on(VEREIN.ID.eq(TIMETABLE_ENTRY.FK_VEREIN))
                           .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                           .join(JUDGE).on(JUDGE.ID.eq(JUDGE_REPORT.FK_JUDGE))
                           .join(LOCATION).on(LOCATION.ID.eq(TIMETABLE_ENTRY.FK_LOCATION))
                           .orderBy(TIMETABLE_ENTRY.DATE, TIMETABLE_ENTRY.START_TIME, TIMETABLE_ENTRY.END_TIME);
        try (var stream = query.stream()) {
            return stream.collect(groupingBy(it -> it.get(TIMETABLE_ENTRY.ID), toList()))
                         .values().stream()
                         .<JudgeReportSummaryDTO>mapMulti((values, consumer) -> {
                             var modul = Modul.valueOf(values.getFirst().get(VEREIN_PROGRAMM.MODUL));
                             var valuesPerCategory = values.stream().collect(groupingBy(r -> defaultString(r.get(JUDGE_REPORT.CATEGORY)), toList()));

                             for (var e : valuesPerCategory.entrySet()) {
                                 // only relevant for Modul G, where there are multiple categories and therefore reports for single timeslot
                                 var category = e.getKey();
                                 var records = e.getValue();

                                 var record1 = records.get(0);
                                 var record2 = records.get(1);
                                 var record3 = records.get(2);
                                 var record4 = modul.isParademusik() ? records.get(3) : null;

                                 var klasse = Klasse.fromString(record1.get(VEREIN_PROGRAMM.KLASSE));
                                 var besetzung = Besetzung.fromString(record1.get(VEREIN_PROGRAMM.BESETZUNG));
                                 var modulCategory = JudgeReportModulCategory.fromString(category);
                                 consumer.accept(new JudgeReportSummaryDTO(
                                         record1.get(VEREIN_PROGRAMM.ID),
                                         record1.get(TIMETABLE_ENTRY.DATE),
                                         LocationRepository.toDTO(record1),
                                         modul,
                                         getFullDescription(modul, category),
                                         klasse.orElse(null),
                                         klasse.map(Klasse::getDescription).orElse(null),
                                         besetzung.orElse(null),
                                         besetzung.map(Besetzung::getDescription).orElse(null),
                                         modulCategory.orElse(null),
                                         modulCategory.map(JudgeReportModulCategory::getDescription).orElse(null),
                                         record1.get(VEREIN.VEREINSNAME),
                                         overallScore(record1, record2, record3, record4, modul).orElse(null),
                                         records.stream()
                                                .sorted(comparing(it -> it.get(JUDGE_REPORT.ID)))
                                                .map(it -> new JudgeReportScoreDTO(
                                                        it.get(JUDGE_REPORT.ID),
                                                        it.get(JUDGE.EMAIL),
                                                        "%s %s".formatted(it.get(JUDGE.FIRST_NAME), it.get(JUDGE.NAME)),
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
                                 ));
                             }
                         })
                         .sorted(comparing(JudgeReportSummaryDTO::modul)
                                         .thenComparing(JudgeReportSummaryDTO::klasse, nullsLast(naturalOrder()))
                                         .thenComparing(JudgeReportSummaryDTO::besetzung, nullsLast(naturalOrder()))
                                         .thenComparing(JudgeReportSummaryDTO::overallScore, nullsLast(naturalOrder())))
                         .toList();
        }
    }

    private String getFullDescription(Modul modul, String category) {
        return JudgeReportModulCategory.fromString(category)
                                       .map(JudgeReportModulCategory::getDescription)
                                       .map(desc -> "%s (%s)".formatted(modul.getFullDescription(), desc))
                                       .orElse(modul.getFullDescription());
    }

    private boolean isDone(Record record1, Record record2, Record record3) {
        return JudgeReportStatus.valueOf(record1.get(JUDGE_REPORT.STATUS)) == DONE &&
                JudgeReportStatus.valueOf(record2.get(JUDGE_REPORT.STATUS)) == DONE &&
                JudgeReportStatus.valueOf(record3.get(JUDGE_REPORT.STATUS)) == DONE;
    }

    private Optional<BigDecimal> overallScore(Record record1, Record record2, Record record3, Record record4, Modul modul) {
        var score1 = record1.get(JUDGE_REPORT.SCORE);
        var score2 = record2.get(JUDGE_REPORT.SCORE);
        var score3 = record3.get(JUDGE_REPORT.SCORE);
        if (modul.isParademusik()) {
            var score4 = record4.get(JUDGE_REPORT.SCORE);
            if (score1 != null && score2 != null && score3 != null && score4 != null) {
                return Optional.of(score1.add(score2).add(score3).add(score4)
                                         .divide(BigDecimal.valueOf(4), 2, HALF_UP));
            }
        } else {
            if (score1 != null && score2 != null && score3 != null) {
                return Optional.of(score1.add(score2).add(score3)
                                         .divide(BigDecimal.valueOf(3), 2, HALF_UP));
            }
        }
        return Optional.empty();
    }

    public List<JudgeRankingEntryDTO> getRanking(Long reportId) {
        return getRanking(reportId, null);
    }

    public List<JudgeRankingEntryDTO> getRanking(Long reportId, Long judgeId) {
        var judgeTeamIdentifier = jooqDsl.select(VEREIN_PROGRAMM.MODUL,
                                                 VEREIN_PROGRAMM.KLASSE,
                                                 VEREIN_PROGRAMM.BESETZUNG,
                                                 JUDGE_REPORT.CATEGORY,
                                                 LOCATION.IDENTIFIER)
                                         .from(JUDGE_REPORT)
                                         .join(TIMETABLE_ENTRY).on(TIMETABLE_ENTRY.ID.eq(JUDGE_REPORT.FK_TIMETABLE_ENTRY))
                                         .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                                         .join(LOCATION).on(LOCATION.ID.eq(TIMETABLE_ENTRY.FK_LOCATION))
                                         .where(JUDGE_REPORT.ID.eq(reportId))
                                         .fetchSingle(it -> new JudgeTeamIdentifier(Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)),
                                                                                    Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).orElse(null),
                                                                                    Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).orElse(null),
                                                                                    JudgeReportModulCategory.fromString(it.get(JUDGE_REPORT.CATEGORY)).orElse(null),
                                                                                    it.get(LOCATION.IDENTIFIER)));

        var klasse = judgeTeamIdentifier.klasse();
        var besetzung = judgeTeamIdentifier.besetzung();
        var category = judgeTeamIdentifier.category();

        var query = jooqDsl.select(TIMETABLE_ENTRY.ID,
                                   VEREIN.VEREINSNAME,
                                   VEREIN_PROGRAMM.MODUL,
                                   JUDGE_REPORT.SCORE)
                           .from(JUDGE_REPORT)
                           .join(JUDGE).on(JUDGE.ID.eq(JUDGE_REPORT.FK_JUDGE))
                           .join(TIMETABLE_ENTRY).on(TIMETABLE_ENTRY.ID.eq(JUDGE_REPORT.FK_TIMETABLE_ENTRY))
                           .join(VEREIN).on(VEREIN.ID.eq(TIMETABLE_ENTRY.FK_VEREIN))
                           .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                           .join(LOCATION).on(LOCATION.ID.eq(TIMETABLE_ENTRY.FK_LOCATION))
                           .where(VEREIN_PROGRAMM.MODUL.eq(judgeTeamIdentifier.modul().name()),
                                  klasse != null ? VEREIN_PROGRAMM.KLASSE.eq(klasse.name()) : VEREIN_PROGRAMM.KLASSE.isNull(),
                                  besetzung != null ? VEREIN_PROGRAMM.BESETZUNG.eq(besetzung.name()) : VEREIN_PROGRAMM.BESETZUNG.isNull(),
                                  category != null ? JUDGE_REPORT.CATEGORY.eq(category.name()) : JUDGE_REPORT.CATEGORY.isNull(),
                                  LOCATION.IDENTIFIER.eq(judgeTeamIdentifier.locationIdentifier()))
                           .and(judgeId != null ? JUDGE.ID.eq(judgeId) : DSL.noCondition());
        try (var stream = query.stream()) {
            return stream.collect(groupingBy(it -> it.get(TIMETABLE_ENTRY.ID), toList()))
                         .values().stream()
                         .map(values -> {
                             if (judgeId != null) {
                                 var record1 = values.getFirst();
                                 return new JudgeRankingEntryDTO(record1.get(VEREIN.VEREINSNAME), record1.get(JUDGE_REPORT.SCORE));
                             } else {
                                 var record1 = values.get(0);
                                 var record2 = values.get(1);
                                 var record3 = values.get(2);
                                 var modul = Modul.valueOf(record1.get(VEREIN_PROGRAMM.MODUL));
                                 var record4 = modul.isParademusik() ? values.get(3) : null;
                                 var overallScore = overallScore(record1, record2, record3, record4, modul);
                                 return new JudgeRankingEntryDTO(record1.get(VEREIN.VEREINSNAME), overallScore.orElse(null));
                             }
                         })
                         .sorted(comparing(JudgeRankingEntryDTO::score, nullsFirst(naturalOrder())).reversed()
                                                                                                   .thenComparing(JudgeRankingEntryDTO::verein))
                         .toList();
        }
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

    public Set<JudgeReportModulCategory> getModulGCategories(Long timetableEntryId) {
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                      .where(TIMETABLE_ENTRY.ID.eq(timetableEntryId))
                      .fetchOptional(it -> {
                          Set<JudgeReportModulCategory> result = new HashSet<>();
                          if (it.get(VEREIN_PROGRAMM.MODUL_G_KAT_A_TITEL_1_ID) != null) {
                              result.add(MODUL_G_KAT_A);
                          }
                          if (it.get(VEREIN_PROGRAMM.MODUL_G_KAT_B_TITEL_ID) != null) {
                              result.add(MODUL_G_KAT_B);
                          }
                          if (it.get(VEREIN_PROGRAMM.MODUL_G_KAT_C_TITEL_ID) != null) {
                              result.add(MODUL_G_KAT_C);
                          }
                          return result;
                      })
                      .orElse(Set.of());
    }

    public List<VereinPlayingDTO> getVereinPlayingEntries(String locationIdentifier) {
        return jooqDsl.select()
                      .from(TIMETABLE_ENTRY)
                      .join(VEREIN).on(VEREIN.ID.eq(TIMETABLE_ENTRY.FK_VEREIN))
                      .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.ID.eq(TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM))
                      .join(LOCATION).on(LOCATION.ID.eq(TIMETABLE_ENTRY.FK_LOCATION))
                      .leftJoin(CURRENTLY_PLAYING).on(CURRENTLY_PLAYING.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .leftJoin(RANKING_PENALTY).on(RANKING_PENALTY.FK_TIMETABLE_ENTRY.eq(TIMETABLE_ENTRY.ID))
                      .where(LOCATION.IDENTIFIER.equalIgnoreCase(locationIdentifier),
                             TIMETABLE_ENTRY.ENTRY_TYPE.eq(WETTSPIEL))
                      .orderBy(CURRENTLY_PLAYING.ENDED_AT.nullsFirst(), TIMETABLE_ENTRY.DATE, TIMETABLE_ENTRY.START_TIME, TIMETABLE_ENTRY.END_TIME)
                      .fetch(it -> {
                          var minMaxDuration = programmVorgabenRepository.findMinMaxDuration(Modul.valueOf(it.get(VEREIN_PROGRAMM.MODUL)),
                                                                                             Klasse.fromString(it.get(VEREIN_PROGRAMM.KLASSE)).orElse(null),
                                                                                             Besetzung.fromString(it.get(VEREIN_PROGRAMM.BESETZUNG)).orElse(null));

                          return new VereinPlayingDTO(
                                  it.get(TIMETABLE_ENTRY.ID),
                                  it.get(VEREIN.VEREINSNAME),
                                  LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.START_TIME)),
                                  LocalDateTime.of(it.get(TIMETABLE_ENTRY.DATE), it.get(TIMETABLE_ENTRY.END_TIME)),
                                  minMaxDuration.map(ProgrammVorgabenRepository.MinMaxDuration::minDurationInSeconds).orElse(null),
                                  minMaxDuration.map(ProgrammVorgabenRepository.MinMaxDuration::maxDurationInSeconds).orElse(null),
                                  it.get(CURRENTLY_PLAYING.STARTED_AT) != null,
                                  it.get(CURRENTLY_PLAYING.ENDED_AT) != null,
                                  getJury(it.get(TIMETABLE_ENTRY.ID)),
                                  it.get(RANKING_PENALTY.MINUTES_OVERRUN)
                          );
                      });

    }

    private String getJury(long timetableEntryId) {
        return String.join(", ", jooqDsl.select()
                                        .from(JUDGE_REPORT)
                                        .join(JUDGE).on(JUDGE.ID.eq(JUDGE_REPORT.FK_JUDGE))
                                        .where(JUDGE_REPORT.FK_TIMETABLE_ENTRY.eq(timetableEntryId))
                                        .orderBy(JUDGE_REPORT.ROLE)
                                        .fetch(it -> "%s %s".formatted(it.get(JUDGE.FIRST_NAME), it.get(JUDGE.NAME))));
    }
}
