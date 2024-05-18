package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CurrentTimetablePreviewDTO(TimetablePreviewDTO current,
                                         TimetablePreviewDTO next,
                                         @NotNull List<SponsorDTO> sponsoren) {
}
