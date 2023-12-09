package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record SponsorDTO(@NotNull String name,
                         @NotNull String cloudflareId,
                         String url) {
}
