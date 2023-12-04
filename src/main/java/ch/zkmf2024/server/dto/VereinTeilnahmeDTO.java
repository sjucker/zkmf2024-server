package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record VereinTeilnahmeDTO(@NotNull Long id,
                                 @NotNull String identifier,
                                 @NotNull String name,
                                 String logoImgId,
                                 String bildImgId,
                                 String homepage,
                                 String facebook,
                                 String instagram,
                                 String websiteText) {
}
