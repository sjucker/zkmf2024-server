package ch.zkmf2024.server.dto;

import lombok.Getter;

public enum Klasse {
    // Modul A
    HOECHSTKLASSE("Höchstklasse"),
    KLASSE_1("1. Klasse"),
    KLASSE_2("2. Klasse"),
    KLASSE_3("3. Klasse"),
    KLASSE_4("4. Klasse"),

    // Modul B
    OBERSTUFE("Oberstufe"),
    MITTELSTUFE("Mittelstufe"),
    UNTERSTUFE("Unterstufe");

    @Getter
    private final String description;

    Klasse(String description) {
        this.description = description;
    }
}
