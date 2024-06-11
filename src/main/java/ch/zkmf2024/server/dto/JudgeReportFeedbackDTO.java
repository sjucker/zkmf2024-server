package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record JudgeReportFeedbackDTO(@NotNull String verein,
                                     @NotNull Modul modul,
                                     JudgeReportModulCategory category,
                                     @NotNull String modulDescription,
                                     @NotNull String scoreRange,
                                     @NotNull JudgeReportViewDTO judge1,
                                     @NotNull JudgeReportViewDTO judge2,
                                     @NotNull JudgeReportViewDTO judge3) {
}
