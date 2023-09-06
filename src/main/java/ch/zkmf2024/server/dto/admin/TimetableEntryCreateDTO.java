package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record TimetableEntryCreateDTO(@NotNull Long vereinId,
                                      @NotNull Long vereinProgrammId,
                                      @NotNull Long locationId,
                                      @NotNull LocalDate date,
                                      @NotNull LocalTime start,
                                      @NotNull LocalTime end,
                                      @NotNull Long judge1Id,
                                      @NotNull Long judge2Id,
                                      @NotNull Long judge3Id) {
}
