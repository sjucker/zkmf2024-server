/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.Location;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.ILocation;
import ch.zkmf2024.server.jooq.generated.tables.pojos.LocationPojo;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import java.math.BigDecimal;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class LocationRecord extends UpdatableRecordImpl<LocationRecord> implements Record5<Long, String, String, BigDecimal, BigDecimal>, ILocation {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>location.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>location.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>location.name</code>.
     */
    @Override
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>location.name</code>.
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>location.address</code>.
     */
    @Override
    public void setAddress(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>location.address</code>.
     */
    @Override
    public String getAddress() {
        return (String) get(2);
    }

    /**
     * Setter for <code>location.latitude</code>.
     */
    @Override
    public void setLatitude(BigDecimal value) {
        set(3, value);
    }

    /**
     * Getter for <code>location.latitude</code>.
     */
    @Override
    public BigDecimal getLatitude() {
        return (BigDecimal) get(3);
    }

    /**
     * Setter for <code>location.longitude</code>.
     */
    @Override
    public void setLongitude(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>location.longitude</code>.
     */
    @Override
    public BigDecimal getLongitude() {
        return (BigDecimal) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, BigDecimal, BigDecimal> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, String, String, BigDecimal, BigDecimal> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Location.LOCATION.ID;
    }

    @Override
    public Field<String> field2() {
        return Location.LOCATION.NAME;
    }

    @Override
    public Field<String> field3() {
        return Location.LOCATION.ADDRESS;
    }

    @Override
    public Field<BigDecimal> field4() {
        return Location.LOCATION.LATITUDE;
    }

    @Override
    public Field<BigDecimal> field5() {
        return Location.LOCATION.LONGITUDE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getAddress();
    }

    @Override
    public BigDecimal component4() {
        return getLatitude();
    }

    @Override
    public BigDecimal component5() {
        return getLongitude();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getAddress();
    }

    @Override
    public BigDecimal value4() {
        return getLatitude();
    }

    @Override
    public BigDecimal value5() {
        return getLongitude();
    }

    @Override
    public LocationRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public LocationRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public LocationRecord value3(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public LocationRecord value4(BigDecimal value) {
        setLatitude(value);
        return this;
    }

    @Override
    public LocationRecord value5(BigDecimal value) {
        setLongitude(value);
        return this;
    }

    @Override
    public LocationRecord values(Long value1, String value2, String value3, BigDecimal value4, BigDecimal value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ILocation from) {
        setId(from.getId());
        setName(from.getName());
        setAddress(from.getAddress());
        setLatitude(from.getLatitude());
        setLongitude(from.getLongitude());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends ILocation> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LocationRecord
     */
    public LocationRecord() {
        super(Location.LOCATION);
    }

    /**
     * Create a detached, initialised LocationRecord
     */
    public LocationRecord(Long id, String name, String address, BigDecimal latitude, BigDecimal longitude) {
        super(Location.LOCATION);

        setId(id);
        setName(name);
        setAddress(address);
        setLatitude(latitude);
        setLongitude(longitude);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised LocationRecord
     */
    public LocationRecord(LocationPojo value) {
        super(Location.LOCATION);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setAddress(value.getAddress());
            setLatitude(value.getLatitude());
            setLongitude(value.getLongitude());
            resetChangedOnNotNull();
        }
    }
}
