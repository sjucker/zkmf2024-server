/*
 * This file is generated by jOOQ.
 */
package ch.zkmf2024.server.jooq.generated.tables.pojos;

import ch.zkmf2024.server.jooq.generated.tables.interfaces.IHelperRegistration;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class HelperRegistrationPojo implements IHelperRegistration {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String vorname;
    private String name;
    private String email;
    private String telefon;
    private String comment;
    private String adresse;
    private String plzOrt;
    private LocalDate geburtsdatum;
    private String vereinszugehoerigkeit;
    private String aufgaben;
    private String anzahlEinsaetze;
    private String einsatzMittwoch;
    private String einsatzDonnerstag;
    private String einsatzFreitag;
    private String einsatzSamstag;
    private String einsatzSonntag;
    private String einsatzMontag;
    private String einsatzDienstag;
    private String groesseShirt;
    private LocalDateTime registeredAt;

    public HelperRegistrationPojo() {
    }

    public HelperRegistrationPojo(IHelperRegistration value) {
        this.id = value.getId();
        this.vorname = value.getVorname();
        this.name = value.getName();
        this.email = value.getEmail();
        this.telefon = value.getTelefon();
        this.comment = value.getComment();
        this.adresse = value.getAdresse();
        this.plzOrt = value.getPlzOrt();
        this.geburtsdatum = value.getGeburtsdatum();
        this.vereinszugehoerigkeit = value.getVereinszugehoerigkeit();
        this.aufgaben = value.getAufgaben();
        this.anzahlEinsaetze = value.getAnzahlEinsaetze();
        this.einsatzMittwoch = value.getEinsatzMittwoch();
        this.einsatzDonnerstag = value.getEinsatzDonnerstag();
        this.einsatzFreitag = value.getEinsatzFreitag();
        this.einsatzSamstag = value.getEinsatzSamstag();
        this.einsatzSonntag = value.getEinsatzSonntag();
        this.einsatzMontag = value.getEinsatzMontag();
        this.einsatzDienstag = value.getEinsatzDienstag();
        this.groesseShirt = value.getGroesseShirt();
        this.registeredAt = value.getRegisteredAt();
    }

    public HelperRegistrationPojo(
            Long id,
            String vorname,
            String name,
            String email,
            String telefon,
            String comment,
            String adresse,
            String plzOrt,
            LocalDate geburtsdatum,
            String vereinszugehoerigkeit,
            String aufgaben,
            String anzahlEinsaetze,
            String einsatzMittwoch,
            String einsatzDonnerstag,
            String einsatzFreitag,
            String einsatzSamstag,
            String einsatzSonntag,
            String einsatzMontag,
            String einsatzDienstag,
            String groesseShirt,
            LocalDateTime registeredAt
    ) {
        this.id = id;
        this.vorname = vorname;
        this.name = name;
        this.email = email;
        this.telefon = telefon;
        this.comment = comment;
        this.adresse = adresse;
        this.plzOrt = plzOrt;
        this.geburtsdatum = geburtsdatum;
        this.vereinszugehoerigkeit = vereinszugehoerigkeit;
        this.aufgaben = aufgaben;
        this.anzahlEinsaetze = anzahlEinsaetze;
        this.einsatzMittwoch = einsatzMittwoch;
        this.einsatzDonnerstag = einsatzDonnerstag;
        this.einsatzFreitag = einsatzFreitag;
        this.einsatzSamstag = einsatzSamstag;
        this.einsatzSonntag = einsatzSonntag;
        this.einsatzMontag = einsatzMontag;
        this.einsatzDienstag = einsatzDienstag;
        this.groesseShirt = groesseShirt;
        this.registeredAt = registeredAt;
    }

    /**
     * Getter for <code>helper_registration.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>helper_registration.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>helper_registration.vorname</code>.
     */
    @Override
    public String getVorname() {
        return this.vorname;
    }

    /**
     * Setter for <code>helper_registration.vorname</code>.
     */
    @Override
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Getter for <code>helper_registration.name</code>.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>helper_registration.name</code>.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>helper_registration.email</code>.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>helper_registration.email</code>.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for <code>helper_registration.telefon</code>.
     */
    @Override
    public String getTelefon() {
        return this.telefon;
    }

    /**
     * Setter for <code>helper_registration.telefon</code>.
     */
    @Override
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Getter for <code>helper_registration.comment</code>.
     */
    @Override
    public String getComment() {
        return this.comment;
    }

    /**
     * Setter for <code>helper_registration.comment</code>.
     */
    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter for <code>helper_registration.adresse</code>.
     */
    @Override
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Setter for <code>helper_registration.adresse</code>.
     */
    @Override
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Getter for <code>helper_registration.plz_ort</code>.
     */
    @Override
    public String getPlzOrt() {
        return this.plzOrt;
    }

    /**
     * Setter for <code>helper_registration.plz_ort</code>.
     */
    @Override
    public void setPlzOrt(String plzOrt) {
        this.plzOrt = plzOrt;
    }

    /**
     * Getter for <code>helper_registration.geburtsdatum</code>.
     */
    @Override
    public LocalDate getGeburtsdatum() {
        return this.geburtsdatum;
    }

    /**
     * Setter for <code>helper_registration.geburtsdatum</code>.
     */
    @Override
    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * Getter for <code>helper_registration.vereinszugehoerigkeit</code>.
     */
    @Override
    public String getVereinszugehoerigkeit() {
        return this.vereinszugehoerigkeit;
    }

    /**
     * Setter for <code>helper_registration.vereinszugehoerigkeit</code>.
     */
    @Override
    public void setVereinszugehoerigkeit(String vereinszugehoerigkeit) {
        this.vereinszugehoerigkeit = vereinszugehoerigkeit;
    }

    /**
     * Getter for <code>helper_registration.aufgaben</code>.
     */
    @Override
    public String getAufgaben() {
        return this.aufgaben;
    }

    /**
     * Setter for <code>helper_registration.aufgaben</code>.
     */
    @Override
    public void setAufgaben(String aufgaben) {
        this.aufgaben = aufgaben;
    }

    /**
     * Getter for <code>helper_registration.anzahl_einsaetze</code>.
     */
    @Override
    public String getAnzahlEinsaetze() {
        return this.anzahlEinsaetze;
    }

    /**
     * Setter for <code>helper_registration.anzahl_einsaetze</code>.
     */
    @Override
    public void setAnzahlEinsaetze(String anzahlEinsaetze) {
        this.anzahlEinsaetze = anzahlEinsaetze;
    }

    /**
     * Getter for <code>helper_registration.einsatz_mittwoch</code>.
     */
    @Override
    public String getEinsatzMittwoch() {
        return this.einsatzMittwoch;
    }

    /**
     * Setter for <code>helper_registration.einsatz_mittwoch</code>.
     */
    @Override
    public void setEinsatzMittwoch(String einsatzMittwoch) {
        this.einsatzMittwoch = einsatzMittwoch;
    }

    /**
     * Getter for <code>helper_registration.einsatz_donnerstag</code>.
     */
    @Override
    public String getEinsatzDonnerstag() {
        return this.einsatzDonnerstag;
    }

    /**
     * Setter for <code>helper_registration.einsatz_donnerstag</code>.
     */
    @Override
    public void setEinsatzDonnerstag(String einsatzDonnerstag) {
        this.einsatzDonnerstag = einsatzDonnerstag;
    }

    /**
     * Getter for <code>helper_registration.einsatz_freitag</code>.
     */
    @Override
    public String getEinsatzFreitag() {
        return this.einsatzFreitag;
    }

    /**
     * Setter for <code>helper_registration.einsatz_freitag</code>.
     */
    @Override
    public void setEinsatzFreitag(String einsatzFreitag) {
        this.einsatzFreitag = einsatzFreitag;
    }

    /**
     * Getter for <code>helper_registration.einsatz_samstag</code>.
     */
    @Override
    public String getEinsatzSamstag() {
        return this.einsatzSamstag;
    }

    /**
     * Setter for <code>helper_registration.einsatz_samstag</code>.
     */
    @Override
    public void setEinsatzSamstag(String einsatzSamstag) {
        this.einsatzSamstag = einsatzSamstag;
    }

    /**
     * Getter for <code>helper_registration.einsatz_sonntag</code>.
     */
    @Override
    public String getEinsatzSonntag() {
        return this.einsatzSonntag;
    }

    /**
     * Setter for <code>helper_registration.einsatz_sonntag</code>.
     */
    @Override
    public void setEinsatzSonntag(String einsatzSonntag) {
        this.einsatzSonntag = einsatzSonntag;
    }

    /**
     * Getter for <code>helper_registration.einsatz_montag</code>.
     */
    @Override
    public String getEinsatzMontag() {
        return this.einsatzMontag;
    }

    /**
     * Setter for <code>helper_registration.einsatz_montag</code>.
     */
    @Override
    public void setEinsatzMontag(String einsatzMontag) {
        this.einsatzMontag = einsatzMontag;
    }

    /**
     * Getter for <code>helper_registration.einsatz_dienstag</code>.
     */
    @Override
    public String getEinsatzDienstag() {
        return this.einsatzDienstag;
    }

    /**
     * Setter for <code>helper_registration.einsatz_dienstag</code>.
     */
    @Override
    public void setEinsatzDienstag(String einsatzDienstag) {
        this.einsatzDienstag = einsatzDienstag;
    }

    /**
     * Getter for <code>helper_registration.groesse_shirt</code>.
     */
    @Override
    public String getGroesseShirt() {
        return this.groesseShirt;
    }

    /**
     * Setter for <code>helper_registration.groesse_shirt</code>.
     */
    @Override
    public void setGroesseShirt(String groesseShirt) {
        this.groesseShirt = groesseShirt;
    }

    /**
     * Getter for <code>helper_registration.registered_at</code>.
     */
    @Override
    public LocalDateTime getRegisteredAt() {
        return this.registeredAt;
    }

    /**
     * Setter for <code>helper_registration.registered_at</code>.
     */
    @Override
    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final HelperRegistrationPojo other = (HelperRegistrationPojo) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.vorname == null) {
            if (other.vorname != null)
                return false;
        } else if (!this.vorname.equals(other.vorname))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.email == null) {
            if (other.email != null)
                return false;
        } else if (!this.email.equals(other.email))
            return false;
        if (this.telefon == null) {
            if (other.telefon != null)
                return false;
        } else if (!this.telefon.equals(other.telefon))
            return false;
        if (this.comment == null) {
            if (other.comment != null)
                return false;
        } else if (!this.comment.equals(other.comment))
            return false;
        if (this.adresse == null) {
            if (other.adresse != null)
                return false;
        } else if (!this.adresse.equals(other.adresse))
            return false;
        if (this.plzOrt == null) {
            if (other.plzOrt != null)
                return false;
        } else if (!this.plzOrt.equals(other.plzOrt))
            return false;
        if (this.geburtsdatum == null) {
            if (other.geburtsdatum != null)
                return false;
        } else if (!this.geburtsdatum.equals(other.geburtsdatum))
            return false;
        if (this.vereinszugehoerigkeit == null) {
            if (other.vereinszugehoerigkeit != null)
                return false;
        } else if (!this.vereinszugehoerigkeit.equals(other.vereinszugehoerigkeit))
            return false;
        if (this.aufgaben == null) {
            if (other.aufgaben != null)
                return false;
        } else if (!this.aufgaben.equals(other.aufgaben))
            return false;
        if (this.anzahlEinsaetze == null) {
            if (other.anzahlEinsaetze != null)
                return false;
        } else if (!this.anzahlEinsaetze.equals(other.anzahlEinsaetze))
            return false;
        if (this.einsatzMittwoch == null) {
            if (other.einsatzMittwoch != null)
                return false;
        } else if (!this.einsatzMittwoch.equals(other.einsatzMittwoch))
            return false;
        if (this.einsatzDonnerstag == null) {
            if (other.einsatzDonnerstag != null)
                return false;
        } else if (!this.einsatzDonnerstag.equals(other.einsatzDonnerstag))
            return false;
        if (this.einsatzFreitag == null) {
            if (other.einsatzFreitag != null)
                return false;
        } else if (!this.einsatzFreitag.equals(other.einsatzFreitag))
            return false;
        if (this.einsatzSamstag == null) {
            if (other.einsatzSamstag != null)
                return false;
        } else if (!this.einsatzSamstag.equals(other.einsatzSamstag))
            return false;
        if (this.einsatzSonntag == null) {
            if (other.einsatzSonntag != null)
                return false;
        } else if (!this.einsatzSonntag.equals(other.einsatzSonntag))
            return false;
        if (this.einsatzMontag == null) {
            if (other.einsatzMontag != null)
                return false;
        } else if (!this.einsatzMontag.equals(other.einsatzMontag))
            return false;
        if (this.einsatzDienstag == null) {
            if (other.einsatzDienstag != null)
                return false;
        } else if (!this.einsatzDienstag.equals(other.einsatzDienstag))
            return false;
        if (this.groesseShirt == null) {
            if (other.groesseShirt != null)
                return false;
        } else if (!this.groesseShirt.equals(other.groesseShirt))
            return false;
        if (this.registeredAt == null) {
            if (other.registeredAt != null)
                return false;
        } else if (!this.registeredAt.equals(other.registeredAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.vorname == null) ? 0 : this.vorname.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.telefon == null) ? 0 : this.telefon.hashCode());
        result = prime * result + ((this.comment == null) ? 0 : this.comment.hashCode());
        result = prime * result + ((this.adresse == null) ? 0 : this.adresse.hashCode());
        result = prime * result + ((this.plzOrt == null) ? 0 : this.plzOrt.hashCode());
        result = prime * result + ((this.geburtsdatum == null) ? 0 : this.geburtsdatum.hashCode());
        result = prime * result + ((this.vereinszugehoerigkeit == null) ? 0 : this.vereinszugehoerigkeit.hashCode());
        result = prime * result + ((this.aufgaben == null) ? 0 : this.aufgaben.hashCode());
        result = prime * result + ((this.anzahlEinsaetze == null) ? 0 : this.anzahlEinsaetze.hashCode());
        result = prime * result + ((this.einsatzMittwoch == null) ? 0 : this.einsatzMittwoch.hashCode());
        result = prime * result + ((this.einsatzDonnerstag == null) ? 0 : this.einsatzDonnerstag.hashCode());
        result = prime * result + ((this.einsatzFreitag == null) ? 0 : this.einsatzFreitag.hashCode());
        result = prime * result + ((this.einsatzSamstag == null) ? 0 : this.einsatzSamstag.hashCode());
        result = prime * result + ((this.einsatzSonntag == null) ? 0 : this.einsatzSonntag.hashCode());
        result = prime * result + ((this.einsatzMontag == null) ? 0 : this.einsatzMontag.hashCode());
        result = prime * result + ((this.einsatzDienstag == null) ? 0 : this.einsatzDienstag.hashCode());
        result = prime * result + ((this.groesseShirt == null) ? 0 : this.groesseShirt.hashCode());
        result = prime * result + ((this.registeredAt == null) ? 0 : this.registeredAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HelperRegistrationPojo (");

        sb.append(id);
        sb.append(", ").append(vorname);
        sb.append(", ").append(name);
        sb.append(", ").append(email);
        sb.append(", ").append(telefon);
        sb.append(", ").append(comment);
        sb.append(", ").append(adresse);
        sb.append(", ").append(plzOrt);
        sb.append(", ").append(geburtsdatum);
        sb.append(", ").append(vereinszugehoerigkeit);
        sb.append(", ").append(aufgaben);
        sb.append(", ").append(anzahlEinsaetze);
        sb.append(", ").append(einsatzMittwoch);
        sb.append(", ").append(einsatzDonnerstag);
        sb.append(", ").append(einsatzFreitag);
        sb.append(", ").append(einsatzSamstag);
        sb.append(", ").append(einsatzSonntag);
        sb.append(", ").append(einsatzMontag);
        sb.append(", ").append(einsatzDienstag);
        sb.append(", ").append(groesseShirt);
        sb.append(", ").append(registeredAt);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IHelperRegistration from) {
        setId(from.getId());
        setVorname(from.getVorname());
        setName(from.getName());
        setEmail(from.getEmail());
        setTelefon(from.getTelefon());
        setComment(from.getComment());
        setAdresse(from.getAdresse());
        setPlzOrt(from.getPlzOrt());
        setGeburtsdatum(from.getGeburtsdatum());
        setVereinszugehoerigkeit(from.getVereinszugehoerigkeit());
        setAufgaben(from.getAufgaben());
        setAnzahlEinsaetze(from.getAnzahlEinsaetze());
        setEinsatzMittwoch(from.getEinsatzMittwoch());
        setEinsatzDonnerstag(from.getEinsatzDonnerstag());
        setEinsatzFreitag(from.getEinsatzFreitag());
        setEinsatzSamstag(from.getEinsatzSamstag());
        setEinsatzSonntag(from.getEinsatzSonntag());
        setEinsatzMontag(from.getEinsatzMontag());
        setEinsatzDienstag(from.getEinsatzDienstag());
        setGroesseShirt(from.getGroesseShirt());
        setRegisteredAt(from.getRegisteredAt());
    }

    @Override
    public <E extends IHelperRegistration> E into(E into) {
        into.from(this);
        return into;
    }
}
