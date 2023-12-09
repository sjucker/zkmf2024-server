/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IVerein;

import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class VereinPojo implements IVerein {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private Long praesidentKontaktId;
    private Long direktionKontaktId;
    private String vereinsname;
    private String adresse;
    private Integer plz;
    private String ort;
    private String homepage;
    private String facebook;
    private String instagram;
    private String iban;
    private Boolean modula;
    private Boolean modulb;
    private Boolean modulc;
    private Boolean moduld;
    private Boolean module;
    private Boolean modulf;
    private Boolean modulg;
    private Boolean modulh;
    private String klasseModula;
    private String klasseModulb;
    private String klasseModulh;
    private Boolean harmonie;
    private Boolean brassBand;
    private Boolean fanfare;
    private Boolean tambouren;
    private Boolean perkussionsensemble;
    private String websiteText;
    private Boolean direktionDoppeleinsatz;
    private String direktionDoppeleinsatzVerein;
    private Boolean mitspielerDoppeleinsatz;
    private LocalDateTime confirmedAt;
    private Boolean tambourenKatA;
    private Boolean tambourenKatB;
    private Boolean tambourenKatC;
    private String phase2ConfirmedBy;
    private LocalDateTime phase2ConfirmedAt;
    private String provWettspiel;
    private String provParademusik;
    private String provPlatzkonzert;
    private LocalDateTime programmLastUpdated;
    private String identifier;

    public VereinPojo() {
    }

    public VereinPojo(IVerein value) {
        this.id = value.getId();
        this.email = value.getEmail();
        this.praesidentKontaktId = value.getPraesidentKontaktId();
        this.direktionKontaktId = value.getDirektionKontaktId();
        this.vereinsname = value.getVereinsname();
        this.adresse = value.getAdresse();
        this.plz = value.getPlz();
        this.ort = value.getOrt();
        this.homepage = value.getHomepage();
        this.facebook = value.getFacebook();
        this.instagram = value.getInstagram();
        this.iban = value.getIban();
        this.modula = value.getModula();
        this.modulb = value.getModulb();
        this.modulc = value.getModulc();
        this.moduld = value.getModuld();
        this.module = value.getModule();
        this.modulf = value.getModulf();
        this.modulg = value.getModulg();
        this.modulh = value.getModulh();
        this.klasseModula = value.getKlasseModula();
        this.klasseModulb = value.getKlasseModulb();
        this.klasseModulh = value.getKlasseModulh();
        this.harmonie = value.getHarmonie();
        this.brassBand = value.getBrassBand();
        this.fanfare = value.getFanfare();
        this.tambouren = value.getTambouren();
        this.perkussionsensemble = value.getPerkussionsensemble();
        this.websiteText = value.getWebsiteText();
        this.direktionDoppeleinsatz = value.getDirektionDoppeleinsatz();
        this.direktionDoppeleinsatzVerein = value.getDirektionDoppeleinsatzVerein();
        this.mitspielerDoppeleinsatz = value.getMitspielerDoppeleinsatz();
        this.confirmedAt = value.getConfirmedAt();
        this.tambourenKatA = value.getTambourenKatA();
        this.tambourenKatB = value.getTambourenKatB();
        this.tambourenKatC = value.getTambourenKatC();
        this.phase2ConfirmedBy = value.getPhase2ConfirmedBy();
        this.phase2ConfirmedAt = value.getPhase2ConfirmedAt();
        this.provWettspiel = value.getProvWettspiel();
        this.provParademusik = value.getProvParademusik();
        this.provPlatzkonzert = value.getProvPlatzkonzert();
        this.programmLastUpdated = value.getProgrammLastUpdated();
        this.identifier = value.getIdentifier();
    }

    public VereinPojo(
            Long id,
            String email,
            Long praesidentKontaktId,
            Long direktionKontaktId,
            String vereinsname,
            String adresse,
            Integer plz,
            String ort,
            String homepage,
            String facebook,
            String instagram,
            String iban,
            Boolean modula,
            Boolean modulb,
            Boolean modulc,
            Boolean moduld,
            Boolean module,
            Boolean modulf,
            Boolean modulg,
            Boolean modulh,
            String klasseModula,
            String klasseModulb,
            String klasseModulh,
            Boolean harmonie,
            Boolean brassBand,
            Boolean fanfare,
            Boolean tambouren,
            Boolean perkussionsensemble,
            String websiteText,
            Boolean direktionDoppeleinsatz,
            String direktionDoppeleinsatzVerein,
            Boolean mitspielerDoppeleinsatz,
            LocalDateTime confirmedAt,
            Boolean tambourenKatA,
            Boolean tambourenKatB,
            Boolean tambourenKatC,
            String phase2ConfirmedBy,
            LocalDateTime phase2ConfirmedAt,
            String provWettspiel,
            String provParademusik,
            String provPlatzkonzert,
            LocalDateTime programmLastUpdated,
            String identifier
    ) {
        this.id = id;
        this.email = email;
        this.praesidentKontaktId = praesidentKontaktId;
        this.direktionKontaktId = direktionKontaktId;
        this.vereinsname = vereinsname;
        this.adresse = adresse;
        this.plz = plz;
        this.ort = ort;
        this.homepage = homepage;
        this.facebook = facebook;
        this.instagram = instagram;
        this.iban = iban;
        this.modula = modula;
        this.modulb = modulb;
        this.modulc = modulc;
        this.moduld = moduld;
        this.module = module;
        this.modulf = modulf;
        this.modulg = modulg;
        this.modulh = modulh;
        this.klasseModula = klasseModula;
        this.klasseModulb = klasseModulb;
        this.klasseModulh = klasseModulh;
        this.harmonie = harmonie;
        this.brassBand = brassBand;
        this.fanfare = fanfare;
        this.tambouren = tambouren;
        this.perkussionsensemble = perkussionsensemble;
        this.websiteText = websiteText;
        this.direktionDoppeleinsatz = direktionDoppeleinsatz;
        this.direktionDoppeleinsatzVerein = direktionDoppeleinsatzVerein;
        this.mitspielerDoppeleinsatz = mitspielerDoppeleinsatz;
        this.confirmedAt = confirmedAt;
        this.tambourenKatA = tambourenKatA;
        this.tambourenKatB = tambourenKatB;
        this.tambourenKatC = tambourenKatC;
        this.phase2ConfirmedBy = phase2ConfirmedBy;
        this.phase2ConfirmedAt = phase2ConfirmedAt;
        this.provWettspiel = provWettspiel;
        this.provParademusik = provParademusik;
        this.provPlatzkonzert = provPlatzkonzert;
        this.programmLastUpdated = programmLastUpdated;
        this.identifier = identifier;
    }

    /**
     * Getter for <code>verein.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>verein.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>verein.email</code>.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>verein.email</code>.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for <code>verein.praesident_kontakt_id</code>.
     */
    @Override
    public Long getPraesidentKontaktId() {
        return this.praesidentKontaktId;
    }

    /**
     * Setter for <code>verein.praesident_kontakt_id</code>.
     */
    @Override
    public void setPraesidentKontaktId(Long praesidentKontaktId) {
        this.praesidentKontaktId = praesidentKontaktId;
    }

    /**
     * Getter for <code>verein.direktion_kontakt_id</code>.
     */
    @Override
    public Long getDirektionKontaktId() {
        return this.direktionKontaktId;
    }

    /**
     * Setter for <code>verein.direktion_kontakt_id</code>.
     */
    @Override
    public void setDirektionKontaktId(Long direktionKontaktId) {
        this.direktionKontaktId = direktionKontaktId;
    }

    /**
     * Getter for <code>verein.vereinsname</code>.
     */
    @Override
    public String getVereinsname() {
        return this.vereinsname;
    }

    /**
     * Setter for <code>verein.vereinsname</code>.
     */
    @Override
    public void setVereinsname(String vereinsname) {
        this.vereinsname = vereinsname;
    }

    /**
     * Getter for <code>verein.adresse</code>.
     */
    @Override
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Setter for <code>verein.adresse</code>.
     */
    @Override
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Getter for <code>verein.plz</code>.
     */
    @Override
    public Integer getPlz() {
        return this.plz;
    }

    /**
     * Setter for <code>verein.plz</code>.
     */
    @Override
    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    /**
     * Getter for <code>verein.ort</code>.
     */
    @Override
    public String getOrt() {
        return this.ort;
    }

    /**
     * Setter for <code>verein.ort</code>.
     */
    @Override
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * Getter for <code>verein.homepage</code>.
     */
    @Override
    public String getHomepage() {
        return this.homepage;
    }

    /**
     * Setter for <code>verein.homepage</code>.
     */
    @Override
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /**
     * Getter for <code>verein.facebook</code>.
     */
    @Override
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Setter for <code>verein.facebook</code>.
     */
    @Override
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * Getter for <code>verein.instagram</code>.
     */
    @Override
    public String getInstagram() {
        return this.instagram;
    }

    /**
     * Setter for <code>verein.instagram</code>.
     */
    @Override
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    /**
     * Getter for <code>verein.iban</code>.
     */
    @Override
    public String getIban() {
        return this.iban;
    }

    /**
     * Setter for <code>verein.iban</code>.
     */
    @Override
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Getter for <code>verein.modula</code>.
     */
    @Override
    public Boolean getModula() {
        return this.modula;
    }

    /**
     * Setter for <code>verein.modula</code>.
     */
    @Override
    public void setModula(Boolean modula) {
        this.modula = modula;
    }

    /**
     * Getter for <code>verein.modulb</code>.
     */
    @Override
    public Boolean getModulb() {
        return this.modulb;
    }

    /**
     * Setter for <code>verein.modulb</code>.
     */
    @Override
    public void setModulb(Boolean modulb) {
        this.modulb = modulb;
    }

    /**
     * Getter for <code>verein.modulc</code>.
     */
    @Override
    public Boolean getModulc() {
        return this.modulc;
    }

    /**
     * Setter for <code>verein.modulc</code>.
     */
    @Override
    public void setModulc(Boolean modulc) {
        this.modulc = modulc;
    }

    /**
     * Getter for <code>verein.moduld</code>.
     */
    @Override
    public Boolean getModuld() {
        return this.moduld;
    }

    /**
     * Setter for <code>verein.moduld</code>.
     */
    @Override
    public void setModuld(Boolean moduld) {
        this.moduld = moduld;
    }

    /**
     * Getter for <code>verein.module</code>.
     */
    @Override
    public Boolean getModule() {
        return this.module;
    }

    /**
     * Setter for <code>verein.module</code>.
     */
    @Override
    public void setModule(Boolean module) {
        this.module = module;
    }

    /**
     * Getter for <code>verein.modulf</code>.
     */
    @Override
    public Boolean getModulf() {
        return this.modulf;
    }

    /**
     * Setter for <code>verein.modulf</code>.
     */
    @Override
    public void setModulf(Boolean modulf) {
        this.modulf = modulf;
    }

    /**
     * Getter for <code>verein.modulg</code>.
     */
    @Override
    public Boolean getModulg() {
        return this.modulg;
    }

    /**
     * Setter for <code>verein.modulg</code>.
     */
    @Override
    public void setModulg(Boolean modulg) {
        this.modulg = modulg;
    }

    /**
     * Getter for <code>verein.modulh</code>.
     */
    @Override
    public Boolean getModulh() {
        return this.modulh;
    }

    /**
     * Setter for <code>verein.modulh</code>.
     */
    @Override
    public void setModulh(Boolean modulh) {
        this.modulh = modulh;
    }

    /**
     * Getter for <code>verein.klasse_modula</code>.
     */
    @Override
    public String getKlasseModula() {
        return this.klasseModula;
    }

    /**
     * Setter for <code>verein.klasse_modula</code>.
     */
    @Override
    public void setKlasseModula(String klasseModula) {
        this.klasseModula = klasseModula;
    }

    /**
     * Getter for <code>verein.klasse_modulb</code>.
     */
    @Override
    public String getKlasseModulb() {
        return this.klasseModulb;
    }

    /**
     * Setter for <code>verein.klasse_modulb</code>.
     */
    @Override
    public void setKlasseModulb(String klasseModulb) {
        this.klasseModulb = klasseModulb;
    }

    /**
     * Getter for <code>verein.klasse_modulh</code>.
     */
    @Override
    public String getKlasseModulh() {
        return this.klasseModulh;
    }

    /**
     * Setter for <code>verein.klasse_modulh</code>.
     */
    @Override
    public void setKlasseModulh(String klasseModulh) {
        this.klasseModulh = klasseModulh;
    }

    /**
     * Getter for <code>verein.harmonie</code>.
     */
    @Override
    public Boolean getHarmonie() {
        return this.harmonie;
    }

    /**
     * Setter for <code>verein.harmonie</code>.
     */
    @Override
    public void setHarmonie(Boolean harmonie) {
        this.harmonie = harmonie;
    }

    /**
     * Getter for <code>verein.brass_band</code>.
     */
    @Override
    public Boolean getBrassBand() {
        return this.brassBand;
    }

    /**
     * Setter for <code>verein.brass_band</code>.
     */
    @Override
    public void setBrassBand(Boolean brassBand) {
        this.brassBand = brassBand;
    }

    /**
     * Getter for <code>verein.fanfare</code>.
     */
    @Override
    public Boolean getFanfare() {
        return this.fanfare;
    }

    /**
     * Setter for <code>verein.fanfare</code>.
     */
    @Override
    public void setFanfare(Boolean fanfare) {
        this.fanfare = fanfare;
    }

    /**
     * Getter for <code>verein.tambouren</code>.
     */
    @Override
    public Boolean getTambouren() {
        return this.tambouren;
    }

    /**
     * Setter for <code>verein.tambouren</code>.
     */
    @Override
    public void setTambouren(Boolean tambouren) {
        this.tambouren = tambouren;
    }

    /**
     * Getter for <code>verein.perkussionsensemble</code>.
     */
    @Override
    public Boolean getPerkussionsensemble() {
        return this.perkussionsensemble;
    }

    /**
     * Setter for <code>verein.perkussionsensemble</code>.
     */
    @Override
    public void setPerkussionsensemble(Boolean perkussionsensemble) {
        this.perkussionsensemble = perkussionsensemble;
    }

    /**
     * Getter for <code>verein.website_text</code>.
     */
    @Override
    public String getWebsiteText() {
        return this.websiteText;
    }

    /**
     * Setter for <code>verein.website_text</code>.
     */
    @Override
    public void setWebsiteText(String websiteText) {
        this.websiteText = websiteText;
    }

    /**
     * Getter for <code>verein.direktion_doppeleinsatz</code>.
     */
    @Override
    public Boolean getDirektionDoppeleinsatz() {
        return this.direktionDoppeleinsatz;
    }

    /**
     * Setter for <code>verein.direktion_doppeleinsatz</code>.
     */
    @Override
    public void setDirektionDoppeleinsatz(Boolean direktionDoppeleinsatz) {
        this.direktionDoppeleinsatz = direktionDoppeleinsatz;
    }

    /**
     * Getter for <code>verein.direktion_doppeleinsatz_verein</code>.
     */
    @Override
    public String getDirektionDoppeleinsatzVerein() {
        return this.direktionDoppeleinsatzVerein;
    }

    /**
     * Setter for <code>verein.direktion_doppeleinsatz_verein</code>.
     */
    @Override
    public void setDirektionDoppeleinsatzVerein(String direktionDoppeleinsatzVerein) {
        this.direktionDoppeleinsatzVerein = direktionDoppeleinsatzVerein;
    }

    /**
     * Getter for <code>verein.mitspieler_doppeleinsatz</code>.
     */
    @Override
    public Boolean getMitspielerDoppeleinsatz() {
        return this.mitspielerDoppeleinsatz;
    }

    /**
     * Setter for <code>verein.mitspieler_doppeleinsatz</code>.
     */
    @Override
    public void setMitspielerDoppeleinsatz(Boolean mitspielerDoppeleinsatz) {
        this.mitspielerDoppeleinsatz = mitspielerDoppeleinsatz;
    }

    /**
     * Getter for <code>verein.confirmed_at</code>.
     */
    @Override
    public LocalDateTime getConfirmedAt() {
        return this.confirmedAt;
    }

    /**
     * Setter for <code>verein.confirmed_at</code>.
     */
    @Override
    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    /**
     * Getter for <code>verein.tambouren_kat_a</code>.
     */
    @Override
    public Boolean getTambourenKatA() {
        return this.tambourenKatA;
    }

    /**
     * Setter for <code>verein.tambouren_kat_a</code>.
     */
    @Override
    public void setTambourenKatA(Boolean tambourenKatA) {
        this.tambourenKatA = tambourenKatA;
    }

    /**
     * Getter for <code>verein.tambouren_kat_b</code>.
     */
    @Override
    public Boolean getTambourenKatB() {
        return this.tambourenKatB;
    }

    /**
     * Setter for <code>verein.tambouren_kat_b</code>.
     */
    @Override
    public void setTambourenKatB(Boolean tambourenKatB) {
        this.tambourenKatB = tambourenKatB;
    }

    /**
     * Getter for <code>verein.tambouren_kat_c</code>.
     */
    @Override
    public Boolean getTambourenKatC() {
        return this.tambourenKatC;
    }

    /**
     * Setter for <code>verein.tambouren_kat_c</code>.
     */
    @Override
    public void setTambourenKatC(Boolean tambourenKatC) {
        this.tambourenKatC = tambourenKatC;
    }

    /**
     * Getter for <code>verein.phase2_confirmed_by</code>.
     */
    @Override
    public String getPhase2ConfirmedBy() {
        return this.phase2ConfirmedBy;
    }

    /**
     * Setter for <code>verein.phase2_confirmed_by</code>.
     */
    @Override
    public void setPhase2ConfirmedBy(String phase2ConfirmedBy) {
        this.phase2ConfirmedBy = phase2ConfirmedBy;
    }

    /**
     * Getter for <code>verein.phase2_confirmed_at</code>.
     */
    @Override
    public LocalDateTime getPhase2ConfirmedAt() {
        return this.phase2ConfirmedAt;
    }

    /**
     * Setter for <code>verein.phase2_confirmed_at</code>.
     */
    @Override
    public void setPhase2ConfirmedAt(LocalDateTime phase2ConfirmedAt) {
        this.phase2ConfirmedAt = phase2ConfirmedAt;
    }

    /**
     * Getter for <code>verein.prov_wettspiel</code>.
     */
    @Override
    public String getProvWettspiel() {
        return this.provWettspiel;
    }

    /**
     * Setter for <code>verein.prov_wettspiel</code>.
     */
    @Override
    public void setProvWettspiel(String provWettspiel) {
        this.provWettspiel = provWettspiel;
    }

    /**
     * Getter for <code>verein.prov_parademusik</code>.
     */
    @Override
    public String getProvParademusik() {
        return this.provParademusik;
    }

    /**
     * Setter for <code>verein.prov_parademusik</code>.
     */
    @Override
    public void setProvParademusik(String provParademusik) {
        this.provParademusik = provParademusik;
    }

    /**
     * Getter for <code>verein.prov_platzkonzert</code>.
     */
    @Override
    public String getProvPlatzkonzert() {
        return this.provPlatzkonzert;
    }

    /**
     * Setter for <code>verein.prov_platzkonzert</code>.
     */
    @Override
    public void setProvPlatzkonzert(String provPlatzkonzert) {
        this.provPlatzkonzert = provPlatzkonzert;
    }

    /**
     * Getter for <code>verein.programm_last_updated</code>.
     */
    @Override
    public LocalDateTime getProgrammLastUpdated() {
        return this.programmLastUpdated;
    }

    /**
     * Setter for <code>verein.programm_last_updated</code>.
     */
    @Override
    public void setProgrammLastUpdated(LocalDateTime programmLastUpdated) {
        this.programmLastUpdated = programmLastUpdated;
    }

    /**
     * Getter for <code>verein.identifier</code>.
     */
    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Setter for <code>verein.identifier</code>.
     */
    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VereinPojo other = (VereinPojo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.email == null) {
            if (other.email != null)
                return false;
        } else if (!this.email.equals(other.email))
            return false;
        if (this.praesidentKontaktId == null) {
            if (other.praesidentKontaktId != null)
                return false;
        } else if (!this.praesidentKontaktId.equals(other.praesidentKontaktId))
            return false;
        if (this.direktionKontaktId == null) {
            if (other.direktionKontaktId != null)
                return false;
        } else if (!this.direktionKontaktId.equals(other.direktionKontaktId))
            return false;
        if (this.vereinsname == null) {
            if (other.vereinsname != null)
                return false;
        } else if (!this.vereinsname.equals(other.vereinsname))
            return false;
        if (this.adresse == null) {
            if (other.adresse != null)
                return false;
        } else if (!this.adresse.equals(other.adresse))
            return false;
        if (this.plz == null) {
            if (other.plz != null)
                return false;
        } else if (!this.plz.equals(other.plz))
            return false;
        if (this.ort == null) {
            if (other.ort != null)
                return false;
        } else if (!this.ort.equals(other.ort))
            return false;
        if (this.homepage == null) {
            if (other.homepage != null)
                return false;
        } else if (!this.homepage.equals(other.homepage))
            return false;
        if (this.facebook == null) {
            if (other.facebook != null)
                return false;
        } else if (!this.facebook.equals(other.facebook))
            return false;
        if (this.instagram == null) {
            if (other.instagram != null)
                return false;
        } else if (!this.instagram.equals(other.instagram))
            return false;
        if (this.iban == null) {
            if (other.iban != null)
                return false;
        } else if (!this.iban.equals(other.iban))
            return false;
        if (this.modula == null) {
            if (other.modula != null)
                return false;
        } else if (!this.modula.equals(other.modula))
            return false;
        if (this.modulb == null) {
            if (other.modulb != null)
                return false;
        } else if (!this.modulb.equals(other.modulb))
            return false;
        if (this.modulc == null) {
            if (other.modulc != null)
                return false;
        } else if (!this.modulc.equals(other.modulc))
            return false;
        if (this.moduld == null) {
            if (other.moduld != null)
                return false;
        } else if (!this.moduld.equals(other.moduld))
            return false;
        if (this.module == null) {
            if (other.module != null)
                return false;
        } else if (!this.module.equals(other.module))
            return false;
        if (this.modulf == null) {
            if (other.modulf != null)
                return false;
        } else if (!this.modulf.equals(other.modulf))
            return false;
        if (this.modulg == null) {
            if (other.modulg != null)
                return false;
        } else if (!this.modulg.equals(other.modulg))
            return false;
        if (this.modulh == null) {
            if (other.modulh != null)
                return false;
        } else if (!this.modulh.equals(other.modulh))
            return false;
        if (this.klasseModula == null) {
            if (other.klasseModula != null)
                return false;
        } else if (!this.klasseModula.equals(other.klasseModula))
            return false;
        if (this.klasseModulb == null) {
            if (other.klasseModulb != null)
                return false;
        } else if (!this.klasseModulb.equals(other.klasseModulb))
            return false;
        if (this.klasseModulh == null) {
            if (other.klasseModulh != null)
                return false;
        } else if (!this.klasseModulh.equals(other.klasseModulh))
            return false;
        if (this.harmonie == null) {
            if (other.harmonie != null)
                return false;
        } else if (!this.harmonie.equals(other.harmonie))
            return false;
        if (this.brassBand == null) {
            if (other.brassBand != null)
                return false;
        } else if (!this.brassBand.equals(other.brassBand))
            return false;
        if (this.fanfare == null) {
            if (other.fanfare != null)
                return false;
        } else if (!this.fanfare.equals(other.fanfare))
            return false;
        if (this.tambouren == null) {
            if (other.tambouren != null)
                return false;
        } else if (!this.tambouren.equals(other.tambouren))
            return false;
        if (this.perkussionsensemble == null) {
            if (other.perkussionsensemble != null)
                return false;
        } else if (!this.perkussionsensemble.equals(other.perkussionsensemble))
            return false;
        if (this.websiteText == null) {
            if (other.websiteText != null)
                return false;
        } else if (!this.websiteText.equals(other.websiteText))
            return false;
        if (this.direktionDoppeleinsatz == null) {
            if (other.direktionDoppeleinsatz != null)
                return false;
        } else if (!this.direktionDoppeleinsatz.equals(other.direktionDoppeleinsatz))
            return false;
        if (this.direktionDoppeleinsatzVerein == null) {
            if (other.direktionDoppeleinsatzVerein != null)
                return false;
        } else if (!this.direktionDoppeleinsatzVerein.equals(other.direktionDoppeleinsatzVerein))
            return false;
        if (this.mitspielerDoppeleinsatz == null) {
            if (other.mitspielerDoppeleinsatz != null)
                return false;
        } else if (!this.mitspielerDoppeleinsatz.equals(other.mitspielerDoppeleinsatz))
            return false;
        if (this.confirmedAt == null) {
            if (other.confirmedAt != null)
                return false;
        } else if (!this.confirmedAt.equals(other.confirmedAt))
            return false;
        if (this.tambourenKatA == null) {
            if (other.tambourenKatA != null)
                return false;
        } else if (!this.tambourenKatA.equals(other.tambourenKatA))
            return false;
        if (this.tambourenKatB == null) {
            if (other.tambourenKatB != null)
                return false;
        } else if (!this.tambourenKatB.equals(other.tambourenKatB))
            return false;
        if (this.tambourenKatC == null) {
            if (other.tambourenKatC != null)
                return false;
        } else if (!this.tambourenKatC.equals(other.tambourenKatC))
            return false;
        if (this.phase2ConfirmedBy == null) {
            if (other.phase2ConfirmedBy != null)
                return false;
        } else if (!this.phase2ConfirmedBy.equals(other.phase2ConfirmedBy))
            return false;
        if (this.phase2ConfirmedAt == null) {
            if (other.phase2ConfirmedAt != null)
                return false;
        } else if (!this.phase2ConfirmedAt.equals(other.phase2ConfirmedAt))
            return false;
        if (this.provWettspiel == null) {
            if (other.provWettspiel != null)
                return false;
        } else if (!this.provWettspiel.equals(other.provWettspiel))
            return false;
        if (this.provParademusik == null) {
            if (other.provParademusik != null)
                return false;
        } else if (!this.provParademusik.equals(other.provParademusik))
            return false;
        if (this.provPlatzkonzert == null) {
            if (other.provPlatzkonzert != null)
                return false;
        } else if (!this.provPlatzkonzert.equals(other.provPlatzkonzert))
            return false;
        if (this.programmLastUpdated == null) {
            if (other.programmLastUpdated != null)
                return false;
        } else if (!this.programmLastUpdated.equals(other.programmLastUpdated))
            return false;
        if (this.identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!this.identifier.equals(other.identifier))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.praesidentKontaktId == null) ? 0 : this.praesidentKontaktId.hashCode());
        result = prime * result + ((this.direktionKontaktId == null) ? 0 : this.direktionKontaktId.hashCode());
        result = prime * result + ((this.vereinsname == null) ? 0 : this.vereinsname.hashCode());
        result = prime * result + ((this.adresse == null) ? 0 : this.adresse.hashCode());
        result = prime * result + ((this.plz == null) ? 0 : this.plz.hashCode());
        result = prime * result + ((this.ort == null) ? 0 : this.ort.hashCode());
        result = prime * result + ((this.homepage == null) ? 0 : this.homepage.hashCode());
        result = prime * result + ((this.facebook == null) ? 0 : this.facebook.hashCode());
        result = prime * result + ((this.instagram == null) ? 0 : this.instagram.hashCode());
        result = prime * result + ((this.iban == null) ? 0 : this.iban.hashCode());
        result = prime * result + ((this.modula == null) ? 0 : this.modula.hashCode());
        result = prime * result + ((this.modulb == null) ? 0 : this.modulb.hashCode());
        result = prime * result + ((this.modulc == null) ? 0 : this.modulc.hashCode());
        result = prime * result + ((this.moduld == null) ? 0 : this.moduld.hashCode());
        result = prime * result + ((this.module == null) ? 0 : this.module.hashCode());
        result = prime * result + ((this.modulf == null) ? 0 : this.modulf.hashCode());
        result = prime * result + ((this.modulg == null) ? 0 : this.modulg.hashCode());
        result = prime * result + ((this.modulh == null) ? 0 : this.modulh.hashCode());
        result = prime * result + ((this.klasseModula == null) ? 0 : this.klasseModula.hashCode());
        result = prime * result + ((this.klasseModulb == null) ? 0 : this.klasseModulb.hashCode());
        result = prime * result + ((this.klasseModulh == null) ? 0 : this.klasseModulh.hashCode());
        result = prime * result + ((this.harmonie == null) ? 0 : this.harmonie.hashCode());
        result = prime * result + ((this.brassBand == null) ? 0 : this.brassBand.hashCode());
        result = prime * result + ((this.fanfare == null) ? 0 : this.fanfare.hashCode());
        result = prime * result + ((this.tambouren == null) ? 0 : this.tambouren.hashCode());
        result = prime * result + ((this.perkussionsensemble == null) ? 0 : this.perkussionsensemble.hashCode());
        result = prime * result + ((this.websiteText == null) ? 0 : this.websiteText.hashCode());
        result = prime * result + ((this.direktionDoppeleinsatz == null) ? 0 : this.direktionDoppeleinsatz.hashCode());
        result = prime * result + ((this.direktionDoppeleinsatzVerein == null) ? 0 : this.direktionDoppeleinsatzVerein.hashCode());
        result = prime * result + ((this.mitspielerDoppeleinsatz == null) ? 0 : this.mitspielerDoppeleinsatz.hashCode());
        result = prime * result + ((this.confirmedAt == null) ? 0 : this.confirmedAt.hashCode());
        result = prime * result + ((this.tambourenKatA == null) ? 0 : this.tambourenKatA.hashCode());
        result = prime * result + ((this.tambourenKatB == null) ? 0 : this.tambourenKatB.hashCode());
        result = prime * result + ((this.tambourenKatC == null) ? 0 : this.tambourenKatC.hashCode());
        result = prime * result + ((this.phase2ConfirmedBy == null) ? 0 : this.phase2ConfirmedBy.hashCode());
        result = prime * result + ((this.phase2ConfirmedAt == null) ? 0 : this.phase2ConfirmedAt.hashCode());
        result = prime * result + ((this.provWettspiel == null) ? 0 : this.provWettspiel.hashCode());
        result = prime * result + ((this.provParademusik == null) ? 0 : this.provParademusik.hashCode());
        result = prime * result + ((this.provPlatzkonzert == null) ? 0 : this.provPlatzkonzert.hashCode());
        result = prime * result + ((this.programmLastUpdated == null) ? 0 : this.programmLastUpdated.hashCode());
        result = prime * result + ((this.identifier == null) ? 0 : this.identifier.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VereinPojo (");

        sb.append(id);
        sb.append(", ").append(email);
        sb.append(", ").append(praesidentKontaktId);
        sb.append(", ").append(direktionKontaktId);
        sb.append(", ").append(vereinsname);
        sb.append(", ").append(adresse);
        sb.append(", ").append(plz);
        sb.append(", ").append(ort);
        sb.append(", ").append(homepage);
        sb.append(", ").append(facebook);
        sb.append(", ").append(instagram);
        sb.append(", ").append(iban);
        sb.append(", ").append(modula);
        sb.append(", ").append(modulb);
        sb.append(", ").append(modulc);
        sb.append(", ").append(moduld);
        sb.append(", ").append(module);
        sb.append(", ").append(modulf);
        sb.append(", ").append(modulg);
        sb.append(", ").append(modulh);
        sb.append(", ").append(klasseModula);
        sb.append(", ").append(klasseModulb);
        sb.append(", ").append(klasseModulh);
        sb.append(", ").append(harmonie);
        sb.append(", ").append(brassBand);
        sb.append(", ").append(fanfare);
        sb.append(", ").append(tambouren);
        sb.append(", ").append(perkussionsensemble);
        sb.append(", ").append(websiteText);
        sb.append(", ").append(direktionDoppeleinsatz);
        sb.append(", ").append(direktionDoppeleinsatzVerein);
        sb.append(", ").append(mitspielerDoppeleinsatz);
        sb.append(", ").append(confirmedAt);
        sb.append(", ").append(tambourenKatA);
        sb.append(", ").append(tambourenKatB);
        sb.append(", ").append(tambourenKatC);
        sb.append(", ").append(phase2ConfirmedBy);
        sb.append(", ").append(phase2ConfirmedAt);
        sb.append(", ").append(provWettspiel);
        sb.append(", ").append(provParademusik);
        sb.append(", ").append(provPlatzkonzert);
        sb.append(", ").append(programmLastUpdated);
        sb.append(", ").append(identifier);

        sb.append(")");
        return sb.toString();
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
    }

    @Override
    public <E extends IVerein> E into(E into) {
        into.from(this);
        return into;
    }
}
