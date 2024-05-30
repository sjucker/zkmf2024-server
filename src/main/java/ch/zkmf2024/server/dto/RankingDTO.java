package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record RankingDTO(@NotNull String modul,
                         @NotNull BigDecimal score) {
}
