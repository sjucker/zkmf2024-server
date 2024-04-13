/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.interfaces;

import org.jooq.JSONB;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public interface IVereinAnmeldungDetail extends Serializable {

    /**
     * Setter for <code>verein_anmeldung_detail.id</code>.
     */
    public void setId(Long value);

    /**
     * Getter for <code>verein_anmeldung_detail.id</code>.
     */
    public Long getId();

    /**
     * Setter for <code>verein_anmeldung_detail.fk_verein</code>.
     */
    public void setFkVerein(Long value);

    /**
     * Getter for <code>verein_anmeldung_detail.fk_verein</code>.
     */
    public Long getFkVerein();

    /**
     * Setter for <code>verein_anmeldung_detail.festfuehrer_amount</code>.
     */
    public void setFestfuehrerAmount(Integer value);

    /**
     * Getter for <code>verein_anmeldung_detail.festfuehrer_amount</code>.
     */
    public Integer getFestfuehrerAmount();

    /**
     * Setter for
     * <code>verein_anmeldung_detail.festkarten_musiker_amount</code>.
     */
    public void setFestkartenMusikerAmount(Integer value);

    /**
     * Getter for
     * <code>verein_anmeldung_detail.festkarten_musiker_amount</code>.
     */
    public Integer getFestkartenMusikerAmount();

    /**
     * Setter for
     * <code>verein_anmeldung_detail.festkarten_begleiter_amount</code>.
     */
    public void setFestkartenBegleiterAmount(Integer value);

    /**
     * Getter for
     * <code>verein_anmeldung_detail.festkarten_begleiter_amount</code>.
     */
    public Integer getFestkartenBegleiterAmount();

    /**
     * Setter for <code>verein_anmeldung_detail.freitagabend_amount</code>.
     */
    public void setFreitagabendAmount(Integer value);

    /**
     * Getter for <code>verein_anmeldung_detail.freitagabend_amount</code>.
     */
    public Integer getFreitagabendAmount();

    /**
     * Setter for <code>verein_anmeldung_detail.gehbehinderung</code>.
     */
    public void setGehbehinderung(Boolean value);

    /**
     * Getter for <code>verein_anmeldung_detail.gehbehinderung</code>.
     */
    public Boolean getGehbehinderung();

    /**
     * Setter for <code>verein_anmeldung_detail.partituren_sent</code>.
     */
    public void setPartiturenSent(Boolean value);

    /**
     * Getter for <code>verein_anmeldung_detail.partituren_sent</code>.
     */
    public Boolean getPartiturenSent();

    /**
     * Setter for <code>verein_anmeldung_detail.partituren_sent_at</code>.
     */
    public void setPartiturenSentAt(LocalDate value);

    /**
     * Getter for <code>verein_anmeldung_detail.partituren_sent_at</code>.
     */
    public LocalDate getPartiturenSentAt();

    /**
     * Setter for <code>verein_anmeldung_detail.gesamtchor</code>.
     */
    public void setGesamtchor(Boolean value);

    /**
     * Getter for <code>verein_anmeldung_detail.gesamtchor</code>.
     */
    public Boolean getGesamtchor();

    /**
     * Setter for <code>verein_anmeldung_detail.adhoc_orchester</code>.
     */
    public void setAdhocOrchester(Boolean value);

    /**
     * Getter for <code>verein_anmeldung_detail.adhoc_orchester</code>.
     */
    public Boolean getAdhocOrchester();

    /**
     * Setter for <code>verein_anmeldung_detail.anreise_public_transport</code>.
     */
    public void setAnreisePublicTransport(Boolean value);

    /**
     * Getter for <code>verein_anmeldung_detail.anreise_public_transport</code>.
     */
    public Boolean getAnreisePublicTransport();

    /**
     * Setter for
     * <code>verein_anmeldung_detail.anreise_public_transport_type</code>.
     */
    public void setAnreisePublicTransportType(String value);

    /**
     * Getter for
     * <code>verein_anmeldung_detail.anreise_public_transport_type</code>.
     */
    public String getAnreisePublicTransportType();

    /**
     * Setter for <code>verein_anmeldung_detail.anreise_otherwise</code>.
     */
    public void setAnreiseOtherwise(String value);

    /**
     * Getter for <code>verein_anmeldung_detail.anreise_otherwise</code>.
     */
    public String getAnreiseOtherwise();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_meat</code>.
     */
    public void setVerpflegungMeat(Integer value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_meat</code>.
     */
    public Integer getVerpflegungMeat();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_vegan</code>.
     */
    public void setVerpflegungVegan(Integer value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_vegan</code>.
     */
    public Integer getVerpflegungVegan();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_allergies</code>.
     */
    public void setVerpflegungAllergies(Integer value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_allergies</code>.
     */
    public Integer getVerpflegungAllergies();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_none</code>.
     */
    public void setVerpflegungNone(Integer value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_none</code>.
     */
    public Integer getVerpflegungNone();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_helper_1</code>.
     */
    public void setVerpflegungHelper_1(String value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_helper_1</code>.
     */
    public String getVerpflegungHelper_1();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_helper_2</code>.
     */
    public void setVerpflegungHelper_2(String value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_helper_2</code>.
     */
    public String getVerpflegungHelper_2();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_helper_3</code>.
     */
    public void setVerpflegungHelper_3(String value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_helper_3</code>.
     */
    public String getVerpflegungHelper_3();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_helper_4</code>.
     */
    public void setVerpflegungHelper_4(String value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_helper_4</code>.
     */
    public String getVerpflegungHelper_4();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_helper_5</code>.
     */
    public void setVerpflegungHelper_5(String value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_helper_5</code>.
     */
    public String getVerpflegungHelper_5();

    /**
     * Setter for <code>verein_anmeldung_detail.verpflegung_helper_6</code>.
     */
    public void setVerpflegungHelper_6(String value);

    /**
     * Getter for <code>verein_anmeldung_detail.verpflegung_helper_6</code>.
     */
    public String getVerpflegungHelper_6();

    /**
     * Setter for <code>verein_anmeldung_detail.stage_setup</code>.
     */
    public void setStageSetup(JSONB value);

    /**
     * Getter for <code>verein_anmeldung_detail.stage_setup</code>.
     */
    public JSONB getStageSetup();

    /**
     * Setter for <code>verein_anmeldung_detail.stage_dirigentenpodest</code>.
     */
    public void setStageDirigentenpodest(Boolean value);

    /**
     * Getter for <code>verein_anmeldung_detail.stage_dirigentenpodest</code>.
     */
    public Boolean getStageDirigentenpodest();

    /**
     * Setter for <code>verein_anmeldung_detail.stage_ablagen_amount</code>.
     */
    public void setStageAblagenAmount(Integer value);

    /**
     * Getter for <code>verein_anmeldung_detail.stage_ablagen_amount</code>.
     */
    public Integer getStageAblagenAmount();

    /**
     * Setter for <code>verein_anmeldung_detail.stage_comment</code>.
     */
    public void setStageComment(String value);

    /**
     * Getter for <code>verein_anmeldung_detail.stage_comment</code>.
     */
    public String getStageComment();

    /**
     * Setter for <code>verein_anmeldung_detail.stage_setup_image</code>.
     */
    public void setStageSetupImage(byte[] value);

    /**
     * Getter for <code>verein_anmeldung_detail.stage_setup_image</code>.
     */
    public byte[] getStageSetupImage();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IVereinAnmeldungDetail
     */
    public void from(IVereinAnmeldungDetail from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IVereinAnmeldungDetail
     */
    public <E extends IVereinAnmeldungDetail> E into(E into);
}
