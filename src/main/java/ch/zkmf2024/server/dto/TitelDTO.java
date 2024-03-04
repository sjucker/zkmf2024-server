package ch.zkmf2024.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public record TitelDTO(Long id,
                       Modul modul,
                       String titelName,
                       String composer,
                       String arrangeur,
                       Float grad,
                       String schwierigkeitsgrad,
                       @NotNull Integer durationInSeconds,
                       boolean pflichtStueck,
                       String infoModeration) implements IsValid {

    @Override
    public boolean isValid() {
        if (modul == null) {
            return false;
        }

        return switch (modul) {
            case A, B -> pflichtStueck ||
                    (isNotBlank(titelName) && isNotBlank(composer) && durationInSeconds > 0 && (grad != null && grad > 0));
            case C -> isNotBlank(titelName) && isNotBlank(composer);
            case D, E, F -> isNotBlank(titelName) && isNotBlank(composer) && durationInSeconds > 0;
            case G -> isNotBlank(titelName) && isNotBlank(composer) && (grad != null && grad > 0);
            case H -> isNotBlank(titelName) && isNotBlank(composer) && durationInSeconds > 0 &&
                    isNotBlank(schwierigkeitsgrad);
        };
    }

    public static TitelDTO empty(Modul modul) {
        return new TitelDTO(null, modul, null, null, null, null, null, 0, false, null);
    }

    @JsonIgnore
    public boolean isNotEmpty() {
        return isNotBlank(titelName) || isNotBlank(composer) || durationInSeconds > 0;
    }
}
