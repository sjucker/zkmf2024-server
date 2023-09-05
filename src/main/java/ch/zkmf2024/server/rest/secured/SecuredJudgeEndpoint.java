package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportOverviewDTO;
import ch.zkmf2024.server.dto.admin.JuryLoginCreateDTO;
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
    public ResponseEntity<List<JudgeReportOverviewDTO>> get(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/judge");
        return ResponseEntity.ok(judgeService.getReports(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JudgeReportDTO> get(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable Long id) {

        log.info("GET /secured/judge/" + id);

        return judgeService.getReport(userDetails.getUsername(), id)
                           .map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JudgeReportDTO> update(@AuthenticationPrincipal UserDetails userDetails,
                                                 @PathVariable Long id,
                                                 @RequestBody JudgeReportDTO dto) {
        log.info("PUT /secured/judge/{} {}", id, dto);

        judgeService.update(userDetails.getUsername(), id, dto);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<?> finish(@AuthenticationPrincipal UserDetails userDetails,
                                    @PathVariable Long id) {
        log.info("POST /secured/judge/{}/finish", id);

        judgeService.finish(userDetails.getUsername(), id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Secured({"ADMIN"})
    public ResponseEntity<?> create(@RequestBody JuryLoginCreateDTO dto) {

        log.info("POST /secured/judge/login {} {}", dto.name(), dto.email());

        try {
            judgeService.createLogin(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login konnte nicht erstellt werden.");
        }

    }

}
