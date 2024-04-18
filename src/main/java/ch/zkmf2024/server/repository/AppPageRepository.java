package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.AppPageDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.AppPagePojo;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AppPageRepository {

    private final AppPageDao appPageDao;

    public AppPageRepository(DefaultConfiguration jooqConfig) {
        this.appPageDao = new AppPageDao(jooqConfig);
    }

    public List<AppPagePojo> findAll() {
        return appPageDao.findAll();
    }

    public void insert(AppPagePojo pojo) {
        appPageDao.insert(pojo);
    }

    public void update(AppPagePojo pojo) {
        appPageDao.update(pojo);
    }

    public Optional<AppPagePojo> findById(Long id) {
        return appPageDao.findOptionalById(id);
    }
}
