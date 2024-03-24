package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record FestprogrammEntryDTO(@NotNull LocalDate date,
                                   @NotNull String timeFrom,
                                   String timeTo,
                                   @NotNull String description,
                                   @NotNull String location,
                                   boolean important) {
}
