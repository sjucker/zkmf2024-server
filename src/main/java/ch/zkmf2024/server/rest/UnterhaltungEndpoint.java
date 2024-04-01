package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.UnterhaltungTypeDTO;
import ch.zkmf2024.server.dto.UnterhaltungsEntryDTO;
import ch.zkmf2024.server.service.UnterhaltungService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public/unterhaltung")
public class UnterhaltungEndpoint {

    private final UnterhaltungService unterhaltungService;

    public UnterhaltungEndpoint(UnterhaltungService unterhaltungService) {
        this.unterhaltungService = unterhaltungService;
    }

    @GetMapping
    public ResponseEntity<List<UnterhaltungTypeDTO>> getUnterhaltung() {
        log.info("GET /public/unterhaltung");

        return ResponseEntity.ok(unterhaltungService.get());
    }

    @GetMapping("/band/{identifier}")
    public ResponseEntity<UnterhaltungsEntryDTO> getByUnterhaltungIdentifier(@PathVariable String identifier) {
        log.info("GET /public/unterhaltung/{}", identifier);

        return ResponseEntity.of(unterhaltungService.getByUnterhaltungIdentifier(identifier));
    }

}
