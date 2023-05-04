package ch.zkmf2024.server.dto;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public record VereinsinfoDTO(
        Long logoImgId,
        Long bildImgId,
        String websiteText
) implements IsValid {
    @Override
    public boolean isValid() {
        return isNotBlank(websiteText);
    }
}
