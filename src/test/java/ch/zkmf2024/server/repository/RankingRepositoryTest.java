package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.AbstractIntegrationTest;
import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.JudgeReportModulCategory;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.LocationDTO;
import ch.zkmf2024.server.dto.LocationType;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.RankingStatus;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingEntryPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammPojo;
import ch.zkmf2024.server.util.DateUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static ch.zkmf2024.server.dto.Besetzung.HARMONIE;
import static ch.zkmf2024.server.dto.Besetzung.TAMBOUREN;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_A;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_B;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_C;
import static ch.zkmf2024.server.dto.Klasse.KLASSE_2;
import static ch.zkmf2024.server.dto.Modul.A;
import static ch.zkmf2024.server.dto.Modul.C;
import static ch.zkmf2024.server.dto.Modul.D;
import static ch.zkmf2024.server.dto.Modul.G;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

class RankingRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private VereinRepository vereinRepository;
    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        var locations = locationRepository.findAllByType(LocationType.WETTSPIELLOKAL);
        var location1 = locations.getFirst();
        var location2 = locations.getLast();

        var bachtel = createVerein("Harmonie am Bachtel");
        createVereinProgramm(bachtel.getId(), A, KLASSE_2, HARMONIE);
        createVereinProgramm(bachtel.getId(), D, null, null);
        createVereinProgramm(bachtel.getId(), C, null, null);

        var tambouren = createVerein("Tambourenverein der Stadt Winterthur", true, true, true);
        createVereinProgramm(tambouren.getId(), G, null, TAMBOUREN);

        var ranking1 = createRanking(A, KLASSE_2, HARMONIE, null, location1);
        createRankingEntry(ranking1, bachtel, "76.54", 2);

        var ranking2 = createRanking(D, null, null, null, location1);
        createRankingEntry(ranking2, bachtel, "66.66", 4);

        var ranking3 = createRanking(G, null, TAMBOUREN, MODUL_G_KAT_A, location2);
        createRankingEntry(ranking3, tambouren, "100.00", 1);
        var ranking4 = createRanking(G, null, TAMBOUREN, MODUL_G_KAT_B, location2);
        createRankingEntry(ranking4, tambouren, "91.23", 1);
        var ranking5 = createRanking(G, null, TAMBOUREN, MODUL_G_KAT_C, location2);
        createRankingEntry(ranking5, tambouren, "55.55", 3);
    }

    private void createRankingEntry(RankingPojo ranking2, VereinPojo bachtel, String score, int rank) {
        rankingRepository.insert(new RankingEntryPojo(ranking2.getId(), bachtel.getId(), new BigDecimal(score), rank, "", DateUtil.now(), null, DateUtil.today()));
    }

    @AfterEach
    void tearDown() {
        rankingRepository.deleteAll();
        vereinRepository.deleteAll();
    }

    @Test
    void getAllRankingsPerVerein() {
        var allRankingsPerVerein = rankingRepository.getAllRankingsPerVerein();
        assertThat(allRankingsPerVerein).hasSize(2)
                                        .anySatisfy(dto -> {
                                            assertThat(dto.vereinsName()).isEqualTo("Harmonie am Bachtel");
                                            assertThat(dto.competition()).isEqualTo("2. Klasse Harmonie");
                                            assertThat(dto.rankings()).hasSize(3)
                                                                      .satisfies(r -> {
                                                                          assertThat(r.modulDescription()).isEqualTo("Modul A Konzertmusik");
                                                                          assertThat(r.score()).isEqualByComparingTo("76.54");
                                                                      }, atIndex(0))
                                                                      .satisfies(r -> {
                                                                          assertThat(r.modulDescription()).isEqualTo("Modul C Platzkonzerte");
                                                                          assertThat(r.score()).isNull();
                                                                      }, atIndex(1))
                                                                      .satisfies(r -> {
                                                                          assertThat(r.modulDescription()).isEqualTo("Modul D Parademusik traditionell");
                                                                          assertThat(r.score()).isEqualTo("66.66");
                                                                      }, atIndex(2));
                                        })
                                        .anySatisfy(dto -> {
                                            assertThat(dto.vereinsName()).isEqualTo("Tambourenverein der Stadt Winterthur");
                                            assertThat(dto.competition()).isEqualTo("Tambouren");
                                            assertThat(dto.rankings()).hasSize(3)
                                                                      .satisfies(r -> {
                                                                          assertThat(r.modulDescription()).isEqualTo("Modul G Tambouren (Kat. A)");
                                                                          assertThat(r.score()).isEqualByComparingTo("100.00");
                                                                      }, atIndex(0))
                                                                      .satisfies(r -> {
                                                                          assertThat(r.modulDescription()).isEqualTo("Modul G Tambouren (Kat. B)");
                                                                          assertThat(r.score()).isEqualByComparingTo("91.23");
                                                                      }, atIndex(1))
                                                                      .satisfies(r -> {
                                                                          assertThat(r.modulDescription()).isEqualTo("Modul G Tambouren (Kat. C)");
                                                                          assertThat(r.score()).isEqualTo("55.55");
                                                                      }, atIndex(2));
                                        });
    }

    private VereinProgrammPojo createVereinProgramm(Long vereinId, Modul modul, Klasse klasse, Besetzung besetzung) {
        var programm = new VereinProgrammPojo(null, vereinId, modul.name(), klasse != null ? klasse.name() : null, besetzung != null ? besetzung.name() : null, null, null, null, null, null, null, null, null, null, false, false, false, false, false, null, null, null, null, null, null, null, null);
        vereinRepository.insert(programm);
        return programm;
    }

    private VereinPojo createVerein(String vereinsname) {
        return createVerein(vereinsname, false, false, false);
    }

    private VereinPojo createVerein(String vereinsname, boolean tambourenKatA, boolean tambourenKatB, boolean tambourenKatC) {
        var kontakt1 = new KontaktPojo();
        vereinRepository.insert(kontakt1);
        var kontakt2 = new KontaktPojo();
        vereinRepository.insert(kontakt2);

        var verein = new VereinPojo();
        verein.setVereinsname(vereinsname);
        verein.setEmail(vereinsname);
        verein.setDirektionKontaktId(kontakt1.getId());
        verein.setPraesidentKontaktId(kontakt2.getId());
        verein.setIdentifier(vereinsname);
        verein.setTambourenKatA(tambourenKatA);
        verein.setTambourenKatB(tambourenKatB);
        verein.setTambourenKatC(tambourenKatC);
        vereinRepository.insert(verein);
        return verein;
    }

    private RankingPojo createRanking(Modul modul, Klasse klasse, Besetzung besetzung, JudgeReportModulCategory category, LocationDTO location) {
        var ranking = new RankingPojo(null, modul.name(), klasse != null ? klasse.name() : null, besetzung != null ? besetzung.name() : null, category != null ? category.name() : null, RankingStatus.FINAL.name(), location.id());
        rankingRepository.insert(ranking);
        return ranking;
    }
}
