package ch.zkmf2024.server.service;

import ch.zkmf2024.server.AbstractIntegrationTest;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.RankingDTO;
import ch.zkmf2024.server.dto.admin.RankingSummaryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

class RankingsServiceTest extends AbstractIntegrationTest {

    @Autowired
    private RankingsService rankingsService;

    @Test
    void shouldGenerateRankingSummary() throws IOException {
        var rankings = generateTestData();
        var result = rankingsService.generateDiplomas(rankings);
        OutputStream out = new FileOutputStream("rankings.pdf");
        out.write(result);
        out.close();
    }

    private List<RankingSummaryDTO> generateTestData() {
        var rankingDto = new RankingDTO(Modul.B, "Unterhaltungsmusik", new BigDecimal("123.2"), LocalDate.now());
        var rankingDto2 = new RankingDTO(Modul.G, "Tambouren", new BigDecimal("187.9"), LocalDate.now());
        var rankingDto3 = new RankingDTO(Modul.H, "Perkussionsensembles", new BigDecimal("150.0"), LocalDate.now());
        var rankingSummaryDTO = new RankingSummaryDTO("Musikverein Zürich-Seebach", "3. Klasse Singvogel", List.of(rankingDto, rankingDto2));
        var rankingSummaryDTO2 = new RankingSummaryDTO("Musikverein Harmonie Zürich Schwamendingen", "2. Klasse Harmonie", List.of(rankingDto, rankingDto2, rankingDto3));
        return List.of(rankingSummaryDTO, rankingSummaryDTO2);
    }

}
