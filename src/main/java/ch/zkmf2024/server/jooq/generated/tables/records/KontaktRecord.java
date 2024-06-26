/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.Kontakt;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IKontakt;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class KontaktRecord extends UpdatableRecordImpl<KontaktRecord> implements IKontakt {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>kontakt.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>kontakt.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>kontakt.vorname</code>.
     */
    @Override
    public void setVorname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>kontakt.vorname</code>.
     */
    @Override
    public String getVorname() {
        return (String) get(1);
    }

    /**
     * Setter for <code>kontakt.nachname</code>.
     */
    @Override
    public void setNachname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>kontakt.nachname</code>.
     */
    @Override
    public String getNachname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>kontakt.adresse</code>.
     */
    @Override
    public void setAdresse(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>kontakt.adresse</code>.
     */
    @Override
    public String getAdresse() {
        return (String) get(3);
    }

    /**
     * Setter for <code>kontakt.plz</code>.
     */
    @Override
    public void setPlz(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>kontakt.plz</code>.
     */
    @Override
    public Integer getPlz() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>kontakt.ort</code>.
     */
    @Override
    public void setOrt(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>kontakt.ort</code>.
     */
    @Override
    public String getOrt() {
        return (String) get(5);
    }

    /**
     * Setter for <code>kontakt.email</code>.
     */
    @Override
    public void setEmail(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>kontakt.email</code>.
     */
    @Override
    public String getEmail() {
        return (String) get(6);
    }

    /**
     * Setter for <code>kontakt.telefon_privat</code>.
     */
    @Override
    public void setTelefonPrivat(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>kontakt.telefon_privat</code>.
     */
    @Override
    public String getTelefonPrivat() {
        return (String) get(7);
    }

    /**
     * Setter for <code>kontakt.telefon_mobile</code>.
     */
    @Override
    public void setTelefonMobile(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>kontakt.telefon_mobile</code>.
     */
    @Override
    public String getTelefonMobile() {
        return (String) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IKontakt from) {
        setId(from.getId());
        setVorname(from.getVorname());
        setNachname(from.getNachname());
        setAdresse(from.getAdresse());
        setPlz(from.getPlz());
        setOrt(from.getOrt());
        setEmail(from.getEmail());
        setTelefonPrivat(from.getTelefonPrivat());
        setTelefonMobile(from.getTelefonMobile());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IKontakt> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached KontaktRecord
     */
    public KontaktRecord() {
        super(Kontakt.KONTAKT);
    }

    /**
     * Create a detached, initialised KontaktRecord
     */
    public KontaktRecord(Long id, String vorname, String nachname, String adresse, Integer plz, String ort, String email, String telefonPrivat, String telefonMobile) {
        super(Kontakt.KONTAKT);

        setId(id);
        setVorname(vorname);
        setNachname(nachname);
        setAdresse(adresse);
        setPlz(plz);
        setOrt(ort);
        setEmail(email);
        setTelefonPrivat(telefonPrivat);
        setTelefonMobile(telefonMobile);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised KontaktRecord
     */
    public KontaktRecord(KontaktPojo value) {
        super(Kontakt.KONTAKT);

        if (value != null) {
            setId(value.getId());
            setVorname(value.getVorname());
            setNachname(value.getNachname());
            setAdresse(value.getAdresse());
            setPlz(value.getPlz());
            setOrt(value.getOrt());
            setEmail(value.getEmail());
            setTelefonPrivat(value.getTelefonPrivat());
            setTelefonMobile(value.getTelefonMobile());
            resetChangedOnNotNull();
        }
    }
}
