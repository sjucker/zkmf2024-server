package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ConfirmScoreDTO(@NotNull Long vereinProgrammId,
                              JudgeReportModulCategory category,
                              @NotNull BigDecimal score) {
}
