package ch.zkmf2024.server.dto;

import javax.validation.constraints.NotNull;

public record HelperRegistrationDTO(@NotNull Long id,
                                    @NotNull String vorname,
                                    @NotNull String name,
                                    @NotNull String email,
                                    @NotNull String mobile,
                                    boolean availableFriday,
                                    boolean availableSaturday,
                                    boolean availableSunday,
                                    String comment) {
}
