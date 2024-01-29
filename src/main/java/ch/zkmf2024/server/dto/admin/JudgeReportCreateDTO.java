package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.Modul;

import jakarta.validation.constraints.NotNull;

public record JudgeReportCreateDTO(@NotNull Long timetableEntryId,
                                   @NotNull Modul modul,
                                   @NotNull Long judge1Id,
                                   @NotNull Long judge2Id,
                                   @NotNull Long judge3Id,
                                   Long judge4Id) {
}
