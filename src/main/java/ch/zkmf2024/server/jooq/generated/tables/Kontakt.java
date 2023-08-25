/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.KontaktRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function9;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row9;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Kontakt extends TableImpl<KontaktRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>kontakt</code>
     */
    public static final Kontakt KONTAKT = new Kontakt();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<KontaktRecord> getRecordType() {
        return KontaktRecord.class;
    }

    /**
     * The column <code>kontakt.id</code>.
     */
    public final TableField<KontaktRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>kontakt.vorname</code>.
     */
    public final TableField<KontaktRecord, String> VORNAME = createField(DSL.name("vorname"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>kontakt.nachname</code>.
     */
    public final TableField<KontaktRecord, String> NACHNAME = createField(DSL.name("nachname"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>kontakt.adresse</code>.
     */
    public final TableField<KontaktRecord, String> ADRESSE = createField(DSL.name("adresse"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>kontakt.plz</code>.
     */
    public final TableField<KontaktRecord, Integer> PLZ = createField(DSL.name("plz"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>kontakt.ort</code>.
     */
    public final TableField<KontaktRecord, String> ORT = createField(DSL.name("ort"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>kontakt.email</code>.
     */
    public final TableField<KontaktRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>kontakt.telefon_privat</code>.
     */
    public final TableField<KontaktRecord, String> TELEFON_PRIVAT = createField(DSL.name("telefon_privat"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>kontakt.telefon_mobile</code>.
     */
    public final TableField<KontaktRecord, String> TELEFON_MOBILE = createField(DSL.name("telefon_mobile"), SQLDataType.VARCHAR(255), this, "");

    private Kontakt(Name alias, Table<KontaktRecord> aliased) {
        this(alias, aliased, null);
    }

    private Kontakt(Name alias, Table<KontaktRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>kontakt</code> table reference
     */
    public Kontakt(String alias) {
        this(DSL.name(alias), KONTAKT);
    }

    /**
     * Create an aliased <code>kontakt</code> table reference
     */
    public Kontakt(Name alias) {
        this(alias, KONTAKT);
    }

    /**
     * Create a <code>kontakt</code> table reference
     */
    public Kontakt() {
        this(DSL.name("kontakt"), null);
    }

    public <O extends Record> Kontakt(Table<O> child, ForeignKey<O, KontaktRecord> key) {
        super(child, key, KONTAKT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<KontaktRecord, Long> getIdentity() {
        return (Identity<KontaktRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<KontaktRecord> getPrimaryKey() {
        return Keys.KEY_KONTAKT_PRIMARY;
    }

    @Override
    public Kontakt as(String alias) {
        return new Kontakt(DSL.name(alias), this);
    }

    @Override
    public Kontakt as(Name alias) {
        return new Kontakt(alias, this);
    }

    @Override
    public Kontakt as(Table<?> alias) {
        return new Kontakt(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Kontakt rename(String name) {
        return new Kontakt(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Kontakt rename(Name name) {
        return new Kontakt(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Kontakt rename(Table<?> name) {
        return new Kontakt(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, String, String, String, Integer, String, String, String, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function9<? super Long, ? super String, ? super String, ? super String, ? super Integer, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function9<? super Long, ? super String, ? super String, ? super String, ? super Integer, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
