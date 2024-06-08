package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.RankingListDTO;
import ch.zkmf2024.server.service.RankingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public/ranking")
public class RankingEndpoint {

    private final RankingsService rankingsService;

    public RankingEndpoint(RankingsService rankingsService) {
        this.rankingsService = rankingsService;
    }

    @GetMapping
    public ResponseEntity<List<RankingListDTO>> rankings() {
        log.info("GET /public/ranking");
        return ResponseEntity.ok(rankingsService.getAllRankingLists(RankingListDTO::isNotPending));

    }

    @GetMapping("/{rankingId}")
    public ResponseEntity<RankingListDTO> ranking(@PathVariable("rankingId") Long rankingId) {
        log.info("GET /public/ranking/{}", rankingId);

        return ResponseEntity.of(rankingsService.findRankingListById(rankingId));

    }

    @GetMapping("/available")
    public ResponseEntity<?> hasRankings() {
        log.info("GET /public/ranking/available");
        return rankingsService.hasPublishedRankings() ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }

    @GetMapping("/verein/{vereinIdentifier}")
    public ResponseEntity<List<RankingListDTO>> rankingPerVerein(@PathVariable("vereinIdentifier") String vereinIdentifier) {
        log.info("GET /public/ranking/verein/{}", vereinIdentifier);
        return ResponseEntity.ok(rankingsService.findRankingsByVerein(vereinIdentifier));
    }

}
