package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.CurrentlyPlayingDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.CurrentlyPlayingPojo;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public class CurrentlyPlayingRepository {
    private final CurrentlyPlayingDao currentlyPlayingDao;

    public CurrentlyPlayingRepository(DefaultConfiguration jooqConfig) {
        this.currentlyPlayingDao = new CurrentlyPlayingDao(jooqConfig);
    }

    public void insert(CurrentlyPlayingPojo pojo) {
        currentlyPlayingDao.insert(pojo);
    }
}
