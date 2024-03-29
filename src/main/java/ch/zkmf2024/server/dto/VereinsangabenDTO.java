package ch.zkmf2024.server.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public record VereinsangabenDTO(String vereinsname,
                                String adresse,
                                Integer plz,
                                String ort,
                                String homepage,
                                String facebook,
                                String instagram,
                                String iban,
                                boolean direktionDoppeleinsatz,
                                String direktionDoppeleinsatzVerein,
                                boolean mitspielerDoppeleinsatz) implements IsValid {
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(vereinsname) &&
                Objects.nonNull(plz) &&
                StringUtils.isNotBlank(ort);
    }
}
