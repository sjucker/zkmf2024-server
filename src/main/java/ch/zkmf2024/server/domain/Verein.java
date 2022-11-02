package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Verein {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Embedded
    private Vereinsangaben angaben = new Vereinsangaben();

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "praesident_kontakt_id", nullable = false)
    private Kontakt praesident = new Kontakt();

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "kassier_kontakt_id", nullable = false)
    private Kontakt kassier = new Kontakt();

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "direktion_kontakt_id", nullable = false)
    private Kontakt direktion = new Kontakt();

    @Enumerated(STRING)
    private StaerkeKlasse staerkeKlasse;

    private Integer anzahlMusikanten;
    private Integer anzahlDirigenten;
    private Integer anzahlTambouren;

    public enum StaerkeKlasse {
        HOECHSTKLASSE,
        KLASSE_1,
        KLASSE_2,
        KLASSE_3,
        KLASSE_4,
        UNTERSTUFE,
        MITTELSTUFE,
        OBERSTUFE
        // TODO correct?
    }

}
