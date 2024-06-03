package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.JudgePresentationDTO;
import ch.zkmf2024.server.dto.JudgeRankingEntryDTO;
import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportModulCategory;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.JudgeReportRatingDTO;
import ch.zkmf2024.server.dto.JudgeReportStatus;
import ch.zkmf2024.server.dto.JudgeReportSummaryDTO;
import ch.zkmf2024.server.dto.JudgeReportViewDTO;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.ModulDSelectionDTO;
import ch.zkmf2024.server.dto.UserRole;
import ch.zkmf2024.server.dto.VereinPlayingDTO;
import ch.zkmf2024.server.dto.admin.JudgeDTO;
import ch.zkmf2024.server.dto.admin.JudgeReportCreateDTO;
import ch.zkmf2024.server.dto.admin.JuryLoginCreateDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.CurrentlyPlayingPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgePojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportCommentPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportRatingPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.Zkmf2024UserPojo;
import ch.zkmf2024.server.repository.CurrentlyPlayingRepository;
import ch.zkmf2024.server.repository.JudgeRepository;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static ch.zkmf2024.server.dto.JudgeReportStatus.DONE;
import static ch.zkmf2024.server.dto.JudgeReportStatus.IN_PROGRESS;
import static ch.zkmf2024.server.dto.JudgeReportStatus.NEW;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_1;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_1_OPTISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_2;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_2_MUSIKALISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_3;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_3_MUSIKALISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_4_OPTISCH;
import static ch.zkmf2024.server.dto.UserRole.ADMIN;
import static ch.zkmf2024.server.dto.UserRole.JUDGE;
import static ch.zkmf2024.server.util.DateUtil.now;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.toRootLowerCase;

@Slf4j
@Service
public class JudgeService {

    private final JudgeRepository judgeRepository;
    private final UserRepository userRepository;
    private final VereinRepository vereinRepository;
    private final TimetableRepository timetableRepository;
    private final LocationRepository locationRepository;
    private final CurrentlyPlayingRepository currentlyPlayingRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public JudgeService(JudgeRepository judgeRepository,
                        UserRepository userRepository,
                        VereinRepository vereinRepository,
                        TimetableRepository timetableRepository,
                        LocationRepository locationRepository,
                        CurrentlyPlayingRepository currentlyPlayingRepository,
                        MailService mailService,
                        PasswordEncoder passwordEncoder) {
        this.judgeRepository = judgeRepository;
        this.userRepository = userRepository;
        this.vereinRepository = vereinRepository;
        this.timetableRepository = timetableRepository;
        this.locationRepository = locationRepository;
        this.currentlyPlayingRepository = currentlyPlayingRepository;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<JudgeReportOverviewDTO> getReports(String username) {
        return judgeRepository.findByEmail(username)
                              .map(judge -> judgeRepository.getReports(judge.getId()))
                              .orElseThrow();
    }

    public Optional<JudgeReportDTO> getReport(String username, Long reportId) {
        return judgeRepository.findByEmail(username)
                              .flatMap(judge -> judgeRepository.getReport(judge.getId(), reportId));

    }

    public Optional<JudgeReportViewDTO> getReport(Long reportId) {
        return judgeRepository.getReport(reportId);
    }

    public void update(String username, Long reportId, JudgeReportDTO dto) {
        if (!judgeRepository.exists(username, reportId)) {
            throw new IllegalStateException();
        }

        var reportPojo = judgeRepository.findReportById(reportId);
        if (JudgeReportStatus.valueOf(reportPojo.getStatus()) == DONE) {
            throw new IllegalArgumentException("tried to update report with id %d that was already done".formatted(reportId));
        }

        reportPojo.setScore(getScore(dto.score(), dto.modul()));
        reportPojo.setStatus(IN_PROGRESS.name());
        judgeRepository.update(reportPojo);

        judgeRepository.deleteReportCommentsByReportId(reportId);
        judgeRepository.deleteReportRatingsByReportId(reportId);

        for (var reportTitle : dto.titles()) {
            judgeRepository.insert(
                    new JudgeReportCommentPojo(reportId, reportTitle.titel().id(), reportTitle.comment())
            );
            updateRatings(reportTitle.ratings(), reportId, reportTitle.titel().id());
        }

        updateRatings(dto.overallRatings(), reportId, null);
    }

    private BigDecimal getScore(BigDecimal score, Modul modul) {
        if (score == null) {
            return null;
        }

        return switch (modul) {
            case G -> score.setScale(1, HALF_UP);
            default -> score.setScale(0, HALF_UP);
        };
    }

    private void updateRatings(List<JudgeReportRatingDTO> reportTitle, Long reportId, Long titleId) {
        for (var rating : reportTitle) {
            judgeRepository.insert(
                    new JudgeReportRatingPojo(
                            null,
                            reportId,
                            titleId,
                            rating.category().name(),
                            rating.rating().name(),
                            rating.comment(),
                            rating.score()
                    )
            );
        }
    }

    public void fixRating(String username, Long reportId) {
        if (!judgeRepository.exists(username, reportId)) {
            throw new IllegalArgumentException("no report exists for %s and id %d".formatted(username, reportId));
        }

        var reportPojo = judgeRepository.findReportById(reportId);
        if (reportPojo.getRatingFixed()) {
            throw new IllegalArgumentException("tried to fix rating for report with id %d that was already fixed".formatted(reportId));
        }

        reportPojo.setRatingFixed(true);
        judgeRepository.update(reportPojo);
    }

    public void finish(String username, Long reportId) {
        if (!judgeRepository.exists(username, reportId)) {
            throw new IllegalArgumentException("no report exists for %s and id %d".formatted(username, reportId));
        }

        var reportPojo = judgeRepository.findReportById(reportId);
        if (JudgeReportStatus.valueOf(reportPojo.getStatus()) == DONE) {
            throw new IllegalArgumentException("tried to finish report with id %d that was already done".formatted(reportId));
        }

        reportPojo.setRatingFixed(true);
        reportPojo.setStatus(DONE.name());
        reportPojo.setFinishedAt(now());
        judgeRepository.update(reportPojo);
    }

    public void createLogin(JuryLoginCreateDTO dto) {
        judgeRepository.insert(new JudgePojo(null, dto.email(), dto.name(), dto.firstName(), null, null, null));
        userRepository.insert(new Zkmf2024UserPojo(toRootLowerCase(dto.email()),
                                                   JUDGE.name(),
                                                   passwordEncoder.encode(dto.password()),
                                                   null,
                                                   now(),
                                                   null,
                                                   null,
                                                   null,
                                                   false));
    }

    public List<JudgeDTO> findAll() {
        return judgeRepository.findAll().stream()
                              .map(it -> new JudgeDTO(it.getId(), it.getName(), it.getEmail()))
                              .sorted(comparing(JudgeDTO::name))
                              .toList();
    }

    public List<JudgeReportSummaryDTO> findSummaries(String username) {
        var user = userRepository.findById(username).orElseThrow(() -> new NoSuchElementException("no user found for %s".formatted(username)));
        var summaries = judgeRepository.findSummaries();
        if (UserRole.valueOf(user.getRole()) == ADMIN) {
            // admin sees everything
            return summaries;
        } else {
            // find all relevant modul/klasse/besetzung for current judge
            var relevant = summaries.stream()
                                    .filter(dto -> dto.scores().stream().anyMatch(d -> StringUtils.equals(d.judgeEmail(), user.getEmail())))
                                    .map(JudgeTeamIdentifier::of)
                                    .collect(toSet());

            // only display the entries that are relevant for judge
            return summaries.stream()
                            .filter(dto -> relevant.contains(JudgeTeamIdentifier.of(dto)))
                            .toList();
        }
    }

    public record JudgeTeamIdentifier(Modul modul, Klasse klasse, Besetzung besetzung, JudgeReportModulCategory category, String locationIdentifier) {
        public static JudgeTeamIdentifier of(JudgeReportSummaryDTO dto) {
            return new JudgeTeamIdentifier(dto.modul(), dto.klasse(), dto.besetzung(), dto.category(), dto.location().identifier());
        }
    }

    public void createReports(Long timetableEntryId, JudgeReportCreateDTO dto) {
        if (dto.modul().isParademusik()) {
            for (var modulGCategory : judgeRepository.getModulGCategories(timetableEntryId)) {
                judgeRepository.insert(new JudgeReportPojo(null, dto.judge1Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_1_OPTISCH.name(), modulGCategory.name()));
                judgeRepository.insert(new JudgeReportPojo(null, dto.judge2Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_2_MUSIKALISCH.name(), modulGCategory.name()));
                judgeRepository.insert(new JudgeReportPojo(null, dto.judge3Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_3_MUSIKALISCH.name(), modulGCategory.name()));
                judgeRepository.insert(new JudgeReportPojo(null, dto.judge4Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_4_OPTISCH.name(), modulGCategory.name()));
            }
        } else {
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge1Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_1.name(), null));
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge2Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_2.name(), null));
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge3Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_3.name(), null));
        }
    }

    public List<JudgeRankingEntryDTO> getRanking(Long reportId) {
        return judgeRepository.getRanking(reportId);
    }

    public List<JudgeRankingEntryDTO> getRankingOwnOnly(Long reportId, String username) {
        return judgeRepository.getRanking(reportId, judgeRepository.findByEmail(username).map(JudgePojo::getId).orElseThrow());
    }

    public void addRanking(String username, Long programmId) {

    }

    public void confirmScores(String username, Long programmId) {
        vereinRepository.confirmScores(username, programmId);
        mailService.sendScoresConfirmation(vereinRepository.getEmailByProgrammId(programmId).orElseThrow());
    }

    public List<ModulDSelectionDTO> getModulDSelection() {
        return judgeRepository.getModulDSelection();
    }

    public void updateModulD(List<ModulDSelectionDTO> dtos) {
        var allModulDProgramme = vereinRepository.findAllModulDProgramme().stream()
                                                 .collect(toMap(VereinProgrammPojo::getId, identity()));

        for (var dto : dtos) {
            var vereinProgrammPojo = allModulDProgramme.get(dto.vereinProgrammId());
            if (!vereinProgrammPojo.getModulDTitelSelection().equals(dto.selection().name())) {
                vereinProgrammPojo.setModulDTitelSelection(dto.selection().name());
                vereinRepository.update(vereinProgrammPojo);
                log.info("updating VereinProgramm {} modul D selection: {}", vereinProgrammPojo.getId(), dto.selection());
            }
        }

    }

    public List<JudgePresentationDTO> getJudgePresentations() {
        return judgeRepository.findAll().stream()
                              .filter(judge -> isNotBlank(judge.getModul()))
                              .sorted(comparing(JudgePojo::getModul).thenComparing(JudgePojo::getName).thenComparing(JudgePojo::getFirstName))
                              .map(judge -> new JudgePresentationDTO("%s %s".formatted(judge.getName(), judge.getFirstName()),
                                                                     judge.getModul(),
                                                                     judge.getCloudflareId(),
                                                                     judge.getPresentationText()))
                              .toList();
    }

    public List<VereinPlayingDTO> getVereinPlayingEntries(String locationIdentifier) {
        return judgeRepository.getVereinPlayingEntries(locationIdentifier);
    }

    public void setCurrentlyPlaying(String locationIdentifier, Long timetableEntryId, Consumer<CurrentlyPlayingPojo> setter) {
        var location = locationRepository.findByIdentifier(locationIdentifier).orElseThrow(() -> new NoSuchElementException("unknown locationIdentifier: " + locationIdentifier));
        var timetableEntry = timetableRepository.find(timetableEntryId).orElseThrow(() -> new NoSuchElementException("unknown timetableEntryId: " + timetableEntryId));

        if (!Objects.equals(timetableEntry.getFkLocation(), location.id())) {
            throw new IllegalArgumentException("tried to update timetable entry %s for wrong location %s".formatted(timetableEntryId, locationIdentifier));
        }

        currentlyPlayingRepository.findByTimetableEntryId(timetableEntryId)
                                  .ifPresentOrElse(pojo -> {
                                                       setter.accept(pojo);
                                                       currentlyPlayingRepository.update(pojo);
                                                   },
                                                   () -> {
                                                       var pojo = new CurrentlyPlayingPojo(null, timetableEntry.getId(), null, null);
                                                       setter.accept(pojo);
                                                       currentlyPlayingRepository.insert(pojo);

                                                   });
    }

    public void setRankingPenalty(String locationIdentifier, Long vereinProgrammId, int minutesOverrun) {
        var location = locationRepository.findByIdentifier(locationIdentifier).orElseThrow(() -> new NoSuchElementException("unknown locationIdentifier: " + locationIdentifier));
        var timetableEntry = timetableRepository.findWettspielByProgrammId(vereinProgrammId).orElseThrow(() -> new NoSuchElementException("unknown vereinProgrammId: " + vereinProgrammId));

        if (!Objects.equals(timetableEntry.getFkLocation(), location.id())) {
            throw new IllegalArgumentException("tried to set ranking penalty for verein programm %s for wrong location %s".formatted(vereinProgrammId, locationIdentifier));
        }

        vereinRepository.findVereinProgramm(vereinProgrammId)
                        .ifPresentOrElse(pojo -> {
                                             pojo.setMinutesOverrun(minutesOverrun);
                                             vereinRepository.update(pojo);
                                         },
                                         () -> {
                                             throw new NoSuchElementException("no verein programm found for: " + vereinProgrammId);
                                         });
    }
}
