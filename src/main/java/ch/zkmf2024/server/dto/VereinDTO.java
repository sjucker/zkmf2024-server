package ch.zkmf2024.server.dto;

import ch.zkmf2024.server.domain.Verein.StaerkeKlasse;

import javax.validation.constraints.NotNull;

public record VereinDTO(@NotNull String email,
                        @NotNull VereinsangabenDTO angaben,
                        @NotNull KontaktDTO praesident,
                        @NotNull KontaktDTO kassier,
                        @NotNull KontaktDTO direktion,
                        StaerkeKlasse staerkeKlasse,
                        Integer anzahlMusikanten,
                        Integer anzahlDirigenten,
                        Integer anzahlTambouren) {
}
