package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record VereinStageSetupDTO(@NotNull String stageSetup) {
    // TODO add flags for Podest, etc.
}