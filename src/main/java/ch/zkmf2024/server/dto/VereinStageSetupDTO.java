package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record VereinStageSetupDTO(@NotNull Long vereinId,
                                  @NotNull String locationIdentifier,
                                  @NotNull String stageSetup,
                                  boolean dirigentenpodest,
                                  Integer ablagenAmount,
                                  String comment,
                                  boolean hasAdditionalImage) {
}
