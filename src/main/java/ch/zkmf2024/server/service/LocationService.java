package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.CoordinatesDTO;
import ch.zkmf2024.server.dto.LocationDTO;
import ch.zkmf2024.server.dto.LocationType;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.util.DistanceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDTO> findAllByType(LocationType type) {
        return locationRepository.findAllByType(type);
    }

    public Optional<LocationDTO> findByIdentifier(String identifier) {
        return locationRepository.findByIdentifier(identifier);
    }

    public String calculateDistanceToLocation(Long id, CoordinatesDTO coordinates) {
        var location = locationRepository.findById(id)
                                         .orElseThrow(() -> new NoSuchElementException("no location with ID %d found".formatted(id)));

        var distanceInMeters = DistanceUtil.calculateDistanceBetweenPointsInMeters(location.getCoordinates(), coordinates);

        return "%sm".formatted(distanceInMeters.intValue());
    }
}
