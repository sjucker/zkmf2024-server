package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum Besetzung implements HasDescription {
    HARMONIE("Harmonie", true),
    BRASS_BAND("Brass Band", true),
    FANFARE("Fanfare", true),
    TAMBOUREN("Tambouren", false),
    PERKUSSIONSENSEMBLE("Perkussionsensemble", false);

    private final String description;
    private final boolean relevantForModul;

    Besetzung(String description, boolean relevantForModul) {
        this.description = description;
        this.relevantForModul = relevantForModul;
    }

    public Optional<String> getRelevantDescription() {
        return relevantForModul ? Optional.of(description) : Optional.empty();
    }

    public static Optional<Besetzung> fromString(String besetzung) {
        try {
            return Optional.of(Besetzung.valueOf(besetzung));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
