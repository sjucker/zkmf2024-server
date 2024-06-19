package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum JudgeReportModulCategory implements HasDescription {
    MODUL_G_KAT_A("Kat. A"),
    MODUL_G_KAT_B("Kat. B"),
    MODUL_G_KAT_C("Kat. C");

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
