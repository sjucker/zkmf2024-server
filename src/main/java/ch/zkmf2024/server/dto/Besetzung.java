package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

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

    public static Optional<Besetzung> fromString(String besetzung) {
        try {
            return Optional.of(Besetzung.valueOf(besetzung));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
