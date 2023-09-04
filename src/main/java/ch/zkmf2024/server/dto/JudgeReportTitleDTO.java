package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record JudgeReportTitleDTO(
        @NotNull TitelDTO titel,
        String comment,
        @NotNull List<JudgeReportRatingDTO> ratings
) {
}
