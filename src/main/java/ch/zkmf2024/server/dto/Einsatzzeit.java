package ch.zkmf2024.server.dto;

import lombok.Getter;

public enum Einsatzzeit {
    MORGEN("Morgen"),
    MITTAG("Mittag"),
    NACHMITTAG("Nachmittag"),
    ABEND("Abend"),
    NACHT("Nacht");

    @Getter
    private final String description;

    Einsatzzeit(String description) {
        this.description = description;
    }
}
