package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.JudgeReportModulCategory;
import ch.zkmf2024.server.dto.RankingListDTO;
import ch.zkmf2024.server.dto.admin.RankingSummaryDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingEntryPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingPojo;
import ch.zkmf2024.server.repository.RankingRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import ch.zkmf2024.server.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import static ch.zkmf2024.server.dto.RankingStatus.FINAL;
import static ch.zkmf2024.server.dto.RankingStatus.PENDING;

@Slf4j
@Service
public class RankingsService {

    private final RankingRepository rankingRepository;
    private final VereinRepository vereinRepository;
    private final TimetableRepository timetableRepository;

    public RankingsService(RankingRepository rankingRepository,
                           VereinRepository vereinRepository,
                           TimetableRepository timetableRepository) {
        this.rankingRepository = rankingRepository;
        this.vereinRepository = vereinRepository;
        this.timetableRepository = timetableRepository;
    }

    public List<RankingSummaryDTO> getAllRankings() {
        return rankingRepository.getAllRankingsPerVerein();
    }

    public boolean hasFinalRankings() {
        return rankingRepository.hasFinalRankings();
    }

    public List<RankingListDTO> getAllRankingLists() {
        return rankingRepository.getAllRankingLists();
    }

    public List<RankingListDTO> getAllRankingLists(Predicate<RankingListDTO> predicate) {
        return rankingRepository.getAllRankingLists(predicate);
    }

    public Optional<RankingListDTO> findRankingListById(Long rankingId) {
        return rankingRepository.findRankingListById(rankingId);
    }

    public void confirmScore(String username, Long vereinProgrammId, JudgeReportModulCategory category, BigDecimal score) {
        var vereinProgramm = vereinRepository.findVereinProgramm(vereinProgrammId).orElseThrow();
        var timetableEntry = timetableRepository.findWettspielByProgrammId(vereinProgrammId).orElseThrow();

        var ranking = rankingRepository.find(vereinProgramm.getModul(), vereinProgramm.getKlasse(), vereinProgramm.getBesetzung(), category, timetableEntry.getFkLocation()).orElse(null);
        if (ranking == null) {
            ranking = new RankingPojo(null, vereinProgramm.getModul(), vereinProgramm.getKlasse(), vereinProgramm.getBesetzung(),
                                      category != null ? category.name() : null, PENDING.name(), timetableEntry.getFkLocation());
            rankingRepository.insert(ranking);
        }

        rankingRepository.insert(new RankingEntryPojo(ranking.getId(), vereinProgramm.getFkVerein(), score, 0, username, DateUtil.now()));

        int rank = 0;
        int sameRank = 0;
        BigDecimal lastScore = null;
        for (var rankingEntry : rankingRepository.findAllByRankingId(ranking.getId()).stream()
                                                 .sorted(Comparator.comparing(RankingEntryPojo::getScore).reversed())
                                                 .toList()) {
            if (lastScore != null && lastScore.compareTo(rankingEntry.getScore()) == 0) {
                rankingEntry.setRank(rank);
                sameRank++;
            } else {
                rank += 1 + sameRank;
                rankingEntry.setRank(rank);
                sameRank = 0;
            }
            rankingRepository.update(rankingEntry);

            lastScore = rankingEntry.getScore();
        }
    }

    public void publishRankingList(Long rankingId) {
        var rankingPojo = rankingRepository.findById(rankingId).orElseThrow(() -> new NoSuchElementException("no ranking found for id: " + rankingId));
        rankingPojo.setStatus(FINAL.name());
        rankingRepository.update(rankingPojo);
    }
}
