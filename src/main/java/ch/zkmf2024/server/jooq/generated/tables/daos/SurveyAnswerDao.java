/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.SurveyAnswer;
import ch.zkmf2024.server.jooq.generated.tables.pojos.SurveyAnswerPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.SurveyAnswerRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class SurveyAnswerDao extends DAOImpl<SurveyAnswerRecord, SurveyAnswerPojo, Long> {

    /**
     * Create a new SurveyAnswerDao without any configuration
     */
    public SurveyAnswerDao() {
        super(SurveyAnswer.SURVEY_ANSWER, SurveyAnswerPojo.class);
    }

    /**
     * Create a new SurveyAnswerDao with an attached configuration
     */
    public SurveyAnswerDao(Configuration configuration) {
        super(SurveyAnswer.SURVEY_ANSWER, SurveyAnswerPojo.class, configuration);
    }

    @Override
    public Long getId(SurveyAnswerPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchById(Long... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public SurveyAnswerPojo fetchOneById(Long value) {
        return fetchOne(SurveyAnswer.SURVEY_ANSWER.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<SurveyAnswerPojo> fetchOptionalById(Long value) {
        return fetchOptional(SurveyAnswer.SURVEY_ANSWER.ID, value);
    }

    /**
     * Fetch records that have <code>timestamp BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfTimestamp(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.TIMESTAMP, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>timestamp IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByTimestamp(LocalDateTime... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.TIMESTAMP, values);
    }

    /**
     * Fetch records that have <code>vereins_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfVereinsName(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.VEREINS_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>vereins_name IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByVereinsName(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.VEREINS_NAME, values);
    }

    /**
     * Fetch records that have <code>besetzung BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfBesetzung(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.BESETZUNG, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>besetzung IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByBesetzung(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.BESETZUNG, values);
    }

    /**
     * Fetch records that have <code>staerke_klasse BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfStaerkeKlasse(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.STAERKE_KLASSE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>staerke_klasse IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByStaerkeKlasse(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.STAERKE_KLASSE, values);
    }

    /**
     * Fetch records that have <code>anzahl_mitglieder BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfAnzahlMitglieder(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.ANZAHL_MITGLIEDER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>anzahl_mitglieder IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByAnzahlMitglieder(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.ANZAHL_MITGLIEDER, values);
    }

    /**
     * Fetch records that have <code>kontakt_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfKontaktName(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.KONTAKT_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>kontakt_name IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByKontaktName(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.KONTAKT_NAME, values);
    }

    /**
     * Fetch records that have <code>kontakt_email BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfKontaktEmail(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.KONTAKT_EMAIL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>kontakt_email IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByKontaktEmail(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.KONTAKT_EMAIL, values);
    }

    /**
     * Fetch records that have <code>kontakt_telefon BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfKontaktTelefon(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.KONTAKT_TELEFON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>kontakt_telefon IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByKontaktTelefon(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.KONTAKT_TELEFON, values);
    }

    /**
     * Fetch records that have <code>modul_auswahl BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfModulAuswahl(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.MODUL_AUSWAHL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modul_auswahl IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByModulAuswahl(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.MODUL_AUSWAHL, values);
    }

    /**
     * Fetch records that have <code>zusage_kommentar BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfZusageKommentar(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.ZUSAGE_KOMMENTAR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>zusage_kommentar IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByZusageKommentar(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.ZUSAGE_KOMMENTAR, values);
    }

    /**
     * Fetch records that have <code>absage BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfAbsage(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.ABSAGE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>absage IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByAbsage(Boolean... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.ABSAGE, values);
    }

    /**
     * Fetch records that have <code>absage_kommentar BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfAbsageKommentar(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.ABSAGE_KOMMENTAR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>absage_kommentar IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByAbsageKommentar(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.ABSAGE_KOMMENTAR, values);
    }

    /**
     * Fetch records that have <code>absage_kontaktaufnahme BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfAbsageKontaktaufnahme(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.ABSAGE_KONTAKTAUFNAHME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>absage_kontaktaufnahme IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByAbsageKontaktaufnahme(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.ABSAGE_KONTAKTAUFNAHME, values);
    }

    /**
     * Fetch records that have <code>helfer BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<SurveyAnswerPojo> fetchRangeOfHelfer(String lowerInclusive, String upperInclusive) {
        return fetchRange(SurveyAnswer.SURVEY_ANSWER.HELFER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>helfer IN (values)</code>
     */
    public List<SurveyAnswerPojo> fetchByHelfer(String... values) {
        return fetch(SurveyAnswer.SURVEY_ANSWER.HELFER, values);
    }
}
