/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.SponsorRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Identity;
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

import java.util.function.Function;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Sponsor extends TableImpl<SponsorRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>sponsor</code>
     */
    public static final Sponsor SPONSOR = new Sponsor();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SponsorRecord> getRecordType() {
        return SponsorRecord.class;
    }

    /**
     * The column <code>sponsor.id</code>.
     */
    public final TableField<SponsorRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>sponsor.type</code>.
     */
    public final TableField<SponsorRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>sponsor.name</code>.
     */
    public final TableField<SponsorRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>sponsor.cloudflare_id</code>.
     */
    public final TableField<SponsorRecord, String> CLOUDFLARE_ID = createField(DSL.name("cloudflare_id"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>sponsor.url</code>.
     */
    public final TableField<SponsorRecord, String> URL = createField(DSL.name("url"), SQLDataType.VARCHAR(255), this, "");

    private Sponsor(Name alias, Table<SponsorRecord> aliased) {
        this(alias, aliased, null);
    }

    private Sponsor(Name alias, Table<SponsorRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>sponsor</code> table reference
     */
    public Sponsor(String alias) {
        this(DSL.name(alias), SPONSOR);
    }

    /**
     * Create an aliased <code>sponsor</code> table reference
     */
    public Sponsor(Name alias) {
        this(alias, SPONSOR);
    }

    /**
     * Create a <code>sponsor</code> table reference
     */
    public Sponsor() {
        this(DSL.name("sponsor"), null);
    }

    public <O extends Record> Sponsor(Table<O> child, ForeignKey<O, SponsorRecord> key) {
        super(child, key, SPONSOR);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<SponsorRecord, Long> getIdentity() {
        return (Identity<SponsorRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<SponsorRecord> getPrimaryKey() {
        return Keys.PK_SPONSOR;
    }

    @Override
    public Sponsor as(String alias) {
        return new Sponsor(DSL.name(alias), this);
    }

    @Override
    public Sponsor as(Name alias) {
        return new Sponsor(alias, this);
    }

    @Override
    public Sponsor as(Table<?> alias) {
        return new Sponsor(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Sponsor rename(String name) {
        return new Sponsor(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Sponsor rename(Name name) {
        return new Sponsor(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Sponsor rename(Table<?> name) {
        return new Sponsor(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Long, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Long, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
