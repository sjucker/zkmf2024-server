package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.LocationDTO;
import ch.zkmf2024.server.dto.LocationType;
import ch.zkmf2024.server.dto.admin.LocationSelectionDTO;
import ch.zkmf2024.server.jooq.generated.enums.LocationLocationType;
import ch.zkmf2024.server.jooq.generated.tables.daos.LocationDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.LocationPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
public class LocationRepository {

    private final DSLContext jooqDsl;
    private final LocationDao locationDao;

    public LocationRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
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
                          .map(pojo -> new LocationDTO(
                                  pojo.getId(),
                                  pojo.getName(),
                                  pojo.getAddress(),
                                  pojo.getLatitude(),
                                  pojo.getLongitude(),
                                  getGoogleMapsAddress(pojo),
                                  getGoogleMapsCoordinates(pojo),
                                  type,
                                  pojo.getCapacity(),
                                  pojo.getModules(),
                                  findById(pojo.getEinspiellokalId()).orElse(null),
                                  findById(pojo.getInstrumentendepotId()).orElse(null),
                                  findById(pojo.getJuryfeedbackId()).orElse(null)
                          ))
                          .toList();
    }

    public Optional<LocationDTO> findById(Long locationId) {
        if (locationId == null) {
            return Optional.empty();
        }

        return locationDao.findOptionalById(locationId)
                          .map(pojo -> new LocationDTO(
                                  pojo.getId(),
                                  pojo.getName(),
                                  pojo.getAddress(),
                                  pojo.getLatitude(),
                                  pojo.getLongitude(),
                                  getGoogleMapsAddress(pojo),
                                  getGoogleMapsCoordinates(pojo),
                                  LocationType.valueOf(pojo.getLocationType().getLiteral()),
                                  pojo.getCapacity(),
                                  pojo.getModules(),
                                  findById(pojo.getEinspiellokalId()).orElse(null),
                                  findById(pojo.getInstrumentendepotId()).orElse(null),
                                  findById(pojo.getJuryfeedbackId()).orElse(null)
                          ));
    }

    private String getGoogleMapsAddress(LocationPojo pojo) {
        return "https://www.google.ch/maps/place/%s".formatted(URLEncoder.encode(pojo.getAddress(), UTF_8));
    }

    private String getGoogleMapsCoordinates(LocationPojo pojo) {
        return "http://www.google.com/maps?q=%s,%s".formatted(pojo.getLatitude(), pojo.getLongitude());
    }

}
