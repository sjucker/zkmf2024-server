package ch.zkmf2024.server.dto;

import lombok.Getter;

@Getter
public enum Aufgaben implements HasDescription {
    EGAL("egal, überall einsetzbar"),
    KOERPERLICH("körperliche Arbeit möglich"),
    MUSIKALISCH("Mithilfe beim musikalischen Wettbewerb am Samstag und Sonntag (z.B. Betreuer Wettspiellokal und Einspiellokal, Türsteher, Roadie)"),
    MODERATION("Moderation der Wettspiele (für sprachgewandte Personen)"),
    JURYSEKRETARIAT("Jurysekretariat (für musikaffine Personen)"),
    SERVICE("Festwirtschaft (Aufnahme Bestellung, Service)"),
    BUFFET("Festwirtschaft (Buffet, Ausgabe)"),
    KUECHE("Festwirtschaft (Küche, Grill)"),
    NACHSCHUB("Festwirtschaft (Nachschub, Rückschub, Entsorgung)"),
    AUSSENSTAND("Festwirtschaft Aussenstand (Getränke, Food, Grill)"),
    RAHMENPROGRAMM("Rahmenprogramm (Auf-/Abbau für Bands)"),
    AUFBAU("Aufbau bzw. Rückbau kleinere Infrastruktur"),
    AUFSICHT("Aufsicht, Schicht-Chef");

    private final String description;

    Aufgaben(String description) {
        this.description = description;
    }
}
