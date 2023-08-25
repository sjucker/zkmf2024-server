/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.Titel;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TitelPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.TitelRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class TitelDao extends DAOImpl<TitelRecord, TitelPojo, Long> {

    /**
     * Create a new TitelDao without any configuration
     */
    public TitelDao() {
        super(Titel.TITEL, TitelPojo.class);
    }

    /**
     * Create a new TitelDao with an attached configuration
     */
    public TitelDao(Configuration configuration) {
        super(Titel.TITEL, TitelPojo.class, configuration);
    }

    @Override
    public Long getId(TitelPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Titel.TITEL.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<TitelPojo> fetchById(Long... values) {
        return fetch(Titel.TITEL.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public TitelPojo fetchOneById(Long value) {
        return fetchOne(Titel.TITEL.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<TitelPojo> fetchOptionalById(Long value) {
        return fetchOptional(Titel.TITEL.ID, value);
    }

    /**
     * Fetch records that have <code>fk_verein BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfFkVerein(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Titel.TITEL.FK_VEREIN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_verein IN (values)</code>
     */
    public List<TitelPojo> fetchByFkVerein(Long... values) {
        return fetch(Titel.TITEL.FK_VEREIN, values);
    }

    /**
     * Fetch records that have <code>titel_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfTitelName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Titel.TITEL.TITEL_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>titel_name IN (values)</code>
     */
    public List<TitelPojo> fetchByTitelName(String... values) {
        return fetch(Titel.TITEL.TITEL_NAME, values);
    }

    /**
     * Fetch records that have <code>composer BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfComposer(String lowerInclusive, String upperInclusive) {
        return fetchRange(Titel.TITEL.COMPOSER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>composer IN (values)</code>
     */
    public List<TitelPojo> fetchByComposer(String... values) {
        return fetch(Titel.TITEL.COMPOSER, values);
    }

    /**
     * Fetch records that have <code>arrangeur BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfArrangeur(String lowerInclusive, String upperInclusive) {
        return fetchRange(Titel.TITEL.ARRANGEUR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>arrangeur IN (values)</code>
     */
    public List<TitelPojo> fetchByArrangeur(String... values) {
        return fetch(Titel.TITEL.ARRANGEUR, values);
    }

    /**
     * Fetch records that have <code>grad BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfGrad(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(Titel.TITEL.GRAD, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>grad IN (values)</code>
     */
    public List<TitelPojo> fetchByGrad(BigDecimal... values) {
        return fetch(Titel.TITEL.GRAD, values);
    }

    /**
     * Fetch records that have <code>duration_in_seconds BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfDurationInSeconds(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Titel.TITEL.DURATION_IN_SECONDS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>duration_in_seconds IN (values)</code>
     */
    public List<TitelPojo> fetchByDurationInSeconds(Integer... values) {
        return fetch(Titel.TITEL.DURATION_IN_SECONDS, values);
    }

    /**
     * Fetch records that have <code>modul BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfModul(String lowerInclusive, String upperInclusive) {
        return fetchRange(Titel.TITEL.MODUL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modul IN (values)</code>
     */
    public List<TitelPojo> fetchByModul(String... values) {
        return fetch(Titel.TITEL.MODUL, values);
    }

    /**
     * Fetch records that have <code>klasse BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfKlasse(String lowerInclusive, String upperInclusive) {
        return fetchRange(Titel.TITEL.KLASSE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>klasse IN (values)</code>
     */
    public List<TitelPojo> fetchByKlasse(String... values) {
        return fetch(Titel.TITEL.KLASSE, values);
    }

    /**
     * Fetch records that have <code>besetzung BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfBesetzung(String lowerInclusive, String upperInclusive) {
        return fetchRange(Titel.TITEL.BESETZUNG, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>besetzung IN (values)</code>
     */
    public List<TitelPojo> fetchByBesetzung(String... values) {
        return fetch(Titel.TITEL.BESETZUNG, values);
    }

    /**
     * Fetch records that have <code>info_moderation BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfInfoModeration(String lowerInclusive, String upperInclusive) {
        return fetchRange(Titel.TITEL.INFO_MODERATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>info_moderation IN (values)</code>
     */
    public List<TitelPojo> fetchByInfoModeration(String... values) {
        return fetch(Titel.TITEL.INFO_MODERATION, values);
    }

    /**
     * Fetch records that have <code>schwierigkeitsgrad BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<TitelPojo> fetchRangeOfSchwierigkeitsgrad(String lowerInclusive, String upperInclusive) {
        return fetchRange(Titel.TITEL.SCHWIERIGKEITSGRAD, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>schwierigkeitsgrad IN (values)</code>
     */
    public List<TitelPojo> fetchBySchwierigkeitsgrad(String... values) {
        return fetch(Titel.TITEL.SCHWIERIGKEITSGRAD, values);
    }
}
