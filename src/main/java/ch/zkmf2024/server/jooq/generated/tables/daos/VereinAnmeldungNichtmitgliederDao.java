/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.VereinAnmeldungNichtmitglieder;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinAnmeldungNichtmitgliederPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinAnmeldungNichtmitgliederRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinAnmeldungNichtmitgliederDao extends DAOImpl<VereinAnmeldungNichtmitgliederRecord, VereinAnmeldungNichtmitgliederPojo, Long> {

    /**
     * Create a new VereinAnmeldungNichtmitgliederDao without any configuration
     */
    public VereinAnmeldungNichtmitgliederDao() {
        super(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER, VereinAnmeldungNichtmitgliederPojo.class);
    }

    /**
     * Create a new VereinAnmeldungNichtmitgliederDao with an attached
     * configuration
     */
    public VereinAnmeldungNichtmitgliederDao(Configuration configuration) {
        super(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER, VereinAnmeldungNichtmitgliederPojo.class, configuration);
    }

    @Override
    public Long getId(VereinAnmeldungNichtmitgliederPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungNichtmitgliederPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<VereinAnmeldungNichtmitgliederPojo> fetchById(Long... values) {
        return fetch(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public VereinAnmeldungNichtmitgliederPojo fetchOneById(Long value) {
        return fetchOne(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<VereinAnmeldungNichtmitgliederPojo> fetchOptionalById(Long value) {
        return fetchOptional(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.ID, value);
    }

    /**
     * Fetch records that have <code>fk_verein BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungNichtmitgliederPojo> fetchRangeOfFkVerein(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.FK_VEREIN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_verein IN (values)</code>
     */
    public List<VereinAnmeldungNichtmitgliederPojo> fetchByFkVerein(Long... values) {
        return fetch(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.FK_VEREIN, values);
    }

    /**
     * Fetch records that have <code>amount BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungNichtmitgliederPojo> fetchRangeOfAmount(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>amount IN (values)</code>
     */
    public List<VereinAnmeldungNichtmitgliederPojo> fetchByAmount(Integer... values) {
        return fetch(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.AMOUNT, values);
    }

    /**
     * Fetch records that have <code>instrument BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungNichtmitgliederPojo> fetchRangeOfInstrument(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.INSTRUMENT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>instrument IN (values)</code>
     */
    public List<VereinAnmeldungNichtmitgliederPojo> fetchByInstrument(String... values) {
        return fetch(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.INSTRUMENT, values);
    }
}
