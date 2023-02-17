package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record RegisterHelperRequestDTO(
        @NotNull String email,
        @NotNull String name,
        @NotNull String vorname,
        @NotNull String adresse,
        @NotNull String plzOrt,
        @NotNull LocalDate geburtsdatum,
        @NotNull String telefon,
        @NotNull String vereinszugehoerigkeit,
        @NotNull List<Aufgaben> aufgaben,
        @NotNull String anzahlEinsaetze,
        @NotNull List<Einsatzzeit> einsatzMittwoch,
        @NotNull List<Einsatzzeit> einsatzDonnerstag,
        @NotNull List<Einsatzzeit> einsatzFreitag,
        @NotNull List<Einsatzzeit> einsatzSamstag,
        @NotNull List<Einsatzzeit> einsatzSonntag,
        @NotNull List<Einsatzzeit> einsatzMontag,
        @NotNull List<Einsatzzeit> einsatzDienstag,
        @NotNull String groesseShirt,
        @NotNull String comment) {
}
