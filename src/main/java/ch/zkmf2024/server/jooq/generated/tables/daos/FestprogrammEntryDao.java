/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.FestprogrammEntry;
import ch.zkmf2024.server.jooq.generated.tables.pojos.FestprogrammEntryPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.FestprogrammEntryRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class FestprogrammEntryDao extends DAOImpl<FestprogrammEntryRecord, FestprogrammEntryPojo, Long> {

    /**
     * Create a new FestprogrammEntryDao without any configuration
     */
    public FestprogrammEntryDao() {
        super(FestprogrammEntry.FESTPROGRAMM_ENTRY, FestprogrammEntryPojo.class);
    }

    /**
     * Create a new FestprogrammEntryDao with an attached configuration
     */
    public FestprogrammEntryDao(Configuration configuration) {
        super(FestprogrammEntry.FESTPROGRAMM_ENTRY, FestprogrammEntryPojo.class, configuration);
    }

    @Override
    public Long getId(FestprogrammEntryPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FestprogrammEntryPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(FestprogrammEntry.FESTPROGRAMM_ENTRY.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<FestprogrammEntryPojo> fetchById(Long... values) {
        return fetch(FestprogrammEntry.FESTPROGRAMM_ENTRY.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public FestprogrammEntryPojo fetchOneById(Long value) {
        return fetchOne(FestprogrammEntry.FESTPROGRAMM_ENTRY.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<FestprogrammEntryPojo> fetchOptionalById(Long value) {
        return fetchOptional(FestprogrammEntry.FESTPROGRAMM_ENTRY.ID, value);
    }

    /**
     * Fetch records that have <code>date BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FestprogrammEntryPojo> fetchRangeOfDate(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(FestprogrammEntry.FESTPROGRAMM_ENTRY.DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>date IN (values)</code>
     */
    public List<FestprogrammEntryPojo> fetchByDate(LocalDate... values) {
        return fetch(FestprogrammEntry.FESTPROGRAMM_ENTRY.DATE, values);
    }

    /**
     * Fetch records that have <code>start_time BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FestprogrammEntryPojo> fetchRangeOfStartTime(LocalTime lowerInclusive, LocalTime upperInclusive) {
        return fetchRange(FestprogrammEntry.FESTPROGRAMM_ENTRY.START_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>start_time IN (values)</code>
     */
    public List<FestprogrammEntryPojo> fetchByStartTime(LocalTime... values) {
        return fetch(FestprogrammEntry.FESTPROGRAMM_ENTRY.START_TIME, values);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FestprogrammEntryPojo> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(FestprogrammEntry.FESTPROGRAMM_ENTRY.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<FestprogrammEntryPojo> fetchByDescription(String... values) {
        return fetch(FestprogrammEntry.FESTPROGRAMM_ENTRY.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>location BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FestprogrammEntryPojo> fetchRangeOfLocation(String lowerInclusive, String upperInclusive) {
        return fetchRange(FestprogrammEntry.FESTPROGRAMM_ENTRY.LOCATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>location IN (values)</code>
     */
    public List<FestprogrammEntryPojo> fetchByLocation(String... values) {
        return fetch(FestprogrammEntry.FESTPROGRAMM_ENTRY.LOCATION, values);
    }

    /**
     * Fetch records that have <code>important BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FestprogrammEntryPojo> fetchRangeOfImportant(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(FestprogrammEntry.FESTPROGRAMM_ENTRY.IMPORTANT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>important IN (values)</code>
     */
    public List<FestprogrammEntryPojo> fetchByImportant(Boolean... values) {
        return fetch(FestprogrammEntry.FESTPROGRAMM_ENTRY.IMPORTANT, values);
    }

    /**
     * Fetch records that have <code>end_time BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<FestprogrammEntryPojo> fetchRangeOfEndTime(LocalTime lowerInclusive, LocalTime upperInclusive) {
        return fetchRange(FestprogrammEntry.FESTPROGRAMM_ENTRY.END_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>end_time IN (values)</code>
     */
    public List<FestprogrammEntryPojo> fetchByEndTime(LocalTime... values) {
        return fetch(FestprogrammEntry.FESTPROGRAMM_ENTRY.END_TIME, values);
    }
}
