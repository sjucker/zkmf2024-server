package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record JudgeReportRatingDTO(
        @NotNull JudgeReportCategory category,
        @NotNull String categoryDescription,
        @NotNull String group,
        @NotNull List<String> ratingDescriptions,
        String comment,
        @NotNull JudgeReportCategoryRating rating,
        // only for modul G
        BigDecimal score
) {
}
