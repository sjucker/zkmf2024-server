/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.daos;

import ch.zkmf2024.server.jooq.generated.tables.VereinAnmeldungDetail;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinAnmeldungDetailPojo;
import ch.zkmf2024.server.jooq.generated.tables.records.VereinAnmeldungDetailRecord;
import org.jooq.Configuration;
import org.jooq.JSONB;
import org.jooq.impl.DAOImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinAnmeldungDetailDao extends DAOImpl<VereinAnmeldungDetailRecord, VereinAnmeldungDetailPojo, Long> {

    /**
     * Create a new VereinAnmeldungDetailDao without any configuration
     */
    public VereinAnmeldungDetailDao() {
        super(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL, VereinAnmeldungDetailPojo.class);
    }

    /**
     * Create a new VereinAnmeldungDetailDao with an attached configuration
     */
    public VereinAnmeldungDetailDao(Configuration configuration) {
        super(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL, VereinAnmeldungDetailPojo.class, configuration);
    }

    @Override
    public Long getId(VereinAnmeldungDetailPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchById(Long... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public VereinAnmeldungDetailPojo fetchOneById(Long value) {
        return fetchOne(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<VereinAnmeldungDetailPojo> fetchOptionalById(Long value) {
        return fetchOptional(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ID, value);
    }

    /**
     * Fetch records that have <code>fk_verein BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfFkVerein(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FK_VEREIN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fk_verein IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByFkVerein(Long... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FK_VEREIN, values);
    }

    /**
     * Fetch records that have <code>festfuehrer_amount BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfFestfuehrerAmount(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FESTFUEHRER_AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>festfuehrer_amount IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByFestfuehrerAmount(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FESTFUEHRER_AMOUNT, values);
    }

    /**
     * Fetch records that have <code>festkarten_musiker_amount BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfFestkartenMusikerAmount(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FESTKARTEN_MUSIKER_AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>festkarten_musiker_amount IN
     * (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByFestkartenMusikerAmount(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FESTKARTEN_MUSIKER_AMOUNT, values);
    }

    /**
     * Fetch records that have <code>festkarten_begleiter_amount BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfFestkartenBegleiterAmount(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FESTKARTEN_BEGLEITER_AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>festkarten_begleiter_amount IN
     * (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByFestkartenBegleiterAmount(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FESTKARTEN_BEGLEITER_AMOUNT, values);
    }

    /**
     * Fetch records that have <code>freitagabend_amount BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfFreitagabendAmount(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FREITAGABEND_AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>freitagabend_amount IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByFreitagabendAmount(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.FREITAGABEND_AMOUNT, values);
    }

    /**
     * Fetch records that have <code>gehbehinderung BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfGehbehinderung(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.GEHBEHINDERUNG, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>gehbehinderung IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByGehbehinderung(Boolean... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.GEHBEHINDERUNG, values);
    }

    /**
     * Fetch records that have <code>partituren_sent BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfPartiturenSent(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.PARTITUREN_SENT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>partituren_sent IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByPartiturenSent(Boolean... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.PARTITUREN_SENT, values);
    }

    /**
     * Fetch records that have <code>partituren_sent_at BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfPartiturenSentAt(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.PARTITUREN_SENT_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>partituren_sent_at IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByPartiturenSentAt(LocalDate... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.PARTITUREN_SENT_AT, values);
    }

    /**
     * Fetch records that have <code>gesamtchor BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfGesamtchor(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.GESAMTCHOR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>gesamtchor IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByGesamtchor(Boolean... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.GESAMTCHOR, values);
    }

    /**
     * Fetch records that have <code>adhoc_orchester BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfAdhocOrchester(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ADHOC_ORCHESTER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>adhoc_orchester IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByAdhocOrchester(Boolean... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ADHOC_ORCHESTER, values);
    }

    /**
     * Fetch records that have <code>anreise_public_transport BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfAnreisePublicTransport(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ANREISE_PUBLIC_TRANSPORT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>anreise_public_transport IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByAnreisePublicTransport(Boolean... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ANREISE_PUBLIC_TRANSPORT, values);
    }

    /**
     * Fetch records that have <code>anreise_public_transport_type BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfAnreisePublicTransportType(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ANREISE_PUBLIC_TRANSPORT_TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>anreise_public_transport_type IN
     * (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByAnreisePublicTransportType(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ANREISE_PUBLIC_TRANSPORT_TYPE, values);
    }

    /**
     * Fetch records that have <code>anreise_otherwise BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfAnreiseOtherwise(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ANREISE_OTHERWISE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>anreise_otherwise IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByAnreiseOtherwise(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.ANREISE_OTHERWISE, values);
    }

    /**
     * Fetch records that have <code>verpflegung_meat BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungMeat(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_MEAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_meat IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungMeat(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_MEAT, values);
    }

    /**
     * Fetch records that have <code>verpflegung_vegan BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungVegan(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_VEGAN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_vegan IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungVegan(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_VEGAN, values);
    }

    /**
     * Fetch records that have <code>verpflegung_allergies BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungAllergies(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_ALLERGIES, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_allergies IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungAllergies(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_ALLERGIES, values);
    }

    /**
     * Fetch records that have <code>verpflegung_none BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungNone(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_NONE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_none IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungNone(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_NONE, values);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_1 BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungHelper_1(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_1, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_1 IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungHelper_1(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_1, values);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_2 BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungHelper_2(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_2, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_2 IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungHelper_2(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_2, values);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_3 BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungHelper_3(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_3, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_3 IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungHelper_3(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_3, values);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_4 BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungHelper_4(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_4, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_4 IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungHelper_4(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_4, values);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_5 BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungHelper_5(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_5, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_5 IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungHelper_5(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_5, values);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_6 BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfVerpflegungHelper_6(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_6, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>verpflegung_helper_6 IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByVerpflegungHelper_6(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.VERPFLEGUNG_HELPER_6, values);
    }

    /**
     * Fetch records that have <code>stage_setup BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfStageSetup(JSONB lowerInclusive, JSONB upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_SETUP, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>stage_setup IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByStageSetup(JSONB... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_SETUP, values);
    }

    /**
     * Fetch records that have <code>stage_dirigentenpodest BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfStageDirigentenpodest(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_DIRIGENTENPODEST, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>stage_dirigentenpodest IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByStageDirigentenpodest(Boolean... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_DIRIGENTENPODEST, values);
    }

    /**
     * Fetch records that have <code>stage_ablagen_amount BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfStageAblagenAmount(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_ABLAGEN_AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>stage_ablagen_amount IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByStageAblagenAmount(Integer... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_ABLAGEN_AMOUNT, values);
    }

    /**
     * Fetch records that have <code>stage_comment BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfStageComment(String lowerInclusive, String upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_COMMENT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>stage_comment IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByStageComment(String... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_COMMENT, values);
    }

    /**
     * Fetch records that have <code>stage_setup_image BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchRangeOfStageSetupImage(byte[] lowerInclusive, byte[] upperInclusive) {
        return fetchRange(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_SETUP_IMAGE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>stage_setup_image IN (values)</code>
     */
    public List<VereinAnmeldungDetailPojo> fetchByStageSetupImage(byte[]... values) {
        return fetch(VereinAnmeldungDetail.VEREIN_ANMELDUNG_DETAIL.STAGE_SETUP_IMAGE, values);
    }
}
