package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.UnterhaltungEntryDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.UnterhaltungEntryPojo;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UnterhaltungRepository {

    private final UnterhaltungEntryDao unterhaltungEntryDao;

    public UnterhaltungRepository(DefaultConfiguration jooqConfig) {
        this.unterhaltungEntryDao = new UnterhaltungEntryDao(jooqConfig);
    }

    public List<UnterhaltungEntryPojo> findAll() {
        return unterhaltungEntryDao.findAll();
    }

    public Optional<UnterhaltungEntryPojo> findByUnterhaltungIdentifier(String identifier) {
        return unterhaltungEntryDao.fetchByIdentifier(identifier).stream().findFirst();
    }
}
