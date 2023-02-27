package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import static jakarta.persistence.CascadeType.ALL;
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

    @Embedded
    private Vereinsanmeldung anmeldung = new Vereinsanmeldung();

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "praesident_kontakt_id", nullable = false)
    private Kontakt praesident = new Kontakt();

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "direktion_kontakt_id", nullable = false)
    private Kontakt direktion = new Kontakt();

}
