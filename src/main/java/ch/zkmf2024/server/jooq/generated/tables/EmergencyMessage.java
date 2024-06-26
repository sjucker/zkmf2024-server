/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.EmergencyMessageRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.Collection;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class EmergencyMessage extends TableImpl<EmergencyMessageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>emergency_message</code>
     */
    public static final EmergencyMessage EMERGENCY_MESSAGE = new EmergencyMessage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EmergencyMessageRecord> getRecordType() {
        return EmergencyMessageRecord.class;
    }

    /**
     * The column <code>emergency_message.id</code>.
     */
    public final TableField<EmergencyMessageRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>emergency_message.header</code>.
     */
    public final TableField<EmergencyMessageRecord, String> HEADER = createField(DSL.name("header"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>emergency_message.message</code>.
     */
    public final TableField<EmergencyMessageRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>emergency_message.active</code>.
     */
    public final TableField<EmergencyMessageRecord, Boolean> ACTIVE = createField(DSL.name("active"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private EmergencyMessage(Name alias, Table<EmergencyMessageRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private EmergencyMessage(Name alias, Table<EmergencyMessageRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>emergency_message</code> table reference
     */
    public EmergencyMessage(String alias) {
        this(DSL.name(alias), EMERGENCY_MESSAGE);
    }

    /**
     * Create an aliased <code>emergency_message</code> table reference
     */
    public EmergencyMessage(Name alias) {
        this(alias, EMERGENCY_MESSAGE);
    }

    /**
     * Create a <code>emergency_message</code> table reference
     */
    public EmergencyMessage() {
        this(DSL.name("emergency_message"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<EmergencyMessageRecord, Long> getIdentity() {
        return (Identity<EmergencyMessageRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<EmergencyMessageRecord> getPrimaryKey() {
        return Keys.PK_EMERGENCY_MESSAGE;
    }

    @Override
    public EmergencyMessage as(String alias) {
        return new EmergencyMessage(DSL.name(alias), this);
    }

    @Override
    public EmergencyMessage as(Name alias) {
        return new EmergencyMessage(alias, this);
    }

    @Override
    public EmergencyMessage as(Table<?> alias) {
        return new EmergencyMessage(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public EmergencyMessage rename(String name) {
        return new EmergencyMessage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public EmergencyMessage rename(Name name) {
        return new EmergencyMessage(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public EmergencyMessage rename(Table<?> name) {
        return new EmergencyMessage(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public EmergencyMessage where(Condition condition) {
        return new EmergencyMessage(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public EmergencyMessage where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public EmergencyMessage where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public EmergencyMessage where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public EmergencyMessage where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public EmergencyMessage where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public EmergencyMessage where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public EmergencyMessage where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public EmergencyMessage whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public EmergencyMessage whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
