package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record VereinMessageDTO(@NotNull String message,
                               @NotNull LocalDateTime createdAt,
                               @NotNull String createdBy,
                               @NotNull boolean ownMessage) {
}
