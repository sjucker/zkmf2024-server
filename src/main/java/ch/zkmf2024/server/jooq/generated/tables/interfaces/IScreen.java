/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public interface IScreen extends Serializable {

    /**
     * Setter for <code>screen.id</code>.
     */
    public void setId(Long value);

    /**
     * Getter for <code>screen.id</code>.
     */
    public Long getId();

    /**
     * Setter for <code>screen.location_identifier</code>.
     */
    public void setLocationIdentifier(String value);

    /**
     * Getter for <code>screen.location_identifier</code>.
     */
    public String getLocationIdentifier();

    /**
     * Setter for <code>screen.header</code>.
     */
    public void setHeader(String value);

    /**
     * Getter for <code>screen.header</code>.
     */
    public String getHeader();

    /**
     * Setter for <code>screen.message</code>.
     */
    public void setMessage(String value);

    /**
     * Getter for <code>screen.message</code>.
     */
    public String getMessage();

    /**
     * Setter for <code>screen.cloudflare_id</code>.
     */
    public void setCloudflareId(String value);

    /**
     * Getter for <code>screen.cloudflare_id</code>.
     */
    public String getCloudflareId();

    /**
     * Setter for <code>screen.active</code>.
     */
    public void setActive(Boolean value);

    /**
     * Getter for <code>screen.active</code>.
     */
    public Boolean getActive();

    /**
     * Setter for <code>screen.welcome</code>.
     */
    public void setWelcome(Boolean value);

    /**
     * Getter for <code>screen.welcome</code>.
     */
    public Boolean getWelcome();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IScreen
     */
    public void from(IScreen from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IScreen
     */
    public <E extends IScreen> E into(E into);
}
