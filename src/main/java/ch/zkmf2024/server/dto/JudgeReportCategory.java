package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ch.zkmf2024.server.dto.Modul.A;
import static ch.zkmf2024.server.dto.Modul.B;

@Getter
public enum JudgeReportCategory {
    STIMMUNG_INTONATION("Stimmung und Intonation", Set.of(A, B), false),
    TONKULTUR("Tonkultur", Set.of(A, B), false),
    RHYTHMUS_METRUM("Rhythmus und Metrum", Set.of(A, B), false),
    DYNAMIK_KLANGAUSGLEICH("Dynamik und Klangausgleich", Set.of(A, B), false),
    TECHNIK_ARTIKULATION("Technik und Artikulation", Set.of(A), false),
    TECHNIK_PHRASIERUNG_ARTIKULATION("Technik, Phrasierung, Artikulation", Set.of(B), false),
    MUSIKALISCHER_AUSDRUCK("Musikalischer Ausdruck", Set.of(A, B), false),
    INTERPRETATION("Interpretation", Set.of(A), false),
    INTERPRETATION_STILEMPFINDEN("Interpretation und Stilempfinden", Set.of(B), false),

    PROGRAMMAUFBAU("Programmaufbau", Set.of(A), true),
    VIELSEITIGKEIT_STILEMPFINDEN("Vielseitigkeit und Stilempfinden", Set.of(A), true),
    SCHWIERIGKEITSGRAD("Schwierigkeitsgrad", Set.of(A), true),
    PROGRAMMWAHL("Programmwahl", Set.of(B), true),
    GESAMTEINDRUCK("Gesamteindruck", Set.of(A, B), true);

    private final String description;
    private final Set<Modul> module;
    private final boolean overall;

    JudgeReportCategory(String description, Set<Modul> module, boolean overall) {
        this.description = description;
        this.module = module;
        this.overall = overall;
    }

    public static Optional<JudgeReportCategory> fromString(String category) {
        try {
            return Optional.of(JudgeReportCategory.valueOf(category));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public static List<JudgeReportCategory> get(Modul modul, boolean overall) {
        return Arrays.stream(JudgeReportCategory.values())
                     .filter(category -> category.getModule().contains(modul) &&
                             category.isOverall() == overall)
                     .toList();
    }
}
