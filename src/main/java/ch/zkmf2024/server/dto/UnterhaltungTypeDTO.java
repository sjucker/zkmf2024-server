package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record UnterhaltungTypeDTO(@NotNull UnterhaltungEntryType type,
                                  @NotNull List<UnterhaltungsEntryDTO> entries) {
}
