/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.EmergencyMessage;
import ch.zkmf2024.server.jooq.generated.tables.pojos.EmergencyMessagePojo;
import ch.zkmf2024.server.jooq.generated.tables.records.EmergencyMessageRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class EmergencyMessageDao extends DAOImpl<EmergencyMessageRecord, EmergencyMessagePojo, Long> {

    /**
     * Create a new EmergencyMessageDao without any configuration
     */
    public EmergencyMessageDao() {
        super(EmergencyMessage.EMERGENCY_MESSAGE, EmergencyMessagePojo.class);
    }

    /**
     * Create a new EmergencyMessageDao with an attached configuration
     */
    public EmergencyMessageDao(Configuration configuration) {
        super(EmergencyMessage.EMERGENCY_MESSAGE, EmergencyMessagePojo.class, configuration);
    }

    @Override
    public Long getId(EmergencyMessagePojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<EmergencyMessagePojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(EmergencyMessage.EMERGENCY_MESSAGE.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<EmergencyMessagePojo> fetchById(Long... values) {
        return fetch(EmergencyMessage.EMERGENCY_MESSAGE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public EmergencyMessagePojo fetchOneById(Long value) {
        return fetchOne(EmergencyMessage.EMERGENCY_MESSAGE.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<EmergencyMessagePojo> fetchOptionalById(Long value) {
        return fetchOptional(EmergencyMessage.EMERGENCY_MESSAGE.ID, value);
    }

    /**
     * Fetch records that have <code>header BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<EmergencyMessagePojo> fetchRangeOfHeader(String lowerInclusive, String upperInclusive) {
        return fetchRange(EmergencyMessage.EMERGENCY_MESSAGE.HEADER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>header IN (values)</code>
     */
    public List<EmergencyMessagePojo> fetchByHeader(String... values) {
        return fetch(EmergencyMessage.EMERGENCY_MESSAGE.HEADER, values);
    }

    /**
     * Fetch records that have <code>message BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<EmergencyMessagePojo> fetchRangeOfMessage(String lowerInclusive, String upperInclusive) {
        return fetchRange(EmergencyMessage.EMERGENCY_MESSAGE.MESSAGE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>message IN (values)</code>
     */
    public List<EmergencyMessagePojo> fetchByMessage(String... values) {
        return fetch(EmergencyMessage.EMERGENCY_MESSAGE.MESSAGE, values);
    }

    /**
     * Fetch records that have <code>active BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<EmergencyMessagePojo> fetchRangeOfActive(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(EmergencyMessage.EMERGENCY_MESSAGE.ACTIVE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>active IN (values)</code>
     */
    public List<EmergencyMessagePojo> fetchByActive(Boolean... values) {
        return fetch(EmergencyMessage.EMERGENCY_MESSAGE.ACTIVE, values);
    }
}
