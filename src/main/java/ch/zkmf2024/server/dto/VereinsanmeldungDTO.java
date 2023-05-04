package ch.zkmf2024.server.dto;

import java.util.ArrayList;
import java.util.List;

public record VereinsanmeldungDTO(
        boolean modulA,
        boolean modulB,
        boolean modulC,
        boolean modulD,
        boolean modulE,
        boolean modulF,
        boolean modulG,
        boolean modulH,
        Klasse klasseModulA,
        Klasse klasseModulB,
        Klasse klasseModulH,
        boolean harmonie,
        boolean brassBand,
        boolean fanfare,
        boolean tambouren,
        boolean perkussionsensemble
) implements IsValid {
    @Override
    public boolean isValid() {
        // at least one module selected
        if (getModule().isEmpty()) {
            return false;
        }

        if ((modulA && klasseModulA == null) || (modulB && klasseModulB == null) || (modulH && klasseModulH == null)) {
            return false;
        }

        if ((modulA || modulB || modulD || modulE || modulF) && (!harmonie && !brassBand && !fanfare)) {
            return false;
        }

        if ((modulG && !tambouren) || (modulH && !perkussionsensemble)) {
            return false;
        }

        // at least one Besetzung selected
        return !getBesetzungen().isEmpty();
    }

    public List<Modul> getModule() {
        var module = new ArrayList<Modul>();
        if (modulA) module.add(Modul.A);
        if (modulB) module.add(Modul.B);
        if (modulC) module.add(Modul.C);
        if (modulD) module.add(Modul.D);
        if (modulE) module.add(Modul.E);
        if (modulF) module.add(Modul.F);
        if (modulG) module.add(Modul.G);
        if (modulH) module.add(Modul.H);
        return module;
    }

    public List<Besetzung> getBesetzungen() {
        var besetzungen = new ArrayList<Besetzung>();
        if (harmonie) besetzungen.add(Besetzung.HARMONIE);
        if (brassBand) besetzungen.add(Besetzung.BRASS_BAND);
        if (fanfare) besetzungen.add(Besetzung.FANFARE);
        if (tambouren) besetzungen.add(Besetzung.TAMBOUREN);
        if (perkussionsensemble) besetzungen.add(Besetzung.PERKUSSIONSENSEMBLE);
        return besetzungen;
    }
}
