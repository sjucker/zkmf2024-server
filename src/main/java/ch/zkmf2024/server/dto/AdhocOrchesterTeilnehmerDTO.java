package ch.zkmf2024.server.dto;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public record AdhocOrchesterTeilnehmerDTO(String name,
                                          String email,
                                          String instrument) {

    public boolean isNotEmpty() {
        return isNotBlank(name) && isNotBlank(email) && isNotBlank(instrument);
    }
}
