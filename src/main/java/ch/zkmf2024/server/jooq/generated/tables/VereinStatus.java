/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinStatusRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
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
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinStatus extends TableImpl<VereinStatusRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>verein_status</code>
     */
    public static final VereinStatus VEREIN_STATUS = new VereinStatus();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VereinStatusRecord> getRecordType() {
        return VereinStatusRecord.class;
    }

    /**
     * The column <code>verein_status.fk_verein</code>.
     */
    public final TableField<VereinStatusRecord, Long> FK_VEREIN = createField(DSL.name("fk_verein"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>verein_status.phase1</code>.
     */
    public final TableField<VereinStatusRecord, String> PHASE1 = createField(DSL.name("phase1"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.field(DSL.raw("'NEW'::character varying"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>verein_status.phase2</code>.
     */
    public final TableField<VereinStatusRecord, String> PHASE2 = createField(DSL.name("phase2"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.field(DSL.raw("'NEW'::character varying"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>verein_status.phase4</code>.
     */
    public final TableField<VereinStatusRecord, String> PHASE4 = createField(DSL.name("phase4"), SQLDataType.VARCHAR(255).nullable(false).defaultValue(DSL.field(DSL.raw("'NEW'::character varying"), SQLDataType.VARCHAR)), this, "");

    private VereinStatus(Name alias, Table<VereinStatusRecord> aliased) {
        this(alias, aliased, null);
    }

    private VereinStatus(Name alias, Table<VereinStatusRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>verein_status</code> table reference
     */
    public VereinStatus(String alias) {
        this(DSL.name(alias), VEREIN_STATUS);
    }

    /**
     * Create an aliased <code>verein_status</code> table reference
     */
    public VereinStatus(Name alias) {
        this(alias, VEREIN_STATUS);
    }

    /**
     * Create a <code>verein_status</code> table reference
     */
    public VereinStatus() {
        this(DSL.name("verein_status"), null);
    }

    public <O extends Record> VereinStatus(Table<O> child, ForeignKey<O, VereinStatusRecord> key) {
        super(child, key, VEREIN_STATUS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<VereinStatusRecord> getPrimaryKey() {
        return Keys.PK_VEREIN_STATUS;
    }

    @Override
    public List<ForeignKey<VereinStatusRecord, ?>> getReferences() {
        return Arrays.asList(Keys.VEREIN_STATUS__FK_VEREIN_STATUS);
    }

    private transient Verein _verein;

    /**
     * Get the implicit join path to the <code>public.verein</code> table.
     */
    public Verein verein() {
        if (_verein == null)
            _verein = new Verein(this, Keys.VEREIN_STATUS__FK_VEREIN_STATUS);

        return _verein;
    }

    @Override
    public VereinStatus as(String alias) {
        return new VereinStatus(DSL.name(alias), this);
    }

    @Override
    public VereinStatus as(Name alias) {
        return new VereinStatus(alias, this);
    }

    @Override
    public VereinStatus as(Table<?> alias) {
        return new VereinStatus(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinStatus rename(String name) {
        return new VereinStatus(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinStatus rename(Name name) {
        return new VereinStatus(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinStatus rename(Table<?> name) {
        return new VereinStatus(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
