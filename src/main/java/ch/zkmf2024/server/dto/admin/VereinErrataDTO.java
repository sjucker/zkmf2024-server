package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record VereinErrataDTO(@NotNull String description,
                              @NotNull String text) {
}
