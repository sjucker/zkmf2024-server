package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.JudgeRankingEntryDTO;
import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.JudgeReportSummaryDTO;
import ch.zkmf2024.server.dto.JudgeReportViewDTO;
import ch.zkmf2024.server.dto.ModulDSelectionDTO;
import ch.zkmf2024.server.dto.RankingPenaltyDTO;
import ch.zkmf2024.server.dto.VereinPlayingDTO;
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

import static ch.zkmf2024.server.util.DateUtil.now;

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

        log.info("GET /secured/judge/{}", id);

        return judgeService.getReport(userDetails.getUsername(), id)
                           .map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/view/{id}")
    @Secured({"JUDGE", "ADMIN", "IMPERSONATE"})
    public ResponseEntity<JudgeReportViewDTO> get(@PathVariable Long id) {
        log.info("GET /secured/judge/view/{}", id);

        return ResponseEntity.of(judgeService.getReport(id));
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
    public ResponseEntity<List<JudgeReportSummaryDTO>> summaries(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/judge/summary, {}", userDetails.getUsername());
        return ResponseEntity.ok(judgeService.findSummaries(userDetails.getUsername()));
    }

    @PostMapping("/confirm-scores/{programmId}")
    @Secured({"ADMIN"})
    public ResponseEntity<?> confirmScores(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable("programmId") Long programmId) {
        log.info("POST /secured/judge/confirm-scores/{} {}", programmId, userDetails.getUsername());

        judgeService.confirmScores(userDetails.getUsername(), programmId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/ranking/{reportId}")
    @Secured({"JUDGE", "IMPERSONATE"})
    public ResponseEntity<List<JudgeRankingEntryDTO>> ranking(@PathVariable("reportId") Long reportId) {
        log.info("GET /secured/judge/ranking/{}", reportId);

        return ResponseEntity.ok(judgeService.getRanking(reportId));
    }

    @GetMapping("/ranking/own/{reportId}")
    @Secured({"JUDGE", "IMPERSONATE"})
    public ResponseEntity<List<JudgeRankingEntryDTO>> rankingOwn(@AuthenticationPrincipal UserDetails userDetails,
                                                                 @PathVariable("reportId") Long reportId) {
        log.info("GET /secured/judge/ranking/own/{} {}", reportId, userDetails.getUsername());

        return ResponseEntity.ok(judgeService.getRankingOwnOnly(reportId, userDetails.getUsername()));
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

    @GetMapping("/helper")
    @Secured({"JUDGE_HELPER"})
    public ResponseEntity<List<VereinPlayingDTO>> helper(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/judge/helper");

        // assumption: user with role JUDGE_HELPER has username that corresponds to location's identifier
        return ResponseEntity.ok(judgeService.getVereinPlayingEntries(userDetails.getUsername()));
    }

    @PostMapping("/helper/started/{timetableEntryId}")
    @Secured({"JUDGE_HELPER"})
    public ResponseEntity<?> started(@AuthenticationPrincipal UserDetails userDetails,
                                     @PathVariable("timetableEntryId") Long timetableEntryId) {
        log.info("POST /secured/judge/helper/started/{}", timetableEntryId);

        judgeService.setCurrentlyPlaying(userDetails.getUsername(), timetableEntryId, pojo -> pojo.setStartedAt(now()));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/helper/ended/{timetableEntryId}")
    @Secured({"JUDGE_HELPER"})
    public ResponseEntity<?> ended(@AuthenticationPrincipal UserDetails userDetails,
                                   @PathVariable("timetableEntryId") Long timetableEntryId) {
        log.info("POST /secured/judge/helper/ended/{}", timetableEntryId);

        judgeService.setCurrentlyPlaying(userDetails.getUsername(), timetableEntryId, pojo -> pojo.setEndedAt(now()));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/helper/penalty")
    @Secured({"JUDGE_HELPER"})
    public ResponseEntity<?> rankingPenalty(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody RankingPenaltyDTO dto) {
        log.info("POST /secured/judge/helper/penalty {}", dto);

        judgeService.setRankingPenalty(userDetails.getUsername(), dto.timetableEntryId(), dto.minutesOverrun());

        return ResponseEntity.ok().build();
    }

}
