/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.AppPageRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.function.Function;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class AppPage extends TableImpl<AppPageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>app_page</code>
     */
    public static final AppPage APP_PAGE = new AppPage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AppPageRecord> getRecordType() {
        return AppPageRecord.class;
    }

    /**
     * The column <code>app_page.id</code>.
     */
    public final TableField<AppPageRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>app_page.markdown</code>.
     */
    public final TableField<AppPageRecord, String> MARKDOWN = createField(DSL.name("markdown"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>app_page.cloudflare_id</code>.
     */
    public final TableField<AppPageRecord, String> CLOUDFLARE_ID = createField(DSL.name("cloudflare_id"), SQLDataType.VARCHAR(255), this, "");

    private AppPage(Name alias, Table<AppPageRecord> aliased) {
        this(alias, aliased, null);
    }

    private AppPage(Name alias, Table<AppPageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>app_page</code> table reference
     */
    public AppPage(String alias) {
        this(DSL.name(alias), APP_PAGE);
    }

    /**
     * Create an aliased <code>app_page</code> table reference
     */
    public AppPage(Name alias) {
        this(alias, APP_PAGE);
    }

    /**
     * Create a <code>app_page</code> table reference
     */
    public AppPage() {
        this(DSL.name("app_page"), null);
    }

    public <O extends Record> AppPage(Table<O> child, ForeignKey<O, AppPageRecord> key) {
        super(child, key, APP_PAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<AppPageRecord, Long> getIdentity() {
        return (Identity<AppPageRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<AppPageRecord> getPrimaryKey() {
        return Keys.PK_APP_PAGE;
    }

    @Override
    public AppPage as(String alias) {
        return new AppPage(DSL.name(alias), this);
    }

    @Override
    public AppPage as(Name alias) {
        return new AppPage(alias, this);
    }

    @Override
    public AppPage as(Table<?> alias) {
        return new AppPage(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AppPage rename(String name) {
        return new AppPage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppPage rename(Name name) {
        return new AppPage(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppPage rename(Table<?> name) {
        return new AppPage(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
