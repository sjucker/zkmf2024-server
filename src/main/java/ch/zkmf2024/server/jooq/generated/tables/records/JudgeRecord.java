/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.Judge;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IJudge;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgePojo;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class JudgeRecord extends UpdatableRecordImpl<JudgeRecord> implements Record3<Long, String, String>, IJudge {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>judge.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>judge.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>judge.email</code>.
     */
    @Override
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>judge.email</code>.
     */
    @Override
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>judge.name</code>.
     */
    @Override
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>judge.name</code>.
     */
    @Override
    public String getName() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Judge.JUDGE.ID;
    }

    @Override
    public Field<String> field2() {
        return Judge.JUDGE.EMAIL;
    }

    @Override
    public Field<String> field3() {
        return Judge.JUDGE.NAME;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getEmail();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getEmail();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public JudgeRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public JudgeRecord value2(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public JudgeRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public JudgeRecord values(Long value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IJudge from) {
        setId(from.getId());
        setEmail(from.getEmail());
        setName(from.getName());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IJudge> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JudgeRecord
     */
    public JudgeRecord() {
        super(Judge.JUDGE);
    }

    /**
     * Create a detached, initialised JudgeRecord
     */
    public JudgeRecord(Long id, String email, String name) {
        super(Judge.JUDGE);

        setId(id);
        setEmail(email);
        setName(name);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised JudgeRecord
     */
    public JudgeRecord(JudgePojo value) {
        super(Judge.JUDGE);

        if (value != null) {
            setId(value.getId());
            setEmail(value.getEmail());
            setName(value.getName());
            resetChangedOnNotNull();
        }
    }
}