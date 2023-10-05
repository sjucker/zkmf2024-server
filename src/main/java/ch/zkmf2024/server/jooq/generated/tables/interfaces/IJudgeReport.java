/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public interface IJudgeReport extends Serializable {

    /**
     * Setter for <code>judge_report.id</code>.
     */
    public void setId(Long value);

    /**
     * Getter for <code>judge_report.id</code>.
     */
    public Long getId();

    /**
     * Setter for <code>judge_report.fk_judge</code>.
     */
    public void setFkJudge(Long value);

    /**
     * Getter for <code>judge_report.fk_judge</code>.
     */
    public Long getFkJudge();

    /**
     * Setter for <code>judge_report.fk_timetable_entry</code>.
     */
    public void setFkTimetableEntry(Long value);

    /**
     * Getter for <code>judge_report.fk_timetable_entry</code>.
     */
    public Long getFkTimetableEntry();

    /**
     * Setter for <code>judge_report.score</code>.
     */
    public void setScore(Integer value);

    /**
     * Getter for <code>judge_report.score</code>.
     */
    public Integer getScore();

    /**
     * Setter for <code>judge_report.status</code>.
     */
    public void setStatus(String value);

    /**
     * Getter for <code>judge_report.status</code>.
     */
    public String getStatus();

    /**
     * Setter for <code>judge_report.finished_at</code>.
     */
    public void setFinishedAt(LocalDateTime value);

    /**
     * Getter for <code>judge_report.finished_at</code>.
     */
    public LocalDateTime getFinishedAt();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IJudgeReport
     */
    public void from(IJudgeReport from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IJudgeReport
     */
    public <E extends IJudgeReport> E into(E into);
}
