package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record JudgeReportScoreDTO(@NotNull Long reportId,
                                  @NotNull String judgeName,
                                  @NotNull String judgeRole,
                                  BigDecimal score,
                                  boolean ratingFixed,
                                  boolean done) {
}
