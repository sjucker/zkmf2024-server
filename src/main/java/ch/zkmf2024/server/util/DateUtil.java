package ch.zkmf2024.server.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class DateUtil {

    private DateUtil() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("Europe/Zurich"));
    }

}
