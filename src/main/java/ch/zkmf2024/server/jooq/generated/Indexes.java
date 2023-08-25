/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated;

import ch.zkmf2024.server.jooq.generated.tables.Titel;
import ch.zkmf2024.server.jooq.generated.tables.VereinComment;
import ch.zkmf2024.server.jooq.generated.tables.VereinProgramm;
import ch.zkmf2024.server.jooq.generated.tables.VereinProgrammTitel;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

/**
 * A class modelling indexes of tables in the default schema.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index VEREIN_PROGRAMM_TITEL_FK_TITEL = Internal.createIndex(DSL.name("fk_titel"), VereinProgrammTitel.VEREIN_PROGRAMM_TITEL, new OrderField[]{VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_TITEL}, false);
    public static final Index TITEL_FK_VEREIN = Internal.createIndex(DSL.name("fk_verein"), Titel.TITEL, new OrderField[]{Titel.TITEL.FK_VEREIN}, false);
    public static final Index VEREIN_COMMENT_FK_VEREIN = Internal.createIndex(DSL.name("fk_verein"), VereinComment.VEREIN_COMMENT, new OrderField[]{VereinComment.VEREIN_COMMENT.FK_VEREIN}, false);
    public static final Index VEREIN_PROGRAMM_FK_VEREIN = Internal.createIndex(DSL.name("fk_verein"), VereinProgramm.VEREIN_PROGRAMM, new OrderField[]{VereinProgramm.VEREIN_PROGRAMM.FK_VEREIN}, false);
}
