package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record JudgeReportRatingDTO(
        @NotNull JudgeReportCategory category,
        @NotNull String categoryDescription,
        String comment,
        @NotNull JudgeReportCategoryRating rating
) {
}
