package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterNewsletterRequestDTO(@NotNull String vorname,
                                           @NotNull String name,
                                           @NotNull String email) {

}
