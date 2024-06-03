package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.RankingListDTO;
import ch.zkmf2024.server.service.RankingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        return ResponseEntity.ok(rankingsService.getAllRankingLists(RankingListDTO::isFinal));

    }

    @GetMapping("/available")
    public ResponseEntity<?> hasRankings() {
        log.info("GET /public/ranking/available");
        return rankingsService.hasFinalRankings() ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }

}
