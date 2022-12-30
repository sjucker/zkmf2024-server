package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record RegisterHelperRequestDTO(@NotNull String vorname,
                                       @NotNull String name,
                                       @NotNull String email,
                                       @NotNull String mobile,
                                       @NotNull String comment,
                                       @NotNull List<EventDays> checkedDays) {

}
