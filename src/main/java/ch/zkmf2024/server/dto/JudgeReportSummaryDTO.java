package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record JudgeReportSummaryDTO(@NotNull Long programmId,
                                    @NotNull LocalDate date,
                                    @NotNull LocationDTO location,
                                    @NotNull Modul modul,
                                    @NotNull String modulDescription,
                                    Klasse klasse,
                                    String klasseDescription,
                                    Besetzung besetzung,
                                    String besetzungDescription,
                                    JudgeReportModulCategory category,
                                    String categoryDescription,
                                    @NotNull String verein,
                                    BigDecimal overallScore,
                                    BigDecimal penalty,
                                    @NotNull List<JudgeReportScoreDTO> scores,
                                    boolean done,
                                    boolean scoresConfirmed) {
}
