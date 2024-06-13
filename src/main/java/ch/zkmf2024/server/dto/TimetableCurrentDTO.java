package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record TimetableCurrentDTO(@NotNull String vereinsname,
                                  @NotNull String direktion,
                                  @NotNull String competition,
                                  @NotNull String programmTitel,
                                  @NotNull List<String> titles) {
}
