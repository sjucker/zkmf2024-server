package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(@NotNull String email, @NotNull String password) {
}
