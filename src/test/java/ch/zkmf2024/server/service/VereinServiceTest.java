package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.TitelDTO;
import ch.zkmf2024.server.dto.VereinProgrammTitelDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VereinServiceTest {

    @Test
    void calculateTotalDurationInSeconds() {
        assertThat(VereinService.calculateTotalDurationInSeconds(List.of(
                new VereinProgrammTitelDTO(createTitel(123), 15),
                new VereinProgrammTitelDTO(createTitel(234), null),
                new VereinProgrammTitelDTO(createTitel(345), 30),
                new VereinProgrammTitelDTO(createTitel(567), 60) // applause must be ignored
        ))).isEqualTo(123 + 234 + 345 + 567 + 15 + 30);
    }

    private TitelDTO createTitel(int durationInSeconds) {
        return new TitelDTO(null, null, "", "", "", null, null, durationInSeconds, false, "");
    }
}
