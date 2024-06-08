package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record RankingListEntryDTO(@NotNull int rank,
                                  @NotNull String vereinIdentifier,
                                  @NotNull String vereinsName,
                                  @NotNull BigDecimal score,
                                  String additionalInfo) {
}
