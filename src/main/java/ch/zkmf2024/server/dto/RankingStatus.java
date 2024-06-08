package ch.zkmf2024.server.dto;

public enum RankingStatus {
    PENDING,
    // for rankings that span multiple days (Zwischenrangliste)
    INTERMEDIATE,
    FINAL
}
