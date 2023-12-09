/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.VereinComment;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinCommentPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinCommentRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinCommentDao extends DAOImpl<VereinCommentRecord, VereinCommentPojo, Long> {

    /**
     * Create a new VereinCommentDao without any configuration
     */
    public VereinCommentDao() {
        super(VereinComment.VEREIN_COMMENT, VereinCommentPojo.class);
    }

    /**
     * Create a new VereinCommentDao with an attached configuration
     */
    public VereinCommentDao(Configuration configuration) {
        super(VereinComment.VEREIN_COMMENT, VereinCommentPojo.class, configuration);
    }

    @Override
    public Long getId(VereinCommentPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinCommentPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinComment.VEREIN_COMMENT.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<VereinCommentPojo> fetchById(Long... values) {
        return fetch(VereinComment.VEREIN_COMMENT.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public VereinCommentPojo fetchOneById(Long value) {
        return fetchOne(VereinComment.VEREIN_COMMENT.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<VereinCommentPojo> fetchOptionalById(Long value) {
        return fetchOptional(VereinComment.VEREIN_COMMENT.ID, value);
    }

    /**
     * Fetch records that have <code>fk_verein BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinCommentPojo> fetchRangeOfFkVerein(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinComment.VEREIN_COMMENT.FK_VEREIN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_verein IN (values)</code>
     */
    public List<VereinCommentPojo> fetchByFkVerein(Long... values) {
        return fetch(VereinComment.VEREIN_COMMENT.FK_VEREIN, values);
    }

    /**
     * Fetch records that have <code>comment BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinCommentPojo> fetchRangeOfComment(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinComment.VEREIN_COMMENT.COMMENT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>comment IN (values)</code>
     */
    public List<VereinCommentPojo> fetchByComment(String... values) {
        return fetch(VereinComment.VEREIN_COMMENT.COMMENT, values);
    }

    /**
     * Fetch records that have <code>created_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinCommentPojo> fetchRangeOfCreatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(VereinComment.VEREIN_COMMENT.CREATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<VereinCommentPojo> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(VereinComment.VEREIN_COMMENT.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinCommentPojo> fetchRangeOfCreatedBy(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinComment.VEREIN_COMMENT.CREATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    public List<VereinCommentPojo> fetchByCreatedBy(String... values) {
        return fetch(VereinComment.VEREIN_COMMENT.CREATED_BY, values);
    }
}
