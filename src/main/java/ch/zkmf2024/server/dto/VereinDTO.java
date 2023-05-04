package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record VereinDTO(
        @NotNull String email,
        @NotNull VereinsangabenDTO angaben,
        @NotNull KontaktDTO praesident,
        @NotNull KontaktDTO direktion,
        @NotNull VereinsanmeldungDTO anmeldung,
        @NotNull VereinsinfoDTO info,
        boolean registrationConfirmed
) {

    @NotNull
    public PhaseStatus getPhase1Status() {
        return angaben.isValid() &&
                praesident.isValid() &&
                direktion.isValid() &&
                anmeldung.isValid() &&
                info.isValid() ?
                PhaseStatus.DONE :
                PhaseStatus.IN_PROGRESS;
    }

}
