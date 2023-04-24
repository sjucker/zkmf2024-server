package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.PhaseStatus;

import jakarta.validation.constraints.NotNull;

public record VereinOverviewDTO(
        long id,
        @NotNull String vereinsname,
        boolean modulA,
        boolean modulB,
        boolean modulC,
        boolean modulD,
        boolean modulE,
        boolean modulF,
        boolean modulG,
        boolean modulH,
        Klasse klasseModulA,
        Klasse klasseModulB,
        Klasse klasseModulH,
        boolean harmonie,
        boolean brassBand,
        boolean fanfare,
        boolean tambouren,
        boolean perkussionsensemble,
        @NotNull PhaseStatus phase1,
        @NotNull PhaseStatus phase2
) {
}
