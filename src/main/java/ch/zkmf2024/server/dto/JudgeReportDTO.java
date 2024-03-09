package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record JudgeReportDTO(
        @NotNull Long id,
        @NotNull Modul modul,
        @NotNull String modulDescription,
        @NotNull JudgeRole role,
        @NotNull String roleDescription,
        String klasse,
        String besetzung,
        JudgeReportModulCategory category,
        String categoryDescription,
        @NotNull String location,
        @NotNull String verein,
        @NotNull String dirigent,
        String programmTitel,
        String programmInfo,
        Integer minDurationInSeconds,
        Integer maxDurationInSeconds,
        String modulGKatA1,
        String modulGKatA2,
        BigDecimal score,
        boolean ratingFixed,
        @NotNull JudgeReportStatus status,
        @NotNull List<JudgeReportTitleDTO> titles,
        @NotNull List<JudgeReportRatingDTO> overallRatings
) {
}
