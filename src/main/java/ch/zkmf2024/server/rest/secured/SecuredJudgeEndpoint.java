package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.service.JudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<JudgeReportOverviewDTO>> get(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/judge");
        return ResponseEntity.ok(judgeService.getReports(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JudgeReportDTO> get(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable Long id) {
        // TODO check that judge is allowed to open report
        // TODO actually load the report
        log.info("GET /secured/judge/" + id);
        return ResponseEntity.ok(new JudgeReportDTO());
    }

}
