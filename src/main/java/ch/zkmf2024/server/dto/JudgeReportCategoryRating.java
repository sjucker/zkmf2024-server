package ch.zkmf2024.server.dto;

import java.util.Optional;

public enum JudgeReportCategoryRating {
    NEGATIVE,
    NEUTRAL,
    POSITIVE;

    public static Optional<JudgeReportCategoryRating> fromString(String rating) {
        try {
            return Optional.of(JudgeReportCategoryRating.valueOf(rating));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
