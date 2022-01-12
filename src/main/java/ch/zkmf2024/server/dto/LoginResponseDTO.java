package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;

public record LoginResponseDTO(@NotNull String email, @NotNull UserRole role, @NotNull String jwt) {
}
