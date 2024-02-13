package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.JudgeRankingEntryDTO;
import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.JudgeReportSummaryDTO;
import ch.zkmf2024.server.dto.ModulDSelectionDTO;
import ch.zkmf2024.server.service.JudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/secured/judge")
public class SecuredJudgeEndpoint {

    private final JudgeService judgeService;

    public SecuredJudgeEndpoint(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @GetMapping
    @Secured({"JUDGE", "IMPERSONATE"})
    public ResponseEntity<List<JudgeReportOverviewDTO>> get(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/judge");
        return ResponseEntity.ok(judgeService.getReports(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    @Secured({"JUDGE", "IMPERSONATE"})
    public ResponseEntity<JudgeReportDTO> get(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable Long id) {

        log.info("GET /secured/judge/" + id);

        return judgeService.getReport(userDetails.getUsername(), id)
                           .map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Secured({"JUDGE"})
    public ResponseEntity<JudgeReportDTO> update(@AuthenticationPrincipal UserDetails userDetails,
                                                 @PathVariable Long id,
                                                 @RequestBody JudgeReportDTO dto) {
        log.info("PUT /secured/judge/{} {}", id, dto);

        judgeService.update(userDetails.getUsername(), id, dto);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/fix-rating")
    @Secured({"JUDGE"})
    public ResponseEntity<?> fixRating(@AuthenticationPrincipal UserDetails userDetails,
                                       @PathVariable Long id) {
        log.info("POST /secured/judge/{}/fix-rating", id);

        judgeService.fixRating(userDetails.getUsername(), id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/finish")
    @Secured({"JUDGE"})
    public ResponseEntity<?> finish(@AuthenticationPrincipal UserDetails userDetails,
                                    @PathVariable Long id) {
        log.info("POST /secured/judge/{}/finish", id);

        judgeService.finish(userDetails.getUsername(), id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/summary")
    @Secured({"JUDGE", "ADMIN", "IMPERSONATE"})
    public ResponseEntity<List<JudgeReportSummaryDTO>> summaries() {
        log.info("GET /secured/judge/summary");
        return ResponseEntity.ok(judgeService.findSummaries());
    }

    @PostMapping("/confirm-scores/{programmId}")
    @Secured({"ADMIN"})
    public ResponseEntity<?> confirmScores(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable("programmId") Long programmId) {
        log.info("GET /secured/judge/confirm-scores/{} {}", programmId, userDetails.getUsername());

        judgeService.confirmScores(userDetails.getUsername(), programmId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/ranking/{reportId}")
    @Secured({"JUDGE", "IMPERSONATE"})
    public ResponseEntity<List<JudgeRankingEntryDTO>> ranking(@PathVariable("reportId") Long reportId) {
        log.info("GET /secured/judge/ranking/{}", reportId);

        return ResponseEntity.ok(judgeService.getRanking(reportId));
    }

    @GetMapping("/modul-d")
    @Secured({"JUDGE", "IMPERSONATE"})
    public ResponseEntity<List<ModulDSelectionDTO>> modulD() {
        log.info("GET /secured/judge/modul-d");

        return ResponseEntity.ok(judgeService.getModulDSelection());
    }

    @PostMapping("/modul-d")
    @Secured({"JUDGE"})
    public ResponseEntity<?> updateModulD(@RequestBody List<ModulDSelectionDTO> dtos) {
        log.info("POST /secured/judge/modul-d {}", dtos);

        judgeService.updateModulD(dtos);

        return ResponseEntity.ok().build();
    }

}
