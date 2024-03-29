/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType;
import ch.zkmf2024.server.jooq.generated.tables.TimetableEntry;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.ITimetableEntry;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class TimetableEntryRecord extends UpdatableRecordImpl<TimetableEntryRecord> implements Record8<Long, Long, Long, Long, LocalDate, LocalTime, LocalTime, TimetableEntryType>, ITimetableEntry {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>timetable_entry.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>timetable_entry.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>timetable_entry.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>timetable_entry.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>timetable_entry.fk_verein_programm</code>.
     */
    @Override
    public void setFkVereinProgramm(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>timetable_entry.fk_verein_programm</code>.
     */
    @Override
    public Long getFkVereinProgramm() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>timetable_entry.fk_location</code>.
     */
    @Override
    public void setFkLocation(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>timetable_entry.fk_location</code>.
     */
    @Override
    public Long getFkLocation() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>timetable_entry.date</code>.
     */
    @Override
    public void setDate(LocalDate value) {
        set(4, value);
    }

    /**
     * Getter for <code>timetable_entry.date</code>.
     */
    @Override
    public LocalDate getDate() {
        return (LocalDate) get(4);
    }

    /**
     * Setter for <code>timetable_entry.start_time</code>.
     */
    @Override
    public void setStartTime(LocalTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>timetable_entry.start_time</code>.
     */
    @Override
    public LocalTime getStartTime() {
        return (LocalTime) get(5);
    }

    /**
     * Setter for <code>timetable_entry.end_time</code>.
     */
    @Override
    public void setEndTime(LocalTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>timetable_entry.end_time</code>.
     */
    @Override
    public LocalTime getEndTime() {
        return (LocalTime) get(6);
    }

    /**
     * Setter for <code>timetable_entry.entry_type</code>.
     */
    @Override
    public void setEntryType(TimetableEntryType value) {
        set(7, value);
    }

    /**
     * Getter for <code>timetable_entry.entry_type</code>.
     */
    @Override
    public TimetableEntryType getEntryType() {
        return (TimetableEntryType) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, Long, Long, Long, LocalDate, LocalTime, LocalTime, TimetableEntryType> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Long, Long, Long, Long, LocalDate, LocalTime, LocalTime, TimetableEntryType> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return TimetableEntry.TIMETABLE_ENTRY.ID;
    }

    @Override
    public Field<Long> field2() {
        return TimetableEntry.TIMETABLE_ENTRY.FK_VEREIN;
    }

    @Override
    public Field<Long> field3() {
        return TimetableEntry.TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM;
    }

    @Override
    public Field<Long> field4() {
        return TimetableEntry.TIMETABLE_ENTRY.FK_LOCATION;
    }

    @Override
    public Field<LocalDate> field5() {
        return TimetableEntry.TIMETABLE_ENTRY.DATE;
    }

    @Override
    public Field<LocalTime> field6() {
        return TimetableEntry.TIMETABLE_ENTRY.START_TIME;
    }

    @Override
    public Field<LocalTime> field7() {
        return TimetableEntry.TIMETABLE_ENTRY.END_TIME;
    }

    @Override
    public Field<TimetableEntryType> field8() {
        return TimetableEntry.TIMETABLE_ENTRY.ENTRY_TYPE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getFkVerein();
    }

    @Override
    public Long component3() {
        return getFkVereinProgramm();
    }

    @Override
    public Long component4() {
        return getFkLocation();
    }

    @Override
    public LocalDate component5() {
        return getDate();
    }

    @Override
    public LocalTime component6() {
        return getStartTime();
    }

    @Override
    public LocalTime component7() {
        return getEndTime();
    }

    @Override
    public TimetableEntryType component8() {
        return getEntryType();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getFkVerein();
    }

    @Override
    public Long value3() {
        return getFkVereinProgramm();
    }

    @Override
    public Long value4() {
        return getFkLocation();
    }

    @Override
    public LocalDate value5() {
        return getDate();
    }

    @Override
    public LocalTime value6() {
        return getStartTime();
    }

    @Override
    public LocalTime value7() {
        return getEndTime();
    }

    @Override
    public TimetableEntryType value8() {
        return getEntryType();
    }

    @Override
    public TimetableEntryRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value2(Long value) {
        setFkVerein(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value3(Long value) {
        setFkVereinProgramm(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value4(Long value) {
        setFkLocation(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value5(LocalDate value) {
        setDate(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value6(LocalTime value) {
        setStartTime(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value7(LocalTime value) {
        setEndTime(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value8(TimetableEntryType value) {
        setEntryType(value);
        return this;
    }

    @Override
    public TimetableEntryRecord values(Long value1, Long value2, Long value3, Long value4, LocalDate value5, LocalTime value6, LocalTime value7, TimetableEntryType value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ITimetableEntry from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setFkVereinProgramm(from.getFkVereinProgramm());
        setFkLocation(from.getFkLocation());
        setDate(from.getDate());
        setStartTime(from.getStartTime());
        setEndTime(from.getEndTime());
        setEntryType(from.getEntryType());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends ITimetableEntry> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TimetableEntryRecord
     */
    public TimetableEntryRecord() {
        super(TimetableEntry.TIMETABLE_ENTRY);
    }

    /**
     * Create a detached, initialised TimetableEntryRecord
     */
    public TimetableEntryRecord(Long id, Long fkVerein, Long fkVereinProgramm, Long fkLocation, LocalDate date, LocalTime startTime, LocalTime endTime, TimetableEntryType entryType) {
        super(TimetableEntry.TIMETABLE_ENTRY);

        setId(id);
        setFkVerein(fkVerein);
        setFkVereinProgramm(fkVereinProgramm);
        setFkLocation(fkLocation);
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
        setEntryType(entryType);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised TimetableEntryRecord
     */
    public TimetableEntryRecord(TimetableEntryPojo value) {
        super(TimetableEntry.TIMETABLE_ENTRY);

        if (value != null) {
            setId(value.getId());
            setFkVerein(value.getFkVerein());
            setFkVereinProgramm(value.getFkVereinProgramm());
            setFkLocation(value.getFkLocation());
            setDate(value.getDate());
            setStartTime(value.getStartTime());
            setEndTime(value.getEndTime());
            setEntryType(value.getEntryType());
            resetChangedOnNotNull();
        }
    }
}
