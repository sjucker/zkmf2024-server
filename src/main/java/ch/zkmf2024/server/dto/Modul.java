package ch.zkmf2024.server.dto;

import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;

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

    public Besetzung getRelevantBesetzung(boolean harmonie, boolean brassBand, boolean fanfare) {
        return switch (this) {
            case A, B, D, E, F -> {
                if (harmonie) {
                    yield HARMONIE;
                } else if (brassBand) {
                    yield BRASS_BAND;
                } else if (fanfare) {
                    yield FANFARE;
                } else {
                    throw new IllegalStateException("no matching Besetzung found for Modul: " + this);
                }
            }
            case C -> throw new NotImplementedException(); // TODO
            case G -> TAMBOUREN;
            case H -> PERKUSSIONSENSEMBLE;
        };
    }

    public String getFullDescription() {
        return "%s - %s".formatted(name(), getDescription());
    }
}
