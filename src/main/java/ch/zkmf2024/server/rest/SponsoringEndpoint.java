package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.SponsorDTO;
import ch.zkmf2024.server.dto.SponsoringDTO;
import ch.zkmf2024.server.service.SponsoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/random")
    public ResponseEntity<SponsorDTO> getRandom() {
        log.info("GET /public/sponsoring/random");

        return ResponseEntity.ok(sponsoringService.getRandom(1).getFirst());
    }

    @GetMapping("/random/{count}")
    public ResponseEntity<List<SponsorDTO>> getRandoms(@PathVariable int count) {
        log.info("GET /public/sponsoring/random/{}", count);

        return ResponseEntity.ok(sponsoringService.getRandom(count));
    }
}
