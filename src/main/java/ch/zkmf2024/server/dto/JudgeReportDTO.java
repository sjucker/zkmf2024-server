package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record JudgeReportDTO(
        @NotNull Long id,
        @NotNull String modul,
        String klasse,
        String besetzung,
        @NotNull String location,
        @NotNull String verein,
        @NotNull String dirigent,
        String programmTitel,
        String programmInfo,
        Integer minDurationInSeconds,
        Integer maxDurationInSeconds,
        Integer score,
        @NotNull JudgeReportStatus status,
        @NotNull List<JudgeReportTitleDTO> titles,
        @NotNull List<JudgeReportRatingDTO> overallRatings
) {
}
