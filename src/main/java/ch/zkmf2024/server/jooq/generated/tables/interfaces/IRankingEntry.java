/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public interface IRankingEntry extends Serializable {

    /**
     * Setter for <code>ranking_entry.fk_ranking</code>.
     */
    public void setFkRanking(Long value);

    /**
     * Getter for <code>ranking_entry.fk_ranking</code>.
     */
    public Long getFkRanking();

    /**
     * Setter for <code>ranking_entry.fk_verein</code>.
     */
    public void setFkVerein(Long value);

    /**
     * Getter for <code>ranking_entry.fk_verein</code>.
     */
    public Long getFkVerein();

    /**
     * Setter for <code>ranking_entry.score</code>.
     */
    public void setScore(BigDecimal value);

    /**
     * Getter for <code>ranking_entry.score</code>.
     */
    public BigDecimal getScore();

    /**
     * Setter for <code>ranking_entry.rank</code>.
     */
    public void setRank(Integer value);

    /**
     * Getter for <code>ranking_entry.rank</code>.
     */
    public Integer getRank();

    /**
     * Setter for <code>ranking_entry.confirmed_by</code>.
     */
    public void setConfirmedBy(String value);

    /**
     * Getter for <code>ranking_entry.confirmed_by</code>.
     */
    public String getConfirmedBy();

    /**
     * Setter for <code>ranking_entry.confirmed_at</code>.
     */
    public void setConfirmedAt(LocalDateTime value);

    /**
     * Getter for <code>ranking_entry.confirmed_at</code>.
     */
    public LocalDateTime getConfirmedAt();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IRankingEntry
     */
    public void from(IRankingEntry from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IRankingEntry
     */
    public <E extends IRankingEntry> E into(E into);
}