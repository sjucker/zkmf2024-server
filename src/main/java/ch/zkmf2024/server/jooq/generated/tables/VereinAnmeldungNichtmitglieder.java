/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinAnmeldungNichtmitgliederRecord;
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
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinAnmeldungNichtmitglieder extends TableImpl<VereinAnmeldungNichtmitgliederRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>verein_anmeldung_nichtmitglieder</code>
     */
    public static final VereinAnmeldungNichtmitglieder VEREIN_ANMELDUNG_NICHTMITGLIEDER = new VereinAnmeldungNichtmitglieder();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VereinAnmeldungNichtmitgliederRecord> getRecordType() {
        return VereinAnmeldungNichtmitgliederRecord.class;
    }

    /**
     * The column <code>verein_anmeldung_nichtmitglieder.id</code>.
     */
    public final TableField<VereinAnmeldungNichtmitgliederRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>verein_anmeldung_nichtmitglieder.fk_verein</code>.
     */
    public final TableField<VereinAnmeldungNichtmitgliederRecord, Long> FK_VEREIN = createField(DSL.name("fk_verein"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>verein_anmeldung_nichtmitglieder.amount</code>.
     */
    public final TableField<VereinAnmeldungNichtmitgliederRecord, Integer> AMOUNT = createField(DSL.name("amount"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>verein_anmeldung_nichtmitglieder.instrument</code>.
     */
    public final TableField<VereinAnmeldungNichtmitgliederRecord, String> INSTRUMENT = createField(DSL.name("instrument"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private VereinAnmeldungNichtmitglieder(Name alias, Table<VereinAnmeldungNichtmitgliederRecord> aliased) {
        this(alias, aliased, null);
    }

    private VereinAnmeldungNichtmitglieder(Name alias, Table<VereinAnmeldungNichtmitgliederRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>verein_anmeldung_nichtmitglieder</code> table
     * reference
     */
    public VereinAnmeldungNichtmitglieder(String alias) {
        this(DSL.name(alias), VEREIN_ANMELDUNG_NICHTMITGLIEDER);
    }

    /**
     * Create an aliased <code>verein_anmeldung_nichtmitglieder</code> table
     * reference
     */
    public VereinAnmeldungNichtmitglieder(Name alias) {
        this(alias, VEREIN_ANMELDUNG_NICHTMITGLIEDER);
    }

    /**
     * Create a <code>verein_anmeldung_nichtmitglieder</code> table reference
     */
    public VereinAnmeldungNichtmitglieder() {
        this(DSL.name("verein_anmeldung_nichtmitglieder"), null);
    }

    public <O extends Record> VereinAnmeldungNichtmitglieder(Table<O> child, ForeignKey<O, VereinAnmeldungNichtmitgliederRecord> key) {
        super(child, key, VEREIN_ANMELDUNG_NICHTMITGLIEDER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<VereinAnmeldungNichtmitgliederRecord, Long> getIdentity() {
        return (Identity<VereinAnmeldungNichtmitgliederRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<VereinAnmeldungNichtmitgliederRecord> getPrimaryKey() {
        return Keys.PK_VEREIN_ANMELDUNG_NICHTMITGLIEDER;
    }

    @Override
    public List<ForeignKey<VereinAnmeldungNichtmitgliederRecord, ?>> getReferences() {
        return Arrays.asList(Keys.VEREIN_ANMELDUNG_NICHTMITGLIEDER__FK_VEREIN_ANMELDUNG_NICHTMITGLIEDER_VEREIN);
    }

    private transient Verein _verein;

    /**
     * Get the implicit join path to the <code>public.verein</code> table.
     */
    public Verein verein() {
        if (_verein == null)
            _verein = new Verein(this, Keys.VEREIN_ANMELDUNG_NICHTMITGLIEDER__FK_VEREIN_ANMELDUNG_NICHTMITGLIEDER_VEREIN);

        return _verein;
    }

    @Override
    public VereinAnmeldungNichtmitglieder as(String alias) {
        return new VereinAnmeldungNichtmitglieder(DSL.name(alias), this);
    }

    @Override
    public VereinAnmeldungNichtmitglieder as(Name alias) {
        return new VereinAnmeldungNichtmitglieder(alias, this);
    }

    @Override
    public VereinAnmeldungNichtmitglieder as(Table<?> alias) {
        return new VereinAnmeldungNichtmitglieder(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinAnmeldungNichtmitglieder rename(String name) {
        return new VereinAnmeldungNichtmitglieder(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinAnmeldungNichtmitglieder rename(Name name) {
        return new VereinAnmeldungNichtmitglieder(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinAnmeldungNichtmitglieder rename(Table<?> name) {
        return new VereinAnmeldungNichtmitglieder(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, Integer, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super Long, ? super Integer, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super Long, ? super Integer, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
