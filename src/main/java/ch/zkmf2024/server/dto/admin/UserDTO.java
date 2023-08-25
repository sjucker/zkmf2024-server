package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.UserRole;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UserDTO(@NotNull String email,
                      @NotNull UserRole role,
                      LocalDateTime lastLogin) {
}
