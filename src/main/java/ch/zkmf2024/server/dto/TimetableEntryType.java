package ch.zkmf2024.server.dto;

import lombok.Getter;

@Getter
public enum TimetableEntryType implements HasDescription {
    EINSPIEL("Einspiel"),
    WETTSPIEL("Wettspiel"),
    BESPRECHUNG("Besprechung"),
    PLATZKONZERT("Platzkonzert"),
    MARSCHMUSIK("Marschmusik");

    private final String description;

    TimetableEntryType(String description) {
        this.description = description;
    }

    public LocationType toLocationType() {
        return switch (this) {
            case WETTSPIEL -> LocationType.WETTSPIELLOKAL;
            case EINSPIEL -> LocationType.EINSPIELLOKAL;
            case BESPRECHUNG -> LocationType.JURYFEEDBACK;
            case PLATZKONZERT -> LocationType.PLATZKONZERT;
            case MARSCHMUSIK -> LocationType.PARADEMUSIK;
        };
    }

    public static TimetableEntryType from(ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType type) {
        return valueOf(type.getLiteral());
    }
}
