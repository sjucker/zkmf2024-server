package ch.zkmf2024.server.service;

import java.util.regex.Pattern;

public final class ValidationUtil {

    // simple Email verification, just check for the existence of an @
    private static final Pattern emailPattern = Pattern.compile("^(.+)@(\\S+)$");

    private ValidationUtil() {
    }

    public static boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }
}
