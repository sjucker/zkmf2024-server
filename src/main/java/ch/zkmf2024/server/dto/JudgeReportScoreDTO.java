package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record JudgeReportScoreDTO(@NotNull Long reportId,
                                  @NotNull String judgeName,
                                  @NotNull String judgeRole,
                                  Integer score,
                                  boolean ratingFixed,
                                  boolean done) {
}
