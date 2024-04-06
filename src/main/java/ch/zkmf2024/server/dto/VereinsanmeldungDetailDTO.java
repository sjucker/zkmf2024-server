package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static ch.zkmf2024.server.util.ValidationUtil.isPositive;
import static java.util.Objects.requireNonNullElse;
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
        @NotNull List<AdhocOrchesterTeilnehmerDTO> adhocOrchesterTeilnehmer,
        @NotNull List<NichtmitgliederDTO> nichtmitglieder,
        boolean anreisePublicTransport,
        String anreisePublicTransportType,
        String anreiseOtherwise,
        Integer verpflegungMeat,
        Integer verpflegungVegan,
        Integer verpflegungAllergies,
        Integer verpflegungNone,
        String verpflegungHelper1,
        String verpflegungHelper2,
        String verpflegungHelper3,
        String verpflegungHelper4,
        String verpflegungHelper5,
        String verpflegungHelper6,
        boolean hasPartituren
) implements IsValid {
    @Override
    public boolean isValid() {
        return festfuehrerAmount != null &&
                isPositive(festkartenMusikerAmount) && isValidAmount() &&
                (!needsVerpflegungHelfer() || (isNotBlank(verpflegungHelper1) && isNotBlank(verpflegungHelper2) && isNotBlank(verpflegungHelper3) && isNotBlank(verpflegungHelper4))) &&
                (isNotBlank(anreisePublicTransportType) || isNotBlank(anreiseOtherwise)) &&
                (!hasPartituren || (partiturenSent && partiturenSentAt != null));
    }

    private boolean isValidAmount() {
        return festkartenMusikerAmount + requireNonNullElse(festkartenBegleiterAmount, 0) ==
                requireNonNullElse(verpflegungMeat, 0) + requireNonNullElse(verpflegungVegan, 0) + requireNonNullElse(verpflegungAllergies, 0) + requireNonNullElse(verpflegungNone, 0);
    }

    private boolean needsVerpflegungHelfer() {
        return requireNonNullElse(verpflegungMeat, 0) + requireNonNullElse(verpflegungVegan, 0) + requireNonNullElse(verpflegungAllergies, 0) > 0;
    }
}
