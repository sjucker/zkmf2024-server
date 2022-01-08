package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/survey")
public class SurveyEndpoint {

    private final SurveyService surveyService;

    public SurveyEndpoint(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<?> postSurveyAnswer(@RequestBody SurveyAnswerDTO request) {
        log.info("POST /public/survey: {}", request);

        var result = surveyService.register(request);
        log.info("result for request {}: {}", request, result);

        return switch (result) {
            case REGISTERED -> ResponseEntity.ok().build();
            case INVALID_EMAIL -> ResponseEntity.badRequest().build();
        };
    }
}
