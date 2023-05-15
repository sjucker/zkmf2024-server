/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.JudgeReportRecord;
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

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class JudgeReport extends TableImpl<JudgeReportRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>judge_report</code>
     */
    public static final JudgeReport JUDGE_REPORT = new JudgeReport();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JudgeReportRecord> getRecordType() {
        return JudgeReportRecord.class;
    }

    /**
     * The column <code>judge_report.id</code>.
     */
    public final TableField<JudgeReportRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>judge_report.fk_judge</code>.
     */
    public final TableField<JudgeReportRecord, Long> FK_JUDGE = createField(DSL.name("fk_judge"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>judge_report.fk_timetable_entry</code>.
     */
    public final TableField<JudgeReportRecord, Long> FK_TIMETABLE_ENTRY = createField(DSL.name("fk_timetable_entry"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>judge_report.score</code>.
     */
    public final TableField<JudgeReportRecord, Integer> SCORE = createField(DSL.name("score"), SQLDataType.INTEGER, this, "");

    private JudgeReport(Name alias, Table<JudgeReportRecord> aliased) {
        this(alias, aliased, null);
    }

    private JudgeReport(Name alias, Table<JudgeReportRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>judge_report</code> table reference
     */
    public JudgeReport(String alias) {
        this(DSL.name(alias), JUDGE_REPORT);
    }

    /**
     * Create an aliased <code>judge_report</code> table reference
     */
    public JudgeReport(Name alias) {
        this(alias, JUDGE_REPORT);
    }

    /**
     * Create a <code>judge_report</code> table reference
     */
    public JudgeReport() {
        this(DSL.name("judge_report"), null);
    }

    public <O extends Record> JudgeReport(Table<O> child, ForeignKey<O, JudgeReportRecord> key) {
        super(child, key, JUDGE_REPORT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<JudgeReportRecord, Long> getIdentity() {
        return (Identity<JudgeReportRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<JudgeReportRecord> getPrimaryKey() {
        return Keys.KEY_JUDGE_REPORT_PRIMARY;
    }

    @Override
    public List<UniqueKey<JudgeReportRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_JUDGE_REPORT_UQ_JUDGE_REPORT);
    }

    @Override
    public List<ForeignKey<JudgeReportRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_JUDGE_REPORT_JUDGE, Keys.FK_JUDGE_REPORT_TIMETABLE);
    }

    private transient Judge _judge;
    private transient TimetableEntry _timetableEntry;

    /**
     * Get the implicit join path to the <code>mvurdorf10.judge</code> table.
     */
    public Judge judge() {
        if (_judge == null)
            _judge = new Judge(this, Keys.FK_JUDGE_REPORT_JUDGE);

        return _judge;
    }

    /**
     * Get the implicit join path to the <code>mvurdorf10.timetable_entry</code>
     * table.
     */
    public TimetableEntry timetableEntry() {
        if (_timetableEntry == null)
            _timetableEntry = new TimetableEntry(this, Keys.FK_JUDGE_REPORT_TIMETABLE);

        return _timetableEntry;
    }

    @Override
    public JudgeReport as(String alias) {
        return new JudgeReport(DSL.name(alias), this);
    }

    @Override
    public JudgeReport as(Name alias) {
        return new JudgeReport(alias, this);
    }

    @Override
    public JudgeReport as(Table<?> alias) {
        return new JudgeReport(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public JudgeReport rename(String name) {
        return new JudgeReport(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JudgeReport rename(Name name) {
        return new JudgeReport(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public JudgeReport rename(Table<?> name) {
        return new JudgeReport(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, Long, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super Long, ? super Long, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super Long, ? super Long, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
