package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;

public record VereinCommentCreateDTO(@NotNull String comment) {
}
