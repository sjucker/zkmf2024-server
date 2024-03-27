package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.FestprogrammEntryDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.FestprogrammEntryPojo;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FestprogrammRepository {

    private final FestprogrammEntryDao festprogrammEntryDao;

    public FestprogrammRepository(DefaultConfiguration jooqConfig) {
        this.festprogrammEntryDao = new FestprogrammEntryDao(jooqConfig);
    }

    public List<FestprogrammEntryPojo> findAll() {
        return festprogrammEntryDao.findAll();
    }
}
