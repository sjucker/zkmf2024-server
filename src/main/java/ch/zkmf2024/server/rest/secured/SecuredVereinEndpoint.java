package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.service.VereinService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/secured/verein")
public class SecuredVereinEndpoint {

    private final VereinService vereinService;

    public SecuredVereinEndpoint(VereinService vereinService) {
        this.vereinService = vereinService;
    }

    @GetMapping
    public ResponseEntity<VereinDTO> authorize(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/verein {}", userDetails);

        return vereinService.find(userDetails.getUsername()).map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<VereinDTO> update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody VereinDTO dto) {
        log.info("PUT /secured/verein {} {}", dto, userDetails);

        return ResponseEntity.ok(vereinService.update(userDetails.getUsername(), dto));
    }

    @PostMapping("/bilder-upload")
    public ResponseEntity<?> bildUpload(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestParam(required = false) MultipartFile logo,
                                        @RequestParam(required = false) MultipartFile bild) {
        log.info("POST /secured/verein/bilder-upload logo={}, bild={}, {}", getFileDescription(logo), getFileDescription(bild), userDetails);

        try {
            vereinService.updateBilder(userDetails.getUsername(), logo, bild);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private static Object getFileDescription(MultipartFile file) {
        return file != null ? "%s %s".formatted(file.getOriginalFilename(), FileUtils.byteCountToDisplaySize(file.getSize())) : "-";
    }

}
