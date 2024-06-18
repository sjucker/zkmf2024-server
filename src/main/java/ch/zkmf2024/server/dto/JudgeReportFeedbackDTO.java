package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record JudgeReportFeedbackDTO(@NotNull String verein,
                                     @NotNull Modul modul,
                                     @NotNull String modulDescription,
                                     JudgeReportModulCategory category,
                                     BigDecimal score,
                                     @NotNull String scoreRange,
                                     BigDecimal penalty,
                                     BigDecimal bonus,
                                     @NotNull JudgeReportViewDTO judge1,
                                     @NotNull JudgeReportViewDTO judge2,
                                     @NotNull JudgeReportViewDTO judge3,
                                     JudgeReportViewDTO judge4) {
}
