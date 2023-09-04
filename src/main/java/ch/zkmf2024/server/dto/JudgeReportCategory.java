package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum JudgeReportCategory {
    STIMMUNG_INTONATION("Stimmung und Intonation"),
    RHYTHMUS_METRUM("Rhythmus und Metrum"),
    DYNAMIK_KLANGAUSGLEICH("Dynamik und Klangausgleich"),
    TONKULTUR_TECHNIK_ARTIKULATION("Tonkultur, Technik und Artikulation"),
    MUSIKALISCHER_AUSDRUCK("Musikalischer Ausdruck"),
    INTERPRETATION("Interpretation");

    private final String description;

    JudgeReportCategory(String description) {
        this.description = description;
    }

    public static Optional<JudgeReportCategory> fromString(String category) {
        try {
            return Optional.of(JudgeReportCategory.valueOf(category));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
