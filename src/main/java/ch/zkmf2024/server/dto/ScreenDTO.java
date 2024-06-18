package ch.zkmf2024.server.dto;

import ch.zkmf2024.server.jooq.generated.tables.records.ScreenRecord;

public record ScreenDTO(String locationIdentifier,
                        String header,
                        String message,
                        String cloudflareId) {
    public static ScreenDTO of(ScreenRecord pojo) {
        return new ScreenDTO(pojo.getLocationIdentifier(), pojo.getHeader(), pojo.getMessage(), pojo.getCloudflareId());
    }
}
