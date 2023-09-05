package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record TimetableEntryDTO(@NotNull Long id,
                                @NotNull String modul,
                                String klasse,
                                String besetzung,
                                @NotNull String location,
                                @NotNull String verein,
                                @NotNull LocalDate date,
                                @NotNull LocalTime start,
                                @NotNull LocalTime end) {
}
