/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.VereinDoppeleinsatz;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinDoppeleinsatzPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinDoppeleinsatzRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class VereinDoppeleinsatzDao extends DAOImpl<VereinDoppeleinsatzRecord, VereinDoppeleinsatzPojo, Long> {

    /**
     * Create a new VereinDoppeleinsatzDao without any configuration
     */
    public VereinDoppeleinsatzDao() {
        super(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ, VereinDoppeleinsatzPojo.class);
    }

    /**
     * Create a new VereinDoppeleinsatzDao with an attached configuration
     */
    public VereinDoppeleinsatzDao(Configuration configuration) {
        super(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ, VereinDoppeleinsatzPojo.class, configuration);
    }

    @Override
    public Long getId(VereinDoppeleinsatzPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinDoppeleinsatzPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<VereinDoppeleinsatzPojo> fetchById(Long... values) {
        return fetch(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public VereinDoppeleinsatzPojo fetchOneById(Long value) {
        return fetchOne(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<VereinDoppeleinsatzPojo> fetchOptionalById(Long value) {
        return fetchOptional(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.ID, value);
    }

    /**
     * Fetch records that have <code>fk_verein BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinDoppeleinsatzPojo> fetchRangeOfFkVerein(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.FK_VEREIN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_verein IN (values)</code>
     */
    public List<VereinDoppeleinsatzPojo> fetchByFkVerein(Long... values) {
        return fetch(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.FK_VEREIN, values);
    }

    /**
     * Fetch records that have <code>fk_other_verein BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinDoppeleinsatzPojo> fetchRangeOfFkOtherVerein(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.FK_OTHER_VEREIN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_other_verein IN (values)</code>
     */
    public List<VereinDoppeleinsatzPojo> fetchByFkOtherVerein(Long... values) {
        return fetch(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.FK_OTHER_VEREIN, values);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinDoppeleinsatzPojo> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<VereinDoppeleinsatzPojo> fetchByName(String... values) {
        return fetch(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.NAME, values);
    }
}
