package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TimetablePreviewDTO(@NotNull String header1,
                                  String header2,
                                  String header3,
                                  @NotNull LocationDTO location,
                                  @NotNull LocalDate date,
                                  @NotNull LocalTime startTime,
                                  @NotNull LocalTime endTime,
                                  @NotNull long minutesUntilStart) {

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(date, startTime);
    }
}
