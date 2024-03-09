package ch.zkmf2024.server.dto;

import lombok.Getter;

@Getter
public enum JudgeRole implements HasDescription {
    // modul A, B
    JUROR_1("Juryvorsitz"),
    JUROR_2("Jurymitglied (ohne Gesprächsführung)"),
    JUROR_3("Jurymitglied (mit Gesprächsführung)"),

    // TODO Modul G: 4 Juroren? Und wer macht Gespräch?

    // modul D, E, F
    JUROR_1_OPTISCH("Juror 1 optisch"),
    JUROR_2_MUSIKALISCH("Juror 2 musikalisch"),
    JUROR_3_MUSIKALISCH("Juror 3 musikalisch"),
    JUROR_4_OPTISCH("Juror 4 optisch");

    private final String description;

    JudgeRole(String description) {
        this.description = description;
    }

}
