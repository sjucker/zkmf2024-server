/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.Errata;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IErrata;
import ch.zkmf2024.server.jooq.generated.tables.pojos.ErrataPojo;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class ErrataRecord extends UpdatableRecordImpl<ErrataRecord> implements Record5<Long, String, String, String, String>, IErrata {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>errata.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>errata.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>errata.modul</code>.
     */
    @Override
    public void setModul(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>errata.modul</code>.
     */
    @Override
    public String getModul() {
        return (String) get(1);
    }

    /**
     * Setter for <code>errata.klasse</code>.
     */
    @Override
    public void setKlasse(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>errata.klasse</code>.
     */
    @Override
    public String getKlasse() {
        return (String) get(2);
    }

    /**
     * Setter for <code>errata.besetzung</code>.
     */
    @Override
    public void setBesetzung(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>errata.besetzung</code>.
     */
    @Override
    public String getBesetzung() {
        return (String) get(3);
    }

    /**
     * Setter for <code>errata.text</code>.
     */
    @Override
    public void setText(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>errata.text</code>.
     */
    @Override
    public String getText() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, String, String, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Errata.ERRATA.ID;
    }

    @Override
    public Field<String> field2() {
        return Errata.ERRATA.MODUL;
    }

    @Override
    public Field<String> field3() {
        return Errata.ERRATA.KLASSE;
    }

    @Override
    public Field<String> field4() {
        return Errata.ERRATA.BESETZUNG;
    }

    @Override
    public Field<String> field5() {
        return Errata.ERRATA.TEXT;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getModul();
    }

    @Override
    public String component3() {
        return getKlasse();
    }

    @Override
    public String component4() {
        return getBesetzung();
    }

    @Override
    public String component5() {
        return getText();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getModul();
    }

    @Override
    public String value3() {
        return getKlasse();
    }

    @Override
    public String value4() {
        return getBesetzung();
    }

    @Override
    public String value5() {
        return getText();
    }

    @Override
    public ErrataRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public ErrataRecord value2(String value) {
        setModul(value);
        return this;
    }

    @Override
    public ErrataRecord value3(String value) {
        setKlasse(value);
        return this;
    }

    @Override
    public ErrataRecord value4(String value) {
        setBesetzung(value);
        return this;
    }

    @Override
    public ErrataRecord value5(String value) {
        setText(value);
        return this;
    }

    @Override
    public ErrataRecord values(Long value1, String value2, String value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IErrata from) {
        setId(from.getId());
        setModul(from.getModul());
        setKlasse(from.getKlasse());
        setBesetzung(from.getBesetzung());
        setText(from.getText());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IErrata> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ErrataRecord
     */
    public ErrataRecord() {
        super(Errata.ERRATA);
    }

    /**
     * Create a detached, initialised ErrataRecord
     */
    public ErrataRecord(Long id, String modul, String klasse, String besetzung, String text) {
        super(Errata.ERRATA);

        setId(id);
        setModul(modul);
        setKlasse(klasse);
        setBesetzung(besetzung);
        setText(text);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ErrataRecord
     */
    public ErrataRecord(ErrataPojo value) {
        super(Errata.ERRATA);

        if (value != null) {
            setId(value.getId());
            setModul(value.getModul());
            setKlasse(value.getKlasse());
            setBesetzung(value.getBesetzung());
            setText(value.getText());
            resetChangedOnNotNull();
        }
    }
}
