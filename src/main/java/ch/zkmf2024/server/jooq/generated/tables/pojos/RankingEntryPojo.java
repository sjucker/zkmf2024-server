/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IRankingEntry;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class RankingEntryPojo implements IRankingEntry {

    private static final long serialVersionUID = 1L;

    private Long fkRanking;
    private Long fkVerein;
    private BigDecimal score;
    private Integer rank;
    private String confirmedBy;
    private LocalDateTime confirmedAt;

    public RankingEntryPojo() {
    }

    public RankingEntryPojo(IRankingEntry value) {
        this.fkRanking = value.getFkRanking();
        this.fkVerein = value.getFkVerein();
        this.score = value.getScore();
        this.rank = value.getRank();
        this.confirmedBy = value.getConfirmedBy();
        this.confirmedAt = value.getConfirmedAt();
    }

    public RankingEntryPojo(
            Long fkRanking,
            Long fkVerein,
            BigDecimal score,
            Integer rank,
            String confirmedBy,
            LocalDateTime confirmedAt
    ) {
        this.fkRanking = fkRanking;
        this.fkVerein = fkVerein;
        this.score = score;
        this.rank = rank;
        this.confirmedBy = confirmedBy;
        this.confirmedAt = confirmedAt;
    }

    /**
     * Getter for <code>ranking_entry.fk_ranking</code>.
     */
    @Override
    public Long getFkRanking() {
        return this.fkRanking;
    }

    /**
     * Setter for <code>ranking_entry.fk_ranking</code>.
     */
    @Override
    public void setFkRanking(Long fkRanking) {
        this.fkRanking = fkRanking;
    }

    /**
     * Getter for <code>ranking_entry.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return this.fkVerein;
    }

    /**
     * Setter for <code>ranking_entry.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long fkVerein) {
        this.fkVerein = fkVerein;
    }

    /**
     * Getter for <code>ranking_entry.score</code>.
     */
    @Override
    public BigDecimal getScore() {
        return this.score;
    }

    /**
     * Setter for <code>ranking_entry.score</code>.
     */
    @Override
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * Getter for <code>ranking_entry.rank</code>.
     */
    @Override
    public Integer getRank() {
        return this.rank;
    }

    /**
     * Setter for <code>ranking_entry.rank</code>.
     */
    @Override
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * Getter for <code>ranking_entry.confirmed_by</code>.
     */
    @Override
    public String getConfirmedBy() {
        return this.confirmedBy;
    }

    /**
     * Setter for <code>ranking_entry.confirmed_by</code>.
     */
    @Override
    public void setConfirmedBy(String confirmedBy) {
        this.confirmedBy = confirmedBy;
    }

    /**
     * Getter for <code>ranking_entry.confirmed_at</code>.
     */
    @Override
    public LocalDateTime getConfirmedAt() {
        return this.confirmedAt;
    }

    /**
     * Setter for <code>ranking_entry.confirmed_at</code>.
     */
    @Override
    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final RankingEntryPojo other = (RankingEntryPojo) obj;
        if (this.fkRanking == null) {
            if (other.fkRanking != null)
                return false;
        } else if (!this.fkRanking.equals(other.fkRanking))
            return false;
        if (this.fkVerein == null) {
            if (other.fkVerein != null)
                return false;
        } else if (!this.fkVerein.equals(other.fkVerein))
            return false;
        if (this.score == null) {
            if (other.score != null)
                return false;
        } else if (!this.score.equals(other.score))
            return false;
        if (this.rank == null) {
            if (other.rank != null)
                return false;
        } else if (!this.rank.equals(other.rank))
            return false;
        if (this.confirmedBy == null) {
            if (other.confirmedBy != null)
                return false;
        } else if (!this.confirmedBy.equals(other.confirmedBy))
            return false;
        if (this.confirmedAt == null) {
            if (other.confirmedAt != null)
                return false;
        } else if (!this.confirmedAt.equals(other.confirmedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.fkRanking == null) ? 0 : this.fkRanking.hashCode());
        result = prime * result + ((this.fkVerein == null) ? 0 : this.fkVerein.hashCode());
        result = prime * result + ((this.score == null) ? 0 : this.score.hashCode());
        result = prime * result + ((this.rank == null) ? 0 : this.rank.hashCode());
        result = prime * result + ((this.confirmedBy == null) ? 0 : this.confirmedBy.hashCode());
        result = prime * result + ((this.confirmedAt == null) ? 0 : this.confirmedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RankingEntryPojo (");

        sb.append(fkRanking);
        sb.append(", ").append(fkVerein);
        sb.append(", ").append(score);
        sb.append(", ").append(rank);
        sb.append(", ").append(confirmedBy);
        sb.append(", ").append(confirmedAt);

        sb.append(")");
        return sb.toString();
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
    }

    @Override
    public <E extends IRankingEntry> E into(E into) {
        into.from(this);
        return into;
    }
}
