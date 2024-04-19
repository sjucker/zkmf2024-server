package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record AppPageCreateDTO(@NotNull String markdown,
                               @NotNull String title,
                               boolean news,
                               String cloudflareId) {
}
