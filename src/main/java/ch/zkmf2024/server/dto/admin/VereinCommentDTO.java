package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record VereinCommentDTO(@NotNull String comment,
                               @NotNull LocalDateTime createdAt,
                               @NotNull String createdBy) {
}
