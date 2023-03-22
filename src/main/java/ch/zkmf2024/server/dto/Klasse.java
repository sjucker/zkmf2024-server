package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

public enum Klasse {
    // Modul A
    HOECHSTKLASSE("Höchstklasse"),
    KLASSE_1("1. Klasse"),
    KLASSE_2("2. Klasse"),
    KLASSE_3("3. Klasse"),
    KLASSE_4("4. Klasse"),

    // Modul B/H
    OBERSTUFE("Oberstufe"),
    MITTELSTUFE("Mittelstufe"),
    UNTERSTUFE("Unterstufe");

    @Getter
    private final String description;

    Klasse(String description) {
        this.description = description;
    }

    public static Optional<Klasse> fromString(String klasse) {
        try {
            return Optional.of(Klasse.valueOf(klasse));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
