package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record VereinTimetableEntryDTO(@NotNull String competition,
                                      @NotNull LocationDTO location,
                                      @NotNull String dateTime) {
}
