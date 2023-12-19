package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record JudgeReportSummaryDTO(@NotNull Long programmId,
                                    @NotNull String modul,
                                    String klasse,
                                    String besetzung,
                                    @NotNull String verein,
                                    BigDecimal overallScore,
                                    @NotNull List<JudgeReportScoreDTO> scores,
                                    boolean done,
                                    boolean scoresConfirmed,
                                    String scoresConfirmedBy,
                                    LocalDateTime scoresConfirmedAt) {
}
