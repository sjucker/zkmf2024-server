package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportCommentPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportRatingPojo;
import ch.zkmf2024.server.repository.JudgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JudgeService {

    private final JudgeRepository judgeRepository;

    public JudgeService(JudgeRepository judgeRepository) {
        this.judgeRepository = judgeRepository;
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
        reportPojo.setScore(dto.score());
        judgeRepository.update(reportPojo);

        judgeRepository.deleteReportCommentsByReportId(reportId);
        judgeRepository.deleteReportRatingsByReportId(reportId);

        for (var reportTitle : dto.titles()) {
            judgeRepository.insert(
                    new JudgeReportCommentPojo(reportId, reportTitle.titel().id(), reportTitle.comment())
            );

            for (var rating : reportTitle.ratings()) {
                judgeRepository.insert(
                        new JudgeReportRatingPojo(
                                reportId,
                                reportTitle.titel().id(),
                                rating.category().name(),
                                rating.rating().name(),
                                rating.comment()
                        )
                );
            }
        }
    }
}
