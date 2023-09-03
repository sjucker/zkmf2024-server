/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVereinDoppeleinsatz;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class VereinDoppeleinsatzPojo implements IVereinDoppeleinsatz {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fkVerein;
    private Long fkOtherVerein;
    private String name;

    public VereinDoppeleinsatzPojo() {
    }

    public VereinDoppeleinsatzPojo(IVereinDoppeleinsatz value) {
        this.id = value.getId();
        this.fkVerein = value.getFkVerein();
        this.fkOtherVerein = value.getFkOtherVerein();
        this.name = value.getName();
    }

    public VereinDoppeleinsatzPojo(
            Long id,
            Long fkVerein,
            Long fkOtherVerein,
            String name
    ) {
        this.id = id;
        this.fkVerein = fkVerein;
        this.fkOtherVerein = fkOtherVerein;
        this.name = name;
    }

    /**
     * Getter for <code>verein_doppeleinsatz.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>verein_doppeleinsatz.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>verein_doppeleinsatz.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return this.fkVerein;
    }

    /**
     * Setter for <code>verein_doppeleinsatz.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long fkVerein) {
        this.fkVerein = fkVerein;
    }

    /**
     * Getter for <code>verein_doppeleinsatz.fk_other_verein</code>.
     */
    @Override
    public Long getFkOtherVerein() {
        return this.fkOtherVerein;
    }

    /**
     * Setter for <code>verein_doppeleinsatz.fk_other_verein</code>.
     */
    @Override
    public void setFkOtherVerein(Long fkOtherVerein) {
        this.fkOtherVerein = fkOtherVerein;
    }

    /**
     * Getter for <code>verein_doppeleinsatz.name</code>.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>verein_doppeleinsatz.name</code>.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VereinDoppeleinsatzPojo other = (VereinDoppeleinsatzPojo) obj;
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
        if (this.fkOtherVerein == null) {
            if (other.fkOtherVerein != null)
                return false;
        } else if (!this.fkOtherVerein.equals(other.fkOtherVerein))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.fkVerein == null) ? 0 : this.fkVerein.hashCode());
        result = prime * result + ((this.fkOtherVerein == null) ? 0 : this.fkOtherVerein.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VereinDoppeleinsatzPojo (");

        sb.append(id);
        sb.append(", ").append(fkVerein);
        sb.append(", ").append(fkOtherVerein);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IVereinDoppeleinsatz from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setFkOtherVerein(from.getFkOtherVerein());
        setName(from.getName());
    }

    @Override
    public <E extends IVereinDoppeleinsatz> E into(E into) {
        into.from(this);
        return into;
    }
}
