package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record ResetPasswordRequestDTO(@NotNull String email,
                                      @NotNull String token,
                                      @NotNull String newPassword) {
}
