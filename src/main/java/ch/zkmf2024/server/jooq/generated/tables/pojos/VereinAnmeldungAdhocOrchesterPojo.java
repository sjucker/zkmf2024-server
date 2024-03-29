/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVereinAnmeldungAdhocOrchester;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinAnmeldungAdhocOrchesterPojo implements IVereinAnmeldungAdhocOrchester {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fkVerein;
    private String name;
    private String email;
    private String instrument;

    public VereinAnmeldungAdhocOrchesterPojo() {
    }

    public VereinAnmeldungAdhocOrchesterPojo(IVereinAnmeldungAdhocOrchester value) {
        this.id = value.getId();
        this.fkVerein = value.getFkVerein();
        this.name = value.getName();
        this.email = value.getEmail();
        this.instrument = value.getInstrument();
    }

    public VereinAnmeldungAdhocOrchesterPojo(
            Long id,
            Long fkVerein,
            String name,
            String email,
            String instrument
    ) {
        this.id = id;
        this.fkVerein = fkVerein;
        this.name = name;
        this.email = email;
        this.instrument = instrument;
    }

    /**
     * Getter for <code>verein_anmeldung_adhoc_orchester.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>verein_anmeldung_adhoc_orchester.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>verein_anmeldung_adhoc_orchester.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return this.fkVerein;
    }

    /**
     * Setter for <code>verein_anmeldung_adhoc_orchester.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long fkVerein) {
        this.fkVerein = fkVerein;
    }

    /**
     * Getter for <code>verein_anmeldung_adhoc_orchester.name</code>.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>verein_anmeldung_adhoc_orchester.name</code>.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>verein_anmeldung_adhoc_orchester.email</code>.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>verein_anmeldung_adhoc_orchester.email</code>.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for <code>verein_anmeldung_adhoc_orchester.instrument</code>.
     */
    @Override
    public String getInstrument() {
        return this.instrument;
    }

    /**
     * Setter for <code>verein_anmeldung_adhoc_orchester.instrument</code>.
     */
    @Override
    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VereinAnmeldungAdhocOrchesterPojo other = (VereinAnmeldungAdhocOrchesterPojo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.fkVerein == null) {
            if (other.fkVerein != null)
                return false;
        } else if (!this.fkVerein.equals(other.fkVerein))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.email == null) {
            if (other.email != null)
                return false;
        } else if (!this.email.equals(other.email))
            return false;
        if (this.instrument == null) {
            if (other.instrument != null)
                return false;
        } else if (!this.instrument.equals(other.instrument))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.fkVerein == null) ? 0 : this.fkVerein.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.instrument == null) ? 0 : this.instrument.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VereinAnmeldungAdhocOrchesterPojo (");

        sb.append(id);
        sb.append(", ").append(fkVerein);
        sb.append(", ").append(name);
        sb.append(", ").append(email);
        sb.append(", ").append(instrument);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IVereinAnmeldungAdhocOrchester from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setName(from.getName());
        setEmail(from.getEmail());
        setInstrument(from.getInstrument());
    }

    @Override
    public <E extends IVereinAnmeldungAdhocOrchester> E into(E into) {
        into.from(this);
        return into;
    }
}
