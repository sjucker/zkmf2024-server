package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;

import jakarta.validation.constraints.NotNull;

public record ErrataSendDTO(@NotNull Modul modul,
                            @NotNull Klasse klasse,
                            @NotNull Besetzung besetzung) {
}
