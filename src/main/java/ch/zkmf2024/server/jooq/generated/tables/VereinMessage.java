/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Indexes;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinMessageRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Identity;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinMessage extends TableImpl<VereinMessageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>verein_message</code>
     */
    public static final VereinMessage VEREIN_MESSAGE = new VereinMessage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VereinMessageRecord> getRecordType() {
        return VereinMessageRecord.class;
    }

    /**
     * The column <code>verein_message.id</code>.
     */
    public final TableField<VereinMessageRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>verein_message.fk_verein</code>.
     */
    public final TableField<VereinMessageRecord, Long> FK_VEREIN = createField(DSL.name("fk_verein"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>verein_message.message</code>.
     */
    public final TableField<VereinMessageRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.VARCHAR(8000).nullable(false), this, "");

    /**
     * The column <code>verein_message.created_at</code>.
     */
    public final TableField<VereinMessageRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>verein_message.created_by</code>.
     */
    public final TableField<VereinMessageRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private VereinMessage(Name alias, Table<VereinMessageRecord> aliased) {
        this(alias, aliased, null);
    }

    private VereinMessage(Name alias, Table<VereinMessageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>verein_message</code> table reference
     */
    public VereinMessage(String alias) {
        this(DSL.name(alias), VEREIN_MESSAGE);
    }

    /**
     * Create an aliased <code>verein_message</code> table reference
     */
    public VereinMessage(Name alias) {
        this(alias, VEREIN_MESSAGE);
    }

    /**
     * Create a <code>verein_message</code> table reference
     */
    public VereinMessage() {
        this(DSL.name("verein_message"), null);
    }

    public <O extends Record> VereinMessage(Table<O> child, ForeignKey<O, VereinMessageRecord> key) {
        super(child, key, VEREIN_MESSAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_FK_VEREIN_MESSAGE_VEREIN);
    }

    @Override
    public Identity<VereinMessageRecord, Long> getIdentity() {
        return (Identity<VereinMessageRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<VereinMessageRecord> getPrimaryKey() {
        return Keys.PK_VEREIN_MESSAGE;
    }

    @Override
    public List<ForeignKey<VereinMessageRecord, ?>> getReferences() {
        return Arrays.asList(Keys.VEREIN_MESSAGE__FK_VEREIN_MESSAGE_VEREIN);
    }

    private transient Verein _verein;

    /**
     * Get the implicit join path to the <code>public.verein</code> table.
     */
    public Verein verein() {
        if (_verein == null)
            _verein = new Verein(this, Keys.VEREIN_MESSAGE__FK_VEREIN_MESSAGE_VEREIN);

        return _verein;
    }

    @Override
    public VereinMessage as(String alias) {
        return new VereinMessage(DSL.name(alias), this);
    }

    @Override
    public VereinMessage as(Name alias) {
        return new VereinMessage(alias, this);
    }

    @Override
    public VereinMessage as(Table<?> alias) {
        return new VereinMessage(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinMessage rename(String name) {
        return new VereinMessage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinMessage rename(Name name) {
        return new VereinMessage(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinMessage rename(Table<?> name) {
        return new VereinMessage(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, String, LocalDateTime, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
