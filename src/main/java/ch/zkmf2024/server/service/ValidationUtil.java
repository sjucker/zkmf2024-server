package ch.zkmf2024.server.service;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

public final class ValidationUtil {

    // simple Email verification, just check for the existence of an @
    private static final Pattern emailPattern = Pattern.compile("^(.+)@(\\S+)$");

    private ValidationUtil() {
    }

    public static boolean isValidEmail(String email) {
        if (isBlank(email)) {
            return false;
        }
        return emailPattern.matcher(email).matches();
    }
}
