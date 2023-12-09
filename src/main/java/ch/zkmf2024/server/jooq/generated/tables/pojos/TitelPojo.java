/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.ITitel;

import java.math.BigDecimal;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class TitelPojo implements ITitel {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fkVerein;
    private String titelName;
    private String composer;
    private String arrangeur;
    private BigDecimal grad;
    private Integer durationInSeconds;
    private String modul;
    private String klasse;
    private String besetzung;
    private String infoModeration;
    private String schwierigkeitsgrad;

    public TitelPojo() {
    }

    public TitelPojo(ITitel value) {
        this.id = value.getId();
        this.fkVerein = value.getFkVerein();
        this.titelName = value.getTitelName();
        this.composer = value.getComposer();
        this.arrangeur = value.getArrangeur();
        this.grad = value.getGrad();
        this.durationInSeconds = value.getDurationInSeconds();
        this.modul = value.getModul();
        this.klasse = value.getKlasse();
        this.besetzung = value.getBesetzung();
        this.infoModeration = value.getInfoModeration();
        this.schwierigkeitsgrad = value.getSchwierigkeitsgrad();
    }

    public TitelPojo(
            Long id,
            Long fkVerein,
            String titelName,
            String composer,
            String arrangeur,
            BigDecimal grad,
            Integer durationInSeconds,
            String modul,
            String klasse,
            String besetzung,
            String infoModeration,
            String schwierigkeitsgrad
    ) {
        this.id = id;
        this.fkVerein = fkVerein;
        this.titelName = titelName;
        this.composer = composer;
        this.arrangeur = arrangeur;
        this.grad = grad;
        this.durationInSeconds = durationInSeconds;
        this.modul = modul;
        this.klasse = klasse;
        this.besetzung = besetzung;
        this.infoModeration = infoModeration;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    /**
     * Getter for <code>titel.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>titel.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>titel.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return this.fkVerein;
    }

    /**
     * Setter for <code>titel.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long fkVerein) {
        this.fkVerein = fkVerein;
    }

    /**
     * Getter for <code>titel.titel_name</code>.
     */
    @Override
    public String getTitelName() {
        return this.titelName;
    }

    /**
     * Setter for <code>titel.titel_name</code>.
     */
    @Override
    public void setTitelName(String titelName) {
        this.titelName = titelName;
    }

    /**
     * Getter for <code>titel.composer</code>.
     */
    @Override
    public String getComposer() {
        return this.composer;
    }

    /**
     * Setter for <code>titel.composer</code>.
     */
    @Override
    public void setComposer(String composer) {
        this.composer = composer;
    }

    /**
     * Getter for <code>titel.arrangeur</code>.
     */
    @Override
    public String getArrangeur() {
        return this.arrangeur;
    }

    /**
     * Setter for <code>titel.arrangeur</code>.
     */
    @Override
    public void setArrangeur(String arrangeur) {
        this.arrangeur = arrangeur;
    }

    /**
     * Getter for <code>titel.grad</code>.
     */
    @Override
    public BigDecimal getGrad() {
        return this.grad;
    }

    /**
     * Setter for <code>titel.grad</code>.
     */
    @Override
    public void setGrad(BigDecimal grad) {
        this.grad = grad;
    }

    /**
     * Getter for <code>titel.duration_in_seconds</code>.
     */
    @Override
    public Integer getDurationInSeconds() {
        return this.durationInSeconds;
    }

    /**
     * Setter for <code>titel.duration_in_seconds</code>.
     */
    @Override
    public void setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    /**
     * Getter for <code>titel.modul</code>.
     */
    @Override
    public String getModul() {
        return this.modul;
    }

    /**
     * Setter for <code>titel.modul</code>.
     */
    @Override
    public void setModul(String modul) {
        this.modul = modul;
    }

    /**
     * Getter for <code>titel.klasse</code>.
     */
    @Override
    public String getKlasse() {
        return this.klasse;
    }

    /**
     * Setter for <code>titel.klasse</code>.
     */
    @Override
    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    /**
     * Getter for <code>titel.besetzung</code>.
     */
    @Override
    public String getBesetzung() {
        return this.besetzung;
    }

    /**
     * Setter for <code>titel.besetzung</code>.
     */
    @Override
    public void setBesetzung(String besetzung) {
        this.besetzung = besetzung;
    }

    /**
     * Getter for <code>titel.info_moderation</code>.
     */
    @Override
    public String getInfoModeration() {
        return this.infoModeration;
    }

    /**
     * Setter for <code>titel.info_moderation</code>.
     */
    @Override
    public void setInfoModeration(String infoModeration) {
        this.infoModeration = infoModeration;
    }

    /**
     * Getter for <code>titel.schwierigkeitsgrad</code>.
     */
    @Override
    public String getSchwierigkeitsgrad() {
        return this.schwierigkeitsgrad;
    }

    /**
     * Setter for <code>titel.schwierigkeitsgrad</code>.
     */
    @Override
    public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TitelPojo other = (TitelPojo) obj;
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
        if (this.titelName == null) {
            if (other.titelName != null)
                return false;
        } else if (!this.titelName.equals(other.titelName))
            return false;
        if (this.composer == null) {
            if (other.composer != null)
                return false;
        } else if (!this.composer.equals(other.composer))
            return false;
        if (this.arrangeur == null) {
            if (other.arrangeur != null)
                return false;
        } else if (!this.arrangeur.equals(other.arrangeur))
            return false;
        if (this.grad == null) {
            if (other.grad != null)
                return false;
        } else if (!this.grad.equals(other.grad))
            return false;
        if (this.durationInSeconds == null) {
            if (other.durationInSeconds != null)
                return false;
        } else if (!this.durationInSeconds.equals(other.durationInSeconds))
            return false;
        if (this.modul == null) {
            if (other.modul != null)
                return false;
        } else if (!this.modul.equals(other.modul))
            return false;
        if (this.klasse == null) {
            if (other.klasse != null)
                return false;
        } else if (!this.klasse.equals(other.klasse))
            return false;
        if (this.besetzung == null) {
            if (other.besetzung != null)
                return false;
        } else if (!this.besetzung.equals(other.besetzung))
            return false;
        if (this.infoModeration == null) {
            if (other.infoModeration != null)
                return false;
        } else if (!this.infoModeration.equals(other.infoModeration))
            return false;
        if (this.schwierigkeitsgrad == null) {
            if (other.schwierigkeitsgrad != null)
                return false;
        } else if (!this.schwierigkeitsgrad.equals(other.schwierigkeitsgrad))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.fkVerein == null) ? 0 : this.fkVerein.hashCode());
        result = prime * result + ((this.titelName == null) ? 0 : this.titelName.hashCode());
        result = prime * result + ((this.composer == null) ? 0 : this.composer.hashCode());
        result = prime * result + ((this.arrangeur == null) ? 0 : this.arrangeur.hashCode());
        result = prime * result + ((this.grad == null) ? 0 : this.grad.hashCode());
        result = prime * result + ((this.durationInSeconds == null) ? 0 : this.durationInSeconds.hashCode());
        result = prime * result + ((this.modul == null) ? 0 : this.modul.hashCode());
        result = prime * result + ((this.klasse == null) ? 0 : this.klasse.hashCode());
        result = prime * result + ((this.besetzung == null) ? 0 : this.besetzung.hashCode());
        result = prime * result + ((this.infoModeration == null) ? 0 : this.infoModeration.hashCode());
        result = prime * result + ((this.schwierigkeitsgrad == null) ? 0 : this.schwierigkeitsgrad.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TitelPojo (");

        sb.append(id);
        sb.append(", ").append(fkVerein);
        sb.append(", ").append(titelName);
        sb.append(", ").append(composer);
        sb.append(", ").append(arrangeur);
        sb.append(", ").append(grad);
        sb.append(", ").append(durationInSeconds);
        sb.append(", ").append(modul);
        sb.append(", ").append(klasse);
        sb.append(", ").append(besetzung);
        sb.append(", ").append(infoModeration);
        sb.append(", ").append(schwierigkeitsgrad);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ITitel from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setTitelName(from.getTitelName());
        setComposer(from.getComposer());
        setArrangeur(from.getArrangeur());
        setGrad(from.getGrad());
        setDurationInSeconds(from.getDurationInSeconds());
        setModul(from.getModul());
        setKlasse(from.getKlasse());
        setBesetzung(from.getBesetzung());
        setInfoModeration(from.getInfoModeration());
        setSchwierigkeitsgrad(from.getSchwierigkeitsgrad());
    }

    @Override
    public <E extends ITitel> E into(E into) {
        into.from(this);
        return into;
    }
}
