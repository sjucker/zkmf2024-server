package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record VereinProgrammDTO(long id,
                                @NotNull String modul,
                                String klasse,
                                String besetzung,
                                String titel,
                                String infoModeration,
                                Integer totalDurationInSeconds,
                                Integer minDurationInSeconds,
                                Integer maxDurationInSeconds,
                                @NotNull List<TitelDTO> availableTitel,
                                @NotNull List<VereinProgrammTitelDTO> ablauf) {
}
