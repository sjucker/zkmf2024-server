package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.ForgotPasswordRequestDTO;
import ch.zkmf2024.server.dto.RegisterVereinRequestDTO;
import ch.zkmf2024.server.dto.ResetPasswordRequestDTO;
import ch.zkmf2024.server.dto.VereinMemberInfoDTO;
import ch.zkmf2024.server.dto.VereinPresentationDTO;
import ch.zkmf2024.server.dto.VereinTeilnahmeDTO;
import ch.zkmf2024.server.dto.VerifyEmailRequestDTO;
import ch.zkmf2024.server.service.VereinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestDTO request) {
        log.info("POST /public/verein/forgot-password {}", request.email());

        vereinService.forgotPassword(request.email());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ResetPasswordRequestDTO request) {
        log.info("POST /public/verein/reset-password {} {}", request.email(), request.token());

        var success = vereinService.resetPassword(request.email(), request.token(), request.newPassword());

        return success ?
                ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("/overview")
    public ResponseEntity<List<VereinTeilnahmeDTO>> overview() {
        log.info("GET /public/verein/overview");

        return ResponseEntity.ok(vereinService.getOverview());
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<VereinPresentationDTO> vereinById(@PathVariable String identifier) {
        log.info("GET /public/verein/{}", identifier);

        return ResponseEntity.of(vereinService.findPresentationByIdentifier(identifier));
    }

    @GetMapping("/member/{identifier}")
    public ResponseEntity<VereinMemberInfoDTO> vereinDetailById(@PathVariable String identifier) {
        log.info("GET /public/verein/member/{}", identifier);

        return ResponseEntity.ok(vereinService.getVereinMemberInfo(identifier));
    }
}
