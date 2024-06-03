/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.RankingEntry;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IRankingEntry;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingEntryPojo;
import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class RankingEntryRecord extends UpdatableRecordImpl<RankingEntryRecord> implements IRankingEntry {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ranking_entry.fk_ranking</code>.
     */
    @Override
    public void setFkRanking(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>ranking_entry.fk_ranking</code>.
     */
    @Override
    public Long getFkRanking() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>ranking_entry.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>ranking_entry.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>ranking_entry.score</code>.
     */
    @Override
    public void setScore(BigDecimal value) {
        set(2, value);
    }

    /**
     * Getter for <code>ranking_entry.score</code>.
     */
    @Override
    public BigDecimal getScore() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for <code>ranking_entry.rank</code>.
     */
    @Override
    public void setRank(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>ranking_entry.rank</code>.
     */
    @Override
    public Integer getRank() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>ranking_entry.confirmed_by</code>.
     */
    @Override
    public void setConfirmedBy(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>ranking_entry.confirmed_by</code>.
     */
    @Override
    public String getConfirmedBy() {
        return (String) get(4);
    }

    /**
     * Setter for <code>ranking_entry.confirmed_at</code>.
     */
    @Override
    public void setConfirmedAt(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>ranking_entry.confirmed_at</code>.
     */
    @Override
    public LocalDateTime getConfirmedAt() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IRankingEntry from) {
        setFkRanking(from.getFkRanking());
        setFkVerein(from.getFkVerein());
        setScore(from.getScore());
        setRank(from.getRank());
        setConfirmedBy(from.getConfirmedBy());
        setConfirmedAt(from.getConfirmedAt());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IRankingEntry> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RankingEntryRecord
     */
    public RankingEntryRecord() {
        super(RankingEntry.RANKING_ENTRY);
    }

    /**
     * Create a detached, initialised RankingEntryRecord
     */
    public RankingEntryRecord(Long fkRanking, Long fkVerein, BigDecimal score, Integer rank, String confirmedBy, LocalDateTime confirmedAt) {
        super(RankingEntry.RANKING_ENTRY);

        setFkRanking(fkRanking);
        setFkVerein(fkVerein);
        setScore(score);
        setRank(rank);
        setConfirmedBy(confirmedBy);
        setConfirmedAt(confirmedAt);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised RankingEntryRecord
     */
    public RankingEntryRecord(RankingEntryPojo value) {
        super(RankingEntry.RANKING_ENTRY);

        if (value != null) {
            setFkRanking(value.getFkRanking());
            setFkVerein(value.getFkVerein());
            setScore(value.getScore());
            setRank(value.getRank());
            setConfirmedBy(value.getConfirmedBy());
            setConfirmedAt(value.getConfirmedAt());
            resetChangedOnNotNull();
        }
    }
}
