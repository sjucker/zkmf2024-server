/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Indexes;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinProgrammTitelRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row5;
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
public class VereinProgrammTitel extends TableImpl<VereinProgrammTitelRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>verein_programm_titel</code>
     */
    public static final VereinProgrammTitel VEREIN_PROGRAMM_TITEL = new VereinProgrammTitel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VereinProgrammTitelRecord> getRecordType() {
        return VereinProgrammTitelRecord.class;
    }

    /**
     * The column <code>verein_programm_titel.fk_programm</code>.
     */
    public final TableField<VereinProgrammTitelRecord, Long> FK_PROGRAMM = createField(DSL.name("fk_programm"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>verein_programm_titel.fk_titel</code>.
     */
    public final TableField<VereinProgrammTitelRecord, Long> FK_TITEL = createField(DSL.name("fk_titel"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>verein_programm_titel.position</code>.
     */
    public final TableField<VereinProgrammTitelRecord, Integer> POSITION = createField(DSL.name("position"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>verein_programm_titel.duration_in_seconds</code>.
     */
    public final TableField<VereinProgrammTitelRecord, Integer> DURATION_IN_SECONDS = createField(DSL.name("duration_in_seconds"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>verein_programm_titel.applaus_in_seconds</code>.
     */
    public final TableField<VereinProgrammTitelRecord, Integer> APPLAUS_IN_SECONDS = createField(DSL.name("applaus_in_seconds"), SQLDataType.INTEGER, this, "");

    private VereinProgrammTitel(Name alias, Table<VereinProgrammTitelRecord> aliased) {
        this(alias, aliased, null);
    }

    private VereinProgrammTitel(Name alias, Table<VereinProgrammTitelRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>verein_programm_titel</code> table reference
     */
    public VereinProgrammTitel(String alias) {
        this(DSL.name(alias), VEREIN_PROGRAMM_TITEL);
    }

    /**
     * Create an aliased <code>verein_programm_titel</code> table reference
     */
    public VereinProgrammTitel(Name alias) {
        this(alias, VEREIN_PROGRAMM_TITEL);
    }

    /**
     * Create a <code>verein_programm_titel</code> table reference
     */
    public VereinProgrammTitel() {
        this(DSL.name("verein_programm_titel"), null);
    }

    public <O extends Record> VereinProgrammTitel(Table<O> child, ForeignKey<O, VereinProgrammTitelRecord> key) {
        super(child, key, VEREIN_PROGRAMM_TITEL);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.VEREIN_PROGRAMM_TITEL_FK_TITEL);
    }

    @Override
    public UniqueKey<VereinProgrammTitelRecord> getPrimaryKey() {
        return Keys.KEY_VEREIN_PROGRAMM_TITEL_PRIMARY;
    }

    @Override
    public List<UniqueKey<VereinProgrammTitelRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_VEREIN_PROGRAMM_TITEL_UQ_VEREIN_PROGRAMM_TITEL);
    }

    @Override
    public List<ForeignKey<VereinProgrammTitelRecord, ?>> getReferences() {
        return Arrays.asList(Keys.VEREIN_PROGRAMM_TITEL_IBFK_1, Keys.VEREIN_PROGRAMM_TITEL_IBFK_2);
    }

    private transient VereinProgramm _vereinProgramm;
    private transient Titel _titel;

    /**
     * Get the implicit join path to the <code>mvurdorf10.verein_programm</code>
     * table.
     */
    public VereinProgramm vereinProgramm() {
        if (_vereinProgramm == null)
            _vereinProgramm = new VereinProgramm(this, Keys.VEREIN_PROGRAMM_TITEL_IBFK_1);

        return _vereinProgramm;
    }

    /**
     * Get the implicit join path to the <code>mvurdorf10.titel</code> table.
     */
    public Titel titel() {
        if (_titel == null)
            _titel = new Titel(this, Keys.VEREIN_PROGRAMM_TITEL_IBFK_2);

        return _titel;
    }

    @Override
    public VereinProgrammTitel as(String alias) {
        return new VereinProgrammTitel(DSL.name(alias), this);
    }

    @Override
    public VereinProgrammTitel as(Name alias) {
        return new VereinProgrammTitel(alias, this);
    }

    @Override
    public VereinProgrammTitel as(Table<?> alias) {
        return new VereinProgrammTitel(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinProgrammTitel rename(String name) {
        return new VereinProgrammTitel(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinProgrammTitel rename(Name name) {
        return new VereinProgrammTitel(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinProgrammTitel rename(Table<?> name) {
        return new VereinProgrammTitel(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, Integer, Integer, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Long, ? super Long, ? super Integer, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Long, ? super Long, ? super Integer, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
