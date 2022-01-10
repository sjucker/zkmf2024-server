package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.HelperRegistrationDTO;
import ch.zkmf2024.server.dto.NewsletterRecipientDTO;
import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.service.HelperRegistrationService;
import ch.zkmf2024.server.service.NewsletterService;
import ch.zkmf2024.server.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin") // TODO move to /secured
public class AdminEndpoint {

    private final SurveyService surveyService;
    private final NewsletterService newsletterService;
    private final HelperRegistrationService helperRegistrationService;

    public AdminEndpoint(SurveyService surveyService,
                         NewsletterService newsletterService,
                         HelperRegistrationService helperRegistrationService) {
        this.surveyService = surveyService;
        this.newsletterService = newsletterService;
        this.helperRegistrationService = helperRegistrationService;
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

    @GetMapping(path = "/newsletter")
    public ResponseEntity<List<NewsletterRecipientDTO>> getNewsletter() {
        log.info("GET /admin/newsletter");
        return ResponseEntity.ok(newsletterService.getAll());
    }

    @DeleteMapping(path = "/newsletter/{email}")
    public ResponseEntity<?> deleteNewsletter(@PathVariable String email) {
        log.info("DELETE /admin/newsletter/{}", email);

        newsletterService.delete(email);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(path = "/newsletter/unsubscribe/{email}")
    public ResponseEntity<?> unsubscribeNewsletter(@PathVariable String email) {
        log.info("POST /admin/newsletter/unsubscribe/{}", email);

        newsletterService.unsubscribe(email);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/helfer")
    public ResponseEntity<List<HelperRegistrationDTO>> getHelperRegistration() {
        log.info("GET /admin/helfer");
        return ResponseEntity.ok(helperRegistrationService.getAll());
    }

}
