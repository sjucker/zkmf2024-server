package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record TimetableEntryCreateDTO(@NotNull Long vereinId,
                                      // null for Einspielen, Besprechung
                                      Long vereinProgrammId,
                                      @NotNull Long locationId,
                                      @NotNull LocalDate date,
                                      @NotNull LocalTime start,
                                      @NotNull LocalTime end) {
}
