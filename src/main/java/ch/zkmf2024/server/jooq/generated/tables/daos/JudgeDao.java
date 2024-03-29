/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.Judge;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgePojo;
import ch.zkmf2024.server.jooq.generated.tables.records.JudgeRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class JudgeDao extends DAOImpl<JudgeRecord, JudgePojo, Long> {

    /**
     * Create a new JudgeDao without any configuration
     */
    public JudgeDao() {
        super(Judge.JUDGE, JudgePojo.class);
    }

    /**
     * Create a new JudgeDao with an attached configuration
     */
    public JudgeDao(Configuration configuration) {
        super(Judge.JUDGE, JudgePojo.class, configuration);
    }

    @Override
    public Long getId(JudgePojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<JudgePojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Judge.JUDGE.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<JudgePojo> fetchById(Long... values) {
        return fetch(Judge.JUDGE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public JudgePojo fetchOneById(Long value) {
        return fetchOne(Judge.JUDGE.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<JudgePojo> fetchOptionalById(Long value) {
        return fetchOptional(Judge.JUDGE.ID, value);
    }

    /**
     * Fetch records that have <code>email BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<JudgePojo> fetchRangeOfEmail(String lowerInclusive, String upperInclusive) {
        return fetchRange(Judge.JUDGE.EMAIL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<JudgePojo> fetchByEmail(String... values) {
        return fetch(Judge.JUDGE.EMAIL, values);
    }

    /**
     * Fetch a unique record that has <code>email = value</code>
     */
    public JudgePojo fetchOneByEmail(String value) {
        return fetchOne(Judge.JUDGE.EMAIL, value);
    }

    /**
     * Fetch a unique record that has <code>email = value</code>
     */
    public Optional<JudgePojo> fetchOptionalByEmail(String value) {
        return fetchOptional(Judge.JUDGE.EMAIL, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<JudgePojo> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Judge.JUDGE.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<JudgePojo> fetchByName(String... values) {
        return fetch(Judge.JUDGE.NAME, values);
    }

    /**
     * Fetch records that have <code>first_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<JudgePojo> fetchRangeOfFirstName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Judge.JUDGE.FIRST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    public List<JudgePojo> fetchByFirstName(String... values) {
        return fetch(Judge.JUDGE.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>modul BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<JudgePojo> fetchRangeOfModul(String lowerInclusive, String upperInclusive) {
        return fetchRange(Judge.JUDGE.MODUL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modul IN (values)</code>
     */
    public List<JudgePojo> fetchByModul(String... values) {
        return fetch(Judge.JUDGE.MODUL, values);
    }

    /**
     * Fetch records that have <code>cloudflare_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<JudgePojo> fetchRangeOfCloudflareId(String lowerInclusive, String upperInclusive) {
        return fetchRange(Judge.JUDGE.CLOUDFLARE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>cloudflare_id IN (values)</code>
     */
    public List<JudgePojo> fetchByCloudflareId(String... values) {
        return fetch(Judge.JUDGE.CLOUDFLARE_ID, values);
    }

    /**
     * Fetch records that have <code>presentation_text BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<JudgePojo> fetchRangeOfPresentationText(String lowerInclusive, String upperInclusive) {
        return fetchRange(Judge.JUDGE.PRESENTATION_TEXT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>presentation_text IN (values)</code>
     */
    public List<JudgePojo> fetchByPresentationText(String... values) {
        return fetch(Judge.JUDGE.PRESENTATION_TEXT, values);
    }
}
