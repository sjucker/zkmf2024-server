package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.LocationDTO;
import ch.zkmf2024.server.dto.LocationType;
import ch.zkmf2024.server.dto.admin.LocationSelectionDTO;
import ch.zkmf2024.server.jooq.generated.enums.LocationLocationType;
import ch.zkmf2024.server.jooq.generated.tables.daos.LocationDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.LocationPojo;
import org.jooq.Record;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
public class LocationRepository {

    private final LocationDao locationDao;

    public LocationRepository(DefaultConfiguration jooqConfig) {
        this.locationDao = new LocationDao(jooqConfig);
    }

    public List<LocationSelectionDTO> findAllSelectionByType(LocationType type) {
        return locationDao.fetchByLocationType(LocationLocationType.lookupLiteral(type.name())).stream()
                          .map(pojo -> new LocationSelectionDTO(
                                  pojo.getId(),
                                  pojo.getName()
                          ))
                          .toList();
    }

    public List<LocationDTO> findAllByType(LocationType type) {
        return locationDao.fetchByLocationType(LocationLocationType.lookupLiteral(type.name())).stream()
                          .map(this::toLocationDTO)
                          .toList();
    }

    private LocationDTO toLocationDTO(LocationPojo pojo) {
        return new LocationDTO(
                pojo.getId(),
                pojo.getIdentifier(),
                pojo.getName(),
                pojo.getAddress(),
                pojo.getLatitude(),
                pojo.getLongitude(),
                getGoogleMapsAddress(pojo.getAddress()),
                getGoogleMapsCoordinates(pojo),
                LocationType.valueOf(pojo.getLocationType().getLiteral()),
                pojo.getCapacity(),
                pojo.getModules(),
                pojo.getSortOrder(),
                pojo.getCloudflareId(),
                pojo.getKuulaId(),
                findById(pojo.getEinspiellokalId()).orElse(null),
                findById(pojo.getInstrumentendepotId()).orElse(null),
                findById(pojo.getJuryfeedbackId()).orElse(null)
        );
    }

    public Optional<LocationDTO> findById(Long locationId) {
        if (locationId == null) {
            return Optional.empty();
        }

        return locationDao.findOptionalById(locationId)
                          .map(this::toLocationDTO);
    }

    public Optional<LocationDTO> findByIdentifier(String identifier) {
        return locationDao.fetchOptionalByIdentifier(identifier)
                          .map(this::toLocationDTO);
    }

    public static LocationDTO toDTO(Record it) {
        return new LocationDTO(
                it.get(LOCATION.ID),
                it.get(LOCATION.IDENTIFIER),
                it.get(LOCATION.NAME),
                it.get(LOCATION.ADDRESS),
                it.get(LOCATION.LATITUDE),
                it.get(LOCATION.LONGITUDE),
                getGoogleMapsAddress(it.get(LOCATION.ADDRESS)),
                getGoogleMapsCoordinates(it.get(LOCATION.LATITUDE), it.get(LOCATION.LONGITUDE)),
                LocationType.valueOf(it.get(LOCATION.LOCATION_TYPE).getLiteral()),
                it.get(LOCATION.CAPACITY),
                it.get(LOCATION.MODULES),
                it.get(LOCATION.SORT_ORDER),
                it.get(LOCATION.CLOUDFLARE_ID),
                it.get(LOCATION.KUULA_ID),
                null, null, null
        );
    }

    private static String getGoogleMapsAddress(String address) {
        return "https://www.google.ch/maps/place/%s".formatted(URLEncoder.encode(address, UTF_8));
    }

    private static String getGoogleMapsCoordinates(LocationPojo pojo) {
        return getGoogleMapsCoordinates(pojo.getLatitude(), pojo.getLongitude());
    }

    private static String getGoogleMapsCoordinates(BigDecimal latitude, BigDecimal longitude) {
        return "http://www.google.com/maps?q=%s,%s".formatted(latitude, longitude);
    }
}
