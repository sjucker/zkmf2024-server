package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record VereinSelectionDTO(@NotNull Long id,
                                 @NotNull String name) {
}
