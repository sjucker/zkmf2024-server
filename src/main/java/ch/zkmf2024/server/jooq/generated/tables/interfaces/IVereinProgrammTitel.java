/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public interface IVereinProgrammTitel extends Serializable {

    /**
     * Setter for <code>verein_programm_titel.fk_programm</code>.
     */
    public void setFkProgramm(Long value);

    /**
     * Getter for <code>verein_programm_titel.fk_programm</code>.
     */
    public Long getFkProgramm();

    /**
     * Setter for <code>verein_programm_titel.fk_titel</code>.
     */
    public void setFkTitel(Long value);

    /**
     * Getter for <code>verein_programm_titel.fk_titel</code>.
     */
    public Long getFkTitel();

    /**
     * Setter for <code>verein_programm_titel.position</code>.
     */
    public void setPosition(Integer value);

    /**
     * Getter for <code>verein_programm_titel.position</code>.
     */
    public Integer getPosition();

    /**
     * Setter for <code>verein_programm_titel.duration_in_seconds</code>.
     */
    public void setDurationInSeconds(Integer value);

    /**
     * Getter for <code>verein_programm_titel.duration_in_seconds</code>.
     */
    public Integer getDurationInSeconds();

    /**
     * Setter for <code>verein_programm_titel.applaus_in_seconds</code>.
     */
    public void setApplausInSeconds(Integer value);

    /**
     * Getter for <code>verein_programm_titel.applaus_in_seconds</code>.
     */
    public Integer getApplausInSeconds();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IVereinProgrammTitel
     */
    public void from(IVereinProgrammTitel from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IVereinProgrammTitel
     */
    public <E extends IVereinProgrammTitel> E into(E into);
}
