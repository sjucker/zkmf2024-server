package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;

public record BandDTO(@NotNull long id,
                      @NotNull String email,
                      @NotNull String name,
                      @NotNull String contactFirstName,
                      @NotNull String contactLastName) {
}
