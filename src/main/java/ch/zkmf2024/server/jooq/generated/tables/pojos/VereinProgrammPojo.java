/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVereinProgramm;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class VereinProgrammPojo implements IVereinProgramm {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fkVerein;
    private String modul;
    private String titel;
    private String infoModeration;
    private Integer totalDurationInSeconds;

    public VereinProgrammPojo() {
    }

    public VereinProgrammPojo(IVereinProgramm value) {
        this.id = value.getId();
        this.fkVerein = value.getFkVerein();
        this.modul = value.getModul();
        this.titel = value.getTitel();
        this.infoModeration = value.getInfoModeration();
        this.totalDurationInSeconds = value.getTotalDurationInSeconds();
    }

    public VereinProgrammPojo(
            Long id,
            Long fkVerein,
            String modul,
            String titel,
            String infoModeration,
            Integer totalDurationInSeconds
    ) {
        this.id = id;
        this.fkVerein = fkVerein;
        this.modul = modul;
        this.titel = titel;
        this.infoModeration = infoModeration;
        this.totalDurationInSeconds = totalDurationInSeconds;
    }

    /**
     * Getter for <code>verein_programm.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>verein_programm.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>verein_programm.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return this.fkVerein;
    }

    /**
     * Setter for <code>verein_programm.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long fkVerein) {
        this.fkVerein = fkVerein;
    }

    /**
     * Getter for <code>verein_programm.modul</code>.
     */
    @Override
    public String getModul() {
        return this.modul;
    }

    /**
     * Setter for <code>verein_programm.modul</code>.
     */
    @Override
    public void setModul(String modul) {
        this.modul = modul;
    }

    /**
     * Getter for <code>verein_programm.titel</code>.
     */
    @Override
    public String getTitel() {
        return this.titel;
    }

    /**
     * Setter for <code>verein_programm.titel</code>.
     */
    @Override
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     * Getter for <code>verein_programm.info_moderation</code>.
     */
    @Override
    public String getInfoModeration() {
        return this.infoModeration;
    }

    /**
     * Setter for <code>verein_programm.info_moderation</code>.
     */
    @Override
    public void setInfoModeration(String infoModeration) {
        this.infoModeration = infoModeration;
    }

    /**
     * Getter for <code>verein_programm.total_duration_in_seconds</code>.
     */
    @Override
    public Integer getTotalDurationInSeconds() {
        return this.totalDurationInSeconds;
    }

    /**
     * Setter for <code>verein_programm.total_duration_in_seconds</code>.
     */
    @Override
    public void setTotalDurationInSeconds(Integer totalDurationInSeconds) {
        this.totalDurationInSeconds = totalDurationInSeconds;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VereinProgrammPojo other = (VereinProgrammPojo) obj;
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
        if (this.modul == null) {
            if (other.modul != null)
                return false;
        } else if (!this.modul.equals(other.modul))
            return false;
        if (this.titel == null) {
            if (other.titel != null)
                return false;
        } else if (!this.titel.equals(other.titel))
            return false;
        if (this.infoModeration == null) {
            if (other.infoModeration != null)
                return false;
        } else if (!this.infoModeration.equals(other.infoModeration))
            return false;
        if (this.totalDurationInSeconds == null) {
            if (other.totalDurationInSeconds != null)
                return false;
        } else if (!this.totalDurationInSeconds.equals(other.totalDurationInSeconds))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.fkVerein == null) ? 0 : this.fkVerein.hashCode());
        result = prime * result + ((this.modul == null) ? 0 : this.modul.hashCode());
        result = prime * result + ((this.titel == null) ? 0 : this.titel.hashCode());
        result = prime * result + ((this.infoModeration == null) ? 0 : this.infoModeration.hashCode());
        result = prime * result + ((this.totalDurationInSeconds == null) ? 0 : this.totalDurationInSeconds.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VereinProgrammPojo (");

        sb.append(id);
        sb.append(", ").append(fkVerein);
        sb.append(", ").append(modul);
        sb.append(", ").append(titel);
        sb.append(", ").append(infoModeration);
        sb.append(", ").append(totalDurationInSeconds);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IVereinProgramm from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setModul(from.getModul());
        setTitel(from.getTitel());
        setInfoModeration(from.getInfoModeration());
        setTotalDurationInSeconds(from.getTotalDurationInSeconds());
    }

    @Override
    public <E extends IVereinProgramm> E into(E into) {
        into.from(this);
        return into;
    }
}
