package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record AppPageDTO(@NotNull Long id,
                         @NotNull String markdown,
                         String cloudflareId) {
}
