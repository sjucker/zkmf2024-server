package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.BandDTO;
import ch.zkmf2024.server.service.BandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/secured/band")
public class BandEndpoint {

    private final BandService bandService;

    public BandEndpoint(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping
    public ResponseEntity<BandDTO> authorize(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /secured/band {}", userDetails);

        Optional<BandDTO> band = bandService.find(userDetails.getUsername());

        return band.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
