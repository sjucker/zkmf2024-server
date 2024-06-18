package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record JudgeReportFeedbackSelectionDTO(@NotNull Modul modul,
                                              @NotNull String modulDescription,
                                              JudgeReportModulCategory category) {
}
