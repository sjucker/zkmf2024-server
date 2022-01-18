package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "survey_answer")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SurveyAnswer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String vereinsName;

    @Column(nullable = false)
    private String besetzung;
    @Column(nullable = false)
    private String staerkeKlasse;
    @Column(nullable = false)
    private String anzahlMitglieder;

    @Column(nullable = false)
    private String kontaktName;
    @Column(nullable = false)
    private String kontaktEmail;
    @Column(nullable = false)
    private String kontaktTelefon;

    private String modulAuswahl;
    private String zusageKommentar;

    private boolean absage;
    private String absageKommentar;
    private String absageKontaktaufnahme;

    private String helfer;
}
