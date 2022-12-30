package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record SurveyAnswerDTO(Long id,
                              @NotNull String vereinsName,
                              @NotNull List<String> besetzung,
                              @NotNull String staerkeKlasse,
                              @NotNull String anzahlMitglieder,
                              @NotNull String kontaktName,
                              @NotNull String kontaktEmail,
                              @NotNull String kontaktTelefon,
                              @NotNull List<String> modulAuswahl,
                              String zusageKommentar,
                              boolean absage,
                              String absageKommentar,
                              String absageKontaktaufnahme,
                              String helfer) {
}
