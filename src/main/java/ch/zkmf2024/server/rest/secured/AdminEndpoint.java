package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.NewsletterRecipientDTO;
import ch.zkmf2024.server.dto.TimetableEntryType;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VereinMessageDTO;
import ch.zkmf2024.server.dto.VereinSelectionDTO;
import ch.zkmf2024.server.dto.admin.BroadcastCreateDTO;
import ch.zkmf2024.server.dto.admin.ErrataDTO;
import ch.zkmf2024.server.dto.admin.JudgeDTO;
import ch.zkmf2024.server.dto.admin.JuryLoginCreateDTO;
import ch.zkmf2024.server.dto.admin.LocationSelectionDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryCreateDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.dto.admin.UserCreateDTO;
import ch.zkmf2024.server.dto.admin.UserDTO;
import ch.zkmf2024.server.dto.admin.VereinCommentCreateDTO;
import ch.zkmf2024.server.dto.admin.VereinCommentDTO;
import ch.zkmf2024.server.dto.admin.VereinMessageCreateDTO;
import ch.zkmf2024.server.dto.admin.VereinOverviewDTO;
import ch.zkmf2024.server.service.ErrataService;
import ch.zkmf2024.server.service.ExportService;
import ch.zkmf2024.server.service.HelperRegistrationService;
import ch.zkmf2024.server.service.JudgeService;
import ch.zkmf2024.server.service.NewsletterService;
import ch.zkmf2024.server.service.TimetableService;
import ch.zkmf2024.server.service.VereinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final ExportService exportService;
    private final JudgeService judgeService;
    private final TimetableService timetableService;
    private final ErrataService errataService;

    public AdminEndpoint(NewsletterService newsletterService,
                         HelperRegistrationService helperRegistrationService,
                         VereinService vereinService,
                         ExportService exportService,
                         JudgeService judgeService,
                         TimetableService timetableService,
                         ErrataService errataService) {
        this.newsletterService = newsletterService;
        this.helperRegistrationService = helperRegistrationService;
        this.vereinService = vereinService;
        this.exportService = exportService;
        this.judgeService = judgeService;
        this.timetableService = timetableService;
        this.errataService = errataService;
    }

    @GetMapping(path = "/download/helfer")
    @Secured({"ADMIN"})
    public ResponseEntity<Resource> exportHelfer() throws IOException {
        log.info("GET /secured/admin/download/helfer");
        return export(helperRegistrationService.export());
    }

    @GetMapping(path = "/download/helfer-import")
    @Secured({"ADMIN"})
    public ResponseEntity<Resource> exportHelferImport() throws IOException {
        log.info("GET /secured/admin/download/helfer-import");
        return export(helperRegistrationService.exportForImport());
    }

    @GetMapping(path = "/download/vereine")
    @Secured({"ADMIN"})
    public ResponseEntity<Resource> exportVereine() throws IOException {
        log.info("GET /secured/admin/download/vereine");
        return export(exportService.exportVereine());
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
    @Secured({"ADMIN"})
    public ResponseEntity<List<NewsletterRecipientDTO>> getNewsletter() {
        log.info("GET /secured/admin/newsletter");
        return ResponseEntity.ok(newsletterService.getAll());
    }

    @DeleteMapping(path = "/newsletter/{email}")
    @Secured({"ADMIN"})
    public ResponseEntity<?> deleteNewsletter(@PathVariable String email) {
        log.info("DELETE /secured/admin/newsletter/{}", email);

        newsletterService.delete(email);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(path = "/newsletter/unsubscribe/{email}")
    @Secured({"ADMIN"})
    public ResponseEntity<?> unsubscribeNewsletter(@PathVariable String email) {
        log.info("POST /secured/admin/newsletter/unsubscribe/{}", email);

        newsletterService.unsubscribe(email);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/vereine")
    @Secured({"ADMIN"})
    public ResponseEntity<List<VereinOverviewDTO>> vereine() {
        log.info("GET /secured/admin/vereine");

        return ResponseEntity.ok(vereinService.findAll());
    }

    @GetMapping(path = "/vereine-selection")
    @Secured({"ADMIN"})
    public ResponseEntity<List<VereinSelectionDTO>> vereineSelection() {
        log.info("GET /secured/admin/vereine-selection");

        return ResponseEntity.ok(timetableService.findVereine());
    }

    @GetMapping(path = "/vereine/{id}/programme")
    @Secured({"ADMIN"})
    public ResponseEntity<List<TimetableEntryCreateDTO>> vereinProgrammeSelection(@PathVariable Long id) {
        log.info("GET /secured/admin/vereine/{}/programme", id);

        return ResponseEntity.ok(timetableService.findProgrammeByVerein(id));
    }

    @GetMapping(path = "/vereine/{id}")
    @Secured({"ADMIN"})
    public ResponseEntity<VereinDTO> vereinById(@PathVariable Long id) {
        log.info("GET /secured/admin/vereine/{}", id);

        return ResponseEntity.ok(vereinService.findById(id).orElseThrow());
    }

    @GetMapping(path = "/vereine/{id}/comments")
    @Secured({"ADMIN"})
    public ResponseEntity<List<VereinCommentDTO>> vereinComments(@PathVariable Long id) {
        log.info("GET /secured/admin/vereine/{}/comments", id);

        return ResponseEntity.ok(vereinService.findCommentsByVereinId(id));
    }

    @GetMapping(path = "/vereine/{id}/messages")
    @Secured({"ADMIN"})
    public ResponseEntity<List<VereinMessageDTO>> vereinMessages(@AuthenticationPrincipal UserDetails userDetails,
                                                                 @PathVariable Long id) {
        log.info("GET /secured/admin/vereine/{}/messages", id);

        return ResponseEntity.ok(vereinService.findMessagesByVereinId(id, userDetails.getUsername()));
    }

    @PostMapping(path = "/vereine/{id}/comments")
    @Secured({"ADMIN"})
    public ResponseEntity<VereinCommentDTO> postVereinComment(@AuthenticationPrincipal UserDetails userDetails,
                                                              @PathVariable Long id,
                                                              @RequestBody VereinCommentCreateDTO dto) {
        log.info("POST /secured/admin/vereine/{}/comments {}", id, dto);

        return ResponseEntity.ok(vereinService.saveComment(userDetails.getUsername(), id, dto.comment()));
    }

    @PostMapping(path = "/vereine/{id}/messages")
    @Secured({"ADMIN"})
    public ResponseEntity<VereinMessageDTO> postVereinMessage(@AuthenticationPrincipal UserDetails userDetails,
                                                              @PathVariable Long id,
                                                              @RequestBody VereinMessageCreateDTO dto) {
        log.info("POST /secured/admin/vereine/{}/messages {}", id, dto);

        return ResponseEntity.ok(vereinService.saveMessage(userDetails.getUsername(), id, dto.message()));
    }

    @PostMapping(path = "/vereine/broadcast")
    @Secured({"ADMIN"})
    public ResponseEntity<List<VereinMessageDTO>> broadcast(@AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody BroadcastCreateDTO dto) {
        log.info("POST /secured/admin/vereine/broadcast {}", dto);

        return ResponseEntity.ok(vereinService.broadcast(userDetails.getUsername(), dto.ids(), dto.message()));
    }

    @PostMapping(path = "/vereine/{id}/confirm-programm")
    @Secured({"ADMIN"})
    public ResponseEntity<VereinDTO> confirmProgramm(@AuthenticationPrincipal UserDetails userDetails,
                                                     @PathVariable Long id) {
        log.info("POST /secured/admin/vereine/{}/confirm-programm", id);

        try {
            vereinService.confirmProgramm(userDetails.getUsername(), id);
            return ResponseEntity.ok(vereinService.findById(id).orElseThrow());
        } catch (RuntimeException e) {
            log.error("unexpected error during confirming of programm", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/user")
    @Secured({"ADMIN"})
    public ResponseEntity<UserDTO> createUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserCreateDTO dto) {
        log.info("POST /user {}", dto);

        // TODO
        return ResponseEntity.ok(null);
    }

    @PostMapping("/jury/login")
    @Secured({"ADMIN"})
    public ResponseEntity<?> create(@RequestBody JuryLoginCreateDTO dto) {

        log.info("POST /secured/admin/jury/login {} {}", dto.name(), dto.email());

        try {
            judgeService.createLogin(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("unexpected error occurred", e);
            return ResponseEntity.badRequest().body("Login konnte nicht erstellt werden.");
        }
    }

    @GetMapping("/jury")
    @Secured({"ADMIN"})
    public ResponseEntity<List<JudgeDTO>> getJury() {
        log.info("GET /secured/admin/jury");

        return ResponseEntity.ok(judgeService.findAll());
    }

    @GetMapping("/timetable")
    @Secured({"ADMIN"})
    public ResponseEntity<List<TimetableEntryDTO>> getTimetable() {
        log.info("GET /secured/admin/timetable");

        return ResponseEntity.ok(timetableService.findAll());
    }

    @PostMapping("/timetable/{id}")
    @Secured({"ADMIN"})
    public ResponseEntity<?> createTimetableEntry(@PathVariable Long id, @RequestBody List<TimetableEntryCreateDTO> dtos) {
        log.info("POST /secured/admin/timetable/{} {}", id, dtos);
        try {
            timetableService.create(id, dtos);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("unexpected error occurred", e);
            return ResponseEntity.badRequest().body("Einträge konnten nicht erstellt werden.");
        }
    }

    @PatchMapping("/timetable/entry/{id}")
    @Secured({"ADMIN"})
    public ResponseEntity<?> updateTimetableEntry(@PathVariable Long id, @RequestBody TimetableEntryDTO dto) {
        log.info("PATCH /secured/admin/timetable/entry/{} {}", id, dto);
        timetableService.updateEntry(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/timetable/entry/{id}")
    @Secured({"ADMIN"})
    public ResponseEntity<?> deleteTimetableEntry(@PathVariable Long id) {
        log.info("DELETE /secured/admin/timetable/entry/{}", id);
        timetableService.deleteEntry(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/location/{type}")
    @Secured({"ADMIN"})
    public ResponseEntity<List<LocationSelectionDTO>> getLocationsByType(@PathVariable TimetableEntryType type) {
        log.info("GET /secured/admin/location/{}", type);
        return ResponseEntity.ok(timetableService.findLocationsByType(type.toLocationType()));
    }

    @GetMapping("/errata")
    @Secured({"ADMIN"})
    public ResponseEntity<List<ErrataDTO>> getErrata() {
        log.info("GET /secured/admin/errata");
        return ResponseEntity.ok(errataService.findAll());
    }

    @PostMapping("/errata")
    @Secured({"ADMIN"})
    public ResponseEntity<Void> updateErrata(@RequestBody List<ErrataDTO> dtos) {
        log.info("POST /secured/admin/errata {}", dtos);
        errataService.update(dtos);
        return ResponseEntity.ok().build();
    }

}
