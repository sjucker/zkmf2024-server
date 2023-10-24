package ch.zkmf2024.server.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class FormatUtil {

    private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("de-CH");

    private FormatUtil() {
    }

    public static String formatDate(LocalDate localDate) {
        return formatDate(localDate, false);
    }

    public static String formatDate(LocalDate localDate, boolean withWeekDay) {
        if (localDate == null) {
            return "";
        }
        return localDate.format(ofPattern(withWeekDay ? "E, dd.MM.yyyy" : "dd.MM.yyyy").withLocale(DEFAULT_LOCALE));
    }

    public static String formatTime(LocalTime localTime) {
        if (localTime == null) {
            return "";
        }
        return localTime.format(ofPattern("HH:mm"));
    }
}
