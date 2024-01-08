package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CoordinatesDTO(@NotNull BigDecimal latitude,
                             @NotNull BigDecimal longitude) {
}
