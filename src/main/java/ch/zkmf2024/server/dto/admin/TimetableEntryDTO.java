package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.TimetableEntryType;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record TimetableEntryDTO(@NotNull Long id,
                                @NotNull Modul modul,
                                @NotNull String modulDescription,
                                String klasse,
                                String besetzung,
                                @NotNull Long locationId,
                                @NotNull String location,
                                @NotNull String verein,
                                @NotNull LocalDate date,
                                @NotNull LocalTime start,
                                @NotNull LocalTime end,
                                @NotNull TimetableEntryType type,
                                String judge1,
                                String judge2,
                                String judge3,
                                String judge4) {
}
