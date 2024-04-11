package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.RankingDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public class RankingsRepository {

    private final DSLContext jooqDsl;
    private final RankingDao rankingDao;

    public RankingsRepository(DSLContext jooqDsl,
                              DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.rankingDao = new RankingDao(jooqConfig);
    }

    public void insert(RankingPojo pojo) {
        rankingDao.insert(pojo);
    }

}
