/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import ch.zkmf2024.server.jooq.generated.enums.LocationLocationType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public interface ILocation extends Serializable {

    /**
     * Setter for <code>location.id</code>.
     */
    public void setId(Long value);

    /**
     * Getter for <code>location.id</code>.
     */
    public Long getId();

    /**
     * Setter for <code>location.name</code>.
     */
    public void setName(String value);

    /**
     * Getter for <code>location.name</code>.
     */
    public String getName();

    /**
     * Setter for <code>location.address</code>.
     */
    public void setAddress(String value);

    /**
     * Getter for <code>location.address</code>.
     */
    public String getAddress();

    /**
     * Setter for <code>location.latitude</code>.
     */
    public void setLatitude(BigDecimal value);

    /**
     * Getter for <code>location.latitude</code>.
     */
    public BigDecimal getLatitude();

    /**
     * Setter for <code>location.longitude</code>.
     */
    public void setLongitude(BigDecimal value);

    /**
     * Getter for <code>location.longitude</code>.
     */
    public BigDecimal getLongitude();

    /**
     * Setter for <code>location.location_type</code>.
     */
    public void setLocationType(LocationLocationType value);

    /**
     * Getter for <code>location.location_type</code>.
     */
    public LocationLocationType getLocationType();

    /**
     * Setter for <code>location.capacity</code>.
     */
    public void setCapacity(String value);

    /**
     * Getter for <code>location.capacity</code>.
     */
    public String getCapacity();

    /**
     * Setter for <code>location.modules</code>.
     */
    public void setModules(String value);

    /**
     * Getter for <code>location.modules</code>.
     */
    public String getModules();

    /**
     * Setter for <code>location.einspiellokal_id</code>.
     */
    public void setEinspiellokalId(Long value);

    /**
     * Getter for <code>location.einspiellokal_id</code>.
     */
    public Long getEinspiellokalId();

    /**
     * Setter for <code>location.instrumentendepot_id</code>.
     */
    public void setInstrumentendepotId(Long value);

    /**
     * Getter for <code>location.instrumentendepot_id</code>.
     */
    public Long getInstrumentendepotId();

    /**
     * Setter for <code>location.juryfeedback_id</code>.
     */
    public void setJuryfeedbackId(Long value);

    /**
     * Getter for <code>location.juryfeedback_id</code>.
     */
    public Long getJuryfeedbackId();

    /**
     * Setter for <code>location.sort_order</code>.
     */
    public void setSortOrder(Integer value);

    /**
     * Getter for <code>location.sort_order</code>.
     */
    public Integer getSortOrder();

    /**
     * Setter for <code>location.identifier</code>.
     */
    public void setIdentifier(String value);

    /**
     * Getter for <code>location.identifier</code>.
     */
    public String getIdentifier();

    /**
     * Setter for <code>location.cloudflare_id</code>.
     */
    public void setCloudflareId(String value);

    /**
     * Getter for <code>location.cloudflare_id</code>.
     */
    public String getCloudflareId();

    /**
     * Setter for <code>location.kuula_id</code>.
     */
    public void setKuulaId(String value);

    /**
     * Getter for <code>location.kuula_id</code>.
     */
    public String getKuulaId();

    /**
     * Setter for <code>location.percussion_equipment</code>.
     */
    public void setPercussionEquipment(String value);

    /**
     * Getter for <code>location.percussion_equipment</code>.
     */
    public String getPercussionEquipment();

    /**
     * Setter for <code>location.map_id</code>.
     */
    public void setMapId(String value);

    /**
     * Getter for <code>location.map_id</code>.
     */
    public String getMapId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface ILocation
     */
    public void from(ILocation from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface ILocation
     */
    public <E extends ILocation> E into(E into);
}
