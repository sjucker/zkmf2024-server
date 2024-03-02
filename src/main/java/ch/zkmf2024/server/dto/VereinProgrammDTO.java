package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public record VereinProgrammDTO(long id,
                                @NotNull Modul modul,
                                @NotNull String modulDescription,
                                String klasse,
                                String besetzung,
                                String titel,
                                String infoModeration,
                                Integer totalDurationInSeconds,
                                Integer minDurationInSeconds,
                                Integer maxDurationInSeconds,
                                @NotNull List<VereinProgrammTitelDTO> ablauf,
                                boolean tambourenKatA,
                                boolean tambourenKatB,
                                boolean tambourenKatC,
                                TambourenGrundlage tambourenKatAGrundlage1,
                                TambourenGrundlage tambourenKatAGrundlage2,
                                @NotNull TitelDTO tambourenKatATitel1,
                                @NotNull TitelDTO tambourenKatATitel2,
                                @NotNull TitelDTO tambourenKatBTitel,
                                @NotNull TitelDTO tambourenKatCTitel,
                                boolean unterhaltungPA,
                                boolean unterhaltungEGitarre,
                                boolean unterhaltungEBass,
                                boolean unterhaltungKeyboard,
                                boolean unterhaltungGesang,
                                @NotNull TitelDTO parademusikTitel1,
                                @NotNull TitelDTO parademusikTitel2) implements IsValid {

    @Override
    public boolean isValid() {
        return switch (modul) {
            case A, B -> isNotBlank(titel) &&
                    isNotBlank(infoModeration) &&
                    ablauf.stream().allMatch(dto -> (dto.applausInSeconds() == null || dto.applausInSeconds() <= 30) &&
                            dto.titel().isValid() &&
                            (dto.titel().pflichtStueck() || isNotBlank(dto.titel().infoModeration()))) &&
                    totalDurationInSeconds >= minDurationInSeconds && totalDurationInSeconds <= maxDurationInSeconds;

            case C -> !ablauf.isEmpty() && ablauf.stream().allMatch(dto -> dto.titel().isValid());
            case D -> parademusikTitel1.isValid() && parademusikTitel2.isValid();
            case E, F -> !ablauf.isEmpty() && ablauf.stream().allMatch(dto -> dto.titel().isValid()) &&
                    totalDurationInSeconds >= minDurationInSeconds && totalDurationInSeconds <= maxDurationInSeconds;
            case H -> !ablauf.isEmpty() && ablauf.stream().allMatch(dto -> (dto.applausInSeconds() == null || dto.applausInSeconds() <= 30) &&
                    dto.titel().isValid() &&
                    isNotBlank(dto.titel().infoModeration())) &&
                    totalDurationInSeconds >= minDurationInSeconds && totalDurationInSeconds <= maxDurationInSeconds;
            case G -> {
                if (tambourenKatA && (tambourenKatAGrundlage1 == null || tambourenKatAGrundlage2 == null || !tambourenKatATitel1.isValid() || !tambourenKatATitel2.isValid())) {
                    yield false;
                }

                if (tambourenKatB && !tambourenKatBTitel.isValid()) {
                    yield false;
                }

                if (tambourenKatC && !tambourenKatCTitel.isValid()) {
                    yield false;
                }

                yield true;
            }
        };
    }

    public boolean hasAddedTitel() {
        return switch (modul) {
            case A, B, C, E, F, H -> ablauf.stream().anyMatch(programmTitel -> !programmTitel.titel().pflichtStueck());
            case D -> parademusikTitel1.isNotEmpty() || parademusikTitel2.isNotEmpty();
            case G -> tambourenKatATitel1.isNotEmpty() || tambourenKatATitel2.isNotEmpty() || tambourenKatBTitel.isNotEmpty() || tambourenKatCTitel.isNotEmpty();
        };
    }
}
