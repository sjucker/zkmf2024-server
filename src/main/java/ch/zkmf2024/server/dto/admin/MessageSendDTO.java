package ch.zkmf2024.server.dto.admin;

import ch.zkmf2024.server.dto.MessageType;

import jakarta.validation.constraints.NotNull;

public record MessageSendDTO(@NotNull MessageType type,
                             @NotNull String title,
                             @NotNull String body,
                             @NotNull String route) {
}
