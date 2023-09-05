package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record LocationSelectionDTO(@NotNull Long id,
                                   @NotNull String name) {
}
