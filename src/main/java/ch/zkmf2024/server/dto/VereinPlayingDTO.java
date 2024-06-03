package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VereinPlayingDTO(@NotNull Long timetableEntryId,
                               @NotNull Long vereinProgrammId,
                               @NotNull String vereinsname,
                               @NotNull Modul modul,
                               @NotNull LocalDateTime startTime,
                               @NotNull LocalDateTime endTime,
                               Integer minDurationInSeconds,
                               Integer maxDurationInSeconds,
                               boolean started,
                               boolean ended,
                               @NotNull String jury,
                               Integer minutesOverrun,
                               BigDecimal bonus) {
}
