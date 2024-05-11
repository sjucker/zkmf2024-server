package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public record TimetablePreviewDTO(@NotNull String header1,
                                  String header2,
                                  String header3,
                                  @NotNull LocationDTO location,
                                  @NotNull LocalTime startTime,
                                  @NotNull LocalTime endTime,
                                  @NotNull long minutesUntilStart) {
}
