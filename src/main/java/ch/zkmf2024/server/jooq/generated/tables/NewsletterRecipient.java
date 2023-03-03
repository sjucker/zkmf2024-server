/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.NewsletterRecipientRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
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
import java.util.function.Function;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
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
    public final TableField<NewsletterRecipientRecord, LocalDateTime> SUBSCRIBED_AT = createField(DSL.name("subscribed_at"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>newsletter_recipient.unsubscribed_at</code>.
     */
    public final TableField<NewsletterRecipientRecord, LocalDateTime> UNSUBSCRIBED_AT = createField(DSL.name("unsubscribed_at"), SQLDataType.LOCALDATETIME(0), this, "");

    private NewsletterRecipient(Name alias, Table<NewsletterRecipientRecord> aliased) {
        this(alias, aliased, null);
    }

    private NewsletterRecipient(Name alias, Table<NewsletterRecipientRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
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

    public <O extends Record> NewsletterRecipient(Table<O> child, ForeignKey<O, NewsletterRecipientRecord> key) {
        super(child, key, NEWSLETTER_RECIPIENT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<NewsletterRecipientRecord> getPrimaryKey() {
        return Keys.KEY_NEWSLETTER_RECIPIENT_PRIMARY;
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

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super String, ? super String, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super String, ? super String, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
