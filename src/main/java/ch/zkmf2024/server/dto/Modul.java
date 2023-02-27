package ch.zkmf2024.server.dto;

import lombok.Getter;

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
}
