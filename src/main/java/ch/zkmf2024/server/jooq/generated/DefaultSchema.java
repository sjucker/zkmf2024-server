/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated;

import ch.zkmf2024.server.jooq.generated.tables.HelperRegistration;
import ch.zkmf2024.server.jooq.generated.tables.Image;
import ch.zkmf2024.server.jooq.generated.tables.Judge;
import ch.zkmf2024.server.jooq.generated.tables.JudgeReport;
import ch.zkmf2024.server.jooq.generated.tables.Kontakt;
import ch.zkmf2024.server.jooq.generated.tables.Location;
import ch.zkmf2024.server.jooq.generated.tables.NewsletterRecipient;
import ch.zkmf2024.server.jooq.generated.tables.ProgrammVorgaben;
import ch.zkmf2024.server.jooq.generated.tables.SurveyAnswer;
import ch.zkmf2024.server.jooq.generated.tables.TimetableEntry;
import ch.zkmf2024.server.jooq.generated.tables.Titel;
import ch.zkmf2024.server.jooq.generated.tables.Verein;
import ch.zkmf2024.server.jooq.generated.tables.VereinProgramm;
import ch.zkmf2024.server.jooq.generated.tables.VereinProgrammTitel;
import ch.zkmf2024.server.jooq.generated.tables.VereinStatus;
import ch.zkmf2024.server.jooq.generated.tables.Zkmf2024User;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.Arrays;
import java.util.List;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEFAULT_SCHEMA</code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>helper_registration</code>.
     */
    public final HelperRegistration HELPER_REGISTRATION = HelperRegistration.HELPER_REGISTRATION;

    /**
     * The table <code>image</code>.
     */
    public final Image IMAGE = Image.IMAGE;

    /**
     * The table <code>judge</code>.
     */
    public final Judge JUDGE = Judge.JUDGE;

    /**
     * The table <code>judge_report</code>.
     */
    public final JudgeReport JUDGE_REPORT = JudgeReport.JUDGE_REPORT;

    /**
     * The table <code>kontakt</code>.
     */
    public final Kontakt KONTAKT = Kontakt.KONTAKT;

    /**
     * The table <code>location</code>.
     */
    public final Location LOCATION = Location.LOCATION;

    /**
     * The table <code>newsletter_recipient</code>.
     */
    public final NewsletterRecipient NEWSLETTER_RECIPIENT = NewsletterRecipient.NEWSLETTER_RECIPIENT;

    /**
     * The table <code>programm_vorgaben</code>.
     */
    public final ProgrammVorgaben PROGRAMM_VORGABEN = ProgrammVorgaben.PROGRAMM_VORGABEN;

    /**
     * The table <code>survey_answer</code>.
     */
    public final SurveyAnswer SURVEY_ANSWER = SurveyAnswer.SURVEY_ANSWER;

    /**
     * The table <code>timetable_entry</code>.
     */
    public final TimetableEntry TIMETABLE_ENTRY = TimetableEntry.TIMETABLE_ENTRY;

    /**
     * The table <code>titel</code>.
     */
    public final Titel TITEL = Titel.TITEL;

    /**
     * The table <code>verein</code>.
     */
    public final Verein VEREIN = Verein.VEREIN;

    /**
     * The table <code>verein_programm</code>.
     */
    public final VereinProgramm VEREIN_PROGRAMM = VereinProgramm.VEREIN_PROGRAMM;

    /**
     * The table <code>verein_programm_titel</code>.
     */
    public final VereinProgrammTitel VEREIN_PROGRAMM_TITEL = VereinProgrammTitel.VEREIN_PROGRAMM_TITEL;

    /**
     * The table <code>verein_status</code>.
     */
    public final VereinStatus VEREIN_STATUS = VereinStatus.VEREIN_STATUS;

    /**
     * The table <code>zkmf2024_user</code>.
     */
    public final Zkmf2024User ZKMF2024_USER = Zkmf2024User.ZKMF2024_USER;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }

    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
                HelperRegistration.HELPER_REGISTRATION,
                Image.IMAGE,
                Judge.JUDGE,
                JudgeReport.JUDGE_REPORT,
                Kontakt.KONTAKT,
                Location.LOCATION,
                NewsletterRecipient.NEWSLETTER_RECIPIENT,
                ProgrammVorgaben.PROGRAMM_VORGABEN,
                SurveyAnswer.SURVEY_ANSWER,
                TimetableEntry.TIMETABLE_ENTRY,
                Titel.TITEL,
                Verein.VEREIN,
                VereinProgramm.VEREIN_PROGRAMM,
                VereinProgrammTitel.VEREIN_PROGRAMM_TITEL,
                VereinStatus.VEREIN_STATUS,
                Zkmf2024User.ZKMF2024_USER
        );
    }
}
