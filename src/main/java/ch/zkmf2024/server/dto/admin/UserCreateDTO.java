package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.UserRole;

import jakarta.validation.constraints.NotNull;

public record UserCreateDTO(@NotNull String email,
                            @NotNull UserRole role,
                            @NotNull String password) {
}
