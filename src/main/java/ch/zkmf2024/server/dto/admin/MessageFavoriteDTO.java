package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record MessageFavoriteDTO(@NotNull String identifier,
                                 @NotNull String title,
                                 @NotNull String body) {
}
