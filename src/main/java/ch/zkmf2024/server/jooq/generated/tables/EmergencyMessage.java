/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.EmergencyMessageRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.function.Function;

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
        this(alias, aliased, null);
    }

    private EmergencyMessage(Name alias, Table<EmergencyMessageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
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

    public <O extends Record> EmergencyMessage(Table<O> child, ForeignKey<O, EmergencyMessageRecord> key) {
        super(child, key, EMERGENCY_MESSAGE);
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

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, Boolean> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super String, ? super String, ? super Boolean, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super String, ? super String, ? super Boolean, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
