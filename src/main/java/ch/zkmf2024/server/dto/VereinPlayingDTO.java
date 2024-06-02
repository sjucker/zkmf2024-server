package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record VereinPlayingDTO(@NotNull Long timetableEntryId,
                               @NotNull String vereinsname,
                               @NotNull LocalDateTime startTime,
                               @NotNull LocalDateTime endTime,
                               Integer minDurationInSeconds,
                               Integer maxDurationInSeconds,
                               boolean started,
                               boolean ended,
                               @NotNull String jury,
                               Integer minutesOverrun) {
}
