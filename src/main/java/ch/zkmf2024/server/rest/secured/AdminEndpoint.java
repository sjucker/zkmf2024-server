package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.HelperRegistrationDTO;
import ch.zkmf2024.server.dto.NewsletterRecipientDTO;
import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.service.HelperRegistrationService;
import ch.zkmf2024.server.service.NewsletterService;
import ch.zkmf2024.server.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

@Slf4j
@RestController
@RequestMapping("/secured/admin")
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
        log.info("GET /secured/admin/umfrage");
        return ResponseEntity.ok(surveyService.getAll());
    }

    @GetMapping(path = "/download/umfrage")
    public ResponseEntity<Resource> exportUmfrage() {
        log.info("GET /secured/admin/download/umfrage");
        File file = null;
        try {
            file = surveyService.export();

            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity.ok()
                                 .header(CONTENT_TYPE, Files.probeContentType(path))
                                 .contentLength(file.length())
                                 .contentType(APPLICATION_OCTET_STREAM)
                                 .body(resource);

        } catch (RuntimeException | IOException e) {
            log.error("Umfrage export failed", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/umfrage/{id}")
    public ResponseEntity<?> deleteUmfrage(@PathVariable Long id) {
        log.info("DELETE /secured/admin/umfrage/{}", id);

        surveyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/newsletter")
    public ResponseEntity<List<NewsletterRecipientDTO>> getNewsletter() {
        log.info("GET /secured/admin/newsletter");
        return ResponseEntity.ok(newsletterService.getAll());
    }

    @DeleteMapping(path = "/newsletter/{email}")
    public ResponseEntity<?> deleteNewsletter(@PathVariable String email) {
        log.info("DELETE /secured/admin/newsletter/{}", email);

        newsletterService.delete(email);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(path = "/newsletter/unsubscribe/{email}")
    public ResponseEntity<?> unsubscribeNewsletter(@PathVariable String email) {
        log.info("POST /secured/admin/newsletter/unsubscribe/{}", email);

        newsletterService.unsubscribe(email);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/helfer")
    public ResponseEntity<List<HelperRegistrationDTO>> getHelperRegistration() {
        log.info("GET /secured/admin/helfer");
        return ResponseEntity.ok(helperRegistrationService.getAll());
    }

}
