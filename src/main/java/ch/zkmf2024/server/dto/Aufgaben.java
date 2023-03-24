package ch.zkmf2024.server.dto;

import lombok.Getter;

public enum Aufgaben {
    EGAL("egal, überall einsetzbar"),
    KOERPERLICH("körperliche Arbeit möglich"),
    MUSIKALISCH("Mithilfe beim musikalischen Wettbewerb (z.B. Betreuer, Roadie)"),
    JURYSEKRETARIAT("Jurysekretariat (für musikaffine Personen)"),
    SERVICE("Festwirtschaft (Aufnahme Bestellung, Service)"),
    BUFFET("Festwirtschaft (Buffet, Ausgabe)"),
    KUECHE("Festwirtschaft (Küche, Grill)"),
    NACHSCHUB("Festwirtschaft (Nachschub, Rückschub, Entsorgung)"),
    AUSSENSTAND("Festwirtschaft Aussenstand (Getränke, Food, Grill)"),
    RAHMENPROGRAMM("Rahmenprogramm (Auf-/Abbau für Bands)"),
    AUFBAU("Aufbau bzw. Rückbau Zelte und Infrastruktur"),
    AUFSICHT("Aufsicht, Schicht-Chef");

    @Getter
    private final String description;

    Aufgaben(String description) {
        this.description = description;
    }
}
