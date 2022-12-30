package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Kontakt {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String vorname;
    private String nachname;
    private String adresse;
    private Integer plz;
    private String ort;
    private String email;
    private String telefonPrivat;
    private String telefonMobile;

}
