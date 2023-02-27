package ch.zkmf2024.server.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public final class VereinDTO {
    private final @NotNull String email;
    private final @NotNull VereinsangabenDTO angaben;
    private final @NotNull KontaktDTO praesident;
    private final @NotNull KontaktDTO direktion;
    private final @NotNull VereinsanmeldungDTO anmeldung;
    private @NotNull VereinsinfoDTO info;
}
