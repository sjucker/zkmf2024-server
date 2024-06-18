package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.JudgeReportFeedbackDTO;
import ch.zkmf2024.server.dto.JudgeReportModulCategory;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VereinMessageDTO;
import ch.zkmf2024.server.dto.VereinStageSetupDTO;
import ch.zkmf2024.server.dto.admin.VereinMessageCreateDTO;
import ch.zkmf2024.server.service.JudgeService;
import ch.zkmf2024.server.service.VereinService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@Slf4j
@RestController
@RequestMapping("/secured/verein")
public class SecuredVereinEndpoint {

    private final VereinService vereinService;
    private final JudgeService judgeService;

    public SecuredVereinEndpoint(VereinService vereinService, JudgeService judgeService) {
        this.vereinService = vereinService;
        this.judgeService = judgeService;
    }

    @GetMapping
    @Secured({"VEREIN", "IMPERSONATE"})
    public ResponseEntity<VereinDTO> get(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/verein");

        return vereinService.find(userDetails.getUsername()).map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/stage")
    @Secured({"VEREIN", "IMPERSONATE"})
    public ResponseEntity<VereinStageSetupDTO> getStageSetup(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/verein/stage");

        return vereinService.findStageSetup(userDetails.getUsername())
                            .map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    @Secured({"VEREIN"})
    public ResponseEntity<VereinDTO> update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody VereinDTO dto) {
        log.info("PUT /secured/verein {}", dto);

        return ResponseEntity.ok(vereinService.update(userDetails.getUsername(), dto));
    }

    @PutMapping("/stage")
    @Secured({"VEREIN"})
    public ResponseEntity<Void> update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody VereinStageSetupDTO dto) {
        log.info("PUT /secured/verein/stage {}", dto);

        vereinService.updateStage(userDetails.getUsername(), dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/stage/additional")
    @Secured({"VEREIN"})
    public ResponseEntity<Void> additionalStageUpload(@AuthenticationPrincipal UserDetails userDetails,
                                                      @RequestParam MultipartFile file) {
        log.info("POST /secured/verein/stage/additional {}", getFileDescription(file));

        try {
            vereinService.updateStageSetupAdditional(userDetails.getUsername(), file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/stage/additional")
    @Secured({"VEREIN"})
    public ResponseEntity<Void> deleteAdditionalStage(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("DELETE /secured/verein/stage/additional");

        vereinService.deleteStageSetupAdditional(userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/stage/additional", produces = IMAGE_JPEG_VALUE)
    @Secured({"VEREIN", "IMPERSONATE"})
    public ResponseEntity<byte[]> getAdditionalStage(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/verein/stage/additional");

        return ResponseEntity.of(vereinService.getStageSetupAdditional(userDetails.getUsername()));
    }

    @PostMapping("/confirm")
    @Secured({"VEREIN"})
    public ResponseEntity<VereinDTO> confirmRegistration(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("POST /secured/verein/confirm");

        return ResponseEntity.ok(vereinService.confirmRegistration(userDetails.getUsername()));
    }

    @PostMapping("/bilder-upload")
    @Secured({"VEREIN"})
    public ResponseEntity<Void> bildUpload(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestParam(required = false) MultipartFile logo,
                                           @RequestParam(required = false) MultipartFile bild) {
        log.info("POST /secured/verein/bilder-upload logo={}, bild={}", getFileDescription(logo), getFileDescription(bild));

        try {
            vereinService.updateBilder(userDetails.getUsername(), logo, bild);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/bilder-upload/{id}")
    @Secured({"VEREIN"})
    public ResponseEntity<Void> deleteBild(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable Long id) {
        log.info("DELETE /secured/verein/bilder-upload/{}", id);

        vereinService.deleteImage(userDetails.getUsername(), id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/messages")
    @Secured({"VEREIN"})
    public ResponseEntity<VereinMessageDTO> postMessage(@AuthenticationPrincipal UserDetails userDetails,
                                                        @RequestBody VereinMessageCreateDTO dto) {
        log.info("POST /secured/verein/messages {}", dto);

        return ResponseEntity.ok(vereinService.saveMessage(userDetails.getUsername(), dto.message()));
    }

    @GetMapping("/feedback/{modul}")
    @Secured({"VEREIN", "IMPERSONATE"})
    public ResponseEntity<JudgeReportFeedbackDTO> feedback(@AuthenticationPrincipal UserDetails userDetails,
                                                           @PathVariable String modul,
                                                           @RequestParam(required = false) String category) {
        log.info("GET /secured/verein/feedback/{} {}", modul, category);

        return ResponseEntity.of(judgeService.getFeedback(userDetails.getUsername(),
                                                          Modul.valueOf(modul),
                                                          JudgeReportModulCategory.fromString(category).orElse(null)));
    }

    private static Object getFileDescription(MultipartFile file) {
        return file != null ? "%s %s".formatted(file.getOriginalFilename(), FileUtils.byteCountToDisplaySize(file.getSize())) : "-";
    }

}
