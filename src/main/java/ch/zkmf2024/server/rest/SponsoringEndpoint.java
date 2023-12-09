package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.SponsoringDTO;
import ch.zkmf2024.server.service.SponsoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/sponsoring")
public class SponsoringEndpoint {

    private final SponsoringService sponsoringService;

    public SponsoringEndpoint(SponsoringService sponsoringService) {
        this.sponsoringService = sponsoringService;
    }

    @GetMapping
    public ResponseEntity<SponsoringDTO> get() {
        log.info("GET /public/sponsoring");

        return ResponseEntity.ok(sponsoringService.getSponsoring());
    }
}
