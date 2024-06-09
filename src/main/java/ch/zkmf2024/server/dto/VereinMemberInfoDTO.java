package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

public record VereinMemberInfoDTO(@NotNull List<TimetableOverviewEntryDTO> timetableEntries,
                                  @NotNull LocalTime lunchTime,
                                  LocationDTO instrumentenDepot,
                                  LocationDTO instrumentenDepotParademusik) {
}
