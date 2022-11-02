package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;

public record VereinDTO(@NotNull long id,
                        @NotNull String email,
                        @NotNull String vereinsname) {
}
