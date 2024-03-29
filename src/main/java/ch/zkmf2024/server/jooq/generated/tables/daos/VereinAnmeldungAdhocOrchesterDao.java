/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.VereinAnmeldungAdhocOrchester;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinAnmeldungAdhocOrchesterPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinAnmeldungAdhocOrchesterRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinAnmeldungAdhocOrchesterDao extends DAOImpl<VereinAnmeldungAdhocOrchesterRecord, VereinAnmeldungAdhocOrchesterPojo, Long> {

    /**
     * Create a new VereinAnmeldungAdhocOrchesterDao without any configuration
     */
    public VereinAnmeldungAdhocOrchesterDao() {
        super(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER, VereinAnmeldungAdhocOrchesterPojo.class);
    }

    /**
     * Create a new VereinAnmeldungAdhocOrchesterDao with an attached
     * configuration
     */
    public VereinAnmeldungAdhocOrchesterDao(Configuration configuration) {
        super(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER, VereinAnmeldungAdhocOrchesterPojo.class, configuration);
    }

    @Override
    public Long getId(VereinAnmeldungAdhocOrchesterPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchById(Long... values) {
        return fetch(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public VereinAnmeldungAdhocOrchesterPojo fetchOneById(Long value) {
        return fetchOne(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<VereinAnmeldungAdhocOrchesterPojo> fetchOptionalById(Long value) {
        return fetchOptional(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.ID, value);
    }

    /**
     * Fetch records that have <code>fk_verein BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchRangeOfFkVerein(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.FK_VEREIN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_verein IN (values)</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchByFkVerein(Long... values) {
        return fetch(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.FK_VEREIN, values);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchByName(String... values) {
        return fetch(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.NAME, values);
    }

    /**
     * Fetch records that have <code>email BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchRangeOfEmail(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.EMAIL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchByEmail(String... values) {
        return fetch(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.EMAIL, values);
    }

    /**
     * Fetch records that have <code>instrument BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchRangeOfInstrument(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.INSTRUMENT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>instrument IN (values)</code>
     */
    public List<VereinAnmeldungAdhocOrchesterPojo> fetchByInstrument(String... values) {
        return fetch(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.INSTRUMENT, values);
    }
}
