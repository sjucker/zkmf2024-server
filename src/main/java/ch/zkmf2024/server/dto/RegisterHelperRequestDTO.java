package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public record RegisterHelperRequestDTO(@NotNull String name,
                                       @NotNull String email,
                                       @NotNull String comment,
                                       @NotNull List<EventDays> checkedDays) {

}
