package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record DoppelEinsatzDTO(@NotNull VereinSelectionDTO otherVerein,
                               @NotNull String mitspielerName) {
}
