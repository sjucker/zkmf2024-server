package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VereinPlayingDTO(@NotNull Long timetableEntryId,
                               @NotNull Long vereinProgrammId,
                               @NotNull String vereinsname,
                               @NotNull Modul modul,
                               // only relevant for Modul G
                               @NotNull List<JudgeReportModulCategory> categories,
                               @NotNull LocalDateTime startTime,
                               @NotNull LocalDateTime endTime,
                               Integer minDurationInSeconds,
                               Integer maxDurationInSeconds,
                               boolean started,
                               boolean ended,
                               @NotNull String jury,
                               Integer actualDurationInSeconds,
                               Integer minutesOverrun,
                               BigDecimal bonusKatA,
                               BigDecimal bonusKatB,
                               BigDecimal bonusKatC) {
}
