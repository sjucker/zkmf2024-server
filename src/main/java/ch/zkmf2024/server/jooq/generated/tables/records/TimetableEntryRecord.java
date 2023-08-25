/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.TimetableEntry;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.ITimetableEntry;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class TimetableEntryRecord extends UpdatableRecordImpl<TimetableEntryRecord> implements Record9<Long, Long, String, String, String, Long, LocalDate, LocalTime, LocalTime>, ITimetableEntry {

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
     * Setter for <code>timetable_entry.modul</code>.
     */
    @Override
    public void setModul(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>timetable_entry.modul</code>.
     */
    @Override
    public String getModul() {
        return (String) get(2);
    }

    /**
     * Setter for <code>timetable_entry.klasse</code>.
     */
    @Override
    public void setKlasse(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>timetable_entry.klasse</code>.
     */
    @Override
    public String getKlasse() {
        return (String) get(3);
    }

    /**
     * Setter for <code>timetable_entry.besetzung</code>.
     */
    @Override
    public void setBesetzung(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>timetable_entry.besetzung</code>.
     */
    @Override
    public String getBesetzung() {
        return (String) get(4);
    }

    /**
     * Setter for <code>timetable_entry.fk_location</code>.
     */
    @Override
    public void setFkLocation(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>timetable_entry.fk_location</code>.
     */
    @Override
    public Long getFkLocation() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>timetable_entry.date</code>.
     */
    @Override
    public void setDate(LocalDate value) {
        set(6, value);
    }

    /**
     * Getter for <code>timetable_entry.date</code>.
     */
    @Override
    public LocalDate getDate() {
        return (LocalDate) get(6);
    }

    /**
     * Setter for <code>timetable_entry.start_time</code>.
     */
    @Override
    public void setStartTime(LocalTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>timetable_entry.start_time</code>.
     */
    @Override
    public LocalTime getStartTime() {
        return (LocalTime) get(7);
    }

    /**
     * Setter for <code>timetable_entry.end_time</code>.
     */
    @Override
    public void setEndTime(LocalTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>timetable_entry.end_time</code>.
     */
    @Override
    public LocalTime getEndTime() {
        return (LocalTime) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, Long, String, String, String, Long, LocalDate, LocalTime, LocalTime> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<Long, Long, String, String, String, Long, LocalDate, LocalTime, LocalTime> valuesRow() {
        return (Row9) super.valuesRow();
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
    public Field<String> field3() {
        return TimetableEntry.TIMETABLE_ENTRY.MODUL;
    }

    @Override
    public Field<String> field4() {
        return TimetableEntry.TIMETABLE_ENTRY.KLASSE;
    }

    @Override
    public Field<String> field5() {
        return TimetableEntry.TIMETABLE_ENTRY.BESETZUNG;
    }

    @Override
    public Field<Long> field6() {
        return TimetableEntry.TIMETABLE_ENTRY.FK_LOCATION;
    }

    @Override
    public Field<LocalDate> field7() {
        return TimetableEntry.TIMETABLE_ENTRY.DATE;
    }

    @Override
    public Field<LocalTime> field8() {
        return TimetableEntry.TIMETABLE_ENTRY.START_TIME;
    }

    @Override
    public Field<LocalTime> field9() {
        return TimetableEntry.TIMETABLE_ENTRY.END_TIME;
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
    public String component3() {
        return getModul();
    }

    @Override
    public String component4() {
        return getKlasse();
    }

    @Override
    public String component5() {
        return getBesetzung();
    }

    @Override
    public Long component6() {
        return getFkLocation();
    }

    @Override
    public LocalDate component7() {
        return getDate();
    }

    @Override
    public LocalTime component8() {
        return getStartTime();
    }

    @Override
    public LocalTime component9() {
        return getEndTime();
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
    public String value3() {
        return getModul();
    }

    @Override
    public String value4() {
        return getKlasse();
    }

    @Override
    public String value5() {
        return getBesetzung();
    }

    @Override
    public Long value6() {
        return getFkLocation();
    }

    @Override
    public LocalDate value7() {
        return getDate();
    }

    @Override
    public LocalTime value8() {
        return getStartTime();
    }

    @Override
    public LocalTime value9() {
        return getEndTime();
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
    public TimetableEntryRecord value3(String value) {
        setModul(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value4(String value) {
        setKlasse(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value5(String value) {
        setBesetzung(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value6(Long value) {
        setFkLocation(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value7(LocalDate value) {
        setDate(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value8(LocalTime value) {
        setStartTime(value);
        return this;
    }

    @Override
    public TimetableEntryRecord value9(LocalTime value) {
        setEndTime(value);
        return this;
    }

    @Override
    public TimetableEntryRecord values(Long value1, Long value2, String value3, String value4, String value5, Long value6, LocalDate value7, LocalTime value8, LocalTime value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ITimetableEntry from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setModul(from.getModul());
        setKlasse(from.getKlasse());
        setBesetzung(from.getBesetzung());
        setFkLocation(from.getFkLocation());
        setDate(from.getDate());
        setStartTime(from.getStartTime());
        setEndTime(from.getEndTime());
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
    public TimetableEntryRecord(Long id, Long fkVerein, String modul, String klasse, String besetzung, Long fkLocation, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(TimetableEntry.TIMETABLE_ENTRY);

        setId(id);
        setFkVerein(fkVerein);
        setModul(modul);
        setKlasse(klasse);
        setBesetzung(besetzung);
        setFkLocation(fkLocation);
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
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
            setModul(value.getModul());
            setKlasse(value.getKlasse());
            setBesetzung(value.getBesetzung());
            setFkLocation(value.getFkLocation());
            setDate(value.getDate());
            setStartTime(value.getStartTime());
            setEndTime(value.getEndTime());
            resetChangedOnNotNull();
        }
    }
}
