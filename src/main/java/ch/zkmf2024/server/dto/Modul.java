package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

import static ch.zkmf2024.server.dto.Besetzung.BRASS_BAND;
import static ch.zkmf2024.server.dto.Besetzung.FANFARE;
import static ch.zkmf2024.server.dto.Besetzung.HARMONIE;
import static ch.zkmf2024.server.dto.Besetzung.PERKUSSIONSENSEMBLE;
import static ch.zkmf2024.server.dto.Besetzung.TAMBOUREN;

@Getter
public enum Modul implements HasDescription {
    A("Konzertmusik", false),
    B("Unterhaltungsmusik", false),
    C("Platzkonzerte", false),
    D("Parademusik traditionell", true),
    E("Parademusik mit Evolutionen", true),
    F("Hallen-/Rasenshow", true),
    G("Tambouren", false),
    H("Perkussionsensembles", false);

    private final String description;
    private final boolean parademusik;

    Modul(String description, boolean parademusik) {
        this.description = description;
        this.parademusik = parademusik;
    }

    public Optional<Klasse> getRelevantKlasse(String klasseA, String klasseB, String klasseH) {
        return switch (this) {
            case A -> Klasse.fromString(klasseA);
            case B -> Klasse.fromString(klasseB);
            case H -> Klasse.fromString(klasseH);
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
