package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.repository.JudgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
