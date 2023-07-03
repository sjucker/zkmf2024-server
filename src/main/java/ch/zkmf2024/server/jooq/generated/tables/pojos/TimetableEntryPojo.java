/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.ITimetableEntry;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class TimetableEntryPojo implements ITimetableEntry {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fkVerein;
    private String modul;
    private String klasse;
    private String besetzung;
    private Long fkLocation;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimetableEntryPojo() {
    }

    public TimetableEntryPojo(ITimetableEntry value) {
        this.id = value.getId();
        this.fkVerein = value.getFkVerein();
        this.modul = value.getModul();
        this.klasse = value.getKlasse();
        this.besetzung = value.getBesetzung();
        this.fkLocation = value.getFkLocation();
        this.date = value.getDate();
        this.startTime = value.getStartTime();
        this.endTime = value.getEndTime();
    }

    public TimetableEntryPojo(
            Long id,
            Long fkVerein,
            String modul,
            String klasse,
            String besetzung,
            Long fkLocation,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    ) {
        this.id = id;
        this.fkVerein = fkVerein;
        this.modul = modul;
        this.klasse = klasse;
        this.besetzung = besetzung;
        this.fkLocation = fkLocation;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Getter for <code>timetable_entry.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>timetable_entry.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>timetable_entry.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return this.fkVerein;
    }

    /**
     * Setter for <code>timetable_entry.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long fkVerein) {
        this.fkVerein = fkVerein;
    }

    /**
     * Getter for <code>timetable_entry.modul</code>.
     */
    @Override
    public String getModul() {
        return this.modul;
    }

    /**
     * Setter for <code>timetable_entry.modul</code>.
     */
    @Override
    public void setModul(String modul) {
        this.modul = modul;
    }

    /**
     * Getter for <code>timetable_entry.klasse</code>.
     */
    @Override
    public String getKlasse() {
        return this.klasse;
    }

    /**
     * Setter for <code>timetable_entry.klasse</code>.
     */
    @Override
    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    /**
     * Getter for <code>timetable_entry.besetzung</code>.
     */
    @Override
    public String getBesetzung() {
        return this.besetzung;
    }

    /**
     * Setter for <code>timetable_entry.besetzung</code>.
     */
    @Override
    public void setBesetzung(String besetzung) {
        this.besetzung = besetzung;
    }

    /**
     * Getter for <code>timetable_entry.fk_location</code>.
     */
    @Override
    public Long getFkLocation() {
        return this.fkLocation;
    }

    /**
     * Setter for <code>timetable_entry.fk_location</code>.
     */
    @Override
    public void setFkLocation(Long fkLocation) {
        this.fkLocation = fkLocation;
    }

    /**
     * Getter for <code>timetable_entry.date</code>.
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Setter for <code>timetable_entry.date</code>.
     */
    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Getter for <code>timetable_entry.start_time</code>.
     */
    @Override
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Setter for <code>timetable_entry.start_time</code>.
     */
    @Override
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for <code>timetable_entry.end_time</code>.
     */
    @Override
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Setter for <code>timetable_entry.end_time</code>.
     */
    @Override
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TimetableEntryPojo other = (TimetableEntryPojo) obj;
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
        if (this.fkLocation == null) {
            if (other.fkLocation != null)
                return false;
        } else if (!this.fkLocation.equals(other.fkLocation))
            return false;
        if (this.date == null) {
            if (other.date != null)
                return false;
        } else if (!this.date.equals(other.date))
            return false;
        if (this.startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!this.startTime.equals(other.startTime))
            return false;
        if (this.endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!this.endTime.equals(other.endTime))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.fkVerein == null) ? 0 : this.fkVerein.hashCode());
        result = prime * result + ((this.modul == null) ? 0 : this.modul.hashCode());
        result = prime * result + ((this.klasse == null) ? 0 : this.klasse.hashCode());
        result = prime * result + ((this.besetzung == null) ? 0 : this.besetzung.hashCode());
        result = prime * result + ((this.fkLocation == null) ? 0 : this.fkLocation.hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.startTime == null) ? 0 : this.startTime.hashCode());
        result = prime * result + ((this.endTime == null) ? 0 : this.endTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TimetableEntryPojo (");

        sb.append(id);
        sb.append(", ").append(fkVerein);
        sb.append(", ").append(modul);
        sb.append(", ").append(klasse);
        sb.append(", ").append(besetzung);
        sb.append(", ").append(fkLocation);
        sb.append(", ").append(date);
        sb.append(", ").append(startTime);
        sb.append(", ").append(endTime);

        sb.append(")");
        return sb.toString();
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
    }

    @Override
    public <E extends ITimetableEntry> E into(E into) {
        into.from(this);
        return into;
    }
}