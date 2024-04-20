package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AppPageDTO(@NotNull Long id,
                         @NotNull String markdown,
                         @NotNull String title,
                         @NotNull LocalDate createdAt,
                         boolean news,
                         String cloudflareId) {
}
