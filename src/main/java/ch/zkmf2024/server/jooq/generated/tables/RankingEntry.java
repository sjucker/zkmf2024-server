/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.Ranking.RankingPath;
import ch.zkmf2024.server.jooq.generated.tables.Verein.VereinPath;
import ch.zkmf2024.server.jooq.generated.tables.records.RankingEntryRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class RankingEntry extends TableImpl<RankingEntryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ranking_entry</code>
     */
    public static final RankingEntry RANKING_ENTRY = new RankingEntry();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RankingEntryRecord> getRecordType() {
        return RankingEntryRecord.class;
    }

    /**
     * The column <code>ranking_entry.fk_ranking</code>.
     */
    public final TableField<RankingEntryRecord, Long> FK_RANKING = createField(DSL.name("fk_ranking"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>ranking_entry.fk_verein</code>.
     */
    public final TableField<RankingEntryRecord, Long> FK_VEREIN = createField(DSL.name("fk_verein"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>ranking_entry.score</code>.
     */
    public final TableField<RankingEntryRecord, BigDecimal> SCORE = createField(DSL.name("score"), SQLDataType.NUMERIC(5, 2).nullable(false), this, "");

    /**
     * The column <code>ranking_entry.rank</code>.
     */
    public final TableField<RankingEntryRecord, Integer> RANK = createField(DSL.name("rank"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>ranking_entry.confirmed_by</code>.
     */
    public final TableField<RankingEntryRecord, String> CONFIRMED_BY = createField(DSL.name("confirmed_by"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>ranking_entry.confirmed_at</code>.
     */
    public final TableField<RankingEntryRecord, LocalDateTime> CONFIRMED_AT = createField(DSL.name("confirmed_at"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>ranking_entry.additional_info</code>.
     */
    public final TableField<RankingEntryRecord, String> ADDITIONAL_INFO = createField(DSL.name("additional_info"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>ranking_entry.day</code>.
     */
    public final TableField<RankingEntryRecord, LocalDate> DAY = createField(DSL.name("day"), SQLDataType.LOCALDATE.nullable(false), this, "");

    private RankingEntry(Name alias, Table<RankingEntryRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private RankingEntry(Name alias, Table<RankingEntryRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>ranking_entry</code> table reference
     */
    public RankingEntry(String alias) {
        this(DSL.name(alias), RANKING_ENTRY);
    }

    /**
     * Create an aliased <code>ranking_entry</code> table reference
     */
    public RankingEntry(Name alias) {
        this(alias, RANKING_ENTRY);
    }

    /**
     * Create a <code>ranking_entry</code> table reference
     */
    public RankingEntry() {
        this(DSL.name("ranking_entry"), null);
    }

    public <O extends Record> RankingEntry(Table<O> path, ForeignKey<O, RankingEntryRecord> childPath, InverseForeignKey<O, RankingEntryRecord> parentPath) {
        super(path, childPath, parentPath, RANKING_ENTRY);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class RankingEntryPath extends RankingEntry implements Path<RankingEntryRecord> {

        private static final long serialVersionUID = 1L;

        public <O extends Record> RankingEntryPath(Table<O> path, ForeignKey<O, RankingEntryRecord> childPath, InverseForeignKey<O, RankingEntryRecord> parentPath) {
            super(path, childPath, parentPath);
        }

        private RankingEntryPath(Name alias, Table<RankingEntryRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public RankingEntryPath as(String alias) {
            return new RankingEntryPath(DSL.name(alias), this);
        }

        @Override
        public RankingEntryPath as(Name alias) {
            return new RankingEntryPath(alias, this);
        }

        @Override
        public RankingEntryPath as(Table<?> alias) {
            return new RankingEntryPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<RankingEntryRecord> getPrimaryKey() {
        return Keys.PK_RANKING_ENTRY;
    }

    @Override
    public List<ForeignKey<RankingEntryRecord, ?>> getReferences() {
        return Arrays.asList(Keys.RANKING_ENTRY__FK_RANKING_ENTRY_RANKING, Keys.RANKING_ENTRY__FK_RANKING_ENTRY_VEREIN);
    }

    private transient RankingPath _ranking;

    /**
     * Get the implicit join path to the <code>public.ranking</code> table.
     */
    public RankingPath ranking() {
        if (_ranking == null)
            _ranking = new RankingPath(this, Keys.RANKING_ENTRY__FK_RANKING_ENTRY_RANKING, null);

        return _ranking;
    }

    private transient VereinPath _verein;

    /**
     * Get the implicit join path to the <code>public.verein</code> table.
     */
    public VereinPath verein() {
        if (_verein == null)
            _verein = new VereinPath(this, Keys.RANKING_ENTRY__FK_RANKING_ENTRY_VEREIN, null);

        return _verein;
    }

    @Override
    public RankingEntry as(String alias) {
        return new RankingEntry(DSL.name(alias), this);
    }

    @Override
    public RankingEntry as(Name alias) {
        return new RankingEntry(alias, this);
    }

    @Override
    public RankingEntry as(Table<?> alias) {
        return new RankingEntry(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public RankingEntry rename(String name) {
        return new RankingEntry(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RankingEntry rename(Name name) {
        return new RankingEntry(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public RankingEntry rename(Table<?> name) {
        return new RankingEntry(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public RankingEntry where(Condition condition) {
        return new RankingEntry(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public RankingEntry where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public RankingEntry where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public RankingEntry where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public RankingEntry where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public RankingEntry where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public RankingEntry where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public RankingEntry where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public RankingEntry whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public RankingEntry whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
