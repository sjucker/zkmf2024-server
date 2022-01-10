package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record NewsletterRecipientDTO(@NotNull String name,
                                     @NotNull String email,
                                     @NotNull LocalDateTime subscribedAt,
                                     LocalDateTime unsubscribedAt) {

}
