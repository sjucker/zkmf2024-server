package ch.zkmf2024.server.dto.admin;

import jakarta.validation.constraints.NotNull;
import java.util.List;

// TODO use it
public record VereinAssignmentDTO(@NotNull Long id,
                                  @NotNull String name,
                                  @NotNull List<VereinAssignmentProgrammDTO> programme) {
}
