package ch.zkmf2024.server.dto;

import lombok.Getter;

@Getter
public enum Einsatzzeit implements HasDescription {
    MORGEN("Morgen"),
    MITTAG("Mittag"),
    NACHMITTAG("Nachmittag"),
    ABEND("Abend"),
    NACHT("Nacht");

    private final String description;

    Einsatzzeit(String description) {
        this.description = description;
    }
}
