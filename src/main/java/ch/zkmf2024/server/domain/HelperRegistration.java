package ch.zkmf2024.server.domain;

import ch.zkmf2024.server.dto.Aufgaben;
import ch.zkmf2024.server.dto.Einsatzzeit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ch.zkmf2024.server.mapper.HelperRegistrationMapper.LIST_DELIMITER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "helper_registration")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HelperRegistration {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String vorname;
    private String adresse;
    private String plzOrt;
    private LocalDate geburtsdatum;
    private String telefon;

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
    private String comment;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    public List<Aufgaben> getAufgabenAsList() {
        return Arrays.stream(StringUtils.split(aufgaben, LIST_DELIMITER)).map(Aufgaben::valueOf).toList();
    }

    public List<Einsatzzeit> getEinsatzMittwochAsList() {
        return getEinsatzzeitAsList(einsatzMittwoch);
    }

    public List<Einsatzzeit> getEinsatzDonnerstagAsList() {
        return getEinsatzzeitAsList(einsatzDonnerstag);
    }

    public List<Einsatzzeit> getEinsatzFreitagAsList() {
        return getEinsatzzeitAsList(einsatzFreitag);
    }

    public List<Einsatzzeit> getEinsatzSamstagAsList() {
        return getEinsatzzeitAsList(einsatzSamstag);
    }

    public List<Einsatzzeit> getEinsatzSonntagAsList() {
        return getEinsatzzeitAsList(einsatzSonntag);
    }

    public List<Einsatzzeit> getEinsatzMontagAsList() {
        return getEinsatzzeitAsList(einsatzMontag);
    }

    public List<Einsatzzeit> getEinsatzDienstagAsList() {
        return getEinsatzzeitAsList(einsatzDienstag);
    }

    private List<Einsatzzeit> getEinsatzzeitAsList(String values) {
        return Arrays.stream(StringUtils.split(values, LIST_DELIMITER)).map(Einsatzzeit::valueOf).toList();
    }
}
