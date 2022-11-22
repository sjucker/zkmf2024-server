package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;

public record VerifyEmailRequestDTO(@NotNull String email,
                                    @NotNull String verification) {
}