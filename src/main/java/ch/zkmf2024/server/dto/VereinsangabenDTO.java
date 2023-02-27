package ch.zkmf2024.server.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public record VereinsangabenDTO(String vereinsname,
                                String adresse,
                                Integer plz,
                                String ort,
                                String homepage,
                                String iban) implements IsValid {
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(vereinsname) &&
                StringUtils.isNotBlank(adresse) &&
                Objects.nonNull(plz) &&
                StringUtils.isNotBlank(ort);
    }
}
