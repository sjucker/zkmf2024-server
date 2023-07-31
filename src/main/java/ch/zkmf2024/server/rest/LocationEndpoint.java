package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.LocationDTO;
import ch.zkmf2024.server.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ch.zkmf2024.server.dto.LocationType.WETTSPIELLOKAL;

@Slf4j
@RestController
@RequestMapping("/public/location")
public class LocationEndpoint {

    private final LocationRepository locationRepository;

    public LocationEndpoint(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/wettspiel")
    public ResponseEntity<List<LocationDTO>> getWettspielLokale() {
        log.info("GET /public/location/wettspiel");

        return ResponseEntity.ok(locationRepository.findAllByType(WETTSPIELLOKAL));
    }
}
