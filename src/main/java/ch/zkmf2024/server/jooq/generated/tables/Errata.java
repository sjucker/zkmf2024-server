/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.ErrataRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Errata extends TableImpl<ErrataRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>errata</code>
     */
    public static final Errata ERRATA = new Errata();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ErrataRecord> getRecordType() {
        return ErrataRecord.class;
    }

    /**
     * The column <code>errata.id</code>.
     */
    public final TableField<ErrataRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>errata.modul</code>.
     */
    public final TableField<ErrataRecord, String> MODUL = createField(DSL.name("modul"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>errata.klasse</code>.
     */
    public final TableField<ErrataRecord, String> KLASSE = createField(DSL.name("klasse"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>errata.besetzung</code>.
     */
    public final TableField<ErrataRecord, String> BESETZUNG = createField(DSL.name("besetzung"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>errata.text</code>.
     */
    public final TableField<ErrataRecord, String> TEXT = createField(DSL.name("text"), SQLDataType.CLOB, this, "");

    private Errata(Name alias, Table<ErrataRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Errata(Name alias, Table<ErrataRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>errata</code> table reference
     */
    public Errata(String alias) {
        this(DSL.name(alias), ERRATA);
    }

    /**
     * Create an aliased <code>errata</code> table reference
     */
    public Errata(Name alias) {
        this(alias, ERRATA);
    }

    /**
     * Create a <code>errata</code> table reference
     */
    public Errata() {
        this(DSL.name("errata"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<ErrataRecord, Long> getIdentity() {
        return (Identity<ErrataRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ErrataRecord> getPrimaryKey() {
        return Keys.PK_ERRATA;
    }

    @Override
    public List<UniqueKey<ErrataRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.UQ_ERRATA);
    }

    @Override
    public Errata as(String alias) {
        return new Errata(DSL.name(alias), this);
    }

    @Override
    public Errata as(Name alias) {
        return new Errata(alias, this);
    }

    @Override
    public Errata as(Table<?> alias) {
        return new Errata(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Errata rename(String name) {
        return new Errata(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Errata rename(Name name) {
        return new Errata(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Errata rename(Table<?> name) {
        return new Errata(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Errata where(Condition condition) {
        return new Errata(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Errata where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Errata where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Errata where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Errata where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Errata where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Errata where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Errata where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Errata whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Errata whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
