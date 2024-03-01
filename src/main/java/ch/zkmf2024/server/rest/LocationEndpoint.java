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

import static ch.zkmf2024.server.dto.LocationType.WETTSPIELLOKAL;

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

        return ResponseEntity.ok(new GeoJSON("FeatureCollection",
                                             locationService.findAll().stream()
                                                            .map(location -> new FeatureItem(
                                                                    "Feature",
                                                                    new Geometry("Point", List.of(location.getCoordinates().longitude(),
                                                                                                  location.getCoordinates().latitude())),
                                                                    Map.of("id", location.mapId(),
                                                                           "name", location.name(),
                                                                           "identifier", location.identifier(),
                                                                           "type", location.type().name(),
                                                                           "info", getInfo(location))
                                                            ))
                                                            .toList()));
    }

    private String getInfo(LocationDTO location) {
        return switch (location.type()) {
            case PARADEMUSIK -> "Parademusik";
            case EINSPIELLOKAL -> "Einspiellokal";
            case INSTRUMENTENDEPOT -> "Instrumentendepot";
            case WETTSPIELLOKAL -> "Module: " + location.modules();
            case JURYFEEDBACK -> "Jurygespräch";
            case PLATZKONZERT -> "Platzkonzert";
            case FESTZELT -> "Festzelt & Food Meile";
            case INFOSTAND -> "Empfang, Infostand";
        };
    }
}
