package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterVereinRequestDTO(@NotNull String email,
                                       @NotNull String password,
                                       @NotNull String vereinsname) {
}
