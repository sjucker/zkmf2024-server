/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.NewsletterRecipientRecord;
import org.jooq.Condition;
import org.jooq.Field;
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

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class NewsletterRecipient extends TableImpl<NewsletterRecipientRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>newsletter_recipient</code>
     */
    public static final NewsletterRecipient NEWSLETTER_RECIPIENT = new NewsletterRecipient();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<NewsletterRecipientRecord> getRecordType() {
        return NewsletterRecipientRecord.class;
    }

    /**
     * The column <code>newsletter_recipient.email</code>.
     */
    public final TableField<NewsletterRecipientRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>newsletter_recipient.vorname</code>.
     */
    public final TableField<NewsletterRecipientRecord, String> VORNAME = createField(DSL.name("vorname"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>newsletter_recipient.name</code>.
     */
    public final TableField<NewsletterRecipientRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>newsletter_recipient.subscribed_at</code>.
     */
    public final TableField<NewsletterRecipientRecord, LocalDateTime> SUBSCRIBED_AT = createField(DSL.name("subscribed_at"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>newsletter_recipient.unsubscribed_at</code>.
     */
    public final TableField<NewsletterRecipientRecord, LocalDateTime> UNSUBSCRIBED_AT = createField(DSL.name("unsubscribed_at"), SQLDataType.LOCALDATETIME(6), this, "");

    private NewsletterRecipient(Name alias, Table<NewsletterRecipientRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private NewsletterRecipient(Name alias, Table<NewsletterRecipientRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>newsletter_recipient</code> table reference
     */
    public NewsletterRecipient(String alias) {
        this(DSL.name(alias), NEWSLETTER_RECIPIENT);
    }

    /**
     * Create an aliased <code>newsletter_recipient</code> table reference
     */
    public NewsletterRecipient(Name alias) {
        this(alias, NEWSLETTER_RECIPIENT);
    }

    /**
     * Create a <code>newsletter_recipient</code> table reference
     */
    public NewsletterRecipient() {
        this(DSL.name("newsletter_recipient"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<NewsletterRecipientRecord> getPrimaryKey() {
        return Keys.PK_NEWSLETTER_RECIPIENT;
    }

    @Override
    public NewsletterRecipient as(String alias) {
        return new NewsletterRecipient(DSL.name(alias), this);
    }

    @Override
    public NewsletterRecipient as(Name alias) {
        return new NewsletterRecipient(alias, this);
    }

    @Override
    public NewsletterRecipient as(Table<?> alias) {
        return new NewsletterRecipient(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public NewsletterRecipient rename(String name) {
        return new NewsletterRecipient(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public NewsletterRecipient rename(Name name) {
        return new NewsletterRecipient(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public NewsletterRecipient rename(Table<?> name) {
        return new NewsletterRecipient(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public NewsletterRecipient where(Condition condition) {
        return new NewsletterRecipient(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public NewsletterRecipient where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public NewsletterRecipient where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public NewsletterRecipient where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public NewsletterRecipient where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public NewsletterRecipient where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public NewsletterRecipient where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public NewsletterRecipient where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public NewsletterRecipient whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public NewsletterRecipient whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
