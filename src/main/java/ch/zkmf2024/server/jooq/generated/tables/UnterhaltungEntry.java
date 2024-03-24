/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.enums.UnterhaltungEntryType;
import ch.zkmf2024.server.jooq.generated.tables.records.UnterhaltungEntryRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function10;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.SelectField;
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
import java.util.List;
import java.util.function.Function;

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
    public final TableField<UnterhaltungEntryRecord, UnterhaltungEntryType> ENTRY_TYPE = createField(DSL.name("entry_type"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(ch.zkmf2024.server.jooq.generated.enums.UnterhaltungEntryType.class), this, "");

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

    private UnterhaltungEntry(Name alias, Table<UnterhaltungEntryRecord> aliased) {
        this(alias, aliased, null);
    }

    private UnterhaltungEntry(Name alias, Table<UnterhaltungEntryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
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

    public <O extends Record> UnterhaltungEntry(Table<O> child, ForeignKey<O, UnterhaltungEntryRecord> key) {
        super(child, key, UNTERHALTUNG_ENTRY);
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

    private transient Location _location;

    /**
     * Get the implicit join path to the <code>public.location</code> table.
     */
    public Location location() {
        if (_location == null)
            _location = new Location(this, Keys.UNTERHALTUNG_ENTRY__FK_UNTERHALTUNG_LOCATION);

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

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Long, UnterhaltungEntryType, LocalDate, LocalTime, LocalTime, String, String, Long, String, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function10<? super Long, ? super UnterhaltungEntryType, ? super LocalDate, ? super LocalTime, ? super LocalTime, ? super String, ? super String, ? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function10<? super Long, ? super UnterhaltungEntryType, ? super LocalDate, ? super LocalTime, ? super LocalTime, ? super String, ? super String, ? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
