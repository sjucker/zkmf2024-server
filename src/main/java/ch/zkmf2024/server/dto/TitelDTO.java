package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record TitelDTO(Long id,
                       String titelName,
                       String composer,
                       String arrangeur,
                       Float grad,
                       @NotNull Integer durationInSeconds,
                       boolean pflichtStueck) {
}
