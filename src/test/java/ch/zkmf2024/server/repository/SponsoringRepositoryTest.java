package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.AbstractIntegrationTest;
import ch.zkmf2024.server.dto.SponsorDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.SponsorDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.SponsorPojo;
import org.jooq.impl.DefaultConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static ch.zkmf2024.server.dto.SponsorType.MUSIKFAN;
import static ch.zkmf2024.server.dto.SponsorType.SPONSOR;
import static org.assertj.core.api.Assertions.assertThat;

class SponsoringRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private SponsoringRepository sponsoringRepository;
    @Autowired
    private DefaultConfiguration jooqConfig;

    private SponsorDao sponsorDao;

    private SponsorPojo sponsor1;
    private SponsorPojo sponsor2;
    private SponsorPojo sponsor3;

    @BeforeEach
    void setUp() {
        sponsorDao = new SponsorDao(jooqConfig);
        sponsor1 = new SponsorPojo(null, SPONSOR.name(), "Sponsor1", "id1", "url1");
        sponsorDao.insert(sponsor1);
        sponsor2 = new SponsorPojo(null, MUSIKFAN.name(), "Sponsor2", "id2", "url2");
        sponsorDao.insert(sponsor2);
        sponsor3 = new SponsorPojo(null, MUSIKFAN.name(), "Sponsor3", null, null);
        sponsorDao.insert(sponsor3);
    }

    @AfterEach
    void tearDown() {
        sponsorDao.delete(sponsor1);
        sponsorDao.delete(sponsor2);
        sponsorDao.delete(sponsor3);
    }

    @Test
    void getRandom() {
        assertThat(sponsoringRepository.getRandom(0)).isEmpty();
        assertThat(sponsoringRepository.getRandom(1)).hasSize(1)
                                                     .extracting(SponsorDTO::name)
                                                     .containsAnyOf("Sponsor1", "Sponsor2");
        assertThat(sponsoringRepository.getRandom(2)).hasSize(2)
                                                     .extracting(SponsorDTO::name)
                                                     .containsOnly("Sponsor1", "Sponsor2");
        assertThat(sponsoringRepository.getRandom(3)).hasSize(2)
                                                     .extracting(SponsorDTO::name)
                                                     .containsOnly("Sponsor1", "Sponsor2");
    }
}
