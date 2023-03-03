/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVereinStatus;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class VereinStatusPojo implements IVereinStatus {

    private static final long serialVersionUID = 1L;

    private Long fkVerein;
    private String phase1;
    private String phase2;

    public VereinStatusPojo() {
    }

    public VereinStatusPojo(IVereinStatus value) {
        this.fkVerein = value.getFkVerein();
        this.phase1 = value.getPhase1();
        this.phase2 = value.getPhase2();
    }

    public VereinStatusPojo(
            Long fkVerein,
            String phase1,
            String phase2
    ) {
        this.fkVerein = fkVerein;
        this.phase1 = phase1;
        this.phase2 = phase2;
    }

    /**
     * Getter for <code>verein_status.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return this.fkVerein;
    }

    /**
     * Setter for <code>verein_status.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long fkVerein) {
        this.fkVerein = fkVerein;
    }

    /**
     * Getter for <code>verein_status.phase1</code>.
     */
    @Override
    public String getPhase1() {
        return this.phase1;
    }

    /**
     * Setter for <code>verein_status.phase1</code>.
     */
    @Override
    public void setPhase1(String phase1) {
        this.phase1 = phase1;
    }

    /**
     * Getter for <code>verein_status.phase2</code>.
     */
    @Override
    public String getPhase2() {
        return this.phase2;
    }

    /**
     * Setter for <code>verein_status.phase2</code>.
     */
    @Override
    public void setPhase2(String phase2) {
        this.phase2 = phase2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VereinStatusPojo other = (VereinStatusPojo) obj;
        if (this.fkVerein == null) {
            if (other.fkVerein != null)
                return false;
        } else if (!this.fkVerein.equals(other.fkVerein))
            return false;
        if (this.phase1 == null) {
            if (other.phase1 != null)
                return false;
        } else if (!this.phase1.equals(other.phase1))
            return false;
        if (this.phase2 == null) {
            if (other.phase2 != null)
                return false;
        } else if (!this.phase2.equals(other.phase2))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.fkVerein == null) ? 0 : this.fkVerein.hashCode());
        result = prime * result + ((this.phase1 == null) ? 0 : this.phase1.hashCode());
        result = prime * result + ((this.phase2 == null) ? 0 : this.phase2.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VereinStatusPojo (");

        sb.append(fkVerein);
        sb.append(", ").append(phase1);
        sb.append(", ").append(phase2);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IVereinStatus from) {
        setFkVerein(from.getFkVerein());
        setPhase1(from.getPhase1());
        setPhase2(from.getPhase2());
    }

    @Override
    public <E extends IVereinStatus> E into(E into) {
        into.from(this);
        return into;
    }
}
