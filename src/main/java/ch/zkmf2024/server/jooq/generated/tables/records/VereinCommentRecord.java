/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.VereinComment;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVereinComment;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinCommentPojo;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinCommentRecord extends UpdatableRecordImpl<VereinCommentRecord> implements Record5<Long, Long, String, LocalDateTime, String>, IVereinComment {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>verein_comment.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>verein_comment.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>verein_comment.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>verein_comment.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>verein_comment.comment</code>.
     */
    @Override
    public void setComment(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>verein_comment.comment</code>.
     */
    @Override
    public String getComment() {
        return (String) get(2);
    }

    /**
     * Setter for <code>verein_comment.created_at</code>.
     */
    @Override
    public void setCreatedAt(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>verein_comment.created_at</code>.
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>verein_comment.created_by</code>.
     */
    @Override
    public void setCreatedBy(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>verein_comment.created_by</code>.
     */
    @Override
    public String getCreatedBy() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, String, LocalDateTime, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, Long, String, LocalDateTime, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return VereinComment.VEREIN_COMMENT.ID;
    }

    @Override
    public Field<Long> field2() {
        return VereinComment.VEREIN_COMMENT.FK_VEREIN;
    }

    @Override
    public Field<String> field3() {
        return VereinComment.VEREIN_COMMENT.COMMENT;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return VereinComment.VEREIN_COMMENT.CREATED_AT;
    }

    @Override
    public Field<String> field5() {
        return VereinComment.VEREIN_COMMENT.CREATED_BY;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getFkVerein();
    }

    @Override
    public String component3() {
        return getComment();
    }

    @Override
    public LocalDateTime component4() {
        return getCreatedAt();
    }

    @Override
    public String component5() {
        return getCreatedBy();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getFkVerein();
    }

    @Override
    public String value3() {
        return getComment();
    }

    @Override
    public LocalDateTime value4() {
        return getCreatedAt();
    }

    @Override
    public String value5() {
        return getCreatedBy();
    }

    @Override
    public VereinCommentRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public VereinCommentRecord value2(Long value) {
        setFkVerein(value);
        return this;
    }

    @Override
    public VereinCommentRecord value3(String value) {
        setComment(value);
        return this;
    }

    @Override
    public VereinCommentRecord value4(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public VereinCommentRecord value5(String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public VereinCommentRecord values(Long value1, Long value2, String value3, LocalDateTime value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IVereinComment from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setComment(from.getComment());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IVereinComment> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VereinCommentRecord
     */
    public VereinCommentRecord() {
        super(VereinComment.VEREIN_COMMENT);
    }

    /**
     * Create a detached, initialised VereinCommentRecord
     */
    public VereinCommentRecord(Long id, Long fkVerein, String comment, LocalDateTime createdAt, String createdBy) {
        super(VereinComment.VEREIN_COMMENT);

        setId(id);
        setFkVerein(fkVerein);
        setComment(comment);
        setCreatedAt(createdAt);
        setCreatedBy(createdBy);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised VereinCommentRecord
     */
    public VereinCommentRecord(VereinCommentPojo value) {
        super(VereinComment.VEREIN_COMMENT);

        if (value != null) {
            setId(value.getId());
            setFkVerein(value.getFkVerein());
            setComment(value.getComment());
            setCreatedAt(value.getCreatedAt());
            setCreatedBy(value.getCreatedBy());
            resetChangedOnNotNull();
        }
    }
}
