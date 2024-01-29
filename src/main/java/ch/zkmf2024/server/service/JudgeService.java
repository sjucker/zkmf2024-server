package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.JudgeRankingEntryDTO;
import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.JudgeReportRatingDTO;
import ch.zkmf2024.server.dto.JudgeReportStatus;
import ch.zkmf2024.server.dto.JudgeReportSummaryDTO;
import ch.zkmf2024.server.dto.admin.JudgeDTO;
import ch.zkmf2024.server.dto.admin.JudgeReportCreateDTO;
import ch.zkmf2024.server.dto.admin.JuryLoginCreateDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgePojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportCommentPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportRatingPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.Zkmf2024UserPojo;
import ch.zkmf2024.server.repository.JudgeRepository;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import static ch.zkmf2024.server.dto.Modul.A;
import static ch.zkmf2024.server.dto.Modul.B;
import static ch.zkmf2024.server.dto.Modul.D;
import static ch.zkmf2024.server.dto.Modul.E;
import static ch.zkmf2024.server.dto.Modul.F;
import static ch.zkmf2024.server.dto.Modul.G;
import static ch.zkmf2024.server.dto.Modul.H;
import static ch.zkmf2024.server.dto.UserRole.JUDGE;
import static ch.zkmf2024.server.util.DateUtil.now;

@Slf4j
@Service
public class JudgeService {

    private final JudgeRepository judgeRepository;
    private final UserRepository userRepository;
    private final VereinRepository vereinRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public JudgeService(JudgeRepository judgeRepository,
                        UserRepository userRepository,
                        VereinRepository vereinRepository,
                        MailService mailService,
                        PasswordEncoder passwordEncoder) {
        this.judgeRepository = judgeRepository;
        this.userRepository = userRepository;
        this.vereinRepository = vereinRepository;
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

    public void update(String username, Long reportId, JudgeReportDTO dto) {
        if (!judgeRepository.exists(username, reportId)) {
            throw new IllegalStateException();
        }

        var reportPojo = judgeRepository.findReportById(reportId);
        if (JudgeReportStatus.valueOf(reportPojo.getStatus()) == DONE) {
            throw new IllegalArgumentException("tried to update report with id %d that was already done".formatted(reportId));
        }

        reportPojo.setScore(dto.score());
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

    private void updateRatings(List<JudgeReportRatingDTO> reportTitle, Long reportId, Long titleId) {
        for (var rating : reportTitle) {
            judgeRepository.insert(
                    new JudgeReportRatingPojo(
                            null,
                            reportId,
                            titleId,
                            rating.category().name(),
                            rating.rating().name(),
                            rating.comment()
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

        reportPojo.setStatus(DONE.name());
        reportPojo.setFinishedAt(now());
        judgeRepository.update(reportPojo);
    }

    public void createLogin(JuryLoginCreateDTO dto) {
        judgeRepository.insert(new JudgePojo(null, dto.email(), dto.name()));
        userRepository.insert(new Zkmf2024UserPojo(dto.email(),
                                                   JUDGE.name(),
                                                   passwordEncoder.encode(dto.password()),
                                                   null,
                                                   now(),
                                                   null,
                                                   null,
                                                   null));
    }

    public List<JudgeDTO> findAll() {
        return judgeRepository.findAll().stream()
                              .map(it -> new JudgeDTO(it.getId(), it.getName(), it.getEmail()))
                              .toList();
    }

    public List<JudgeReportSummaryDTO> findSummaries() {
        return judgeRepository.findSummaries();
    }

    public void createReports(Long timetableEntryId, JudgeReportCreateDTO dto) {
        if (Set.of(A, B, G, H).contains(dto.modul())) {
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge1Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_1.name()));
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge2Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_2.name()));
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge3Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_3.name()));
        }

        if (Set.of(D, E, F).contains(dto.modul())) {
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge1Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_1_OPTISCH.name()));
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge2Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_2_MUSIKALISCH.name()));
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge3Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_3_MUSIKALISCH.name()));
            judgeRepository.insert(new JudgeReportPojo(null, dto.judge4Id(), timetableEntryId, null, NEW.name(), null, false, JUROR_4_OPTISCH.name()));
        }
    }

    public List<JudgeRankingEntryDTO> getRanking(Long reportId) {
        return judgeRepository.getRanking(reportId);
    }

    public void confirmScores(String username, Long programmId) {
        vereinRepository.confirmScores(username, programmId);
        mailService.sendScoresConfirmation(vereinRepository.getEmailByProgrammId(programmId).orElseThrow());
    }
}
