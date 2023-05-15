package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

import static ch.zkmf2024.server.dto.Besetzung.BRASS_BAND;
import static ch.zkmf2024.server.dto.Besetzung.FANFARE;
import static ch.zkmf2024.server.dto.Besetzung.HARMONIE;
import static ch.zkmf2024.server.dto.Besetzung.PERKUSSIONSENSEMBLE;
import static ch.zkmf2024.server.dto.Besetzung.TAMBOUREN;

public enum Modul {
    A("Konzertmusik"),
    B("Unterhaltungsmusik"),
    C("Platzkonzerte"),
    D("Parademusik traditionell"),
    E("Parademusik mit Evolutionen"),
    F("Hallen-/Rasenshow"),
    G("Tambouren"),
    H("Perkussionsensembles");

    @Getter
    private final String description;

    Modul(String description) {
        this.description = description;
    }

    public Optional<Klasse> getRelevantKlasse(Klasse klasseA, Klasse klasseB, Klasse klasseH) {
        return switch (this) {
            case A -> Optional.ofNullable(klasseA);
            case B -> Optional.ofNullable(klasseB);
            case H -> Optional.ofNullable(klasseH);
            default -> Optional.empty();
        };
    }

    public Optional<Besetzung> getRelevantBesetzung(boolean harmonie, boolean brassBand, boolean fanfare) {
        return switch (this) {
            case A, B, D, E, F -> {
                if (harmonie) {
                    yield Optional.of(HARMONIE);
                } else if (brassBand) {
                    yield Optional.of(BRASS_BAND);
                } else if (fanfare) {
                    yield Optional.of(FANFARE);
                } else {
                    throw new IllegalStateException("no matching Besetzung found for Modul: " + this);
                }
            }
            case C -> Optional.empty();
            case G -> Optional.of(TAMBOUREN);
            case H -> Optional.of(PERKUSSIONSENSEMBLE);
        };
    }

    public String getFullDescription() {
        return "%s - %s".formatted(name(), getDescription());
    }
}
