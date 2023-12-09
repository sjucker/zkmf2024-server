package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record SponsoringDTO(@NotNull List<SponsorDTO> premium,
                            @NotNull List<SponsorDTO> deluxe,
                            @NotNull List<SponsorDTO> sponsor,
                            @NotNull List<SponsorDTO> musikfan,
                            @NotNull List<SponsorDTO> goenner) {
}
