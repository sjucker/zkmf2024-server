package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record JudgePresentationDTO(@NotNull String name,
                                   @NotNull String modul,
                                   String cloudflareId,
                                   String presentationText) {
}
