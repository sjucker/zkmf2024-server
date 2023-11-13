/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.enums.LocationLocationType;
import ch.zkmf2024.server.jooq.generated.tables.Location;
import ch.zkmf2024.server.jooq.generated.tables.pojos.LocationPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.LocationRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class LocationDao extends DAOImpl<LocationRecord, LocationPojo, Long> {

    /**
     * Create a new LocationDao without any configuration
     */
    public LocationDao() {
        super(Location.LOCATION, LocationPojo.class);
    }

    /**
     * Create a new LocationDao with an attached configuration
     */
    public LocationDao(Configuration configuration) {
        super(Location.LOCATION, LocationPojo.class, configuration);
    }

    @Override
    public Long getId(LocationPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Location.LOCATION.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<LocationPojo> fetchById(Long... values) {
        return fetch(Location.LOCATION.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public LocationPojo fetchOneById(Long value) {
        return fetchOne(Location.LOCATION.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<LocationPojo> fetchOptionalById(Long value) {
        return fetchOptional(Location.LOCATION.ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Location.LOCATION.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<LocationPojo> fetchByName(String... values) {
        return fetch(Location.LOCATION.NAME, values);
    }

    /**
     * Fetch records that have <code>address BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfAddress(String lowerInclusive, String upperInclusive) {
        return fetchRange(Location.LOCATION.ADDRESS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>address IN (values)</code>
     */
    public List<LocationPojo> fetchByAddress(String... values) {
        return fetch(Location.LOCATION.ADDRESS, values);
    }

    /**
     * Fetch records that have <code>latitude BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfLatitude(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(Location.LOCATION.LATITUDE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>latitude IN (values)</code>
     */
    public List<LocationPojo> fetchByLatitude(BigDecimal... values) {
        return fetch(Location.LOCATION.LATITUDE, values);
    }

    /**
     * Fetch records that have <code>longitude BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfLongitude(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(Location.LOCATION.LONGITUDE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>longitude IN (values)</code>
     */
    public List<LocationPojo> fetchByLongitude(BigDecimal... values) {
        return fetch(Location.LOCATION.LONGITUDE, values);
    }

    /**
     * Fetch records that have <code>location_type BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfLocationType(LocationLocationType lowerInclusive, LocationLocationType upperInclusive) {
        return fetchRange(Location.LOCATION.LOCATION_TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>location_type IN (values)</code>
     */
    public List<LocationPojo> fetchByLocationType(LocationLocationType... values) {
        return fetch(Location.LOCATION.LOCATION_TYPE, values);
    }

    /**
     * Fetch records that have <code>capacity BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfCapacity(String lowerInclusive, String upperInclusive) {
        return fetchRange(Location.LOCATION.CAPACITY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>capacity IN (values)</code>
     */
    public List<LocationPojo> fetchByCapacity(String... values) {
        return fetch(Location.LOCATION.CAPACITY, values);
    }

    /**
     * Fetch records that have <code>modules BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfModules(String lowerInclusive, String upperInclusive) {
        return fetchRange(Location.LOCATION.MODULES, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modules IN (values)</code>
     */
    public List<LocationPojo> fetchByModules(String... values) {
        return fetch(Location.LOCATION.MODULES, values);
    }

    /**
     * Fetch records that have <code>einspiellokal_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfEinspiellokalId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Location.LOCATION.EINSPIELLOKAL_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>einspiellokal_id IN (values)</code>
     */
    public List<LocationPojo> fetchByEinspiellokalId(Long... values) {
        return fetch(Location.LOCATION.EINSPIELLOKAL_ID, values);
    }

    /**
     * Fetch records that have <code>instrumentendepot_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfInstrumentendepotId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Location.LOCATION.INSTRUMENTENDEPOT_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>instrumentendepot_id IN (values)</code>
     */
    public List<LocationPojo> fetchByInstrumentendepotId(Long... values) {
        return fetch(Location.LOCATION.INSTRUMENTENDEPOT_ID, values);
    }

    /**
     * Fetch records that have <code>juryfeedback_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfJuryfeedbackId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Location.LOCATION.JURYFEEDBACK_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>juryfeedback_id IN (values)</code>
     */
    public List<LocationPojo> fetchByJuryfeedbackId(Long... values) {
        return fetch(Location.LOCATION.JURYFEEDBACK_ID, values);
    }

    /**
     * Fetch records that have <code>sort_order BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<LocationPojo> fetchRangeOfSortOrder(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Location.LOCATION.SORT_ORDER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sort_order IN (values)</code>
     */
    public List<LocationPojo> fetchBySortOrder(Integer... values) {
        return fetch(Location.LOCATION.SORT_ORDER, values);
    }
}
