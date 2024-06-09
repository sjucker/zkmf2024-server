package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record MessageMemberDTO(@NotNull String identifier,
                               @NotNull String title,
                               @NotNull String body,
                               @NotNull String route) {
}
