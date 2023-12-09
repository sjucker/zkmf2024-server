/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Indexes;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinCommentRecord;
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
public class VereinComment extends TableImpl<VereinCommentRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>verein_comment</code>
     */
    public static final VereinComment VEREIN_COMMENT = new VereinComment();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VereinCommentRecord> getRecordType() {
        return VereinCommentRecord.class;
    }

    /**
     * The column <code>verein_comment.id</code>.
     */
    public final TableField<VereinCommentRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>verein_comment.fk_verein</code>.
     */
    public final TableField<VereinCommentRecord, Long> FK_VEREIN = createField(DSL.name("fk_verein"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>verein_comment.comment</code>.
     */
    public final TableField<VereinCommentRecord, String> COMMENT = createField(DSL.name("comment"), SQLDataType.VARCHAR(8000).nullable(false), this, "");

    /**
     * The column <code>verein_comment.created_at</code>.
     */
    public final TableField<VereinCommentRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>verein_comment.created_by</code>.
     */
    public final TableField<VereinCommentRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private VereinComment(Name alias, Table<VereinCommentRecord> aliased) {
        this(alias, aliased, null);
    }

    private VereinComment(Name alias, Table<VereinCommentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>verein_comment</code> table reference
     */
    public VereinComment(String alias) {
        this(DSL.name(alias), VEREIN_COMMENT);
    }

    /**
     * Create an aliased <code>verein_comment</code> table reference
     */
    public VereinComment(Name alias) {
        this(alias, VEREIN_COMMENT);
    }

    /**
     * Create a <code>verein_comment</code> table reference
     */
    public VereinComment() {
        this(DSL.name("verein_comment"), null);
    }

    public <O extends Record> VereinComment(Table<O> child, ForeignKey<O, VereinCommentRecord> key) {
        super(child, key, VEREIN_COMMENT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_FK_VEREIN_COMMENT_VEREIN);
    }

    @Override
    public Identity<VereinCommentRecord, Long> getIdentity() {
        return (Identity<VereinCommentRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<VereinCommentRecord> getPrimaryKey() {
        return Keys.PK_VEREIN_COMMENT;
    }

    @Override
    public List<ForeignKey<VereinCommentRecord, ?>> getReferences() {
        return Arrays.asList(Keys.VEREIN_COMMENT__FK_VEREIN_COMMENT_VEREIN);
    }

    private transient Verein _verein;

    /**
     * Get the implicit join path to the <code>public.verein</code> table.
     */
    public Verein verein() {
        if (_verein == null)
            _verein = new Verein(this, Keys.VEREIN_COMMENT__FK_VEREIN_COMMENT_VEREIN);

        return _verein;
    }

    @Override
    public VereinComment as(String alias) {
        return new VereinComment(DSL.name(alias), this);
    }

    @Override
    public VereinComment as(Name alias) {
        return new VereinComment(alias, this);
    }

    @Override
    public VereinComment as(Table<?> alias) {
        return new VereinComment(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinComment rename(String name) {
        return new VereinComment(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinComment rename(Name name) {
        return new VereinComment(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public VereinComment rename(Table<?> name) {
        return new VereinComment(name.getQualifiedName(), null);
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
