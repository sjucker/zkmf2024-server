package ch.zkmf2024.server.domain;

import ch.zkmf2024.server.dto.Klasse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;

import static jakarta.persistence.EnumType.STRING;

@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vereinsanmeldung {

    private boolean modulA;
    private boolean modulB;
    private boolean modulC;
    private boolean modulD;
    private boolean modulE;
    private boolean modulF;
    private boolean modulG;
    private boolean modulH;

    @Enumerated(STRING)
    private Klasse klasseModulA;
    @Enumerated(STRING)
    private Klasse klasseModulB;
    @Enumerated(STRING)
    private Klasse klasseModulH;

    private boolean harmonie;
    private boolean brassBand;
    private boolean fanfare;
    private boolean tambouren;
    private boolean perkussionsensemble;

}
