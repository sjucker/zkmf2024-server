package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record JudgeReportCreateDTO(@NotNull Long timetableEntryId,
                                   @NotNull Long judge1Id,
                                   @NotNull Long judge2Id,
                                   @NotNull Long judge3Id) {
}
