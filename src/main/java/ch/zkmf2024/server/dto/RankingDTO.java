package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record RankingDTO(@NotNull Modul modul,
                         @NotNull String modulDescription,
                         // might be null for Platzkonzerte
                         BigDecimal score,
                         @NotNull LocalDate day) {
}
