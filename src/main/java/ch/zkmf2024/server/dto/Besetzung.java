package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum Besetzung implements HasDescription {
    HARMONIE("Harmonie"),
    BRASS_BAND("Brass Band"),
    FANFARE("Fanfare"),
    TAMBOUREN("Tambouren"),
    PERKUSSIONSENSEMBLE("Perkussionsensemble");

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
