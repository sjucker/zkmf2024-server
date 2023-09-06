package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record JudgeDTO(@NotNull Long id,
                       @NotNull String name,
                       @NotNull String email) {
}
