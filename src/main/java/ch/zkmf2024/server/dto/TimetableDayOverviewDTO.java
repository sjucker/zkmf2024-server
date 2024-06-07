package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record TimetableDayOverviewDTO(@NotNull String day,
                                      boolean inPast,
                                      @NotNull List<TimetableOverviewEntryDTO> entries) {
}
