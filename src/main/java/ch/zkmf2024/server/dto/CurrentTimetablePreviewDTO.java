package ch.zkmf2024.server.dto;

import ch.zkmf2024.server.util.DateUtil;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

public record CurrentTimetablePreviewDTO(TimetableCurrentDTO current,
                                         TimetablePreviewDTO next,
                                         @NotNull List<SponsorDTO> sponsoren,
                                         @NotNull LocalTime currentTime,
                                         EmergencyMessageDTO emergencyMessage) {

    public static CurrentTimetablePreviewDTO of(EmergencyMessageDTO emergencyMessage) {
        return new CurrentTimetablePreviewDTO(null,
                                              null,
                                              List.of(),
                                              DateUtil.currentTime(),
                                              emergencyMessage);
    }

    public static CurrentTimetablePreviewDTO of(TimetableCurrentDTO current) {
        return new CurrentTimetablePreviewDTO(current,
                                              null,
                                              List.of(),
                                              DateUtil.currentTime(),
                                              null);
    }
}
