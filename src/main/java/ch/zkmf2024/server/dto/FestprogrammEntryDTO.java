package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FestprogrammEntryDTO(@NotNull LocalDateTime start,
                                   LocalDateTime end,
                                   @NotNull String description,
                                   @NotNull String location,
                                   boolean important) {

    public LocalDate getDate() {
        return start.toLocalDate();
    }
}
