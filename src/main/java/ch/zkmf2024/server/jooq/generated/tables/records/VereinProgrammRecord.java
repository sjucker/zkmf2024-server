/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.VereinProgramm;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVereinProgramm;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammPojo;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinProgrammRecord extends UpdatableRecordImpl<VereinProgrammRecord> implements IVereinProgramm {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>verein_programm.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>verein_programm.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>verein_programm.fk_verein</code>.
     */
    @Override
    public void setFkVerein(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>verein_programm.fk_verein</code>.
     */
    @Override
    public Long getFkVerein() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>verein_programm.modul</code>.
     */
    @Override
    public void setModul(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>verein_programm.modul</code>.
     */
    @Override
    public String getModul() {
        return (String) get(2);
    }

    /**
     * Setter for <code>verein_programm.klasse</code>.
     */
    @Override
    public void setKlasse(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>verein_programm.klasse</code>.
     */
    @Override
    public String getKlasse() {
        return (String) get(3);
    }

    /**
     * Setter for <code>verein_programm.besetzung</code>.
     */
    @Override
    public void setBesetzung(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>verein_programm.besetzung</code>.
     */
    @Override
    public String getBesetzung() {
        return (String) get(4);
    }

    /**
     * Setter for <code>verein_programm.titel</code>.
     */
    @Override
    public void setTitel(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>verein_programm.titel</code>.
     */
    @Override
    public String getTitel() {
        return (String) get(5);
    }

    /**
     * Setter for <code>verein_programm.info_moderation</code>.
     */
    @Override
    public void setInfoModeration(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>verein_programm.info_moderation</code>.
     */
    @Override
    public String getInfoModeration() {
        return (String) get(6);
    }

    /**
     * Setter for <code>verein_programm.total_duration_in_seconds</code>.
     */
    @Override
    public void setTotalDurationInSeconds(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>verein_programm.total_duration_in_seconds</code>.
     */
    @Override
    public Integer getTotalDurationInSeconds() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>verein_programm.modul_g_kat_a_1</code>.
     */
    @Override
    public void setModulGKatA_1(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>verein_programm.modul_g_kat_a_1</code>.
     */
    @Override
    public String getModulGKatA_1() {
        return (String) get(8);
    }

    /**
     * Setter for <code>verein_programm.modul_g_kat_a_2</code>.
     */
    @Override
    public void setModulGKatA_2(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>verein_programm.modul_g_kat_a_2</code>.
     */
    @Override
    public String getModulGKatA_2() {
        return (String) get(9);
    }

    /**
     * Setter for <code>verein_programm.modul_g_kat_a_titel_1_id</code>.
     */
    @Override
    public void setModulGKatATitel_1Id(Long value) {
        set(10, value);
    }

    /**
     * Getter for <code>verein_programm.modul_g_kat_a_titel_1_id</code>.
     */
    @Override
    public Long getModulGKatATitel_1Id() {
        return (Long) get(10);
    }

    /**
     * Setter for <code>verein_programm.modul_g_kat_a_titel_2_id</code>.
     */
    @Override
    public void setModulGKatATitel_2Id(Long value) {
        set(11, value);
    }

    /**
     * Getter for <code>verein_programm.modul_g_kat_a_titel_2_id</code>.
     */
    @Override
    public Long getModulGKatATitel_2Id() {
        return (Long) get(11);
    }

    /**
     * Setter for <code>verein_programm.modul_g_kat_b_titel_id</code>.
     */
    @Override
    public void setModulGKatBTitelId(Long value) {
        set(12, value);
    }

    /**
     * Getter for <code>verein_programm.modul_g_kat_b_titel_id</code>.
     */
    @Override
    public Long getModulGKatBTitelId() {
        return (Long) get(12);
    }

    /**
     * Setter for <code>verein_programm.modul_g_kat_c_titel_id</code>.
     */
    @Override
    public void setModulGKatCTitelId(Long value) {
        set(13, value);
    }

    /**
     * Getter for <code>verein_programm.modul_g_kat_c_titel_id</code>.
     */
    @Override
    public Long getModulGKatCTitelId() {
        return (Long) get(13);
    }

    /**
     * Setter for <code>verein_programm.modul_b_pa</code>.
     */
    @Override
    public void setModulBPa(Boolean value) {
        set(14, value);
    }

    /**
     * Getter for <code>verein_programm.modul_b_pa</code>.
     */
    @Override
    public Boolean getModulBPa() {
        return (Boolean) get(14);
    }

    /**
     * Setter for <code>verein_programm.modul_b_egitarre</code>.
     */
    @Override
    public void setModulBEgitarre(Boolean value) {
        set(15, value);
    }

    /**
     * Getter for <code>verein_programm.modul_b_egitarre</code>.
     */
    @Override
    public Boolean getModulBEgitarre() {
        return (Boolean) get(15);
    }

    /**
     * Setter for <code>verein_programm.modul_b_ebass</code>.
     */
    @Override
    public void setModulBEbass(Boolean value) {
        set(16, value);
    }

    /**
     * Getter for <code>verein_programm.modul_b_ebass</code>.
     */
    @Override
    public Boolean getModulBEbass() {
        return (Boolean) get(16);
    }

    /**
     * Setter for <code>verein_programm.modul_b_keyboard</code>.
     */
    @Override
    public void setModulBKeyboard(Boolean value) {
        set(17, value);
    }

    /**
     * Getter for <code>verein_programm.modul_b_keyboard</code>.
     */
    @Override
    public Boolean getModulBKeyboard() {
        return (Boolean) get(17);
    }

    /**
     * Setter for <code>verein_programm.modul_b_gesang</code>.
     */
    @Override
    public void setModulBGesang(Boolean value) {
        set(18, value);
    }

    /**
     * Getter for <code>verein_programm.modul_b_gesang</code>.
     */
    @Override
    public Boolean getModulBGesang() {
        return (Boolean) get(18);
    }

    /**
     * Setter for <code>verein_programm.modul_d_titel_1_id</code>.
     */
    @Override
    public void setModulDTitel_1Id(Long value) {
        set(19, value);
    }

    /**
     * Getter for <code>verein_programm.modul_d_titel_1_id</code>.
     */
    @Override
    public Long getModulDTitel_1Id() {
        return (Long) get(19);
    }

    /**
     * Setter for <code>verein_programm.modul_d_titel_2_id</code>.
     */
    @Override
    public void setModulDTitel_2Id(Long value) {
        set(20, value);
    }

    /**
     * Getter for <code>verein_programm.modul_d_titel_2_id</code>.
     */
    @Override
    public Long getModulDTitel_2Id() {
        return (Long) get(20);
    }

    /**
     * Setter for <code>verein_programm.scores_confirmed_by</code>.
     */
    @Override
    public void setScoresConfirmedBy(String value) {
        set(21, value);
    }

    /**
     * Getter for <code>verein_programm.scores_confirmed_by</code>.
     */
    @Override
    public String getScoresConfirmedBy() {
        return (String) get(21);
    }

    /**
     * Setter for <code>verein_programm.scores_confirmed_at</code>.
     */
    @Override
    public void setScoresConfirmedAt(LocalDateTime value) {
        set(22, value);
    }

    /**
     * Getter for <code>verein_programm.scores_confirmed_at</code>.
     */
    @Override
    public LocalDateTime getScoresConfirmedAt() {
        return (LocalDateTime) get(22);
    }

    /**
     * Setter for <code>verein_programm.modul_d_titel_selection</code>.
     */
    @Override
    public void setModulDTitelSelection(String value) {
        set(23, value);
    }

    /**
     * Getter for <code>verein_programm.modul_d_titel_selection</code>.
     */
    @Override
    public String getModulDTitelSelection() {
        return (String) get(23);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IVereinProgramm from) {
        setId(from.getId());
        setFkVerein(from.getFkVerein());
        setModul(from.getModul());
        setKlasse(from.getKlasse());
        setBesetzung(from.getBesetzung());
        setTitel(from.getTitel());
        setInfoModeration(from.getInfoModeration());
        setTotalDurationInSeconds(from.getTotalDurationInSeconds());
        setModulGKatA_1(from.getModulGKatA_1());
        setModulGKatA_2(from.getModulGKatA_2());
        setModulGKatATitel_1Id(from.getModulGKatATitel_1Id());
        setModulGKatATitel_2Id(from.getModulGKatATitel_2Id());
        setModulGKatBTitelId(from.getModulGKatBTitelId());
        setModulGKatCTitelId(from.getModulGKatCTitelId());
        setModulBPa(from.getModulBPa());
        setModulBEgitarre(from.getModulBEgitarre());
        setModulBEbass(from.getModulBEbass());
        setModulBKeyboard(from.getModulBKeyboard());
        setModulBGesang(from.getModulBGesang());
        setModulDTitel_1Id(from.getModulDTitel_1Id());
        setModulDTitel_2Id(from.getModulDTitel_2Id());
        setScoresConfirmedBy(from.getScoresConfirmedBy());
        setScoresConfirmedAt(from.getScoresConfirmedAt());
        setModulDTitelSelection(from.getModulDTitelSelection());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IVereinProgramm> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VereinProgrammRecord
     */
    public VereinProgrammRecord() {
        super(VereinProgramm.VEREIN_PROGRAMM);
    }

    /**
     * Create a detached, initialised VereinProgrammRecord
     */
    public VereinProgrammRecord(Long id, Long fkVerein, String modul, String klasse, String besetzung, String titel, String infoModeration, Integer totalDurationInSeconds, String modulGKatA_1, String modulGKatA_2, Long modulGKatATitel_1Id, Long modulGKatATitel_2Id, Long modulGKatBTitelId, Long modulGKatCTitelId, Boolean modulBPa, Boolean modulBEgitarre, Boolean modulBEbass, Boolean modulBKeyboard, Boolean modulBGesang, Long modulDTitel_1Id, Long modulDTitel_2Id, String scoresConfirmedBy, LocalDateTime scoresConfirmedAt, String modulDTitelSelection) {
        super(VereinProgramm.VEREIN_PROGRAMM);

        setId(id);
        setFkVerein(fkVerein);
        setModul(modul);
        setKlasse(klasse);
        setBesetzung(besetzung);
        setTitel(titel);
        setInfoModeration(infoModeration);
        setTotalDurationInSeconds(totalDurationInSeconds);
        setModulGKatA_1(modulGKatA_1);
        setModulGKatA_2(modulGKatA_2);
        setModulGKatATitel_1Id(modulGKatATitel_1Id);
        setModulGKatATitel_2Id(modulGKatATitel_2Id);
        setModulGKatBTitelId(modulGKatBTitelId);
        setModulGKatCTitelId(modulGKatCTitelId);
        setModulBPa(modulBPa);
        setModulBEgitarre(modulBEgitarre);
        setModulBEbass(modulBEbass);
        setModulBKeyboard(modulBKeyboard);
        setModulBGesang(modulBGesang);
        setModulDTitel_1Id(modulDTitel_1Id);
        setModulDTitel_2Id(modulDTitel_2Id);
        setScoresConfirmedBy(scoresConfirmedBy);
        setScoresConfirmedAt(scoresConfirmedAt);
        setModulDTitelSelection(modulDTitelSelection);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised VereinProgrammRecord
     */
    public VereinProgrammRecord(VereinProgrammPojo value) {
        super(VereinProgramm.VEREIN_PROGRAMM);

        if (value != null) {
            setId(value.getId());
            setFkVerein(value.getFkVerein());
            setModul(value.getModul());
            setKlasse(value.getKlasse());
            setBesetzung(value.getBesetzung());
            setTitel(value.getTitel());
            setInfoModeration(value.getInfoModeration());
            setTotalDurationInSeconds(value.getTotalDurationInSeconds());
            setModulGKatA_1(value.getModulGKatA_1());
            setModulGKatA_2(value.getModulGKatA_2());
            setModulGKatATitel_1Id(value.getModulGKatATitel_1Id());
            setModulGKatATitel_2Id(value.getModulGKatATitel_2Id());
            setModulGKatBTitelId(value.getModulGKatBTitelId());
            setModulGKatCTitelId(value.getModulGKatCTitelId());
            setModulBPa(value.getModulBPa());
            setModulBEgitarre(value.getModulBEgitarre());
            setModulBEbass(value.getModulBEbass());
            setModulBKeyboard(value.getModulBKeyboard());
            setModulBGesang(value.getModulBGesang());
            setModulDTitel_1Id(value.getModulDTitel_1Id());
            setModulDTitel_2Id(value.getModulDTitel_2Id());
            setScoresConfirmedBy(value.getScoresConfirmedBy());
            setScoresConfirmedAt(value.getScoresConfirmedAt());
            setModulDTitelSelection(value.getModulDTitelSelection());
            resetChangedOnNotNull();
        }
    }
}
