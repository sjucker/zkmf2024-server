package ch.zkmf2024.server.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public final class DateUtil {

    private DateUtil() {
    }

    public static OffsetDateTime now() {
        return OffsetDateTime.now(ZoneId.of("Europe/Zurich"));
    }

    public static LocalDateTime toLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }

}
