package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record RankingListDTO(@NotNull Long id,
                             @NotNull Modul modul,
                             Klasse klasse,
                             Besetzung besetzung,
                             JudgeReportModulCategory category,
                             @NotNull String description,
                             @NotNull RankingStatus status,
                             @NotNull List<RankingListEntryDTO> entries) {
}
