package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record RankingBonusDTO(@NotNull Long vereinProgrammId,
                              @NotNull JudgeReportModulCategory category,
                              @NotNull BigDecimal bonus) {
}
