package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record JudgeReportSummaryDTO(@NotNull String modul,
                                    String klasse,
                                    String besetzung,
                                    @NotNull String verein,
                                    // TODO mit fractions?
                                    Integer overallScore,
                                    @NotNull List<JudgeReportScoreDTO> scores,
                                    boolean done) {
}
