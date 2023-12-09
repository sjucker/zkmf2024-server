/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.JudgeRecord;
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

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Judge extends TableImpl<JudgeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>judge</code>
     */
    public static final Judge JUDGE = new Judge();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JudgeRecord> getRecordType() {
        return JudgeRecord.class;
    }

    /**
     * The column <code>judge.id</code>.
     */
    public final TableField<JudgeRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>judge.email</code>.
     */
    public final TableField<JudgeRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>judge.name</code>.
     */
    public final TableField<JudgeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private Judge(Name alias, Table<JudgeRecord> aliased) {
        this(alias, aliased, null);
    }

    private Judge(Name alias, Table<JudgeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>judge</code> table reference
     */
    public Judge(String alias) {
        this(DSL.name(alias), JUDGE);
    }

    /**
     * Create an aliased <code>judge</code> table reference
     */
    public Judge(Name alias) {
        this(alias, JUDGE);
    }

    /**
     * Create a <code>judge</code> table reference
     */
    public Judge() {
        this(DSL.name("judge"), null);
    }

    public <O extends Record> Judge(Table<O> child, ForeignKey<O, JudgeRecord> key) {
        super(child, key, JUDGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<JudgeRecord, Long> getIdentity() {
        return (Identity<JudgeRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<JudgeRecord> getPrimaryKey() {
        return Keys.PK_JUDGE;
    }

    @Override
    public List<UniqueKey<JudgeRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.JUDGE_EMAIL_KEY);
    }

    @Override
    public Judge as(String alias) {
        return new Judge(DSL.name(alias), this);
    }

    @Override
    public Judge as(Name alias) {
        return new Judge(alias, this);
    }

    @Override
    public Judge as(Table<?> alias) {
        return new Judge(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Judge rename(String name) {
        return new Judge(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Judge rename(Name name) {
        return new Judge(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Judge rename(Table<?> name) {
        return new Judge(name.getQualifiedName(), null);
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
