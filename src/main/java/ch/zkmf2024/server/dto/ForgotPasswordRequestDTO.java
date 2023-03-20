package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record ForgotPasswordRequestDTO(@NotNull String email) {
}
