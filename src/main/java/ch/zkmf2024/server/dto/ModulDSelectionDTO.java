package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ModulDSelectionDTO(@NotNull Long vereinProgrammId,
                                 @NotNull String verein,
                                 @NotNull String titel1,
                                 @NotNull String titel2,
                                 @NotNull ModulDSelection selection,
                                 @NotNull LocalDateTime start) {
}
