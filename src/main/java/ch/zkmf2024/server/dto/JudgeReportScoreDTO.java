package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record JudgeReportScoreDTO(@NotNull Long reportId,
                                  @NotNull String judgeName,
                                  Integer score,
                                  @NotNull JudgeReportStatus status) {
}
