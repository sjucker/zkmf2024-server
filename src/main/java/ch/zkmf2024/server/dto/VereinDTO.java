package ch.zkmf2024.server.dto;

import ch.zkmf2024.server.domain.Verein.StaerkeKlasse;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public final class VereinDTO {
    private final @NotNull String email;
    private final @NotNull VereinsangabenDTO angaben;
    private final @NotNull KontaktDTO praesident;
    private final @NotNull KontaktDTO kassier;
    private final @NotNull KontaktDTO direktion;
    private final StaerkeKlasse staerkeKlasse;
    private final Integer anzahlMusikanten;
    private final Integer anzahlDirigenten;
    private final Integer anzahlTambouren;
    private Long logoImgId;
    private Long bildImgId;
}
