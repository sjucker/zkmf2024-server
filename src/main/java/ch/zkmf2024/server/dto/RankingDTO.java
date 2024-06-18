package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record RankingDTO(@NotNull Modul modul,
                         @NotNull String modulDescription,
                         // might be null for Platzkonzerte
                         BigDecimal score) {
}
