package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record RankingPenaltyDTO(@NotNull Long timetableEntryId,
                                @NotNull Integer minutesOverrun) {
}
