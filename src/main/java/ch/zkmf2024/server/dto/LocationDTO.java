package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record LocationDTO(@NotNull Long id,
                          @NotNull String name,
                          @NotNull String address,
                          @NotNull BigDecimal latitude,
                          @NotNull BigDecimal longitude,
                          @NotNull String googleMapsAddress,
                          @NotNull String googleMapsCoordinates,
                          @NotNull LocationType type,
                          @NotNull String capacity,
                          @NotNull String modules,
                          LocationDTO einspiellokal,
                          LocationDTO instrumentendepot,
                          LocationDTO juryfeedback) {
}