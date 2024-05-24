package ch.zkmf2024.server.dto;

import jakarta.validation.constraints.NotNull;

public record EmergencyMessageDTO(Long id,
                                  @NotNull String header,
                                  @NotNull String message,
                                  boolean active) {
}
