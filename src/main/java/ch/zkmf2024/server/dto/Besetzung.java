package ch.zkmf2024.server.dto;

import lombok.Getter;

public enum Besetzung {
    HARMONIE("Harmonie"),
    BRASS_BAND("Brass Band"),
    FANFARE("Fanfare"),
    TAMBOUREN("Tambouren"),
    PERKUSSIONSENSEMBLE("Perkussionsensemble");

    @Getter
    private final String description;

    Besetzung(String description) {
        this.description = description;
    }
}
