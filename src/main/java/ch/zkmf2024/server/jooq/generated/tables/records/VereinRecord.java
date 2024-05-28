/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.records;

import ch.zkmf2024.server.jooq.generated.tables.Verein;
import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVerein;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinRecord extends UpdatableRecordImpl<VereinRecord> implements IVerein {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>verein.id</code>.
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>verein.id</code>.
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>verein.email</code>.
     */
    @Override
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>verein.email</code>.
     */
    @Override
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>verein.praesident_kontakt_id</code>.
     */
    @Override
    public void setPraesidentKontaktId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>verein.praesident_kontakt_id</code>.
     */
    @Override
    public Long getPraesidentKontaktId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>verein.direktion_kontakt_id</code>.
     */
    @Override
    public void setDirektionKontaktId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>verein.direktion_kontakt_id</code>.
     */
    @Override
    public Long getDirektionKontaktId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>verein.vereinsname</code>.
     */
    @Override
    public void setVereinsname(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>verein.vereinsname</code>.
     */
    @Override
    public String getVereinsname() {
        return (String) get(4);
    }

    /**
     * Setter for <code>verein.adresse</code>.
     */
    @Override
    public void setAdresse(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>verein.adresse</code>.
     */
    @Override
    public String getAdresse() {
        return (String) get(5);
    }

    /**
     * Setter for <code>verein.plz</code>.
     */
    @Override
    public void setPlz(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>verein.plz</code>.
     */
    @Override
    public Integer getPlz() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>verein.ort</code>.
     */
    @Override
    public void setOrt(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>verein.ort</code>.
     */
    @Override
    public String getOrt() {
        return (String) get(7);
    }

    /**
     * Setter for <code>verein.homepage</code>.
     */
    @Override
    public void setHomepage(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>verein.homepage</code>.
     */
    @Override
    public String getHomepage() {
        return (String) get(8);
    }

    /**
     * Setter for <code>verein.facebook</code>.
     */
    @Override
    public void setFacebook(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>verein.facebook</code>.
     */
    @Override
    public String getFacebook() {
        return (String) get(9);
    }

    /**
     * Setter for <code>verein.instagram</code>.
     */
    @Override
    public void setInstagram(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>verein.instagram</code>.
     */
    @Override
    public String getInstagram() {
        return (String) get(10);
    }

    /**
     * Setter for <code>verein.iban</code>.
     */
    @Override
    public void setIban(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>verein.iban</code>.
     */
    @Override
    public String getIban() {
        return (String) get(11);
    }

    /**
     * Setter for <code>verein.modula</code>.
     */
    @Override
    public void setModula(Boolean value) {
        set(12, value);
    }

    /**
     * Getter for <code>verein.modula</code>.
     */
    @Override
    public Boolean getModula() {
        return (Boolean) get(12);
    }

    /**
     * Setter for <code>verein.modulb</code>.
     */
    @Override
    public void setModulb(Boolean value) {
        set(13, value);
    }

    /**
     * Getter for <code>verein.modulb</code>.
     */
    @Override
    public Boolean getModulb() {
        return (Boolean) get(13);
    }

    /**
     * Setter for <code>verein.modulc</code>.
     */
    @Override
    public void setModulc(Boolean value) {
        set(14, value);
    }

    /**
     * Getter for <code>verein.modulc</code>.
     */
    @Override
    public Boolean getModulc() {
        return (Boolean) get(14);
    }

    /**
     * Setter for <code>verein.moduld</code>.
     */
    @Override
    public void setModuld(Boolean value) {
        set(15, value);
    }

    /**
     * Getter for <code>verein.moduld</code>.
     */
    @Override
    public Boolean getModuld() {
        return (Boolean) get(15);
    }

    /**
     * Setter for <code>verein.module</code>.
     */
    @Override
    public void setModule(Boolean value) {
        set(16, value);
    }

    /**
     * Getter for <code>verein.module</code>.
     */
    @Override
    public Boolean getModule() {
        return (Boolean) get(16);
    }

    /**
     * Setter for <code>verein.modulf</code>.
     */
    @Override
    public void setModulf(Boolean value) {
        set(17, value);
    }

    /**
     * Getter for <code>verein.modulf</code>.
     */
    @Override
    public Boolean getModulf() {
        return (Boolean) get(17);
    }

    /**
     * Setter for <code>verein.modulg</code>.
     */
    @Override
    public void setModulg(Boolean value) {
        set(18, value);
    }

    /**
     * Getter for <code>verein.modulg</code>.
     */
    @Override
    public Boolean getModulg() {
        return (Boolean) get(18);
    }

    /**
     * Setter for <code>verein.modulh</code>.
     */
    @Override
    public void setModulh(Boolean value) {
        set(19, value);
    }

    /**
     * Getter for <code>verein.modulh</code>.
     */
    @Override
    public Boolean getModulh() {
        return (Boolean) get(19);
    }

    /**
     * Setter for <code>verein.klasse_modula</code>.
     */
    @Override
    public void setKlasseModula(String value) {
        set(20, value);
    }

    /**
     * Getter for <code>verein.klasse_modula</code>.
     */
    @Override
    public String getKlasseModula() {
        return (String) get(20);
    }

    /**
     * Setter for <code>verein.klasse_modulb</code>.
     */
    @Override
    public void setKlasseModulb(String value) {
        set(21, value);
    }

    /**
     * Getter for <code>verein.klasse_modulb</code>.
     */
    @Override
    public String getKlasseModulb() {
        return (String) get(21);
    }

    /**
     * Setter for <code>verein.klasse_modulh</code>.
     */
    @Override
    public void setKlasseModulh(String value) {
        set(22, value);
    }

    /**
     * Getter for <code>verein.klasse_modulh</code>.
     */
    @Override
    public String getKlasseModulh() {
        return (String) get(22);
    }

    /**
     * Setter for <code>verein.harmonie</code>.
     */
    @Override
    public void setHarmonie(Boolean value) {
        set(23, value);
    }

    /**
     * Getter for <code>verein.harmonie</code>.
     */
    @Override
    public Boolean getHarmonie() {
        return (Boolean) get(23);
    }

    /**
     * Setter for <code>verein.brass_band</code>.
     */
    @Override
    public void setBrassBand(Boolean value) {
        set(24, value);
    }

    /**
     * Getter for <code>verein.brass_band</code>.
     */
    @Override
    public Boolean getBrassBand() {
        return (Boolean) get(24);
    }

    /**
     * Setter for <code>verein.fanfare</code>.
     */
    @Override
    public void setFanfare(Boolean value) {
        set(25, value);
    }

    /**
     * Getter for <code>verein.fanfare</code>.
     */
    @Override
    public Boolean getFanfare() {
        return (Boolean) get(25);
    }

    /**
     * Setter for <code>verein.tambouren</code>.
     */
    @Override
    public void setTambouren(Boolean value) {
        set(26, value);
    }

    /**
     * Getter for <code>verein.tambouren</code>.
     */
    @Override
    public Boolean getTambouren() {
        return (Boolean) get(26);
    }

    /**
     * Setter for <code>verein.perkussionsensemble</code>.
     */
    @Override
    public void setPerkussionsensemble(Boolean value) {
        set(27, value);
    }

    /**
     * Getter for <code>verein.perkussionsensemble</code>.
     */
    @Override
    public Boolean getPerkussionsensemble() {
        return (Boolean) get(27);
    }

    /**
     * Setter for <code>verein.website_text</code>.
     */
    @Override
    public void setWebsiteText(String value) {
        set(28, value);
    }

    /**
     * Getter for <code>verein.website_text</code>.
     */
    @Override
    public String getWebsiteText() {
        return (String) get(28);
    }

    /**
     * Setter for <code>verein.direktion_doppeleinsatz</code>.
     */
    @Override
    public void setDirektionDoppeleinsatz(Boolean value) {
        set(29, value);
    }

    /**
     * Getter for <code>verein.direktion_doppeleinsatz</code>.
     */
    @Override
    public Boolean getDirektionDoppeleinsatz() {
        return (Boolean) get(29);
    }

    /**
     * Setter for <code>verein.direktion_doppeleinsatz_verein</code>.
     */
    @Override
    public void setDirektionDoppeleinsatzVerein(String value) {
        set(30, value);
    }

    /**
     * Getter for <code>verein.direktion_doppeleinsatz_verein</code>.
     */
    @Override
    public String getDirektionDoppeleinsatzVerein() {
        return (String) get(30);
    }

    /**
     * Setter for <code>verein.mitspieler_doppeleinsatz</code>.
     */
    @Override
    public void setMitspielerDoppeleinsatz(Boolean value) {
        set(31, value);
    }

    /**
     * Getter for <code>verein.mitspieler_doppeleinsatz</code>.
     */
    @Override
    public Boolean getMitspielerDoppeleinsatz() {
        return (Boolean) get(31);
    }

    /**
     * Setter for <code>verein.confirmed_at</code>.
     */
    @Override
    public void setConfirmedAt(LocalDateTime value) {
        set(32, value);
    }

    /**
     * Getter for <code>verein.confirmed_at</code>.
     */
    @Override
    public LocalDateTime getConfirmedAt() {
        return (LocalDateTime) get(32);
    }

    /**
     * Setter for <code>verein.tambouren_kat_a</code>.
     */
    @Override
    public void setTambourenKatA(Boolean value) {
        set(33, value);
    }

    /**
     * Getter for <code>verein.tambouren_kat_a</code>.
     */
    @Override
    public Boolean getTambourenKatA() {
        return (Boolean) get(33);
    }

    /**
     * Setter for <code>verein.tambouren_kat_b</code>.
     */
    @Override
    public void setTambourenKatB(Boolean value) {
        set(34, value);
    }

    /**
     * Getter for <code>verein.tambouren_kat_b</code>.
     */
    @Override
    public Boolean getTambourenKatB() {
        return (Boolean) get(34);
    }

    /**
     * Setter for <code>verein.tambouren_kat_c</code>.
     */
    @Override
    public void setTambourenKatC(Boolean value) {
        set(35, value);
    }

    /**
     * Getter for <code>verein.tambouren_kat_c</code>.
     */
    @Override
    public Boolean getTambourenKatC() {
        return (Boolean) get(35);
    }

    /**
     * Setter for <code>verein.phase2_confirmed_by</code>.
     */
    @Override
    public void setPhase2ConfirmedBy(String value) {
        set(36, value);
    }

    /**
     * Getter for <code>verein.phase2_confirmed_by</code>.
     */
    @Override
    public String getPhase2ConfirmedBy() {
        return (String) get(36);
    }

    /**
     * Setter for <code>verein.phase2_confirmed_at</code>.
     */
    @Override
    public void setPhase2ConfirmedAt(LocalDateTime value) {
        set(37, value);
    }

    /**
     * Getter for <code>verein.phase2_confirmed_at</code>.
     */
    @Override
    public LocalDateTime getPhase2ConfirmedAt() {
        return (LocalDateTime) get(37);
    }

    /**
     * Setter for <code>verein.prov_wettspiel</code>.
     */
    @Override
    public void setProvWettspiel(String value) {
        set(38, value);
    }

    /**
     * Getter for <code>verein.prov_wettspiel</code>.
     */
    @Override
    public String getProvWettspiel() {
        return (String) get(38);
    }

    /**
     * Setter for <code>verein.prov_parademusik</code>.
     */
    @Override
    public void setProvParademusik(String value) {
        set(39, value);
    }

    /**
     * Getter for <code>verein.prov_parademusik</code>.
     */
    @Override
    public String getProvParademusik() {
        return (String) get(39);
    }

    /**
     * Setter for <code>verein.prov_platzkonzert</code>.
     */
    @Override
    public void setProvPlatzkonzert(String value) {
        set(40, value);
    }

    /**
     * Getter for <code>verein.prov_platzkonzert</code>.
     */
    @Override
    public String getProvPlatzkonzert() {
        return (String) get(40);
    }

    /**
     * Setter for <code>verein.programm_last_updated</code>.
     */
    @Override
    public void setProgrammLastUpdated(LocalDateTime value) {
        set(41, value);
    }

    /**
     * Getter for <code>verein.programm_last_updated</code>.
     */
    @Override
    public LocalDateTime getProgrammLastUpdated() {
        return (LocalDateTime) get(41);
    }

    /**
     * Setter for <code>verein.identifier</code>.
     */
    @Override
    public void setIdentifier(String value) {
        set(42, value);
    }

    /**
     * Getter for <code>verein.identifier</code>.
     */
    @Override
    public String getIdentifier() {
        return (String) get(42);
    }

    /**
     * Setter for <code>verein.lunch_time</code>.
     */
    @Override
    public void setLunchTime(LocalTime value) {
        set(43, value);
    }

    /**
     * Getter for <code>verein.lunch_time</code>.
     */
    @Override
    public LocalTime getLunchTime() {
        return (LocalTime) get(43);
    }

    /**
     * Setter for <code>verein.phase4_confirmed_at</code>.
     */
    @Override
    public void setPhase4ConfirmedAt(LocalDateTime value) {
        set(44, value);
    }

    /**
     * Getter for <code>verein.phase4_confirmed_at</code>.
     */
    @Override
    public LocalDateTime getPhase4ConfirmedAt() {
        return (LocalDateTime) get(44);
    }

    /**
     * Setter for <code>verein.stage_setup_confirmed_at</code>.
     */
    @Override
    public void setStageSetupConfirmedAt(LocalDateTime value) {
        set(45, value);
    }

    /**
     * Getter for <code>verein.stage_setup_confirmed_at</code>.
     */
    @Override
    public LocalDateTime getStageSetupConfirmedAt() {
        return (LocalDateTime) get(45);
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
    public void from(IVerein from) {
        setId(from.getId());
        setEmail(from.getEmail());
        setPraesidentKontaktId(from.getPraesidentKontaktId());
        setDirektionKontaktId(from.getDirektionKontaktId());
        setVereinsname(from.getVereinsname());
        setAdresse(from.getAdresse());
        setPlz(from.getPlz());
        setOrt(from.getOrt());
        setHomepage(from.getHomepage());
        setFacebook(from.getFacebook());
        setInstagram(from.getInstagram());
        setIban(from.getIban());
        setModula(from.getModula());
        setModulb(from.getModulb());
        setModulc(from.getModulc());
        setModuld(from.getModuld());
        setModule(from.getModule());
        setModulf(from.getModulf());
        setModulg(from.getModulg());
        setModulh(from.getModulh());
        setKlasseModula(from.getKlasseModula());
        setKlasseModulb(from.getKlasseModulb());
        setKlasseModulh(from.getKlasseModulh());
        setHarmonie(from.getHarmonie());
        setBrassBand(from.getBrassBand());
        setFanfare(from.getFanfare());
        setTambouren(from.getTambouren());
        setPerkussionsensemble(from.getPerkussionsensemble());
        setWebsiteText(from.getWebsiteText());
        setDirektionDoppeleinsatz(from.getDirektionDoppeleinsatz());
        setDirektionDoppeleinsatzVerein(from.getDirektionDoppeleinsatzVerein());
        setMitspielerDoppeleinsatz(from.getMitspielerDoppeleinsatz());
        setConfirmedAt(from.getConfirmedAt());
        setTambourenKatA(from.getTambourenKatA());
        setTambourenKatB(from.getTambourenKatB());
        setTambourenKatC(from.getTambourenKatC());
        setPhase2ConfirmedBy(from.getPhase2ConfirmedBy());
        setPhase2ConfirmedAt(from.getPhase2ConfirmedAt());
        setProvWettspiel(from.getProvWettspiel());
        setProvParademusik(from.getProvParademusik());
        setProvPlatzkonzert(from.getProvPlatzkonzert());
        setProgrammLastUpdated(from.getProgrammLastUpdated());
        setIdentifier(from.getIdentifier());
        setLunchTime(from.getLunchTime());
        setPhase4ConfirmedAt(from.getPhase4ConfirmedAt());
        setStageSetupConfirmedAt(from.getStageSetupConfirmedAt());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IVerein> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VereinRecord
     */
    public VereinRecord() {
        super(Verein.VEREIN);
    }

    /**
     * Create a detached, initialised VereinRecord
     */
    public VereinRecord(Long id, String email, Long praesidentKontaktId, Long direktionKontaktId, String vereinsname, String adresse, Integer plz, String ort, String homepage, String facebook, String instagram, String iban, Boolean modula, Boolean modulb, Boolean modulc, Boolean moduld, Boolean module, Boolean modulf, Boolean modulg, Boolean modulh, String klasseModula, String klasseModulb, String klasseModulh, Boolean harmonie, Boolean brassBand, Boolean fanfare, Boolean tambouren, Boolean perkussionsensemble, String websiteText, Boolean direktionDoppeleinsatz, String direktionDoppeleinsatzVerein, Boolean mitspielerDoppeleinsatz, LocalDateTime confirmedAt, Boolean tambourenKatA, Boolean tambourenKatB, Boolean tambourenKatC, String phase2ConfirmedBy, LocalDateTime phase2ConfirmedAt, String provWettspiel, String provParademusik, String provPlatzkonzert, LocalDateTime programmLastUpdated, String identifier, LocalTime lunchTime, LocalDateTime phase4ConfirmedAt, LocalDateTime stageSetupConfirmedAt) {
        super(Verein.VEREIN);

        setId(id);
        setEmail(email);
        setPraesidentKontaktId(praesidentKontaktId);
        setDirektionKontaktId(direktionKontaktId);
        setVereinsname(vereinsname);
        setAdresse(adresse);
        setPlz(plz);
        setOrt(ort);
        setHomepage(homepage);
        setFacebook(facebook);
        setInstagram(instagram);
        setIban(iban);
        setModula(modula);
        setModulb(modulb);
        setModulc(modulc);
        setModuld(moduld);
        setModule(module);
        setModulf(modulf);
        setModulg(modulg);
        setModulh(modulh);
        setKlasseModula(klasseModula);
        setKlasseModulb(klasseModulb);
        setKlasseModulh(klasseModulh);
        setHarmonie(harmonie);
        setBrassBand(brassBand);
        setFanfare(fanfare);
        setTambouren(tambouren);
        setPerkussionsensemble(perkussionsensemble);
        setWebsiteText(websiteText);
        setDirektionDoppeleinsatz(direktionDoppeleinsatz);
        setDirektionDoppeleinsatzVerein(direktionDoppeleinsatzVerein);
        setMitspielerDoppeleinsatz(mitspielerDoppeleinsatz);
        setConfirmedAt(confirmedAt);
        setTambourenKatA(tambourenKatA);
        setTambourenKatB(tambourenKatB);
        setTambourenKatC(tambourenKatC);
        setPhase2ConfirmedBy(phase2ConfirmedBy);
        setPhase2ConfirmedAt(phase2ConfirmedAt);
        setProvWettspiel(provWettspiel);
        setProvParademusik(provParademusik);
        setProvPlatzkonzert(provPlatzkonzert);
        setProgrammLastUpdated(programmLastUpdated);
        setIdentifier(identifier);
        setLunchTime(lunchTime);
        setPhase4ConfirmedAt(phase4ConfirmedAt);
        setStageSetupConfirmedAt(stageSetupConfirmedAt);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised VereinRecord
     */
    public VereinRecord(VereinPojo value) {
        super(Verein.VEREIN);

        if (value != null) {
            setId(value.getId());
            setEmail(value.getEmail());
            setPraesidentKontaktId(value.getPraesidentKontaktId());
            setDirektionKontaktId(value.getDirektionKontaktId());
            setVereinsname(value.getVereinsname());
            setAdresse(value.getAdresse());
            setPlz(value.getPlz());
            setOrt(value.getOrt());
            setHomepage(value.getHomepage());
            setFacebook(value.getFacebook());
            setInstagram(value.getInstagram());
            setIban(value.getIban());
            setModula(value.getModula());
            setModulb(value.getModulb());
            setModulc(value.getModulc());
            setModuld(value.getModuld());
            setModule(value.getModule());
            setModulf(value.getModulf());
            setModulg(value.getModulg());
            setModulh(value.getModulh());
            setKlasseModula(value.getKlasseModula());
            setKlasseModulb(value.getKlasseModulb());
            setKlasseModulh(value.getKlasseModulh());
            setHarmonie(value.getHarmonie());
            setBrassBand(value.getBrassBand());
            setFanfare(value.getFanfare());
            setTambouren(value.getTambouren());
            setPerkussionsensemble(value.getPerkussionsensemble());
            setWebsiteText(value.getWebsiteText());
            setDirektionDoppeleinsatz(value.getDirektionDoppeleinsatz());
            setDirektionDoppeleinsatzVerein(value.getDirektionDoppeleinsatzVerein());
            setMitspielerDoppeleinsatz(value.getMitspielerDoppeleinsatz());
            setConfirmedAt(value.getConfirmedAt());
            setTambourenKatA(value.getTambourenKatA());
            setTambourenKatB(value.getTambourenKatB());
            setTambourenKatC(value.getTambourenKatC());
            setPhase2ConfirmedBy(value.getPhase2ConfirmedBy());
            setPhase2ConfirmedAt(value.getPhase2ConfirmedAt());
            setProvWettspiel(value.getProvWettspiel());
            setProvParademusik(value.getProvParademusik());
            setProvPlatzkonzert(value.getProvPlatzkonzert());
            setProgrammLastUpdated(value.getProgrammLastUpdated());
            setIdentifier(value.getIdentifier());
            setLunchTime(value.getLunchTime());
            setPhase4ConfirmedAt(value.getPhase4ConfirmedAt());
            setStageSetupConfirmedAt(value.getStageSetupConfirmedAt());
            resetChangedOnNotNull();
        }
    }
}
