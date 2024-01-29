package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ch.zkmf2024.server.dto.JudgeRole.JUROR_1_OPTISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_2_MUSIKALISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_3_MUSIKALISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_4_OPTISCH;
import static ch.zkmf2024.server.dto.Modul.A;
import static ch.zkmf2024.server.dto.Modul.B;
import static ch.zkmf2024.server.dto.Modul.D;
import static ch.zkmf2024.server.dto.Modul.E;

@Getter
public enum JudgeReportCategory implements HasDescription {
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
    GESAMTEINDRUCK("Gesamteindruck", Set.of(A, B), true),

    // TODO braucht es jeweils ++ und -- Beschreibung (z.B. "korrekt"- "fehlt")?
    PRAESENTATION_MELDUNG("Meldung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Präsentation", false),
    PRAESENTATION_VERHALTEN("Verhalten", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Präsentation", false),
    PRAESENTATION_AUSSTRAHLUNG("Ausstrahlung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Präsentation", false),
    PRAESENTATION_AUFSTELLUNG("Aufstellung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Präsentation", false),
    PRAESENTATION_INSTRUMENTENHALTUNG("Instrumentenhaltung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Präsentation", false),
    PRAESENTATION_AUSRICHTUNG("Ausrichtung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Präsentation", false),
    PRAESENTATION_ABSTAENDE("Abstände/Diagonalen", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Präsentation", false),
    ABMARSCH_KOMMANDI("Kommandi", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Abmarsch", false),
    ABMARSCH_ABMARSCH("Abmarsch", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Abmarsch", false),
    ABMARSCH_TAMBOURBEGINN("Tambourbeginn", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Abmarsch", false),
    ABMARSCH_INSTRUMENTE_ANHEBEN("Instrumente anheben", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Abmarsch", false),
    ABMARSCH_SPIELWECHSEL_SPIEL("Spielwechsel-Spiel", Set.of(D, E), Set.of(JUROR_1_OPTISCH), "Abmarsch", false),

    PARADE_TEMPO("Tempo", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), "Parade", false),
    PARADE_SCHRITTLAENGE("Schrittlänge", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), "Parade", false),
    PARADE_GLEICHSCHRITT("Gleichschritt", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), "Parade", false),
    PARADE_INSTRUMENTENHALTUNG("Instrumentenhaltung", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), "Parade", false),
    PARADE_AUSRICHTUNG("Ausrichtung", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), "Parade", false),
    PARADE_ABSTAENDE("Abstände/Diagonalen", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), "Parade", false),
    PARADE_GESAMTWIRKUNG("Gesamtwirkung", Set.of(D), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), "Parade", false),
    MUSIKALISCH_GESAMTEINDRUCK1("Gesamteindruck", Set.of(D), Set.of(JUROR_1_OPTISCH), "musikalischer Faktor", false),

    // TODO add modul E & F specifics

    MUSIK_STIMMUNG("Stimmung und Intonation", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), "Musik", false),
    MUSIK_TONKULTUR("Tonkultur", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), "Musik", false),
    MUSIK_RHYTHMUS("Rhythmus und Metrum", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), "Musik", false),
    MUSIK_DYNAMIK("Dynamik und Klangausgleich", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), "Musik", false),
    MUSIK_TECHNIK("Technik und Artikulation", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), "Musik", false),
    MUSIK_AUSDRUCK("Musikalischer Ausdruck", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), "Musik", false),
    MUSIK_INTERPRETATION("Musikalischer Ausdruck", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), "Musik", false),
    OPTISCH_GESAMTWIRKUNG("Gesamtwirkung", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), "optischer Faktor", false),

    SCHLUSSPHASE_KOMMANDI("Kommandi", Set.of(D), Set.of(JUROR_4_OPTISCH), "Schlussphase", false),
    SCHLUSSPHASE_SPIELWECHSEL("Spielwechsel-Tamb", Set.of(D), Set.of(JUROR_4_OPTISCH), "Schlussphase", false),
    SCHLUSSPHASE_INSTRUMENTE_SENKEN("Instrumente senken", Set.of(D), Set.of(JUROR_4_OPTISCH), "Schlussphase", false),
    SCHLUSSPHASE_ARME_SCHWINGEN("Arme schwingen", Set.of(D), Set.of(JUROR_4_OPTISCH), "Schlussphase", false),
    SCHLUSSPHASE_ANHALTEN("Anhalten der Formation", Set.of(D), Set.of(JUROR_4_OPTISCH), "Schlussphase", false),
    MUSIKALISCH_GESAMTEINDRUCK4("Gesamteindruck", Set.of(D), Set.of(JUROR_4_OPTISCH), "musikalischer Faktor", false);

    private final String description;
    private final Set<Modul> modules;
    private final Set<JudgeRole> roles;
    private final String group;
    private final boolean overall;

    JudgeReportCategory(String description, Set<Modul> modules, boolean overall) {
        this(description, modules, Set.of(), "", overall);

    }

    JudgeReportCategory(String description, Set<Modul> modules, Set<JudgeRole> roles, String group, boolean overall) {
        this.description = description;
        this.modules = modules;
        this.roles = roles;
        this.group = group;
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
                     .filter(category -> category.getModules().contains(modul) &&
                             category.isOverall() == overall)
                     .toList();
    }
}
