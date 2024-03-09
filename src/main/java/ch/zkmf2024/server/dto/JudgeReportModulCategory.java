package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum JudgeReportModulCategory implements HasDescription {
    MODUL_G_KAT_A("Kategorie A"),
    MODUL_G_KAT_B("Kategorie B"),
    MODUL_G_KAT_C("Kategorie C");

    private final String description;

    JudgeReportModulCategory(String description) {
        this.description = description;
    }

    public static Optional<JudgeReportModulCategory> fromString(String value) {
        try {
            return Optional.of(JudgeReportModulCategory.valueOf(value));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
