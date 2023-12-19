package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record JudgeRankingEntryDTO(@NotNull String verein,
                                   @NotNull BigDecimal score) {
}
