package ch.zkmf2024.server.dto;

import lombok.Getter;

import java.util.Optional;

public enum TambourenGrundlage {
    WIRBEL("Wirbel"),
    RUF("5er Ruf"),
    BATAFLAFLA("Bataflafla"),
    DOUBLE("Doublé");

    @Getter
    private final String description;

    TambourenGrundlage(String description) {
        this.description = description;
    }

    public static Optional<TambourenGrundlage> fromString(String tambourenGrundlage) {
        try {
            return Optional.of(TambourenGrundlage.valueOf(tambourenGrundlage));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
