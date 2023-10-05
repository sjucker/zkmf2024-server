/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.HelperRegistrationRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function21;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row21;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class HelperRegistration extends TableImpl<HelperRegistrationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>helper_registration</code>
     */
    public static final HelperRegistration HELPER_REGISTRATION = new HelperRegistration();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HelperRegistrationRecord> getRecordType() {
        return HelperRegistrationRecord.class;
    }

    /**
     * The column <code>helper_registration.id</code>.
     */
    public final TableField<HelperRegistrationRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>helper_registration.vorname</code>.
     */
    public final TableField<HelperRegistrationRecord, String> VORNAME = createField(DSL.name("vorname"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>helper_registration.name</code>.
     */
    public final TableField<HelperRegistrationRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>helper_registration.email</code>.
     */
    public final TableField<HelperRegistrationRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>helper_registration.telefon</code>.
     */
    public final TableField<HelperRegistrationRecord, String> TELEFON = createField(DSL.name("telefon"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>helper_registration.comment</code>.
     */
    public final TableField<HelperRegistrationRecord, String> COMMENT = createField(DSL.name("comment"), SQLDataType.VARCHAR(1024), this, "");

    /**
     * The column <code>helper_registration.adresse</code>.
     */
    public final TableField<HelperRegistrationRecord, String> ADRESSE = createField(DSL.name("adresse"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>helper_registration.plz_ort</code>.
     */
    public final TableField<HelperRegistrationRecord, String> PLZ_ORT = createField(DSL.name("plz_ort"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>helper_registration.geburtsdatum</code>.
     */
    public final TableField<HelperRegistrationRecord, LocalDate> GEBURTSDATUM = createField(DSL.name("geburtsdatum"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>helper_registration.vereinszugehoerigkeit</code>.
     */
    public final TableField<HelperRegistrationRecord, String> VEREINSZUGEHOERIGKEIT = createField(DSL.name("vereinszugehoerigkeit"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.aufgaben</code>.
     */
    public final TableField<HelperRegistrationRecord, String> AUFGABEN = createField(DSL.name("aufgaben"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.anzahl_einsaetze</code>.
     */
    public final TableField<HelperRegistrationRecord, String> ANZAHL_EINSAETZE = createField(DSL.name("anzahl_einsaetze"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.einsatz_mittwoch</code>.
     */
    public final TableField<HelperRegistrationRecord, String> EINSATZ_MITTWOCH = createField(DSL.name("einsatz_mittwoch"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.einsatz_donnerstag</code>.
     */
    public final TableField<HelperRegistrationRecord, String> EINSATZ_DONNERSTAG = createField(DSL.name("einsatz_donnerstag"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.einsatz_freitag</code>.
     */
    public final TableField<HelperRegistrationRecord, String> EINSATZ_FREITAG = createField(DSL.name("einsatz_freitag"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.einsatz_samstag</code>.
     */
    public final TableField<HelperRegistrationRecord, String> EINSATZ_SAMSTAG = createField(DSL.name("einsatz_samstag"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.einsatz_sonntag</code>.
     */
    public final TableField<HelperRegistrationRecord, String> EINSATZ_SONNTAG = createField(DSL.name("einsatz_sonntag"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.einsatz_montag</code>.
     */
    public final TableField<HelperRegistrationRecord, String> EINSATZ_MONTAG = createField(DSL.name("einsatz_montag"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.einsatz_dienstag</code>.
     */
    public final TableField<HelperRegistrationRecord, String> EINSATZ_DIENSTAG = createField(DSL.name("einsatz_dienstag"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>helper_registration.groesse_shirt</code>.
     */
    public final TableField<HelperRegistrationRecord, String> GROESSE_SHIRT = createField(DSL.name("groesse_shirt"), SQLDataType.VARCHAR(24).nullable(false), this, "");

    /**
     * The column <code>helper_registration.registered_at</code>.
     */
    public final TableField<HelperRegistrationRecord, LocalDateTime> REGISTERED_AT = createField(DSL.name("registered_at"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private HelperRegistration(Name alias, Table<HelperRegistrationRecord> aliased) {
        this(alias, aliased, null);
    }

    private HelperRegistration(Name alias, Table<HelperRegistrationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>helper_registration</code> table reference
     */
    public HelperRegistration(String alias) {
        this(DSL.name(alias), HELPER_REGISTRATION);
    }

    /**
     * Create an aliased <code>helper_registration</code> table reference
     */
    public HelperRegistration(Name alias) {
        this(alias, HELPER_REGISTRATION);
    }

    /**
     * Create a <code>helper_registration</code> table reference
     */
    public HelperRegistration() {
        this(DSL.name("helper_registration"), null);
    }

    public <O extends Record> HelperRegistration(Table<O> child, ForeignKey<O, HelperRegistrationRecord> key) {
        super(child, key, HELPER_REGISTRATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<HelperRegistrationRecord, Long> getIdentity() {
        return (Identity<HelperRegistrationRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<HelperRegistrationRecord> getPrimaryKey() {
        return Keys.PK_HELPER_REGISTRATION;
    }

    @Override
    public List<UniqueKey<HelperRegistrationRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.HELPER_REGISTRATION_EMAIL_KEY);
    }

    @Override
    public HelperRegistration as(String alias) {
        return new HelperRegistration(DSL.name(alias), this);
    }

    @Override
    public HelperRegistration as(Name alias) {
        return new HelperRegistration(alias, this);
    }

    @Override
    public HelperRegistration as(Table<?> alias) {
        return new HelperRegistration(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public HelperRegistration rename(String name) {
        return new HelperRegistration(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public HelperRegistration rename(Name name) {
        return new HelperRegistration(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public HelperRegistration rename(Table<?> name) {
        return new HelperRegistration(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row21 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row21<Long, String, String, String, String, String, String, String, LocalDate, String, String, String, String, String, String, String, String, String, String, String, LocalDateTime> fieldsRow() {
        return (Row21) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function21<? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super LocalDate, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function21<? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super LocalDate, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
