package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.RegisterVereinRequestDTO;
import ch.zkmf2024.server.dto.VerifyEmailRequestDTO;
import ch.zkmf2024.server.service.VereinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/verein")
public class VereinEndpoint {

    private final VereinService vereinService;

    public VereinEndpoint(VereinService vereinService) {
        this.vereinService = vereinService;
    }

    @PostMapping
    public ResponseEntity<?> registerBand(@RequestBody RegisterVereinRequestDTO request) {
        log.info("POST /public/verein: {}, {}", request.vereinsname(), request.email());

        try {
            vereinService.create(request);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/verification")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequestDTO request) {
        log.info("POST /public/verein/verification: {}", request);

        return vereinService.verifyEmail(request) ?
                ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }
}
