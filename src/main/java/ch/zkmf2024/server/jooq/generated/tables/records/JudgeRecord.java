/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.Judge;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IJudge;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgePojo;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class JudgeRecord extends UpdatableRecordImpl<JudgeRecord> implements IJudge {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>judge.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>judge.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>judge.email</code>.
     */
    @Override
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>judge.email</code>.
     */
    @Override
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>judge.name</code>.
     */
    @Override
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>judge.name</code>.
     */
    @Override
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>judge.first_name</code>.
     */
    @Override
    public void setFirstName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>judge.first_name</code>.
     */
    @Override
    public String getFirstName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>judge.modul</code>.
     */
    @Override
    public void setModul(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>judge.modul</code>.
     */
    @Override
    public String getModul() {
        return (String) get(4);
    }

    /**
     * Setter for <code>judge.cloudflare_id</code>.
     */
    @Override
    public void setCloudflareId(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>judge.cloudflare_id</code>.
     */
    @Override
    public String getCloudflareId() {
        return (String) get(5);
    }

    /**
     * Setter for <code>judge.presentation_text</code>.
     */
    @Override
    public void setPresentationText(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>judge.presentation_text</code>.
     */
    @Override
    public String getPresentationText() {
        return (String) get(6);
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
    public void from(IJudge from) {
        setId(from.getId());
        setEmail(from.getEmail());
        setName(from.getName());
        setFirstName(from.getFirstName());
        setModul(from.getModul());
        setCloudflareId(from.getCloudflareId());
        setPresentationText(from.getPresentationText());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IJudge> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JudgeRecord
     */
    public JudgeRecord() {
        super(Judge.JUDGE);
    }

    /**
     * Create a detached, initialised JudgeRecord
     */
    public JudgeRecord(Long id, String email, String name, String firstName, String modul, String cloudflareId, String presentationText) {
        super(Judge.JUDGE);

        setId(id);
        setEmail(email);
        setName(name);
        setFirstName(firstName);
        setModul(modul);
        setCloudflareId(cloudflareId);
        setPresentationText(presentationText);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised JudgeRecord
     */
    public JudgeRecord(JudgePojo value) {
        super(Judge.JUDGE);

        if (value != null) {
            setId(value.getId());
            setEmail(value.getEmail());
            setName(value.getName());
            setFirstName(value.getFirstName());
            setModul(value.getModul());
            setCloudflareId(value.getCloudflareId());
            setPresentationText(value.getPresentationText());
            resetChangedOnNotNull();
        }
    }
}
