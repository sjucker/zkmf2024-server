/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public interface IJudgeReportComment extends Serializable {

    /**
     * Setter for <code>judge_report_comment.fk_judge_report</code>.
     */
    public void setFkJudgeReport(Long value);

    /**
     * Getter for <code>judge_report_comment.fk_judge_report</code>.
     */
    public Long getFkJudgeReport();

    /**
     * Setter for <code>judge_report_comment.fk_titel</code>.
     */
    public void setFkTitel(Long value);

    /**
     * Getter for <code>judge_report_comment.fk_titel</code>.
     */
    public Long getFkTitel();

    /**
     * Setter for <code>judge_report_comment.comment</code>.
     */
    public void setComment(String value);

    /**
     * Getter for <code>judge_report_comment.comment</code>.
     */
    public String getComment();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IJudgeReportComment
     */
    public void from(IJudgeReportComment from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IJudgeReportComment
     */
    public <E extends IJudgeReportComment> E into(E into);
}
