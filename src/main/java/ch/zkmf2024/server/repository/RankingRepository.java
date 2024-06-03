package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.JudgeReportModulCategory;
import ch.zkmf2024.server.jooq.generated.tables.daos.RankingDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.RankingEntryDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingEntryPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.RANKING;

@Repository
public class RankingRepository {

    private final DSLContext jooqDsl;
    private final RankingDao rankingDao;
    private final RankingEntryDao rankingEntryDao;

    public RankingRepository(DSLContext jooqDsl,
                             DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.rankingDao = new RankingDao(jooqConfig);
        this.rankingEntryDao = new RankingEntryDao(jooqConfig);
    }

    public void insert(RankingPojo pojo) {
        rankingDao.insert(pojo);
    }

    public void insert(RankingEntryPojo pojo) {
        rankingEntryDao.insert(pojo);
    }

    public Optional<RankingPojo> find(String modul, String klasse, String besetzung, JudgeReportModulCategory category, Long locationId) {
        return jooqDsl.selectFrom(RANKING)
                      .where(RANKING.MODUL.eq(modul),
                             (klasse == null ? RANKING.KLASSE.isNull() : RANKING.KLASSE.eq(klasse)),
                             (besetzung == null ? RANKING.BESETZUNG.isNull() : RANKING.BESETZUNG.eq(besetzung)),
                             (category == null ? RANKING.CATEGORY.isNull() : RANKING.CATEGORY.eq(category.name())),
                             RANKING.FK_LOCATION.eq(locationId))
                      .fetchOptionalInto(RankingPojo.class);
    }

    public List<RankingEntryPojo> findAllByRankingId(Long rankingId) {
        return rankingEntryDao.fetchByFkRanking(rankingId);
    }

    public void update(RankingEntryPojo rankingEntry) {
        rankingEntryDao.update(rankingEntry);
    }
}
