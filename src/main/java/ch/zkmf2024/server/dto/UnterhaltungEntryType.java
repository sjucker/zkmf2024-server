package ch.zkmf2024.server.dto;

import lombok.Getter;

@Getter
public enum UnterhaltungEntryType implements HasDescription {
    FREITAG_ABEND("Freitagabend mit Stubete Gäng"),
    SAMSTAG_TAG("Samstag tagsüber"),
    SAMSTAG_ABEND("Samstagabend"),
    SONNTAG("Sonntag");

    private final String description;

    UnterhaltungEntryType(String description) {
        this.description = description;
    }

    public static UnterhaltungEntryType from(ch.zkmf2024.server.jooq.generated.enums.UnterhaltungEntryType type) {
        return valueOf(type.getLiteral());
    }
}
