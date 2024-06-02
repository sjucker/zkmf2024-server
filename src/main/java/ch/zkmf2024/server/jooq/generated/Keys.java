/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated;

import ch.zkmf2024.server.jooq.generated.tables.AppPage;
import ch.zkmf2024.server.jooq.generated.tables.CurrentlyPlaying;
import ch.zkmf2024.server.jooq.generated.tables.EmergencyMessage;
import ch.zkmf2024.server.jooq.generated.tables.Errata;
import ch.zkmf2024.server.jooq.generated.tables.FestprogrammEntry;
import ch.zkmf2024.server.jooq.generated.tables.HelperRegistration;
import ch.zkmf2024.server.jooq.generated.tables.Image;
import ch.zkmf2024.server.jooq.generated.tables.Judge;
import ch.zkmf2024.server.jooq.generated.tables.JudgeReport;
import ch.zkmf2024.server.jooq.generated.tables.JudgeReportComment;
import ch.zkmf2024.server.jooq.generated.tables.JudgeReportRating;
import ch.zkmf2024.server.jooq.generated.tables.Kontakt;
import ch.zkmf2024.server.jooq.generated.tables.Location;
import ch.zkmf2024.server.jooq.generated.tables.NewsletterRecipient;
import ch.zkmf2024.server.jooq.generated.tables.NotificationSent;
import ch.zkmf2024.server.jooq.generated.tables.ProgrammVorgaben;
import ch.zkmf2024.server.jooq.generated.tables.Ranking;
import ch.zkmf2024.server.jooq.generated.tables.RankingPenalty;
import ch.zkmf2024.server.jooq.generated.tables.Sponsor;
import ch.zkmf2024.server.jooq.generated.tables.SurveyAnswer;
import ch.zkmf2024.server.jooq.generated.tables.TimetableEntry;
import ch.zkmf2024.server.jooq.generated.tables.Titel;
import ch.zkmf2024.server.jooq.generated.tables.UnterhaltungEntry;
import ch.zkmf2024.server.jooq.generated.tables.Verein;
import ch.zkmf2024.server.jooq.generated.tables.VereinAnmeldungAdhocOrchester;
import ch.zkmf2024.server.jooq.generated.tables.VereinAnmeldungDetail;
import ch.zkmf2024.server.jooq.generated.tables.VereinAnmeldungNichtmitglieder;
import ch.zkmf2024.server.jooq.generated.tables.VereinComment;
import ch.zkmf2024.server.jooq.generated.tables.VereinDoppeleinsatz;
import ch.zkmf2024.server.jooq.generated.tables.VereinMessage;
import ch.zkmf2024.server.jooq.generated.tables.VereinProgramm;
import ch.zkmf2024.server.jooq.generated.tables.VereinProgrammTitel;
import ch.zkmf2024.server.jooq.generated.tables.VereinStatus;
import ch.zkmf2024.server.jooq.generated.tables.Zkmf2024User;
import ch.zkmf2024.server.jooq.generated.tables.records.AppPageRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.CurrentlyPlayingRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.EmergencyMessageRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.ErrataRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.FestprogrammEntryRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.HelperRegistrationRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.ImageRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.JudgeRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.JudgeReportCommentRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.JudgeReportRatingRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.JudgeReportRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.KontaktRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.LocationRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.NewsletterRecipientRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.NotificationSentRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.ProgrammVorgabenRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.RankingPenaltyRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.RankingRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.SponsorRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.SurveyAnswerRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.TimetableEntryRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.TitelRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.UnterhaltungEntryRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinAnmeldungAdhocOrchesterRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinAnmeldungDetailRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinAnmeldungNichtmitgliederRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinCommentRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinDoppeleinsatzRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinMessageRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinProgrammRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinProgrammTitelRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinStatusRecord;
import ch.zkmf2024.server.jooq.generated.tables.records.Zkmf2024UserRecord;
import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AppPageRecord> PK_APP_PAGE = Internal.createUniqueKey(AppPage.APP_PAGE, DSL.name("pk_app_page"), new TableField[]{AppPage.APP_PAGE.ID}, true);
    public static final UniqueKey<CurrentlyPlayingRecord> PK_CURRENTLY_PLAYING = Internal.createUniqueKey(CurrentlyPlaying.CURRENTLY_PLAYING, DSL.name("pk_currently_playing"), new TableField[]{CurrentlyPlaying.CURRENTLY_PLAYING.ID}, true);
    public static final UniqueKey<EmergencyMessageRecord> PK_EMERGENCY_MESSAGE = Internal.createUniqueKey(EmergencyMessage.EMERGENCY_MESSAGE, DSL.name("pk_emergency_message"), new TableField[]{EmergencyMessage.EMERGENCY_MESSAGE.ID}, true);
    public static final UniqueKey<ErrataRecord> PK_ERRATA = Internal.createUniqueKey(Errata.ERRATA, DSL.name("pk_errata"), new TableField[]{Errata.ERRATA.ID}, true);
    public static final UniqueKey<ErrataRecord> UQ_ERRATA = Internal.createUniqueKey(Errata.ERRATA, DSL.name("uq_errata"), new TableField[]{Errata.ERRATA.MODUL, Errata.ERRATA.KLASSE, Errata.ERRATA.BESETZUNG}, true);
    public static final UniqueKey<FestprogrammEntryRecord> PK_FESTPROGRAMM_ENTRY = Internal.createUniqueKey(FestprogrammEntry.FESTPROGRAMM_ENTRY, DSL.name("pk_festprogramm_entry"), new TableField[]{FestprogrammEntry.FESTPROGRAMM_ENTRY.ID}, true);
    public static final UniqueKey<HelperRegistrationRecord> HELPER_REGISTRATION_EMAIL_KEY = Internal.createUniqueKey(HelperRegistration.HELPER_REGISTRATION, DSL.name("helper_registration_email_key"), new TableField[]{HelperRegistration.HELPER_REGISTRATION.EMAIL}, true);
    public static final UniqueKey<HelperRegistrationRecord> PK_HELPER_REGISTRATION = Internal.createUniqueKey(HelperRegistration.HELPER_REGISTRATION, DSL.name("pk_helper_registration"), new TableField[]{HelperRegistration.HELPER_REGISTRATION.ID}, true);
    public static final UniqueKey<ImageRecord> PK_IMAGE = Internal.createUniqueKey(Image.IMAGE, DSL.name("pk_image"), new TableField[]{Image.IMAGE.ID}, true);
    public static final UniqueKey<JudgeRecord> JUDGE_EMAIL_KEY = Internal.createUniqueKey(Judge.JUDGE, DSL.name("judge_email_key"), new TableField[]{Judge.JUDGE.EMAIL}, true);
    public static final UniqueKey<JudgeRecord> PK_JUDGE = Internal.createUniqueKey(Judge.JUDGE, DSL.name("pk_judge"), new TableField[]{Judge.JUDGE.ID}, true);
    public static final UniqueKey<JudgeReportRecord> PK_JUDGE_REPORT = Internal.createUniqueKey(JudgeReport.JUDGE_REPORT, DSL.name("pk_judge_report"), new TableField[]{JudgeReport.JUDGE_REPORT.ID}, true);
    public static final UniqueKey<JudgeReportRecord> UQ_JUDGE_REPORT = Internal.createUniqueKey(JudgeReport.JUDGE_REPORT, DSL.name("uq_judge_report"), new TableField[]{JudgeReport.JUDGE_REPORT.FK_JUDGE, JudgeReport.JUDGE_REPORT.FK_TIMETABLE_ENTRY, JudgeReport.JUDGE_REPORT.CATEGORY}, true);
    public static final UniqueKey<JudgeReportCommentRecord> PK_JUDGE_REPORT_COMMENT = Internal.createUniqueKey(JudgeReportComment.JUDGE_REPORT_COMMENT, DSL.name("pk_judge_report_comment"), new TableField[]{JudgeReportComment.JUDGE_REPORT_COMMENT.FK_JUDGE_REPORT, JudgeReportComment.JUDGE_REPORT_COMMENT.FK_TITEL}, true);
    public static final UniqueKey<JudgeReportRatingRecord> JUDGE_REPORT_RATING_FK_JUDGE_REPORT_FK_TITEL_CATEGORY_KEY = Internal.createUniqueKey(JudgeReportRating.JUDGE_REPORT_RATING, DSL.name("judge_report_rating_fk_judge_report_fk_titel_category_key"), new TableField[]{JudgeReportRating.JUDGE_REPORT_RATING.FK_JUDGE_REPORT, JudgeReportRating.JUDGE_REPORT_RATING.FK_TITEL, JudgeReportRating.JUDGE_REPORT_RATING.CATEGORY}, true);
    public static final UniqueKey<JudgeReportRatingRecord> PK_JUDGE_REPORT_RATING = Internal.createUniqueKey(JudgeReportRating.JUDGE_REPORT_RATING, DSL.name("pk_judge_report_rating"), new TableField[]{JudgeReportRating.JUDGE_REPORT_RATING.ID}, true);
    public static final UniqueKey<KontaktRecord> PK_KONTAKT = Internal.createUniqueKey(Kontakt.KONTAKT, DSL.name("pk_kontakt"), new TableField[]{Kontakt.KONTAKT.ID}, true);
    public static final UniqueKey<LocationRecord> PK_LOCATION = Internal.createUniqueKey(Location.LOCATION, DSL.name("pk_location"), new TableField[]{Location.LOCATION.ID}, true);
    public static final UniqueKey<LocationRecord> UQ_LOCATION_IDENTIFIER = Internal.createUniqueKey(Location.LOCATION, DSL.name("uq_location_identifier"), new TableField[]{Location.LOCATION.IDENTIFIER}, true);
    public static final UniqueKey<NewsletterRecipientRecord> PK_NEWSLETTER_RECIPIENT = Internal.createUniqueKey(NewsletterRecipient.NEWSLETTER_RECIPIENT, DSL.name("pk_newsletter_recipient"), new TableField[]{NewsletterRecipient.NEWSLETTER_RECIPIENT.EMAIL}, true);
    public static final UniqueKey<NotificationSentRecord> PK_NOTIFICATION_SENT = Internal.createUniqueKey(NotificationSent.NOTIFICATION_SENT, DSL.name("pk_notification_sent"), new TableField[]{NotificationSent.NOTIFICATION_SENT.ID}, true);
    public static final UniqueKey<ProgrammVorgabenRecord> PK_PROGRAMM_VORGABEN = Internal.createUniqueKey(ProgrammVorgaben.PROGRAMM_VORGABEN, DSL.name("pk_programm_vorgaben"), new TableField[]{ProgrammVorgaben.PROGRAMM_VORGABEN.MODUL, ProgrammVorgaben.PROGRAMM_VORGABEN.KLASSE, ProgrammVorgaben.PROGRAMM_VORGABEN.BESETZUNG}, true);
    public static final UniqueKey<RankingRecord> PK_RANKING = Internal.createUniqueKey(Ranking.RANKING, DSL.name("pk_ranking"), new TableField[]{Ranking.RANKING.ID}, true);
    public static final UniqueKey<RankingPenaltyRecord> PK_RANKING_PENALTY = Internal.createUniqueKey(RankingPenalty.RANKING_PENALTY, DSL.name("pk_ranking_penalty"), new TableField[]{RankingPenalty.RANKING_PENALTY.ID}, true);
    public static final UniqueKey<SponsorRecord> PK_SPONSOR = Internal.createUniqueKey(Sponsor.SPONSOR, DSL.name("pk_sponsor"), new TableField[]{Sponsor.SPONSOR.ID}, true);
    public static final UniqueKey<SurveyAnswerRecord> PK_SURVEY_ANSWER = Internal.createUniqueKey(SurveyAnswer.SURVEY_ANSWER, DSL.name("pk_survey_answer"), new TableField[]{SurveyAnswer.SURVEY_ANSWER.ID}, true);
    public static final UniqueKey<TimetableEntryRecord> PK_TIMETABLE_ENTRY = Internal.createUniqueKey(TimetableEntry.TIMETABLE_ENTRY, DSL.name("pk_timetable_entry"), new TableField[]{TimetableEntry.TIMETABLE_ENTRY.ID}, true);
    public static final UniqueKey<TitelRecord> PK_TITEL = Internal.createUniqueKey(Titel.TITEL, DSL.name("pk_titel"), new TableField[]{Titel.TITEL.ID}, true);
    public static final UniqueKey<UnterhaltungEntryRecord> PK_UNTERHALTUNG_ENTRY = Internal.createUniqueKey(UnterhaltungEntry.UNTERHALTUNG_ENTRY, DSL.name("pk_unterhaltung_entry"), new TableField[]{UnterhaltungEntry.UNTERHALTUNG_ENTRY.ID}, true);
    public static final UniqueKey<VereinRecord> PK_VEREIN = Internal.createUniqueKey(Verein.VEREIN, DSL.name("pk_verein"), new TableField[]{Verein.VEREIN.ID}, true);
    public static final UniqueKey<VereinRecord> UQ_VEREIN_IDENTIFIER = Internal.createUniqueKey(Verein.VEREIN, DSL.name("uq_verein_identifier"), new TableField[]{Verein.VEREIN.IDENTIFIER}, true);
    public static final UniqueKey<VereinAnmeldungAdhocOrchesterRecord> PK_VEREIN_ANMELDUNG_ADHOC_ORCHESTER = Internal.createUniqueKey(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER, DSL.name("pk_verein_anmeldung_adhoc_orchester"), new TableField[]{VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.ID}, true);
    public static final UniqueKey<VereinAnmeldungDetailRecord> PK_VEREIN_ANMELDUNG_DETAIL = Internal.createUniqueKey(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL, DSL.name("pk_verein_anmeldung_detail"), new TableField[]{VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ID}, true);
    public static final UniqueKey<VereinAnmeldungNichtmitgliederRecord> PK_VEREIN_ANMELDUNG_NICHTMITGLIEDER = Internal.createUniqueKey(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER, DSL.name("pk_verein_anmeldung_nichtmitglieder"), new TableField[]{VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.ID}, true);
    public static final UniqueKey<VereinCommentRecord> PK_VEREIN_COMMENT = Internal.createUniqueKey(VereinComment.VEREIN_COMMENT, DSL.name("pk_verein_comment"), new TableField[]{VereinComment.VEREIN_COMMENT.ID}, true);
    public static final UniqueKey<VereinDoppeleinsatzRecord> PK_VEREIN_DOPPELEINSATZ = Internal.createUniqueKey(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ, DSL.name("pk_verein_doppeleinsatz"), new TableField[]{VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.ID}, true);
    public static final UniqueKey<VereinMessageRecord> PK_VEREIN_MESSAGE = Internal.createUniqueKey(VereinMessage.VEREIN_MESSAGE, DSL.name("pk_verein_message"), new TableField[]{VereinMessage.VEREIN_MESSAGE.ID}, true);
    public static final UniqueKey<VereinProgrammRecord> PK_VEREIN_PROGRAMM = Internal.createUniqueKey(VereinProgramm.VEREIN_PROGRAMM, DSL.name("pk_verein_programm"), new TableField[]{VereinProgramm.VEREIN_PROGRAMM.ID}, true);
    public static final UniqueKey<VereinProgrammTitelRecord> PK_VEREIN_PROGRAMM_TITEL = Internal.createUniqueKey(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL, DSL.name("pk_verein_programm_titel"), new TableField[]{VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_PROGRAMM, VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_TITEL}, true);
    public static final UniqueKey<VereinProgrammTitelRecord> VEREIN_PROGRAMM_TITEL_FK_PROGRAMM_FK_TITEL_POSITION_KEY = Internal.createUniqueKey(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL, DSL.name("verein_programm_titel_fk_programm_fk_titel_position_key"), new TableField[]{VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_PROGRAMM, VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_TITEL, VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.POSITION}, true);
    public static final UniqueKey<VereinStatusRecord> PK_VEREIN_STATUS = Internal.createUniqueKey(VereinStatus.VEREIN_STATUS, DSL.name("pk_verein_status"), new TableField[]{VereinStatus.VEREIN_STATUS.FK_VEREIN}, true);
    public static final UniqueKey<Zkmf2024UserRecord> PK_ZKMF2024_USER = Internal.createUniqueKey(Zkmf2024User.ZKMF2024_USER, DSL.name("pk_zkmf2024_user"), new TableField[]{Zkmf2024User.ZKMF2024_USER.EMAIL}, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<CurrentlyPlayingRecord, TimetableEntryRecord> CURRENTLY_PLAYING__FK_TIMETABLE_ENTRY = Internal.createForeignKey(CurrentlyPlaying.CURRENTLY_PLAYING, DSL.name("fk_timetable_entry"), new TableField[]{CurrentlyPlaying.CURRENTLY_PLAYING.FK_TIMETABLE_ENTRY}, Keys.PK_TIMETABLE_ENTRY, new TableField[]{TimetableEntry.TIMETABLE_ENTRY.ID}, true);
    public static final ForeignKey<JudgeReportRecord, JudgeRecord> JUDGE_REPORT__FK_JUDGE_REPORT_JUDGE = Internal.createForeignKey(JudgeReport.JUDGE_REPORT, DSL.name("fk_judge_report_judge"), new TableField[]{JudgeReport.JUDGE_REPORT.FK_JUDGE}, Keys.PK_JUDGE, new TableField[]{Judge.JUDGE.ID}, true);
    public static final ForeignKey<JudgeReportRecord, TimetableEntryRecord> JUDGE_REPORT__FK_JUDGE_REPORT_TIMETABLE = Internal.createForeignKey(JudgeReport.JUDGE_REPORT, DSL.name("fk_judge_report_timetable"), new TableField[]{JudgeReport.JUDGE_REPORT.FK_TIMETABLE_ENTRY}, Keys.PK_TIMETABLE_ENTRY, new TableField[]{TimetableEntry.TIMETABLE_ENTRY.ID}, true);
    public static final ForeignKey<JudgeReportCommentRecord, JudgeReportRecord> JUDGE_REPORT_COMMENT__FK_JUDGE_REPORT_COMMENT_REPORT = Internal.createForeignKey(JudgeReportComment.JUDGE_REPORT_COMMENT, DSL.name("fk_judge_report_comment_report"), new TableField[]{JudgeReportComment.JUDGE_REPORT_COMMENT.FK_JUDGE_REPORT}, Keys.PK_JUDGE_REPORT, new TableField[]{JudgeReport.JUDGE_REPORT.ID}, true);
    public static final ForeignKey<JudgeReportCommentRecord, TitelRecord> JUDGE_REPORT_COMMENT__FK_JUDGE_REPORT_COMMENT_TITEL = Internal.createForeignKey(JudgeReportComment.JUDGE_REPORT_COMMENT, DSL.name("fk_judge_report_comment_titel"), new TableField[]{JudgeReportComment.JUDGE_REPORT_COMMENT.FK_TITEL}, Keys.PK_TITEL, new TableField[]{Titel.TITEL.ID}, true);
    public static final ForeignKey<JudgeReportRatingRecord, JudgeReportRecord> JUDGE_REPORT_RATING__FK_JUDGE_REPORT_RATING_REPORT = Internal.createForeignKey(JudgeReportRating.JUDGE_REPORT_RATING, DSL.name("fk_judge_report_rating_report"), new TableField[]{JudgeReportRating.JUDGE_REPORT_RATING.FK_JUDGE_REPORT}, Keys.PK_JUDGE_REPORT, new TableField[]{JudgeReport.JUDGE_REPORT.ID}, true);
    public static final ForeignKey<JudgeReportRatingRecord, TitelRecord> JUDGE_REPORT_RATING__FK_JUDGE_REPORT_RATING_TITEL = Internal.createForeignKey(JudgeReportRating.JUDGE_REPORT_RATING, DSL.name("fk_judge_report_rating_titel"), new TableField[]{JudgeReportRating.JUDGE_REPORT_RATING.FK_TITEL}, Keys.PK_TITEL, new TableField[]{Titel.TITEL.ID}, true);
    public static final ForeignKey<LocationRecord, LocationRecord> LOCATION__FK_LOCATION_EINSPIELLOKAL = Internal.createForeignKey(Location.LOCATION, DSL.name("fk_location_einspiellokal"), new TableField[]{Location.LOCATION.EINSPIELLOKAL_ID}, Keys.PK_LOCATION, new TableField[]{Location.LOCATION.ID}, true);
    public static final ForeignKey<LocationRecord, LocationRecord> LOCATION__FK_LOCATION_INSTRUMENTENDEPOT = Internal.createForeignKey(Location.LOCATION, DSL.name("fk_location_instrumentendepot"), new TableField[]{Location.LOCATION.INSTRUMENTENDEPOT_ID}, Keys.PK_LOCATION, new TableField[]{Location.LOCATION.ID}, true);
    public static final ForeignKey<LocationRecord, LocationRecord> LOCATION__FK_LOCATION_JURYFEEDBACK = Internal.createForeignKey(Location.LOCATION, DSL.name("fk_location_juryfeedback"), new TableField[]{Location.LOCATION.JURYFEEDBACK_ID}, Keys.PK_LOCATION, new TableField[]{Location.LOCATION.ID}, true);
    public static final ForeignKey<RankingRecord, VereinRecord> RANKING__FK_RANKING_VEREIN = Internal.createForeignKey(Ranking.RANKING, DSL.name("fk_ranking_verein"), new TableField[]{Ranking.RANKING.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<RankingPenaltyRecord, TimetableEntryRecord> RANKING_PENALTY__FK_TIMETABLE_ENTRY = Internal.createForeignKey(RankingPenalty.RANKING_PENALTY, DSL.name("fk_timetable_entry"), new TableField[]{RankingPenalty.RANKING_PENALTY.FK_TIMETABLE_ENTRY}, Keys.PK_TIMETABLE_ENTRY, new TableField[]{TimetableEntry.TIMETABLE_ENTRY.ID}, true);
    public static final ForeignKey<TimetableEntryRecord, LocationRecord> TIMETABLE_ENTRY__FK_TIMETABLE_LOCATION = Internal.createForeignKey(TimetableEntry.TIMETABLE_ENTRY, DSL.name("fk_timetable_location"), new TableField[]{TimetableEntry.TIMETABLE_ENTRY.FK_LOCATION}, Keys.PK_LOCATION, new TableField[]{Location.LOCATION.ID}, true);
    public static final ForeignKey<TimetableEntryRecord, VereinProgrammRecord> TIMETABLE_ENTRY__FK_TIMETABLE_PROGRAMM = Internal.createForeignKey(TimetableEntry.TIMETABLE_ENTRY, DSL.name("fk_timetable_programm"), new TableField[]{TimetableEntry.TIMETABLE_ENTRY.FK_VEREIN_PROGRAMM}, Keys.PK_VEREIN_PROGRAMM, new TableField[]{VereinProgramm.VEREIN_PROGRAMM.ID}, true);
    public static final ForeignKey<TimetableEntryRecord, VereinRecord> TIMETABLE_ENTRY__FK_TIMETABLE_VEREIN = Internal.createForeignKey(TimetableEntry.TIMETABLE_ENTRY, DSL.name("fk_timetable_verein"), new TableField[]{TimetableEntry.TIMETABLE_ENTRY.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<TitelRecord, VereinRecord> TITEL__FK_TITEL_VEREIN = Internal.createForeignKey(Titel.TITEL, DSL.name("fk_titel_verein"), new TableField[]{Titel.TITEL.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<UnterhaltungEntryRecord, LocationRecord> UNTERHALTUNG_ENTRY__FK_UNTERHALTUNG_LOCATION = Internal.createForeignKey(UnterhaltungEntry.UNTERHALTUNG_ENTRY, DSL.name("fk_unterhaltung_location"), new TableField[]{UnterhaltungEntry.UNTERHALTUNG_ENTRY.FK_LOCATION}, Keys.PK_LOCATION, new TableField[]{Location.LOCATION.ID}, true);
    public static final ForeignKey<VereinRecord, KontaktRecord> VEREIN__FK_VEREIN_DIREKTION_KONTAKT = Internal.createForeignKey(Verein.VEREIN, DSL.name("fk_verein_direktion_kontakt"), new TableField[]{Verein.VEREIN.DIREKTION_KONTAKT_ID}, Keys.PK_KONTAKT, new TableField[]{Kontakt.KONTAKT.ID}, true);
    public static final ForeignKey<VereinRecord, KontaktRecord> VEREIN__FK_VEREIN_PRAESIDENT_KONTAKT = Internal.createForeignKey(Verein.VEREIN, DSL.name("fk_verein_praesident_kontakt"), new TableField[]{Verein.VEREIN.PRAESIDENT_KONTAKT_ID}, Keys.PK_KONTAKT, new TableField[]{Kontakt.KONTAKT.ID}, true);
    public static final ForeignKey<VereinAnmeldungAdhocOrchesterRecord, VereinRecord> VEREIN_ANMELDUNG_ADHOC_ORCHESTER__FK_VEREIN_ANMELDUNG_ADHOC_ORCHESTER_VEREIN = Internal.createForeignKey(VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER, DSL.name("fk_verein_anmeldung_adhoc_orchester_verein"), new TableField[]{VereinAnmeldungAdhocOrchester.VEREIN_ANMELDUNG_ADHOC_ORCHESTER.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<VereinAnmeldungDetailRecord, VereinRecord> VEREIN_ANMELDUNG_DETAIL__FK_VEREIN_ANMELDUNG_DETAIL_VEREIN = Internal.createForeignKey(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL, DSL.name("fk_verein_anmeldung_detail_verein"), new TableField[]{VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<VereinAnmeldungNichtmitgliederRecord, VereinRecord> VEREIN_ANMELDUNG_NICHTMITGLIEDER__FK_VEREIN_ANMELDUNG_NICHTMITGLIEDER_VEREIN = Internal.createForeignKey(VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER, DSL.name("fk_verein_anmeldung_nichtmitglieder_verein"), new TableField[]{VereinAnmeldungNichtmitglieder.VEREIN_ANMELDUNG_NICHTMITGLIEDER.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<VereinCommentRecord, VereinRecord> VEREIN_COMMENT__FK_VEREIN_COMMENT_VEREIN = Internal.createForeignKey(VereinComment.VEREIN_COMMENT, DSL.name("fk_verein_comment_verein"), new TableField[]{VereinComment.VEREIN_COMMENT.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<VereinDoppeleinsatzRecord, VereinRecord> VEREIN_DOPPELEINSATZ__FK_VEREIN_DOPPELEINSATZ_OTHER_VEREIN = Internal.createForeignKey(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ, DSL.name("fk_verein_doppeleinsatz_other_verein"), new TableField[]{VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.FK_OTHER_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<VereinDoppeleinsatzRecord, VereinRecord> VEREIN_DOPPELEINSATZ__FK_VEREIN_DOPPELEINSATZ_VEREIN = Internal.createForeignKey(VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ, DSL.name("fk_verein_doppeleinsatz_verein"), new TableField[]{VereinDoppeleinsatz.VEREIN_DOPPELEINSATZ.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<VereinMessageRecord, VereinRecord> VEREIN_MESSAGE__FK_VEREIN_MESSAGE_VEREIN = Internal.createForeignKey(VereinMessage.VEREIN_MESSAGE, DSL.name("fk_verein_message_verein"), new TableField[]{VereinMessage.VEREIN_MESSAGE.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<VereinProgrammRecord, VereinRecord> VEREIN_PROGRAMM__FK_VEREIN_PROGRAMM_VEREIN = Internal.createForeignKey(VereinProgramm.VEREIN_PROGRAMM, DSL.name("fk_verein_programm_verein"), new TableField[]{VereinProgramm.VEREIN_PROGRAMM.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
    public static final ForeignKey<VereinProgrammTitelRecord, VereinProgrammRecord> VEREIN_PROGRAMM_TITEL__FK_VEREIN_PROGRAMM_TITEL_PROGRAMM = Internal.createForeignKey(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL, DSL.name("fk_verein_programm_titel_programm"), new TableField[]{VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_PROGRAMM}, Keys.PK_VEREIN_PROGRAMM, new TableField[]{VereinProgramm.VEREIN_PROGRAMM.ID}, true);
    public static final ForeignKey<VereinProgrammTitelRecord, TitelRecord> VEREIN_PROGRAMM_TITEL__FK_VEREIN_PROGRAMM_TITEL_PROGRAMM_TITEL = Internal.createForeignKey(VereinProgrammTitel.VEREIN_PROGRAMM_TITEL, DSL.name("fk_verein_programm_titel_programm_titel"), new TableField[]{VereinProgrammTitel.VEREIN_PROGRAMM_TITEL.FK_TITEL}, Keys.PK_TITEL, new TableField[]{Titel.TITEL.ID}, true);
    public static final ForeignKey<VereinStatusRecord, VereinRecord> VEREIN_STATUS__FK_VEREIN_STATUS = Internal.createForeignKey(VereinStatus.VEREIN_STATUS, DSL.name("fk_verein_status"), new TableField[]{VereinStatus.VEREIN_STATUS.FK_VEREIN}, Keys.PK_VEREIN, new TableField[]{Verein.VEREIN.ID}, true);
}
