/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.FestprogrammEntryRecord;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class FestprogrammEntry extends TableImpl<FestprogrammEntryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>festprogramm_entry</code>
     */
    public static final FestprogrammEntry FESTPROGRAMM_ENTRY = new FestprogrammEntry();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FestprogrammEntryRecord> getRecordType() {
        return FestprogrammEntryRecord.class;
    }

    /**
     * The column <code>festprogramm_entry.id</code>.
     */
    public final TableField<FestprogrammEntryRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>festprogramm_entry.date</code>.
     */
    public final TableField<FestprogrammEntryRecord, LocalDate> DATE = createField(DSL.name("date"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>festprogramm_entry.start_time</code>.
     */
    public final TableField<FestprogrammEntryRecord, LocalTime> START_TIME = createField(DSL.name("start_time"), SQLDataType.LOCALTIME(6).nullable(false), this, "");

    /**
     * The column <code>festprogramm_entry.description</code>.
     */
    public final TableField<FestprogrammEntryRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>festprogramm_entry.location</code>.
     */
    public final TableField<FestprogrammEntryRecord, String> LOCATION = createField(DSL.name("location"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>festprogramm_entry.important</code>.
     */
    public final TableField<FestprogrammEntryRecord, Boolean> IMPORTANT = createField(DSL.name("important"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>festprogramm_entry.time_description</code>.
     */
    public final TableField<FestprogrammEntryRecord, String> TIME_DESCRIPTION = createField(DSL.name("time_description"), SQLDataType.VARCHAR(255), this, "");

    private FestprogrammEntry(Name alias, Table<FestprogrammEntryRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private FestprogrammEntry(Name alias, Table<FestprogrammEntryRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>festprogramm_entry</code> table reference
     */
    public FestprogrammEntry(String alias) {
        this(DSL.name(alias), FESTPROGRAMM_ENTRY);
    }

    /**
     * Create an aliased <code>festprogramm_entry</code> table reference
     */
    public FestprogrammEntry(Name alias) {
        this(alias, FESTPROGRAMM_ENTRY);
    }

    /**
     * Create a <code>festprogramm_entry</code> table reference
     */
    public FestprogrammEntry() {
        this(DSL.name("festprogramm_entry"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<FestprogrammEntryRecord, Long> getIdentity() {
        return (Identity<FestprogrammEntryRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<FestprogrammEntryRecord> getPrimaryKey() {
        return Keys.PK_FESTPROGRAMM_ENTRY;
    }

    @Override
    public FestprogrammEntry as(String alias) {
        return new FestprogrammEntry(DSL.name(alias), this);
    }

    @Override
    public FestprogrammEntry as(Name alias) {
        return new FestprogrammEntry(alias, this);
    }

    @Override
    public FestprogrammEntry as(Table<?> alias) {
        return new FestprogrammEntry(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public FestprogrammEntry rename(String name) {
        return new FestprogrammEntry(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public FestprogrammEntry rename(Name name) {
        return new FestprogrammEntry(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public FestprogrammEntry rename(Table<?> name) {
        return new FestprogrammEntry(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FestprogrammEntry where(Condition condition) {
        return new FestprogrammEntry(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FestprogrammEntry where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FestprogrammEntry where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FestprogrammEntry where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public FestprogrammEntry where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public FestprogrammEntry where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public FestprogrammEntry where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public FestprogrammEntry where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FestprogrammEntry whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FestprogrammEntry whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
