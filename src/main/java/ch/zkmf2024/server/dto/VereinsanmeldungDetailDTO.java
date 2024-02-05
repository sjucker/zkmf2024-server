package ch.zkmf2024.server.dto;

import java.time.LocalDate;

import static ch.zkmf2024.server.util.ValidationUtil.isPositive;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public record VereinsanmeldungDetailDTO(
        Integer festfuehrerAmount,
        Integer festkartenMusikerAmount,
        Integer festkartenBegleiterAmount,
        Integer freitagabendAmount,
        boolean gehbehinderung,
        boolean partiturenSent,
        LocalDate partiturenSentAt,
        boolean gesamtchor,
        boolean adhocOrchester,
        boolean anreisePublicTransport,
        String anreiseOtherwise,
        Integer verpflegungMeat,
        Integer verpflegungVegan,
        Integer verpflegungAllergies,
        Integer verpflegungNone,
        boolean verpflegungHelper,
        String verpflegungHelper1,
        String verpflegungHelper2,
        String verpflegungHelper3,
        String verpflegungHelper4,
        String verpflegungHelper5,
        String verpflegungHelper6
) implements IsValid {
    @Override
    public boolean isValid() {
        return festfuehrerAmount != null &&
                isPositive(festkartenMusikerAmount) &&
                festkartenBegleiterAmount != null &&
                freitagabendAmount != null &&
                partiturenSent &&
                partiturenSentAt != null &&
                verpflegungMeat != null &&
                verpflegungVegan != null &&
                verpflegungAllergies != null &&
                verpflegungNone != null &&
                verpflegungHelper &&
                isNotBlank(verpflegungHelper1) &&
                isNotBlank(verpflegungHelper2) &&
                isNotBlank(verpflegungHelper3) &&
                isNotBlank(verpflegungHelper4);
    }
}
