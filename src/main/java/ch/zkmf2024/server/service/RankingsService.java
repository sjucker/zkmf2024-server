package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.admin.RankingSummaryDTO;
import ch.zkmf2024.server.repository.RankingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RankingsService {

    private final RankingsRepository rankingsRepository;

    public RankingsService(RankingsRepository rankingsRepository) {
        this.rankingsRepository = rankingsRepository;
    }

    public List<RankingSummaryDTO> getAllRankings() {
        // TODO fill this
        return List.of();
    }
}
