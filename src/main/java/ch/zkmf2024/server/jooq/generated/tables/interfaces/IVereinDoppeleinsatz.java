/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public interface IVereinDoppeleinsatz extends Serializable {

    /**
     * Setter for <code>verein_doppeleinsatz.id</code>.
     */
    public void setId(Long value);

    /**
     * Getter for <code>verein_doppeleinsatz.id</code>.
     */
    public Long getId();

    /**
     * Setter for <code>verein_doppeleinsatz.fk_verein</code>.
     */
    public void setFkVerein(Long value);

    /**
     * Getter for <code>verein_doppeleinsatz.fk_verein</code>.
     */
    public Long getFkVerein();

    /**
     * Setter for <code>verein_doppeleinsatz.fk_other_verein</code>.
     */
    public void setFkOtherVerein(Long value);

    /**
     * Getter for <code>verein_doppeleinsatz.fk_other_verein</code>.
     */
    public Long getFkOtherVerein();

    /**
     * Setter for <code>verein_doppeleinsatz.name</code>.
     */
    public void setName(String value);

    /**
     * Getter for <code>verein_doppeleinsatz.name</code>.
     */
    public String getName();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IVereinDoppeleinsatz
     */
    public void from(IVereinDoppeleinsatz from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IVereinDoppeleinsatz
     */
    public <E extends IVereinDoppeleinsatz> E into(E into);
}
