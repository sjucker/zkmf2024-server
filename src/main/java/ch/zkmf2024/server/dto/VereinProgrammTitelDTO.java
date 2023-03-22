package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record VereinProgrammTitelDTO(@NotNull TitelDTO titel,
                                     Integer applausInSeconds) {
}
