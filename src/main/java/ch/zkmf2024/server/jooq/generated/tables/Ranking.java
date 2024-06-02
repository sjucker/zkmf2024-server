/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.Verein.VereinPath;
import ch.zkmf2024.server.jooq.generated.tables.records.RankingRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Ranking extends TableImpl<RankingRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ranking</code>
     */
    public static final Ranking RANKING = new Ranking();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RankingRecord> getRecordType() {
        return RankingRecord.class;
    }

    /**
     * The column <code>ranking.id</code>.
     */
    public final TableField<RankingRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>ranking.fk_verein</code>.
     */
    public final TableField<RankingRecord, Long> FK_VEREIN = createField(DSL.name("fk_verein"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>ranking.modul</code>.
     */
    public final TableField<RankingRecord, String> MODUL = createField(DSL.name("modul"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>ranking.klasse</code>.
     */
    public final TableField<RankingRecord, String> KLASSE = createField(DSL.name("klasse"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>ranking.besetzung</code>.
     */
    public final TableField<RankingRecord, String> BESETZUNG = createField(DSL.name("besetzung"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>ranking.category</code>.
     */
    public final TableField<RankingRecord, String> CATEGORY = createField(DSL.name("category"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>ranking.score</code>.
     */
    public final TableField<RankingRecord, BigDecimal> SCORE = createField(DSL.name("score"), SQLDataType.NUMERIC(5, 2).nullable(false), this, "");

    /**
     * The column <code>ranking.rank</code>.
     */
    public final TableField<RankingRecord, Integer> RANK = createField(DSL.name("rank"), SQLDataType.INTEGER.nullable(false), this, "");

    private Ranking(Name alias, Table<RankingRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Ranking(Name alias, Table<RankingRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>ranking</code> table reference
     */
    public Ranking(String alias) {
        this(DSL.name(alias), RANKING);
    }

    /**
     * Create an aliased <code>ranking</code> table reference
     */
    public Ranking(Name alias) {
        this(alias, RANKING);
    }

    /**
     * Create a <code>ranking</code> table reference
     */
    public Ranking() {
        this(DSL.name("ranking"), null);
    }

    public <O extends Record> Ranking(Table<O> path, ForeignKey<O, RankingRecord> childPath, InverseForeignKey<O, RankingRecord> parentPath) {
        super(path, childPath, parentPath, RANKING);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class RankingPath extends Ranking implements Path<RankingRecord> {

        private static final long serialVersionUID = 1L;

        public <O extends Record> RankingPath(Table<O> path, ForeignKey<O, RankingRecord> childPath, InverseForeignKey<O, RankingRecord> parentPath) {
            super(path, childPath, parentPath);
        }

        private RankingPath(Name alias, Table<RankingRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public RankingPath as(String alias) {
            return new RankingPath(DSL.name(alias), this);
        }

        @Override
        public RankingPath as(Name alias) {
            return new RankingPath(alias, this);
        }

        @Override
        public RankingPath as(Table<?> alias) {
            return new RankingPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<RankingRecord, Long> getIdentity() {
        return (Identity<RankingRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<RankingRecord> getPrimaryKey() {
        return Keys.PK_RANKING;
    }

    @Override
    public List<ForeignKey<RankingRecord, ?>> getReferences() {
        return Arrays.asList(Keys.RANKING__FK_RANKING_VEREIN);
    }

    private transient VereinPath _verein;

    /**
     * Get the implicit join path to the <code>public.verein</code> table.
     */
    public VereinPath verein() {
        if (_verein == null)
            _verein = new VereinPath(this, Keys.RANKING__FK_RANKING_VEREIN, null);

        return _verein;
    }

    @Override
    public Ranking as(String alias) {
        return new Ranking(DSL.name(alias), this);
    }

    @Override
    public Ranking as(Name alias) {
        return new Ranking(alias, this);
    }

    @Override
    public Ranking as(Table<?> alias) {
        return new Ranking(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Ranking rename(String name) {
        return new Ranking(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Ranking rename(Name name) {
        return new Ranking(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Ranking rename(Table<?> name) {
        return new Ranking(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Ranking where(Condition condition) {
        return new Ranking(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Ranking where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Ranking where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Ranking where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Ranking where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Ranking where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Ranking where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Ranking where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Ranking whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Ranking whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
