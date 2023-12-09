/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public interface IJudge extends Serializable {

    /**
     * Setter for <code>judge.id</code>.
     */
    public void setId(Long value);

    /**
     * Getter for <code>judge.id</code>.
     */
    public Long getId();

    /**
     * Setter for <code>judge.email</code>.
     */
    public void setEmail(String value);

    /**
     * Getter for <code>judge.email</code>.
     */
    public String getEmail();

    /**
     * Setter for <code>judge.name</code>.
     */
    public void setName(String value);

    /**
     * Getter for <code>judge.name</code>.
     */
    public String getName();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IJudge
     */
    public void from(IJudge from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IJudge
     */
    public <E extends IJudge> E into(E into);
}
