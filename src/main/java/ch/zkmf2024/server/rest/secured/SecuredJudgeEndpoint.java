package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.ConfirmScoreDTO;
import ch.zkmf2024.server.dto.JudgeRankingEntryDTO;
import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportFeedbackDTO;
import ch.zkmf2024.server.dto.JudgeReportModulCategory;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.JudgeReportSummaryDTO;
import ch.zkmf2024.server.dto.JudgeReportViewDTO;
import ch.zkmf2024.server.dto.ModulDSelectionDTO;
import ch.zkmf2024.server.dto.PublicRankingDTO;
import ch.zkmf2024.server.dto.RankingBonusDTO;
import ch.zkmf2024.server.dto.RankingListDTO;
import ch.zkmf2024.server.dto.RankingPenaltyDTO;
import ch.zkmf2024.server.dto.VereinPlayingDTO;
import ch.zkmf2024.server.service.JudgeService;
import ch.zkmf2024.server.service.RankingsService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ch.zkmf2024.server.util.DateUtil.now;

@Slf4j
@RestController
@RequestMapping("/secured/judge")
public class SecuredJudgeEndpoint {

    private final JudgeService judgeService;
    private final RankingsService rankingsService;

    public SecuredJudgeEndpoint(JudgeService judgeService,
                                RankingsService rankingsService) {
        this.judgeService = judgeService;
        this.rankingsService = rankingsService;
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
    public ResponseEntity<JudgeReportViewDTO> view(@PathVariable Long id) {
        log.info("GET /secured/judge/view/{}", id);

        return ResponseEntity.of(judgeService.getReport(id));
    }

    @GetMapping("/feedback/{programmId}")
    @Secured({"JUDGE", "ADMIN", "IMPERSONATE"})
    public ResponseEntity<JudgeReportFeedbackDTO> feedback(@PathVariable Long programmId,
                                                           @RequestParam(required = false) String category) {
        log.info("GET /secured/judge/feedback/{}", programmId);

        return ResponseEntity.of(judgeService.getFeedback(programmId,
                                                          JudgeReportModulCategory.fromString(category).orElse(null)));
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

    @PostMapping("/confirm-score")
    @Secured({"ADMIN"})
    public ResponseEntity<?> confirmScore(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestBody ConfirmScoreDTO dto) {
        log.info("POST /secured/judge/confirm-score {}", dto);

        rankingsService.confirmScore(userDetails.getUsername(), dto.vereinProgrammId(), dto.category(), dto.score());

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

        // username -> location-identifier
        judgeService.setCurrentlyPlaying(userDetails.getUsername(), timetableEntryId, pojo -> pojo.setStartedAt(now()));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/helper/ended/{timetableEntryId}")
    @Secured({"JUDGE_HELPER"})
    public ResponseEntity<?> ended(@AuthenticationPrincipal UserDetails userDetails,
                                   @PathVariable("timetableEntryId") Long timetableEntryId) {
        log.info("POST /secured/judge/helper/ended/{}", timetableEntryId);

        // username -> location-identifier
        judgeService.setCurrentlyPlaying(userDetails.getUsername(), timetableEntryId, pojo -> pojo.setEndedAt(now()));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/helper/penalty")
    @Secured({"JUDGE_HELPER"})
    public ResponseEntity<?> rankingPenalty(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody RankingPenaltyDTO dto) {
        log.info("POST /secured/judge/helper/penalty {}", dto);

        // username -> location-identifier
        judgeService.setRankingPenalty(userDetails.getUsername(), dto.vereinProgrammId(), dto.actualDurationInSeconds(), dto.minutesOverrun());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/helper/bonus")
    @Secured({"JUDGE_HELPER"})
    public ResponseEntity<?> rankingBonus(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestBody RankingBonusDTO dto) {
        log.info("POST /secured/judge/helper/bonus {}", dto);

        // username -> location-identifier
        judgeService.setRankingBonus(userDetails.getUsername(), dto.vereinProgrammId(), dto.bonus(), dto.category());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/ranking-list")
    @Secured({"ADMIN"})
    public ResponseEntity<List<RankingListDTO>> rankingLists() {
        log.info("GET /secured/judge/ranking-list");

        return ResponseEntity.ok(rankingsService.getAllRankingLists());
    }

    @PostMapping("/ranking-list/{rankingId}")
    @Secured({"ADMIN"})
    public ResponseEntity<?> publishRankingList(@PathVariable("rankingId") Long rankingId,
                                                @RequestBody PublicRankingDTO dto) {
        log.info("POST /secured/judge/ranking-list/{} {}", rankingId, dto);

        rankingsService.publishRankingList(rankingId, dto.intermediate());

        return ResponseEntity.ok().build();
    }

}
