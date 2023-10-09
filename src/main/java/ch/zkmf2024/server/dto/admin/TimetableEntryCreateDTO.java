package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.TimetableEntryType;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record TimetableEntryCreateDTO(@NotNull Long vereinProgrammId,
                                      @NotNull Modul modul,
                                      @NotNull String modulDescription,
                                      String klasse,
                                      String besetzung,
                                      @NotNull List<TimeTableEntryDTO> entries) {

    public record TimeTableEntryDTO(@NotNull TimetableEntryType type,
                                    @NotNull Long locationId,
                                    @NotNull List<LocationSelectionDTO> availableLocations,
                                    @NotNull LocalDate date,
                                    @NotNull LocalTime start,
                                    @NotNull LocalTime end) {
    }
}
