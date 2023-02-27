package ch.zkmf2024.server.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public record KontaktDTO(String vorname,
                         String nachname,
                         String adresse,
                         Integer plz,
                         String ort,
                         String email,
                         String telefonPrivat,
                         String telefonMobile) implements IsValid {
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(vorname) &&
                StringUtils.isNotBlank(nachname) &&
                StringUtils.isNotBlank(adresse) &&
                Objects.nonNull(plz) &&
                StringUtils.isNotBlank(ort) &&
                StringUtils.isNotBlank(email);
    }
}
