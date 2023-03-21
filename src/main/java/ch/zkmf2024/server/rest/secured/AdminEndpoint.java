package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.NewsletterRecipientDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.service.HelperRegistrationService;
import ch.zkmf2024.server.service.NewsletterService;
import ch.zkmf2024.server.service.VereinService;
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
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

@Slf4j
@RestController
@RequestMapping("/secured/admin")
public class AdminEndpoint {

    private final NewsletterService newsletterService;
    private final HelperRegistrationService helperRegistrationService;
    private final VereinService vereinService;

    public AdminEndpoint(NewsletterService newsletterService,
                         HelperRegistrationService helperRegistrationService,
                         VereinService vereinService) {
        this.newsletterService = newsletterService;
        this.helperRegistrationService = helperRegistrationService;
        this.vereinService = vereinService;
    }

    @GetMapping(path = "/download/helfer")
    public ResponseEntity<Resource> exportHelfer() throws IOException {
        log.info("GET /secured/admin/download/helfer");
        return export(helperRegistrationService.export());
    }

    @GetMapping(path = "/download/helfer-import")
    public ResponseEntity<Resource> exportHelferImport() throws IOException {
        log.info("GET /secured/admin/download/helfer-import");
        return export(helperRegistrationService.exportForImport());
    }

    private ResponseEntity<Resource> export(File file) {
        try {
            var path = Paths.get(file.getAbsolutePath());
            var resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity.ok()
                                 .header(CONTENT_TYPE, Files.probeContentType(path))
                                 .contentLength(file.length())
                                 .contentType(APPLICATION_OCTET_STREAM)
                                 .body(resource);

        } catch (RuntimeException | IOException e) {
            log.error("Helfer export failed", e);
            return ResponseEntity.internalServerError().build();
        }
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

    @GetMapping(path = "/vereine")
    public ResponseEntity<List<VereinDTO>> vereine() {
        log.info("GET /secured/admin/vereine");
        
        return ResponseEntity.ok(vereinService.findAll());
    }

}
