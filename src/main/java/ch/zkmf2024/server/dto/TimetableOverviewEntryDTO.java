package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record TimetableOverviewEntryDTO(@NotNull Long vereinId,
                                        @NotNull String vereinIdentifier,
                                        @NotNull String vereinsname,
                                        @NotNull String modul,
                                        String klasse,
                                        String besetzung,
                                        @NotNull String competition,
                                        @NotNull TimetableEntryType type,
                                        @NotNull String typeDescription,
                                        @NotNull LocationDTO location,
                                        @NotNull LocalDate date,
                                        @NotNull LocalTime start,
                                        @NotNull LocalTime end,
                                        @NotNull String time,
                                        boolean inPast) {

    public TimetableOverviewEntryDTO notInPast() {
        return new TimetableOverviewEntryDTO(vereinId,
                                             vereinIdentifier,
                                             vereinsname,
                                             modul,
                                             klasse,
                                             besetzung,
                                             competition,
                                             type,
                                             typeDescription,
                                             location,
                                             date,
                                             start,
                                             end,
                                             time,
                                             false);
    }

}
