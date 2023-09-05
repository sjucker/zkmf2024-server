/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Indexes;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinDoppeleinsatzRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Identity;
import org.jooq.Index;
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
public class VereinDoppeleinsatz extends TableImpl<VereinDoppeleinsatzRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>verein_doppeleinsatz</code>
     */
    public static final VereinDoppeleinsatz VEREIN_DOPPELEINSATZ = new VereinDoppeleinsatz();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VereinDoppeleinsatzRecord> getRecordType() {
        return VereinDoppeleinsatzRecord.class;
    }

    /**
     * The column <code>verein_doppeleinsatz.id</code>.
     */
    public final TableField<VereinDoppeleinsatzRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>verein_doppeleinsatz.fk_verein</code>.
     */
    public final TableField<VereinDoppeleinsatzRecord, Long> FK_VEREIN = createField(DSL.name("fk_verein"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>verein_doppeleinsatz.fk_other_verein</code>.
     */
    public final TableField<VereinDoppeleinsatzRecord, Long> FK_OTHER_VEREIN = createField(DSL.name("fk_other_verein"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>verein_doppeleinsatz.name</code>.
     */
    public final TableField<VereinDoppeleinsatzRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private VereinDoppeleinsatz(Name alias, Table<VereinDoppeleinsatzRecord> aliased) {
        this(alias, aliased, null);
    }

    private VereinDoppeleinsatz(Name alias, Table<VereinDoppeleinsatzRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>verein_doppeleinsatz</code> table reference
     */
    public VereinDoppeleinsatz(String alias) {
        this(DSL.name(alias), VEREIN_DOPPELEINSATZ);
    }

    /**
     * Create an aliased <code>verein_doppeleinsatz</code> table reference
     */
    public VereinDoppeleinsatz(Name alias) {
        this(alias, VEREIN_DOPPELEINSATZ);
    }

    /**
     * Create a <code>verein_doppeleinsatz</code> table reference
     */
    public VereinDoppeleinsatz() {
        this(DSL.name("verein_doppeleinsatz"), null);
    }

    public <O extends Record> VereinDoppeleinsatz(Table<O> child, ForeignKey<O, VereinDoppeleinsatzRecord> key) {
        super(child, key, VEREIN_DOPPELEINSATZ);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.VEREIN_DOPPELEINSATZ_FK_OTHER_VEREIN, Indexes.VEREIN_DOPPELEINSATZ_FK_VEREIN);
    }

    @Override
    public Identity<VereinDoppeleinsatzRecord, Long> getIdentity() {
        return (Identity<VereinDoppeleinsatzRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<VereinDoppeleinsatzRecord> getPrimaryKey() {
        return Keys.KEY_VEREIN_DOPPELEINSATZ_PRIMARY;
    }

    @Override
    public List<ForeignKey<VereinDoppeleinsatzRecord, ?>> getReferences() {
        return Arrays.asList(Keys.VEREIN_DOPPELEINSATZ_IBFK_1, Keys.VEREIN_DOPPELEINSATZ_IBFK_2);
    }

    private transient Verein _vereinDoppeleinsatzIbfk_1;
    private transient Verein _vereinDoppeleinsatzIbfk_2;

    /**
     * Get the implicit join path to the <code>zkmf2024.verein</code> table, via
     * the <code>verein_doppeleinsatz_ibfk_1</code> key.
     */
    public Verein vereinDoppeleinsatzIbfk_1() {
        if (_vereinDoppeleinsatzIbfk_1 == null)
            _vereinDoppeleinsatzIbfk_1 = new Verein(this, Keys.VEREIN_DOPPELEINSATZ_IBFK_1);

        return _vereinDoppeleinsatzIbfk_1;
    }

    /**
     * Get the implicit join path to the <code>zkmf2024.verein</code> table, via
     * the <code>verein_doppeleinsatz_ibfk_2</code> key.
     */
    public Verein vereinDoppeleinsatzIbfk_2() {
        if (_vereinDoppeleinsatzIbfk_2 == null)
            _vereinDoppeleinsatzIbfk_2 = new Verein(this, Keys.VEREIN_DOPPELEINSATZ_IBFK_2);

        return _vereinDoppeleinsatzIbfk_2;
    }

    @Override
    public VereinDoppeleinsatz as(String alias) {
        return new VereinDoppeleinsatz(DSL.name(alias), this);
    }

    @Override
    public VereinDoppeleinsatz as(Name alias) {
        return new VereinDoppeleinsatz(alias, this);
    }

    @Override
    public VereinDoppeleinsatz as(Table<?> alias) {
        return new VereinDoppeleinsatz(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinDoppeleinsatz rename(String name) {
        return new VereinDoppeleinsatz(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinDoppeleinsatz rename(Name name) {
        return new VereinDoppeleinsatz(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinDoppeleinsatz rename(Table<?> name) {
        return new VereinDoppeleinsatz(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, Long, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super Long, ? super Long, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super Long, ? super Long, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}