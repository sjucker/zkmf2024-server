/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.enums;

import ch.zkmf2024.server.jooq.generated.DefaultSchema;
import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public enum TimetableEntryType implements EnumType {

    EINSPIEL("EINSPIEL"),

    WETTSPIEL("WETTSPIEL"),

    BESPRECHUNG("BESPRECHUNG"),

    PLATZKONZERT("PLATZKONZERT"),

    MARSCHMUSIK("MARSCHMUSIK");

    private final String literal;

    private TimetableEntryType(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public String getName() {
        return "timetable_entry_type";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal. Returns
     * <code>null</code>, if no such value could be found, see {@link
     * EnumType#lookupLiteral(Class, String)}.
     */
    public static TimetableEntryType lookupLiteral(String literal) {
        return EnumType.lookupLiteral(TimetableEntryType.class, literal);
    }
}
