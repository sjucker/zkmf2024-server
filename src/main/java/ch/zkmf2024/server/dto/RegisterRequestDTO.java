package ch.zkmf2024.server.dto;


import javax.validation.constraints.NotNull;

public record RegisterRequestDTO(@NotNull String email,
                                 @NotNull String password,
                                 @NotNull String name,
                                 @NotNull String contactFirstName,
                                 @NotNull String contactLastName) {
}
