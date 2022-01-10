package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin") // TODO move to /secured
public class AdminEndpoint {

    private final SurveyService surveyService;

    public AdminEndpoint(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping(path = "/umfrage")
    public ResponseEntity<List<SurveyAnswerDTO>> getUmfrage() {
        log.info("GET /admin/umfrage");
        return ResponseEntity.ok(surveyService.getAll());
    }

    @DeleteMapping(path = "/umfrage/{id}")
    public ResponseEntity<?> deleteUmfrage(@PathVariable Long id) {
        log.info("DELETE /admin/umfrage/{}", id);

        surveyService.delete(id);
        return ResponseEntity.ok().build();
    }

}
