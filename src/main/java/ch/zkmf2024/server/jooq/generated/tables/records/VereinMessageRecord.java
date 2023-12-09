/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.VereinMessage;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVereinMessage;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinMessagePojo;
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
public class VereinMessageRecord extends UpdatableRecordImpl<VereinMessageRecord> implements Record5<Long, Long, String, LocalDateTime, String>, IVereinMessage {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>verein_message.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>verein_message.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>verein_message.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>verein_message.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>verein_message.message</code>.
     */
    @Override
    public void setMessage(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>verein_message.message</code>.
     */
    @Override
    public String getMessage() {
        return (String) get(2);
    }

    /**
     * Setter for <code>verein_message.created_at</code>.
     */
    @Override
    public void setCreatedAt(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>verein_message.created_at</code>.
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>verein_message.created_by</code>.
     */
    @Override
    public void setCreatedBy(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>verein_message.created_by</code>.
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
        return VereinMessage.VEREIN_MESSAGE.ID;
    }

    @Override
    public Field<Long> field2() {
        return VereinMessage.VEREIN_MESSAGE.FK_VEREIN;
    }

    @Override
    public Field<String> field3() {
        return VereinMessage.VEREIN_MESSAGE.MESSAGE;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return VereinMessage.VEREIN_MESSAGE.CREATED_AT;
    }

    @Override
    public Field<String> field5() {
        return VereinMessage.VEREIN_MESSAGE.CREATED_BY;
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
        return getMessage();
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
        return getMessage();
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
    public VereinMessageRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public VereinMessageRecord value2(Long value) {
        setFkVerein(value);
        return this;
    }

    @Override
    public VereinMessageRecord value3(String value) {
        setMessage(value);
        return this;
    }

    @Override
    public VereinMessageRecord value4(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public VereinMessageRecord value5(String value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public VereinMessageRecord values(Long value1, Long value2, String value3, LocalDateTime value4, String value5) {
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
    public void from(IVereinMessage from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setMessage(from.getMessage());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IVereinMessage> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VereinMessageRecord
     */
    public VereinMessageRecord() {
        super(VereinMessage.VEREIN_MESSAGE);
    }

    /**
     * Create a detached, initialised VereinMessageRecord
     */
    public VereinMessageRecord(Long id, Long fkVerein, String message, LocalDateTime createdAt, String createdBy) {
        super(VereinMessage.VEREIN_MESSAGE);

        setId(id);
        setFkVerein(fkVerein);
        setMessage(message);
        setCreatedAt(createdAt);
        setCreatedBy(createdBy);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised VereinMessageRecord
     */
    public VereinMessageRecord(VereinMessagePojo value) {
        super(VereinMessage.VEREIN_MESSAGE);

        if (value != null) {
            setId(value.getId());
            setFkVerein(value.getFkVerein());
            setMessage(value.getMessage());
            setCreatedAt(value.getCreatedAt());
            setCreatedBy(value.getCreatedBy());
            resetChangedOnNotNull();
        }
    }
}
