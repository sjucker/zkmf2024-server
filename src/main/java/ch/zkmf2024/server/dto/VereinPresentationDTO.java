package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record VereinPresentationDTO(@NotNull Long id,
                                    @NotNull String name,
                                    Long logoImgId,
                                    Long bildImgId,
                                    String homepage,
                                    String facebook,
                                    String instagram,
                                    String websiteText,
                                    @NotNull List<VereinTimetableEntryDTO> timetableEntries) {
}
