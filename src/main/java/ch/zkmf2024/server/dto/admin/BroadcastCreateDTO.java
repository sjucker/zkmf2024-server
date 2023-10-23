package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record BroadcastCreateDTO(@NotNull List<Long> ids,
                                 @NotNull String message) {
}
