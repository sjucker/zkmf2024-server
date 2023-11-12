package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record TimetableOverviewEntryDTO(@NotNull Long vereinId,
                                        @NotNull String vereinsname,
                                        @NotNull String modul,
                                        @NotNull String competition,
                                        @NotNull String type,
                                        @NotNull LocationDTO location,
                                        @NotNull LocalDate date,
                                        @NotNull LocalTime start,
                                        @NotNull LocalTime end,
                                        @NotNull String time) {
}
