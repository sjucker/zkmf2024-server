package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;

public record RegisterNewsletterRequestDTO(@NotNull String name,
                                           @NotNull String email) {

}
