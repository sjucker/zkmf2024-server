/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.Screen;
import ch.zkmf2024.server.jooq.generated.tables.pojos.ScreenPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.ScreenRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class ScreenDao extends DAOImpl<ScreenRecord, ScreenPojo, Long> {

    /**
     * Create a new ScreenDao without any configuration
     */
    public ScreenDao() {
        super(Screen.SCREEN, ScreenPojo.class);
    }

    /**
     * Create a new ScreenDao with an attached configuration
     */
    public ScreenDao(Configuration configuration) {
        super(Screen.SCREEN, ScreenPojo.class, configuration);
    }

    @Override
    public Long getId(ScreenPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<ScreenPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Screen.SCREEN.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<ScreenPojo> fetchById(Long... values) {
        return fetch(Screen.SCREEN.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public ScreenPojo fetchOneById(Long value) {
        return fetchOne(Screen.SCREEN.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<ScreenPojo> fetchOptionalById(Long value) {
        return fetchOptional(Screen.SCREEN.ID, value);
    }

    /**
     * Fetch records that have <code>location_identifier BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<ScreenPojo> fetchRangeOfLocationIdentifier(String lowerInclusive, String upperInclusive) {
        return fetchRange(Screen.SCREEN.LOCATION_IDENTIFIER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>location_identifier IN (values)</code>
     */
    public List<ScreenPojo> fetchByLocationIdentifier(String... values) {
        return fetch(Screen.SCREEN.LOCATION_IDENTIFIER, values);
    }

    /**
     * Fetch records that have <code>header BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<ScreenPojo> fetchRangeOfHeader(String lowerInclusive, String upperInclusive) {
        return fetchRange(Screen.SCREEN.HEADER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>header IN (values)</code>
     */
    public List<ScreenPojo> fetchByHeader(String... values) {
        return fetch(Screen.SCREEN.HEADER, values);
    }

    /**
     * Fetch records that have <code>message BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<ScreenPojo> fetchRangeOfMessage(String lowerInclusive, String upperInclusive) {
        return fetchRange(Screen.SCREEN.MESSAGE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>message IN (values)</code>
     */
    public List<ScreenPojo> fetchByMessage(String... values) {
        return fetch(Screen.SCREEN.MESSAGE, values);
    }

    /**
     * Fetch records that have <code>cloudflare_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<ScreenPojo> fetchRangeOfCloudflareId(String lowerInclusive, String upperInclusive) {
        return fetchRange(Screen.SCREEN.CLOUDFLARE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>cloudflare_id IN (values)</code>
     */
    public List<ScreenPojo> fetchByCloudflareId(String... values) {
        return fetch(Screen.SCREEN.CLOUDFLARE_ID, values);
    }

    /**
     * Fetch records that have <code>active BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<ScreenPojo> fetchRangeOfActive(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Screen.SCREEN.ACTIVE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>active IN (values)</code>
     */
    public List<ScreenPojo> fetchByActive(Boolean... values) {
        return fetch(Screen.SCREEN.ACTIVE, values);
    }
}
