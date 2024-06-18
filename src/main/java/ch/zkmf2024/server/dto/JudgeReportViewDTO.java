package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record JudgeReportViewDTO(
        @NotNull String judge,
        @NotNull Modul modul,
        @NotNull String modulDescription,
        String klasse,
        String besetzung,
        JudgeReportModulCategory category,
        String categoryDescription,
        @NotNull String verein,
        BigDecimal score,
        BigDecimal penalty,
        BigDecimal bonus,
        @NotNull JudgeReportStatus status,
        @NotNull List<JudgeReportTitleDTO> titles,
        @NotNull List<JudgeReportRatingDTO> overallRatings
) {
}
