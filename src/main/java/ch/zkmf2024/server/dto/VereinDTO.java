package ch.zkmf2024.server.dto;

import ch.zkmf2024.server.dto.admin.VereinErrataDTO;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ch.zkmf2024.server.util.ValidationUtil.isValidEmail;

public record VereinDTO(
        @NotNull String email,
        @NotNull VereinsangabenDTO angaben,
        @NotNull List<DoppelEinsatzDTO> doppelEinsatz,
        @NotNull KontaktDTO praesident,
        @NotNull KontaktDTO direktion,
        @NotNull VereinsanmeldungDTO anmeldung,
        @NotNull VereinsinfoDTO info,
        boolean registrationConfirmed,
        @NotNull List<VereinProgrammDTO> programme,
        @NotNull VereinsanmeldungDetailDTO anmeldungDetail,
        boolean phase1Done,
        boolean phase2Done,
        boolean phase4Done,
        String phase2ConfirmedBy,
        LocalDateTime phase2ConfirmedAt,
        LocalDateTime phase4ConfirmedAt,
        LocalDateTime stageSetupConfirmedAt,
        @NotNull List<TimetableOverviewEntryDTO> timetableEntries,
        @NotNull List<VereinMessageDTO> messages,
        @NotNull List<VereinErrataDTO> errata,
        @NotNull LocalTime lunchTime,
        LocationDTO instrumentenDepot,
        LocationDTO instrumentenDepotParademusik,
        // used for client to signal that programm was updated
        boolean programmUpdated,
        List<JudgeReportFeedbackSelectionDTO> availableFeedbacks
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

    @NotNull
    public PhaseStatus getPhase4Status() {
        return anmeldungDetail.isValid() ? PhaseStatus.DONE : PhaseStatus.IN_PROGRESS;
    }

}
