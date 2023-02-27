package ch.zkmf2024.server.dto;

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
        if (!modulA && !modulB && !modulC && !modulD && !modulE && !modulF && !modulG && !modulH) {
            return false;
        }

        if ((modulA && klasseModulA == null) || (modulB && klasseModulB == null) || (modulH && klasseModulH == null)) {
            return false;
        }

        if (!harmonie && !brassBand && !fanfare && !tambouren && !perkussionsensemble) {
            return false;
        }

        // TODO more rules?

        return true;
    }
}
