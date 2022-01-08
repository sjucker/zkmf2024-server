package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public record SurveyAnswerDTO(@NotNull String vereinsName,
                              @NotNull String besetzung,
                              @NotNull String staerkeKlasse,
                              @NotNull String anzahlMitglieder,
                              @NotNull String kontaktName,
                              @NotNull String kontaktEmail,
                              @NotNull String kontaktTelefon,
                              @NotNull List<String> modulAuswahl,
                              String absageKommentar,
                              String absageKontaktaufnahme,
                              String helfer) {
}
