package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record JuryLoginCreateDTO(@NotNull String name,
                                 @NotNull String firstName,
                                 @NotNull String email,
                                 @NotNull String password) {
}
