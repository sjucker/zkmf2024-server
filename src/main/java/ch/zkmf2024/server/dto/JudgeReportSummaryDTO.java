package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record JudgeReportSummaryDTO(@NotNull Long programmId,
                                    @NotNull Modul modul,
                                    @NotNull String modulDescription,
                                    Klasse klasse,
                                    String klasseDescription,
                                    Besetzung besetzung,
                                    String besetzungDescription,
                                    @NotNull String verein,
                                    BigDecimal overallScore,
                                    @NotNull List<JudgeReportScoreDTO> scores,
                                    boolean done,
                                    boolean scoresConfirmed,
                                    String scoresConfirmedBy,
                                    LocalDateTime scoresConfirmedAt) {
}
