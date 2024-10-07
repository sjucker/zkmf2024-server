/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Indexes;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType;
import ch.zkmf2024.server.jooq.generated.tables.CurrentlyPlaying.CurrentlyPlayingPath;
import ch.zkmf2024.server.jooq.generated.tables.JudgeReport.JudgeReportPath;
import ch.zkmf2024.server.jooq.generated.tables.Location.LocationPath;
import ch.zkmf2024.server.jooq.generated.tables.Verein.VereinPath;
import ch.zkmf2024.server.jooq.generated.tables.VereinProgramm.VereinProgrammPath;
import ch.zkmf2024.server.jooq.generated.tables.records.TimetableEntryRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
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
public class TimetableEntry extends TableImpl<TimetableEntryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>timetable_entry</code>
     */
    public static final TimetableEntry TIMETABLE_ENTRY = new TimetableEntry();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TimetableEntryRecord> getRecordType() {
        return TimetableEntryRecord.class;
    }

    /**
     * The column <code>timetable_entry.id</code>.
     */
    public final TableField<TimetableEntryRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>timetable_entry.fk_verein</code>.
     */
    public final TableField<TimetableEntryRecord, Long> FK_VEREIN = createField(DSL.name("fk_verein"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>timetable_entry.fk_verein_programm</code>.
     */
    public final TableField<TimetableEntryRecord, Long> FK_VEREIN_PROGRAMM = createField(DSL.name("fk_verein_programm"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>timetable_entry.fk_location</code>.
     */
    public final TableField<TimetableEntryRecord, Long> FK_LOCATION = createField(DSL.name("fk_location"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>timetable_entry.date</code>.
     */
    public final TableField<TimetableEntryRecord, LocalDate> DATE = createField(DSL.name("date"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>timetable_entry.start_time</code>.
     */
    public final TableField<TimetableEntryRecord, LocalTime> START_TIME = createField(DSL.name("start_time"), SQLDataType.LOCALTIME(6).nullable(false), this, "");

    /**
     * The column <code>timetable_entry.end_time</code>.
     */
    public final TableField<TimetableEntryRecord, LocalTime> END_TIME = createField(DSL.name("end_time"), SQLDataType.LOCALTIME(6).nullable(false), this, "");

    /**
     * The column <code>timetable_entry.entry_type</code>.
     */
    public final TableField<TimetableEntryRecord, TimetableEntryType> ENTRY_TYPE = createField(DSL.name("entry_type"), SQLDataType.VARCHAR.nullable(false).defaultValue(DSL.field(DSL.raw("'WETTSPIEL'::timetable_entry_type"), SQLDataType.VARCHAR)).asEnumDataType(TimetableEntryType.class), this, "");

    private TimetableEntry(Name alias, Table<TimetableEntryRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private TimetableEntry(Name alias, Table<TimetableEntryRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>timetable_entry</code> table reference
     */
    public TimetableEntry(String alias) {
        this(DSL.name(alias), TIMETABLE_ENTRY);
    }

    /**
     * Create an aliased <code>timetable_entry</code> table reference
     */
    public TimetableEntry(Name alias) {
        this(alias, TIMETABLE_ENTRY);
    }

    /**
     * Create a <code>timetable_entry</code> table reference
     */
    public TimetableEntry() {
        this(DSL.name("timetable_entry"), null);
    }

    public <O extends Record> TimetableEntry(Table<O> path, ForeignKey<O, TimetableEntryRecord> childPath, InverseForeignKey<O, TimetableEntryRecord> parentPath) {
        super(path, childPath, parentPath, TIMETABLE_ENTRY);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TimetableEntryPath extends TimetableEntry implements Path<TimetableEntryRecord> {

        private static final long serialVersionUID = 1L;

        public <O extends Record> TimetableEntryPath(Table<O> path, ForeignKey<O, TimetableEntryRecord> childPath, InverseForeignKey<O, TimetableEntryRecord> parentPath) {
            super(path, childPath, parentPath);
        }

        private TimetableEntryPath(Name alias, Table<TimetableEntryRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TimetableEntryPath as(String alias) {
            return new TimetableEntryPath(DSL.name(alias), this);
        }

        @Override
        public TimetableEntryPath as(Name alias) {
            return new TimetableEntryPath(alias, this);
        }

        @Override
        public TimetableEntryPath as(Table<?> alias) {
            return new TimetableEntryPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_FK_TIMETABLE_LOCATION, Indexes.IDX_FK_TIMETABLE_PROGRAMM);
    }

    @Override
    public Identity<TimetableEntryRecord, Long> getIdentity() {
        return (Identity<TimetableEntryRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<TimetableEntryRecord> getPrimaryKey() {
        return Keys.PK_TIMETABLE_ENTRY;
    }

    @Override
    public List<ForeignKey<TimetableEntryRecord, ?>> getReferences() {
        return Arrays.asList(Keys.TIMETABLE_ENTRY__FK_TIMETABLE_LOCATION, Keys.TIMETABLE_ENTRY__FK_TIMETABLE_PROGRAMM, Keys.TIMETABLE_ENTRY__FK_TIMETABLE_VEREIN);
    }

    private transient LocationPath _location;

    /**
     * Get the implicit join path to the <code>public.location</code> table.
     */
    public LocationPath location() {
        if (_location == null)
            _location = new LocationPath(this, Keys.TIMETABLE_ENTRY__FK_TIMETABLE_LOCATION, null);

        return _location;
    }

    private transient VereinProgrammPath _vereinProgramm;

    /**
     * Get the implicit join path to the <code>public.verein_programm</code>
     * table.
     */
    public VereinProgrammPath vereinProgramm() {
        if (_vereinProgramm == null)
            _vereinProgramm = new VereinProgrammPath(this, Keys.TIMETABLE_ENTRY__FK_TIMETABLE_PROGRAMM, null);

        return _vereinProgramm;
    }

    private transient VereinPath _verein;

    /**
     * Get the implicit join path to the <code>public.verein</code> table.
     */
    public VereinPath verein() {
        if (_verein == null)
            _verein = new VereinPath(this, Keys.TIMETABLE_ENTRY__FK_TIMETABLE_VEREIN, null);

        return _verein;
    }

    private transient CurrentlyPlayingPath _currentlyPlaying;

    /**
     * Get the implicit to-many join path to the
     * <code>public.currently_playing</code> table
     */
    public CurrentlyPlayingPath currentlyPlaying() {
        if (_currentlyPlaying == null)
            _currentlyPlaying = new CurrentlyPlayingPath(this, null, Keys.CURRENTLY_PLAYING__FK_TIMETABLE_ENTRY.getInverseKey());

        return _currentlyPlaying;
    }

    private transient JudgeReportPath _judgeReport;

    /**
     * Get the implicit to-many join path to the
     * <code>public.judge_report</code> table
     */
    public JudgeReportPath judgeReport() {
        if (_judgeReport == null)
            _judgeReport = new JudgeReportPath(this, null, Keys.JUDGE_REPORT__FK_JUDGE_REPORT_TIMETABLE.getInverseKey());

        return _judgeReport;
    }

    @Override
    public TimetableEntry as(String alias) {
        return new TimetableEntry(DSL.name(alias), this);
    }

    @Override
    public TimetableEntry as(Name alias) {
        return new TimetableEntry(alias, this);
    }

    @Override
    public TimetableEntry as(Table<?> alias) {
        return new TimetableEntry(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TimetableEntry rename(String name) {
        return new TimetableEntry(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TimetableEntry rename(Name name) {
        return new TimetableEntry(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TimetableEntry rename(Table<?> name) {
        return new TimetableEntry(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TimetableEntry where(Condition condition) {
        return new TimetableEntry(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TimetableEntry where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TimetableEntry where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TimetableEntry where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TimetableEntry where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TimetableEntry where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TimetableEntry where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TimetableEntry where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TimetableEntry whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TimetableEntry whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
