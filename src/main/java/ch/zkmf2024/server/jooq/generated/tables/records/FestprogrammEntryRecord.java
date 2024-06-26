/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.FestprogrammEntry;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IFestprogrammEntry;
import ch.zkmf2024.server.jooq.generated.tables.pojos.FestprogrammEntryPojo;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class FestprogrammEntryRecord extends UpdatableRecordImpl<FestprogrammEntryRecord> implements IFestprogrammEntry {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>festprogramm_entry.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>festprogramm_entry.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>festprogramm_entry.date</code>.
     */
    @Override
    public void setDate(LocalDate value) {
        set(1, value);
    }

    /**
     * Getter for <code>festprogramm_entry.date</code>.
     */
    @Override
    public LocalDate getDate() {
        return (LocalDate) get(1);
    }

    /**
     * Setter for <code>festprogramm_entry.start_time</code>.
     */
    @Override
    public void setStartTime(LocalTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>festprogramm_entry.start_time</code>.
     */
    @Override
    public LocalTime getStartTime() {
        return (LocalTime) get(2);
    }

    /**
     * Setter for <code>festprogramm_entry.description</code>.
     */
    @Override
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>festprogramm_entry.description</code>.
     */
    @Override
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>festprogramm_entry.location</code>.
     */
    @Override
    public void setLocation(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>festprogramm_entry.location</code>.
     */
    @Override
    public String getLocation() {
        return (String) get(4);
    }

    /**
     * Setter for <code>festprogramm_entry.important</code>.
     */
    @Override
    public void setImportant(Boolean value) {
        set(5, value);
    }

    /**
     * Getter for <code>festprogramm_entry.important</code>.
     */
    @Override
    public Boolean getImportant() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>festprogramm_entry.time_description</code>.
     */
    @Override
    public void setTimeDescription(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>festprogramm_entry.time_description</code>.
     */
    @Override
    public String getTimeDescription() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IFestprogrammEntry from) {
        setId(from.getId());
        setDate(from.getDate());
        setStartTime(from.getStartTime());
        setDescription(from.getDescription());
        setLocation(from.getLocation());
        setImportant(from.getImportant());
        setTimeDescription(from.getTimeDescription());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IFestprogrammEntry> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FestprogrammEntryRecord
     */
    public FestprogrammEntryRecord() {
        super(FestprogrammEntry.FESTPROGRAMM_ENTRY);
    }

    /**
     * Create a detached, initialised FestprogrammEntryRecord
     */
    public FestprogrammEntryRecord(Long id, LocalDate date, LocalTime startTime, String description, String location, Boolean important, String timeDescription) {
        super(FestprogrammEntry.FESTPROGRAMM_ENTRY);

        setId(id);
        setDate(date);
        setStartTime(startTime);
        setDescription(description);
        setLocation(location);
        setImportant(important);
        setTimeDescription(timeDescription);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised FestprogrammEntryRecord
     */
    public FestprogrammEntryRecord(FestprogrammEntryPojo value) {
        super(FestprogrammEntry.FESTPROGRAMM_ENTRY);

        if (value != null) {
            setId(value.getId());
            setDate(value.getDate());
            setStartTime(value.getStartTime());
            setDescription(value.getDescription());
            setLocation(value.getLocation());
            setImportant(value.getImportant());
            setTimeDescription(value.getTimeDescription());
            resetChangedOnNotNull();
        }
    }
}
