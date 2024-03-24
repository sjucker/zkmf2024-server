package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.FestprogrammDayDTO;
import ch.zkmf2024.server.service.FestprogrammService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public/festprogramm")
public class FestprogrammEndpoint {

    private final FestprogrammService festprogrammService;

    public FestprogrammEndpoint(FestprogrammService festprogrammService) {
        this.festprogrammService = festprogrammService;
    }

    @GetMapping
    public ResponseEntity<List<FestprogrammDayDTO>> getFestprogramm() {
        log.info("GET /public/festprogramm");

        return ResponseEntity.ok(festprogrammService.get());
    }

}
