package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record JudgeReportRatingDTO(
        @NotNull JudgeReportCategory category,
        @NotNull String categoryDescription,
        @NotNull String group,
        String comment,
        @NotNull JudgeReportCategoryRating rating
) {
}
