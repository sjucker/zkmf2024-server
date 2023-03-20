/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import ch.zkmf2024.server.jooq.generated.Keys;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.Arrays;
import java.util.List;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Verein extends TableImpl<VereinRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>verein</code>
     */
    public static final Verein VEREIN = new Verein();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VereinRecord> getRecordType() {
        return VereinRecord.class;
    }

    /**
     * The column <code>verein.id</code>.
     */
    public final TableField<VereinRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>verein.email</code>.
     */
    public final TableField<VereinRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>verein.praesident_kontakt_id</code>.
     */
    public final TableField<VereinRecord, Long> PRAESIDENT_KONTAKT_ID = createField(DSL.name("praesident_kontakt_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>verein.direktion_kontakt_id</code>.
     */
    public final TableField<VereinRecord, Long> DIREKTION_KONTAKT_ID = createField(DSL.name("direktion_kontakt_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>verein.vereinsname</code>.
     */
    public final TableField<VereinRecord, String> VEREINSNAME = createField(DSL.name("vereinsname"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>verein.adresse</code>.
     */
    public final TableField<VereinRecord, String> ADRESSE = createField(DSL.name("adresse"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>verein.plz</code>.
     */
    public final TableField<VereinRecord, Integer> PLZ = createField(DSL.name("plz"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>verein.ort</code>.
     */
    public final TableField<VereinRecord, String> ORT = createField(DSL.name("ort"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>verein.homepage</code>.
     */
    public final TableField<VereinRecord, String> HOMEPAGE = createField(DSL.name("homepage"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>verein.iban</code>.
     */
    public final TableField<VereinRecord, String> IBAN = createField(DSL.name("iban"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>verein.modula</code>.
     */
    public final TableField<VereinRecord, Boolean> MODULA = createField(DSL.name("modula"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.modulb</code>.
     */
    public final TableField<VereinRecord, Boolean> MODULB = createField(DSL.name("modulb"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.modulc</code>.
     */
    public final TableField<VereinRecord, Boolean> MODULC = createField(DSL.name("modulc"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.moduld</code>.
     */
    public final TableField<VereinRecord, Boolean> MODULD = createField(DSL.name("moduld"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.module</code>.
     */
    public final TableField<VereinRecord, Boolean> MODULE = createField(DSL.name("module"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.modulf</code>.
     */
    public final TableField<VereinRecord, Boolean> MODULF = createField(DSL.name("modulf"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.modulg</code>.
     */
    public final TableField<VereinRecord, Boolean> MODULG = createField(DSL.name("modulg"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.modulh</code>.
     */
    public final TableField<VereinRecord, Boolean> MODULH = createField(DSL.name("modulh"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.klasse_modula</code>.
     */
    public final TableField<VereinRecord, String> KLASSE_MODULA = createField(DSL.name("klasse_modula"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>verein.klasse_modulb</code>.
     */
    public final TableField<VereinRecord, String> KLASSE_MODULB = createField(DSL.name("klasse_modulb"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>verein.klasse_modulh</code>.
     */
    public final TableField<VereinRecord, String> KLASSE_MODULH = createField(DSL.name("klasse_modulh"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>verein.harmonie</code>.
     */
    public final TableField<VereinRecord, Boolean> HARMONIE = createField(DSL.name("harmonie"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.brass_band</code>.
     */
    public final TableField<VereinRecord, Boolean> BRASS_BAND = createField(DSL.name("brass_band"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.fanfare</code>.
     */
    public final TableField<VereinRecord, Boolean> FANFARE = createField(DSL.name("fanfare"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.tambouren</code>.
     */
    public final TableField<VereinRecord, Boolean> TAMBOUREN = createField(DSL.name("tambouren"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    /**
     * The column <code>verein.perkussionsensemble</code>.
     */
    public final TableField<VereinRecord, Boolean> PERKUSSIONSENSEMBLE = createField(DSL.name("perkussionsensemble"), SQLDataType.BIT.nullable(false).defaultValue(DSL.inline("b'0'", SQLDataType.BIT)), this, "");

    private Verein(Name alias, Table<VereinRecord> aliased) {
        this(alias, aliased, null);
    }

    private Verein(Name alias, Table<VereinRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>verein</code> table reference
     */
    public Verein(String alias) {
        this(DSL.name(alias), VEREIN);
    }

    /**
     * Create an aliased <code>verein</code> table reference
     */
    public Verein(Name alias) {
        this(alias, VEREIN);
    }

    /**
     * Create a <code>verein</code> table reference
     */
    public Verein() {
        this(DSL.name("verein"), null);
    }

    public <O extends Record> Verein(Table<O> child, ForeignKey<O, VereinRecord> key) {
        super(child, key, VEREIN);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<VereinRecord, Long> getIdentity() {
        return (Identity<VereinRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<VereinRecord> getPrimaryKey() {
        return Keys.KEY_VEREIN_PRIMARY;
    }

    @Override
    public List<ForeignKey<VereinRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_VEREIN_ON_PRAESIDENT_KONTAKT, Keys.FK_VEREIN_ON_DIREKTION_KONTAKT);
    }

    private transient Kontakt _fkVereinOnPraesidentKontakt;
    private transient Kontakt _fkVereinOnDirektionKontakt;

    /**
     * Get the implicit join path to the <code>zkmf2024.kontakt</code> table,
     * via the <code>FK_VEREIN_ON_PRAESIDENT_KONTAKT</code> key.
     */
    public Kontakt fkVereinOnPraesidentKontakt() {
        if (_fkVereinOnPraesidentKontakt == null)
            _fkVereinOnPraesidentKontakt = new Kontakt(this, Keys.FK_VEREIN_ON_PRAESIDENT_KONTAKT);

        return _fkVereinOnPraesidentKontakt;
    }

    /**
     * Get the implicit join path to the <code>zkmf2024.kontakt</code> table,
     * via the <code>FK_VEREIN_ON_DIREKTION_KONTAKT</code> key.
     */
    public Kontakt fkVereinOnDirektionKontakt() {
        if (_fkVereinOnDirektionKontakt == null)
            _fkVereinOnDirektionKontakt = new Kontakt(this, Keys.FK_VEREIN_ON_DIREKTION_KONTAKT);

        return _fkVereinOnDirektionKontakt;
    }

    @Override
    public Verein as(String alias) {
        return new Verein(DSL.name(alias), this);
    }

    @Override
    public Verein as(Name alias) {
        return new Verein(alias, this);
    }

    @Override
    public Verein as(Table<?> alias) {
        return new Verein(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Verein rename(String name) {
        return new Verein(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Verein rename(Name name) {
        return new Verein(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Verein rename(Table<?> name) {
        return new Verein(name.getQualifiedName(), null);
    }
}
