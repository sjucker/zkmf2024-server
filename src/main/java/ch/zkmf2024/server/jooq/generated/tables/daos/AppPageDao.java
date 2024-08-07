/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.AppPage;
import ch.zkmf2024.server.jooq.generated.tables.pojos.AppPagePojo;
import ch.zkmf2024.server.jooq.generated.tables.records.AppPageRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class AppPageDao extends DAOImpl<AppPageRecord, AppPagePojo, Long> {

    /**
     * Create a new AppPageDao without any configuration
     */
    public AppPageDao() {
        super(AppPage.APP_PAGE, AppPagePojo.class);
    }

    /**
     * Create a new AppPageDao with an attached configuration
     */
    public AppPageDao(Configuration configuration) {
        super(AppPage.APP_PAGE, AppPagePojo.class, configuration);
    }

    @Override
    public Long getId(AppPagePojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<AppPagePojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(AppPage.APP_PAGE.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<AppPagePojo> fetchById(Long... values) {
        return fetch(AppPage.APP_PAGE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public AppPagePojo fetchOneById(Long value) {
        return fetchOne(AppPage.APP_PAGE.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<AppPagePojo> fetchOptionalById(Long value) {
        return fetchOptional(AppPage.APP_PAGE.ID, value);
    }

    /**
     * Fetch records that have <code>markdown BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<AppPagePojo> fetchRangeOfMarkdown(String lowerInclusive, String upperInclusive) {
        return fetchRange(AppPage.APP_PAGE.MARKDOWN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>markdown IN (values)</code>
     */
    public List<AppPagePojo> fetchByMarkdown(String... values) {
        return fetch(AppPage.APP_PAGE.MARKDOWN, values);
    }

    /**
     * Fetch records that have <code>cloudflare_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<AppPagePojo> fetchRangeOfCloudflareId(String lowerInclusive, String upperInclusive) {
        return fetchRange(AppPage.APP_PAGE.CLOUDFLARE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>cloudflare_id IN (values)</code>
     */
    public List<AppPagePojo> fetchByCloudflareId(String... values) {
        return fetch(AppPage.APP_PAGE.CLOUDFLARE_ID, values);
    }

    /**
     * Fetch records that have <code>title BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<AppPagePojo> fetchRangeOfTitle(String lowerInclusive, String upperInclusive) {
        return fetchRange(AppPage.APP_PAGE.TITLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>title IN (values)</code>
     */
    public List<AppPagePojo> fetchByTitle(String... values) {
        return fetch(AppPage.APP_PAGE.TITLE, values);
    }

    /**
     * Fetch records that have <code>news BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<AppPagePojo> fetchRangeOfNews(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(AppPage.APP_PAGE.NEWS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>news IN (values)</code>
     */
    public List<AppPagePojo> fetchByNews(Boolean... values) {
        return fetch(AppPage.APP_PAGE.NEWS, values);
    }

    /**
     * Fetch records that have <code>created_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<AppPagePojo> fetchRangeOfCreatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(AppPage.APP_PAGE.CREATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<AppPagePojo> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(AppPage.APP_PAGE.CREATED_AT, values);
    }
}
