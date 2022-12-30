package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequestDTO(@NotNull String oldPassword, @NotNull String newPassword) {
}
