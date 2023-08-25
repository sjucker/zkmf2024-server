package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

import static ch.zkmf2024.server.service.ValidationUtil.isValidEmail;

public record VereinDTO(
        @NotNull String email,
        @NotNull VereinsangabenDTO angaben,
        @NotNull KontaktDTO praesident,
        @NotNull KontaktDTO direktion,
        @NotNull VereinsanmeldungDTO anmeldung,
        @NotNull VereinsinfoDTO info,
        boolean registrationConfirmed,
        @NotNull List<VereinProgrammDTO> programme
) {

    @NotNull
    public PhaseStatus getPhase1Status() {
        return isValidEmail(email) &&
                angaben.isValid() &&
                praesident.isValid() &&
                direktion.isValid() &&
                anmeldung.isValid() &&
                info.isValid() ?
                PhaseStatus.DONE :
                PhaseStatus.IN_PROGRESS;
    }

    @NotNull
    public PhaseStatus getPhase2Status() {
        // if no title added, consider it not yet started
        if (programme.stream().noneMatch(VereinProgrammDTO::hasAddedTitel)) {
            return PhaseStatus.NEW;
        }
        return programme.stream().allMatch(VereinProgrammDTO::isValid) ?
                PhaseStatus.DONE :
                PhaseStatus.IN_PROGRESS;
    }

}
