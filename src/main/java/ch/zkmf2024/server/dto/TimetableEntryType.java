package ch.zkmf2024.server.dto;

public enum TimetableEntryType {
    EINSPIEL,
    WETTSPIEL,
    BESPRECHUNG,
    PLATZKONZERT,
    MARSCHMUSIK;

    public LocationType toLocationType() {
        return switch (this) {
            case WETTSPIEL -> LocationType.WETTSPIELLOKAL;
            case EINSPIEL -> LocationType.EINSPIELLOKAL;
            case BESPRECHUNG -> LocationType.JURYFEEDBACK;
            case PLATZKONZERT -> LocationType.PLATZKONZERT;
            case MARSCHMUSIK -> LocationType.PARADEMUSIK;
        };
    }
}
