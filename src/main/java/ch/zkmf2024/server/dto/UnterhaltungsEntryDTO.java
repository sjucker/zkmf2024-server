package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record UnterhaltungsEntryDTO(@NotNull UnterhaltungEntryType type,
                                    @NotNull LocalDate date,
                                    @NotNull LocalTime start,
                                    LocalTime end,
                                    @NotNull String title,
                                    String subtitle,
                                    @NotNull LocationDTO location,
                                    String cloudflareId,
                                    String vereinIdentifier,
                                    String unterhaltungIdentifier) {
}
