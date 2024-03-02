package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.JudgePresentationDTO;
import ch.zkmf2024.server.service.JudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/judge")
public class JudgeEndpoint {

    private final JudgeService judgeService;

    public JudgeEndpoint(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @GetMapping
    public ResponseEntity<List<JudgePresentationDTO>> getAll() {
        log.info("GET /secured/judge");
        return ResponseEntity.ok(judgeService.getJudgePresentations());
    }

}
