package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.RankingPenaltyDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingPenaltyPojo;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RankingPenaltyRepository {

    private final RankingPenaltyDao rankingPenaltyDao;

    public RankingPenaltyRepository(DefaultConfiguration jooqConfig) {
        this.rankingPenaltyDao = new RankingPenaltyDao(jooqConfig);
    }

    public Optional<RankingPenaltyPojo> findByTimetableEntryId(Long timetableEntryId) {
        return rankingPenaltyDao.fetchByFkTimetableEntry(timetableEntryId).stream().findFirst();
    }

    public void insert(RankingPenaltyPojo pojo) {
        rankingPenaltyDao.insert(pojo);
    }

    public void update(RankingPenaltyPojo pojo) {
        rankingPenaltyDao.update(pojo);
    }
}
