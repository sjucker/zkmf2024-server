package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.Modul;

import jakarta.validation.constraints.NotNull;

public record VereinProgrammSelectionDTO(@NotNull Long id, @NotNull Modul modul,
                                         @NotNull String modulDescription,
                                         String klasse,
                                         String besetzung) {
}
