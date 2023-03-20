package ch.zkmf2024.server.dto;

import org.apache.commons.lang3.StringUtils;

public record VereinsinfoDTO(
        Long logoImgId,
        Long bildImgId,
        String websiteText
) implements IsValid {
    @Override
    public boolean isValid() {
        return (logoImgId != null || bildImgId != null) && StringUtils.isNotBlank(websiteText);
    }
}
