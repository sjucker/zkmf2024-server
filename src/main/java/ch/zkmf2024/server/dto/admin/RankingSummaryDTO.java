package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.RankingDTO;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record RankingSummaryDTO(@NotNull String vereinsName,
                                @NotNull String competition,
                                @NotNull List<RankingDTO> rankings) {
}
