package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

import static ch.zkmf2024.server.dto.RankingStatus.FINAL;
import static ch.zkmf2024.server.dto.RankingStatus.PENDING;

public record RankingListDTO(@NotNull Long id,
                             @NotNull Modul modul,
                             @NotNull String modulDescription,
                             Klasse klasse,
                             String klasseDescription,
                             Besetzung besetzung,
                             String besetzungDescription,
                             JudgeReportModulCategory category,
                             String categoryDescription,
                             @NotNull LocationDTO location,
                             @NotNull String description,
                             @NotNull RankingStatus status,
                             @NotNull List<RankingListEntryDTO> entries) {

    public boolean isFinal() {
        return status == FINAL;
    }

    public boolean isNotPending() {
        return status != PENDING;
    }
}
