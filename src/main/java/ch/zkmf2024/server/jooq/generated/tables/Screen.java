/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.ScreenRecord;
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

import java.util.Collection;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Screen extends TableImpl<ScreenRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>screen</code>
     */
    public static final Screen SCREEN = new Screen();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ScreenRecord> getRecordType() {
        return ScreenRecord.class;
    }

    /**
     * The column <code>screen.id</code>.
     */
    public final TableField<ScreenRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>screen.location_identifier</code>.
     */
    public final TableField<ScreenRecord, String> LOCATION_IDENTIFIER = createField(DSL.name("location_identifier"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>screen.header</code>.
     */
    public final TableField<ScreenRecord, String> HEADER = createField(DSL.name("header"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>screen.message</code>.
     */
    public final TableField<ScreenRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>screen.cloudflare_id</code>.
     */
    public final TableField<ScreenRecord, String> CLOUDFLARE_ID = createField(DSL.name("cloudflare_id"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>screen.active</code>.
     */
    public final TableField<ScreenRecord, Boolean> ACTIVE = createField(DSL.name("active"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private Screen(Name alias, Table<ScreenRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Screen(Name alias, Table<ScreenRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>screen</code> table reference
     */
    public Screen(String alias) {
        this(DSL.name(alias), SCREEN);
    }

    /**
     * Create an aliased <code>screen</code> table reference
     */
    public Screen(Name alias) {
        this(alias, SCREEN);
    }

    /**
     * Create a <code>screen</code> table reference
     */
    public Screen() {
        this(DSL.name("screen"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<ScreenRecord, Long> getIdentity() {
        return (Identity<ScreenRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ScreenRecord> getPrimaryKey() {
        return Keys.PK_SCREEN;
    }

    @Override
    public Screen as(String alias) {
        return new Screen(DSL.name(alias), this);
    }

    @Override
    public Screen as(Name alias) {
        return new Screen(alias, this);
    }

    @Override
    public Screen as(Table<?> alias) {
        return new Screen(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Screen rename(String name) {
        return new Screen(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Screen rename(Name name) {
        return new Screen(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Screen rename(Table<?> name) {
        return new Screen(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Screen where(Condition condition) {
        return new Screen(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Screen where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Screen where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Screen where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Screen where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Screen where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Screen where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Screen where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Screen whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Screen whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
