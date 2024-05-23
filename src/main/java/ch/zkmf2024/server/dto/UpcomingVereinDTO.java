package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public record UpcomingVereinDTO(@NotNull long timetableEntryId,
                                @NotNull String vereinIdentifier,
                                @NotNull String vereinName,
                                @NotNull String location,
                                @NotNull LocalTime startTime,
                                @NotNull long minutesUntilStart) {

    public String getId() {
        return "%s-%s".formatted(timetableEntryId, vereinIdentifier);
    }
}
