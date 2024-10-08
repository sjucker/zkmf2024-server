/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.enums.UnterhaltungEntryType;
import ch.zkmf2024.server.jooq.generated.tables.Location.LocationPath;
import ch.zkmf2024.server.jooq.generated.tables.records.UnterhaltungEntryRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class UnterhaltungEntry extends TableImpl<UnterhaltungEntryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>unterhaltung_entry</code>
     */
    public static final UnterhaltungEntry UNTERHALTUNG_ENTRY = new UnterhaltungEntry();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UnterhaltungEntryRecord> getRecordType() {
        return UnterhaltungEntryRecord.class;
    }

    /**
     * The column <code>unterhaltung_entry.id</code>.
     */
    public final TableField<UnterhaltungEntryRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>unterhaltung_entry.entry_type</code>.
     */
    public final TableField<UnterhaltungEntryRecord, UnterhaltungEntryType> ENTRY_TYPE = createField(DSL.name("entry_type"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(UnterhaltungEntryType.class), this, "");

    /**
     * The column <code>unterhaltung_entry.date</code>.
     */
    public final TableField<UnterhaltungEntryRecord, LocalDate> DATE = createField(DSL.name("date"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>unterhaltung_entry.start_time</code>.
     */
    public final TableField<UnterhaltungEntryRecord, LocalTime> START_TIME = createField(DSL.name("start_time"), SQLDataType.LOCALTIME(6).nullable(false), this, "");

    /**
     * The column <code>unterhaltung_entry.end_time</code>.
     */
    public final TableField<UnterhaltungEntryRecord, LocalTime> END_TIME = createField(DSL.name("end_time"), SQLDataType.LOCALTIME(6), this, "");

    /**
     * The column <code>unterhaltung_entry.title</code>.
     */
    public final TableField<UnterhaltungEntryRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>unterhaltung_entry.subtitle</code>.
     */
    public final TableField<UnterhaltungEntryRecord, String> SUBTITLE = createField(DSL.name("subtitle"), SQLDataType.VARCHAR(1024), this, "");

    /**
     * The column <code>unterhaltung_entry.fk_location</code>.
     */
    public final TableField<UnterhaltungEntryRecord, Long> FK_LOCATION = createField(DSL.name("fk_location"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>unterhaltung_entry.cloudflare_id</code>.
     */
    public final TableField<UnterhaltungEntryRecord, String> CLOUDFLARE_ID = createField(DSL.name("cloudflare_id"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>unterhaltung_entry.text</code>.
     */
    public final TableField<UnterhaltungEntryRecord, String> TEXT = createField(DSL.name("text"), SQLDataType.VARCHAR(4096), this, "");

    /**
     * The column <code>unterhaltung_entry.identifier</code>.
     */
    public final TableField<UnterhaltungEntryRecord, String> IDENTIFIER = createField(DSL.name("identifier"), SQLDataType.VARCHAR(255), this, "");

    private UnterhaltungEntry(Name alias, Table<UnterhaltungEntryRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private UnterhaltungEntry(Name alias, Table<UnterhaltungEntryRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>unterhaltung_entry</code> table reference
     */
    public UnterhaltungEntry(String alias) {
        this(DSL.name(alias), UNTERHALTUNG_ENTRY);
    }

    /**
     * Create an aliased <code>unterhaltung_entry</code> table reference
     */
    public UnterhaltungEntry(Name alias) {
        this(alias, UNTERHALTUNG_ENTRY);
    }

    /**
     * Create a <code>unterhaltung_entry</code> table reference
     */
    public UnterhaltungEntry() {
        this(DSL.name("unterhaltung_entry"), null);
    }

    public <O extends Record> UnterhaltungEntry(Table<O> path, ForeignKey<O, UnterhaltungEntryRecord> childPath, InverseForeignKey<O, UnterhaltungEntryRecord> parentPath) {
        super(path, childPath, parentPath, UNTERHALTUNG_ENTRY);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class UnterhaltungEntryPath extends UnterhaltungEntry implements Path<UnterhaltungEntryRecord> {

        private static final long serialVersionUID = 1L;

        public <O extends Record> UnterhaltungEntryPath(Table<O> path, ForeignKey<O, UnterhaltungEntryRecord> childPath, InverseForeignKey<O, UnterhaltungEntryRecord> parentPath) {
            super(path, childPath, parentPath);
        }

        private UnterhaltungEntryPath(Name alias, Table<UnterhaltungEntryRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public UnterhaltungEntryPath as(String alias) {
            return new UnterhaltungEntryPath(DSL.name(alias), this);
        }

        @Override
        public UnterhaltungEntryPath as(Name alias) {
            return new UnterhaltungEntryPath(alias, this);
        }

        @Override
        public UnterhaltungEntryPath as(Table<?> alias) {
            return new UnterhaltungEntryPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<UnterhaltungEntryRecord, Long> getIdentity() {
        return (Identity<UnterhaltungEntryRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<UnterhaltungEntryRecord> getPrimaryKey() {
        return Keys.PK_UNTERHALTUNG_ENTRY;
    }

    @Override
    public List<ForeignKey<UnterhaltungEntryRecord, ?>> getReferences() {
        return Arrays.asList(Keys.UNTERHALTUNG_ENTRY__FK_UNTERHALTUNG_LOCATION);
    }

    private transient LocationPath _location;

    /**
     * Get the implicit join path to the <code>public.location</code> table.
     */
    public LocationPath location() {
        if (_location == null)
            _location = new LocationPath(this, Keys.UNTERHALTUNG_ENTRY__FK_UNTERHALTUNG_LOCATION, null);

        return _location;
    }

    @Override
    public UnterhaltungEntry as(String alias) {
        return new UnterhaltungEntry(DSL.name(alias), this);
    }

    @Override
    public UnterhaltungEntry as(Name alias) {
        return new UnterhaltungEntry(alias, this);
    }

    @Override
    public UnterhaltungEntry as(Table<?> alias) {
        return new UnterhaltungEntry(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public UnterhaltungEntry rename(String name) {
        return new UnterhaltungEntry(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UnterhaltungEntry rename(Name name) {
        return new UnterhaltungEntry(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public UnterhaltungEntry rename(Table<?> name) {
        return new UnterhaltungEntry(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UnterhaltungEntry where(Condition condition) {
        return new UnterhaltungEntry(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UnterhaltungEntry where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UnterhaltungEntry where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UnterhaltungEntry where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UnterhaltungEntry where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UnterhaltungEntry where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UnterhaltungEntry where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UnterhaltungEntry where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UnterhaltungEntry whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UnterhaltungEntry whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
