/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.VereinProgrammTitel;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammTitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinProgrammTitelRecord;

import java.util.List;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class VereinProgrammTitelDao extends DAOImpl<VereinProgrammTitelRecord, VereinProgrammTitelPojo, Record2<Long, Long>> {

    /**
     * Create a new VereinProgrammTitelDao without any configuration
     */
    public VereinProgrammTitelDao() {
        super(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL, VereinProgrammTitelPojo.class);
    }

    /**
     * Create a new VereinProgrammTitelDao with an attached configuration
     */
    public VereinProgrammTitelDao(Configuration configuration) {
        super(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL, VereinProgrammTitelPojo.class, configuration);
    }

    @Override
    public Record2<Long, Long> getId(VereinProgrammTitelPojo object) {
        return compositeKeyRecord(object.getFkProgramm(), object.getFkTitel());
    }

    /**
     * Fetch records that have <code>fk_programm BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinProgrammTitelPojo> fetchRangeOfFkProgramm(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_PROGRAMM, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_programm IN (values)</code>
     */
    public List<VereinProgrammTitelPojo> fetchByFkProgramm(Long... values) {
        return fetch(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_PROGRAMM, values);
    }

    /**
     * Fetch records that have <code>fk_titel BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinProgrammTitelPojo> fetchRangeOfFkTitel(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_TITEL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_titel IN (values)</code>
     */
    public List<VereinProgrammTitelPojo> fetchByFkTitel(Long... values) {
        return fetch(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_TITEL, values);
    }

    /**
     * Fetch records that have <code>position BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinProgrammTitelPojo> fetchRangeOfPosition(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.POSITION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>position IN (values)</code>
     */
    public List<VereinProgrammTitelPojo> fetchByPosition(Integer... values) {
        return fetch(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.POSITION, values);
    }

    /**
     * Fetch records that have <code>duration_in_seconds BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinProgrammTitelPojo> fetchRangeOfDurationInSeconds(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.DURATION_IN_SECONDS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>duration_in_seconds IN (values)</code>
     */
    public List<VereinProgrammTitelPojo> fetchByDurationInSeconds(Integer... values) {
        return fetch(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.DURATION_IN_SECONDS, values);
    }

    /**
     * Fetch records that have <code>applaus_in_seconds BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinProgrammTitelPojo> fetchRangeOfApplausInSeconds(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.APPLAUS_IN_SECONDS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>applaus_in_seconds IN (values)</code>
     */
    public List<VereinProgrammTitelPojo> fetchByApplausInSeconds(Integer... values) {
        return fetch(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.APPLAUS_IN_SECONDS, values);
    }
}
