package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record JudgeReportOverviewDTO(
        @NotNull Long id,
        @NotNull String verein,
        @NotNull String location,
        @NotNull String locationUrl,
        @NotNull Modul modul,
        @NotNull String modulDescription,
        @NotNull JudgeRole role,
        @NotNull String roleDescription,
        String klasse,
        String besetzung,
        JudgeReportModulCategory category,
        String categoryDescription,
        @NotNull LocalDateTime start,
        @NotNull LocalDateTime end,
        @NotNull JudgeReportStatus status
) {
}
