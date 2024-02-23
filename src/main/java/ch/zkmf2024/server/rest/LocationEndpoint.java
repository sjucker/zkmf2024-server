package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.CoordinatesDTO;
import ch.zkmf2024.server.dto.LocationDTO;
import ch.zkmf2024.server.dto.geojson.FeatureItem;
import ch.zkmf2024.server.dto.geojson.GeoJSON;
import ch.zkmf2024.server.dto.geojson.Geometry;
import ch.zkmf2024.server.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static ch.zkmf2024.server.dto.LocationType.WETTSPIELLOKAL;
import static org.apache.commons.lang3.StringUtils.defaultString;

@Slf4j
@RestController
@RequestMapping("/public/location")
public class LocationEndpoint {

    private final LocationService locationService;

    public LocationEndpoint(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/wettspiel")
    public ResponseEntity<List<LocationDTO>> getWettspielLokale() {
        log.info("GET /public/location/wettspiel");

        return ResponseEntity.ok(locationService.findAllByType(WETTSPIELLOKAL));
    }

    @PostMapping("/{id}/distance")
    public ResponseEntity<String> calculateDistance(@PathVariable Long id, @RequestBody CoordinatesDTO coordinates) {
        log.info("POST /public/location/{}/distance {}", id, coordinates);

        return ResponseEntity.ok(locationService.calculateDistanceToLocation(id, coordinates));
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable String identifier) {
        log.info("GET /public/location/{}", identifier);

        return ResponseEntity.of(locationService.findByIdentifier(identifier));
    }

    @GetMapping(value = "/geojson")
    public ResponseEntity<GeoJSON> getGeoJSON() {
        log.info("GET /public/location/geojson");

        var atomicInteger = new AtomicInteger(1);

        return ResponseEntity.ok(new GeoJSON("FeatureCollection",
                                             locationService.findAll().stream()
                                                            .map(location -> new FeatureItem(
                                                                    "Feature",
                                                                    new Geometry("Point", List.of(location.getCoordinates().longitude(),
                                                                                                  location.getCoordinates().latitude())),
                                                                    Map.of("id", String.valueOf(atomicInteger.getAndIncrement()),
                                                                           "name", location.name(),
                                                                           "type", location.type().name(),
                                                                           "info", defaultString(location.modules()))
                                                            ))
                                                            .toList()));
    }
}
