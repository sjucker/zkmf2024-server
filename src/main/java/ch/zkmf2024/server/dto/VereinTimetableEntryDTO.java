package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record VereinTimetableEntryDTO(@NotNull Modul modul,
                                      @NotNull String competition,
                                      @NotNull LocationDTO location,
                                      @NotNull String dateTime,
                                      String titel,
                                      String description,
                                      @NotNull List<TitelDTO> programm) {
}
