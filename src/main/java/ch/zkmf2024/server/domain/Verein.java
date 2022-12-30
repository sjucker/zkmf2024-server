package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

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
