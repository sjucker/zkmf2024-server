package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record JudgeReportOverviewDTO(
        @NotNull Long id,
        @NotNull String verein,
        @NotNull String location,
        @NotNull String locationUrl,
        @NotNull String modul,
        String klasse,
        String besetzung,
        @NotNull LocalDateTime start,
        @NotNull LocalDateTime end
        // TODO titel?
        // TODO Kurzbericht
        // TODO musikalische Faktoren
) {
}
