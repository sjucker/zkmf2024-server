package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record VereinTeilnahmeDTO(@NotNull Long id,
                                 @NotNull String name,
                                 Long logoImgId,
                                 Long bildImgId,
                                 String homepage,
                                 String facebook,
                                 String instagram,
                                 String websiteText) {
}
