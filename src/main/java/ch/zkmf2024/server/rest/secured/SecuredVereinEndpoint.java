package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.service.VereinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
