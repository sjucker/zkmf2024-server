package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_A;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_B;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_C;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_1;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_1_OPTISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_2;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_2_MUSIKALISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_3;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_3_MUSIKALISCH;
import static ch.zkmf2024.server.dto.JudgeRole.JUROR_4_OPTISCH;
import static ch.zkmf2024.server.dto.Modul.A;
import static ch.zkmf2024.server.dto.Modul.B;
import static ch.zkmf2024.server.dto.Modul.D;
import static ch.zkmf2024.server.dto.Modul.E;
import static ch.zkmf2024.server.dto.Modul.G;
import static ch.zkmf2024.server.dto.Modul.H;

@Getter
public enum JudgeReportCategory implements HasDescription {
    STIMMUNG_INTONATION("Stimmung und Intonation", Set.of(A, B), false),
    TONKULTUR("Tonkultur", Set.of(A, B), false),
    RHYTHMUS_METRUM("Rhythmus und Metrum", Set.of(A, B, H), false),
    DYNAMIK_KLANGAUSGLEICH("Dynamik und Klangausgleich", Set.of(A, B, H), false),
    TECHNIK_ARTIKULATION("Technik und Artikulation", Set.of(A), false),
    TECHNIK_PHRASIERUNG_ARTIKULATION("Technik, Phrasierung, Artikulation", Set.of(B), false),
    TONKULTUR_TECHNIK_ARTIKULATION("Tonkultur, Technik und Artikulation", Set.of(H), false),
    MUSIKALISCHER_AUSDRUCK("Musikalischer Ausdruck", Set.of(A, B, H), false),
    INTERPRETATION("Interpretation", Set.of(A), false),
    INTERPRETATION_STILEMPFINDEN("Interpretation und Stilempfinden", Set.of(B, H), false),

    ORGANISATION_ENSEMBLEAUFSTELLUNG("Organisation und Ensembleaufstellung", Set.of(H), true),
    PROGRAMMAUFBAU("Programmaufbau", Set.of(A, H), true),
    VIELSEITIGKEIT_STILEMPFINDEN("Vielseitigkeit und Stilempfinden", Set.of(A), true),
    SCHWIERIGKEITSGRAD("Schwierigkeitsgrad", Set.of(A, H), true),
    PROGRAMMWAHL("Programmwahl", Set.of(B), true),
    GESAMTEINDRUCK("Gesamteindruck", Set.of(A, B, H), true),

    PRAESENTATION_MELDUNG("Meldung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("korrekt", "zögerlich", "unklar", "fehlt"), "Präsentation", false),
    PRAESENTATION_VERHALTEN("Verhalten", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("überzeugend", "gespannt", "zappelig", "unruhig"), "Präsentation", false),
    PRAESENTATION_AUSSTRAHLUNG("Ausstrahlung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("sicher", "mit einem Lächeln", "zaghaft", "zurückhaltend"), "Präsentation", false),
    PRAESENTATION_AUFSTELLUNG("Aufstellung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("korrekt", "Perkussion falsch", "letzte Reihe nicht vollständig", "ungeeignet"), "Präsentation", false),
    PRAESENTATION_INSTRUMENTENHALTUNG("Instrumentenhaltung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("einheitlich", "einzelne Fehler", "viele Fehler", "unterschiedlich"), "Präsentation", false),
    PRAESENTATION_AUSRICHTUNG("Ausrichtung", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("genau", "klar", "unordentlich", "ungenau"), "Präsentation", false),
    PRAESENTATION_ABSTAENDE("Abstände/Diagonalen", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("gleichmässig", "exakt", "ungenau", "ungleichmässig"), "Präsentation", false),
    ABMARSCH_KOMMANDI("Kommandi", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("klar", "unsauber", "unklar", "fehlen"), "Abmarsch", false),
    ABMARSCH_ABMARSCH("Abmarsch", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("perfekt", "schwungvoll", "wacklig", "ungeordnet"), "Abmarsch", false),
    ABMARSCH_TAMBOURBEGINN("Tambourbeginn", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("überzeugend", "kleine Fehler", "unrhythmisch", "wacklig"), "Abmarsch", false),
    ABMARSCH_INSTRUMENTE_ANHEBEN("Instrumente anheben", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("präzis", "ordentlich", "chaotisch", "unpräzis"), "Abmarsch", false),
    ABMARSCH_SPIELWECHSEL_SPIEL("Spielwechsel-Spiel", Set.of(D, E), Set.of(JUROR_1_OPTISCH), List.of("bestimmt", "prägnant", "ungeordnet", "misslungen"), "Abmarsch", false),

    PARADE_TEMPO("Tempo", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), List.of("passend", "zu schnell", "zu langsam", "unpassend"), "Parade", false),
    PARADE_SCHRITTLAENGE("Schrittlänge", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), List.of("optimal", "zu kurz", "zu lang", "ungeeignet"), "Parade", false),
    PARADE_GLEICHSCHRITT("Gleichschritt", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), List.of("präzis", "genau", "wacklig", "nicht im Schritt"), "Parade", false),
    PARADE_INSTRUMENTENHALTUNG("Instrumentenhaltung", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), List.of("einheitlich", "einzelne Fehler", "viele Fehler", "unterschiedlich"), "Parade", false),
    PARADE_AUSRICHTUNG("Ausrichtung", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), List.of("genau", "klar", "unordentlich", "ungenau"), "Parade", false),
    PARADE_ABSTAENDE("Abstände/Diagonalen", Set.of(D, E), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), List.of("exakt", "gleichmässig", "ungenau", "ungleichmässig"), "Parade", false),
    PARADE_GESAMTWIRKUNG("Gesamtwirkung", Set.of(D), Set.of(JUROR_1_OPTISCH, JUROR_4_OPTISCH), List.of("packend", "schwungvoll", "fahl", "blass"), "Parade", false),
    MUSIKALISCH_GESAMTEINDRUCK1("Gesamteindruck", Set.of(D), Set.of(JUROR_1_OPTISCH), List.of("mitreissend", "aufheiternd", "langweilig", "monoton"), "musikalischer Faktor", false),

    MUSIK_STIMMUNG("Stimmung und Intonation", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), List.of(), "Musik", false),
    MUSIK_TONKULTUR("Tonkultur", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), List.of(), "Musik", false),
    MUSIK_RHYTHMUS("Rhythmus und Metrum", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), List.of(), "Musik", false),
    MUSIK_DYNAMIK("Dynamik und Klangausgleich", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), List.of(), "Musik", false),
    MUSIK_TECHNIK("Technik und Artikulation", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), List.of(), "Musik", false),
    MUSIK_AUSDRUCK("Musikalischer Ausdruck", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), List.of(), "Musik", false),
    MUSIK_INTERPRETATION("Interpretation", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), List.of(), "Musik", false),
    OPTISCH_GESAMTWIRKUNG("Gesamtwirkung", Set.of(D), Set.of(JUROR_2_MUSIKALISCH, JUROR_3_MUSIKALISCH), List.of(), "optischer Faktor", false),

    SCHLUSSPHASE_KOMMANDI("Kommandi", Set.of(D), Set.of(JUROR_4_OPTISCH), List.of("klar", "unsauber", "unklar", "fehlen"), "Schlussphase", false),
    SCHLUSSPHASE_SPIELWECHSEL("Spielwechsel-Tamb", Set.of(D), Set.of(JUROR_4_OPTISCH), List.of("bestimmt", "prägnant", "ungeordnet", "misslungen"), "Schlussphase", false),
    SCHLUSSPHASE_INSTRUMENTE_SENKEN("Instrumente senken", Set.of(D), Set.of(JUROR_4_OPTISCH), List.of("präzis", "ordentlich", "chaotisch", "unpräzis"), "Schlussphase", false),
    SCHLUSSPHASE_ARME_SCHWINGEN("Arme schwingen", Set.of(D), Set.of(JUROR_4_OPTISCH), List.of("synchron", "unsauber", "mechanisch", "wacklig"), "Schlussphase", false),
    SCHLUSSPHASE_ANHALTEN("Anhalten der Formation", Set.of(D), Set.of(JUROR_4_OPTISCH), List.of("perfekt", "ordentlich", "chaotisch", "ungeordnet"), "Schlussphase", false),
    MUSIKALISCH_GESAMTEINDRUCK4("Gesamteindruck", Set.of(D), Set.of(JUROR_4_OPTISCH), List.of("mitreissend", "aufheiternd", "langweilig", "monoton"), "musikalischer Faktor", false),

    // Modul G
    GRUNDLAGE_1("Grundlage 1 - Technische Ausführung", Set.of(G), Set.of(MODUL_G_KAT_A), true),
    GRUNDLAGE_1_ABZUG("Grundlage 1 - Abzug", Set.of(G), Set.of(MODUL_G_KAT_A), true),
    GRUNDLAGE_2("Grundlage 2 - Technische Ausführung", Set.of(G), Set.of(MODUL_G_KAT_A), true),
    GRUNDLAGE_2_ABZUG("Grundlage 2 - Abzug", Set.of(G), Set.of(MODUL_G_KAT_A), true),
    // Kat A & B
    TECHNISCHE_AUSFUEHRUNG("Technische Ausführung", Set.of(G), Set.of(MODUL_G_KAT_A, MODUL_G_KAT_B), false),
    RHYTHMIK("Rhythmik", Set.of(G), Set.of(MODUL_G_KAT_A, MODUL_G_KAT_B), false),
    DYNAMIK("Dynamik", Set.of(G), Set.of(MODUL_G_KAT_A, MODUL_G_KAT_B), false),
    ABZUG("Abzug", Set.of(G), Set.of(MODUL_G_KAT_A, MODUL_G_KAT_B), false),
    // Kat C
    BEWERTUNG_TAMBOUREN("Bewertung Tambouren", Set.of(G), Set.of(MODUL_G_KAT_C), false),
    BEWERTUNG_PERKUSSION_UND_KLEINPERKUSSION("Bewertung Perkussion und Kleinperkussion", Set.of(G), Set.of(MODUL_G_KAT_C), false),
    RHYTHMUS_UND_ZUSAMMENSPIEL("Rhythmus und Zusammenspiel", Set.of(G), Set.of(MODUL_G_KAT_C), false),
    DYNAMIK_UND_AUSGEWOGENHEIT("Dynamik und Ausgewogenheit", Set.of(G), Set.of(MODUL_G_KAT_C), false),
    STIMMUNG_KLANG_UND_WAHL_INSTRUMENTE("Stimmung, Klang und Wahl der Instrumente", Set.of(G), Set.of(MODUL_G_KAT_C), false),
    MUSIKALISCHE_UMSETZUNG_VISUELLE_SCHLEGEL_EFFEKTE("Musikalische Umsetzung, visuelle Schlegel-Effekte", Set.of(G), Set.of(MODUL_G_KAT_C), false);

    private final String description;
    private final Set<Modul> modules;
    private final Set<JudgeReportModulCategory> categories;
    private final Set<JudgeRole> roles;
    // if set should always have 4 items, in following order: ++, +, -, --
    private final List<String> ratingDescriptions;
    private final String group;
    private final boolean overall;

    JudgeReportCategory(String description, Set<Modul> modules, Set<JudgeReportModulCategory> categories, boolean overall) {
        this(description, modules, categories, Set.of(JUROR_1, JUROR_2, JUROR_3), List.of(), "", overall);
    }

    JudgeReportCategory(String description, Set<Modul> modules, boolean overall) {
        this(description, modules, Set.of(), Set.of(JUROR_1, JUROR_2, JUROR_3), List.of(), "", overall);
    }

    JudgeReportCategory(String description, Set<Modul> modules, Set<JudgeRole> roles, List<String> ratingDescriptions, String group, boolean overall) {
        this(description, modules, Set.of(), roles, ratingDescriptions, group, overall);
    }

    JudgeReportCategory(String description, Set<Modul> modules, Set<JudgeReportModulCategory> categories, Set<JudgeRole> roles, List<String> ratingDescriptions, String group, boolean overall) {
        this.description = description;
        this.modules = modules;
        this.categories = categories;
        this.roles = roles;
        this.ratingDescriptions = ratingDescriptions;
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

    public static List<JudgeReportCategory> get(Modul modul, JudgeReportModulCategory category, JudgeRole role, boolean overall) {
        return Arrays.stream(JudgeReportCategory.values())
                     .filter(c -> c.getModules().contains(modul) &&
                             (category == null || c.getCategories().contains(category)) &&
                             c.getRoles().contains(role) &&
                             c.isOverall() == overall)
                     .toList();
    }
}
