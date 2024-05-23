package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.NotificationSentDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.NotificationSentPojo;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {

    private final NotificationSentDao notificationSentDao;

    public NotificationRepository(DefaultConfiguration jooqConfig) {
        this.notificationSentDao = new NotificationSentDao(jooqConfig);
    }

    public List<NotificationSentPojo> findAll() {
        return notificationSentDao.findAll();
    }

    public void insert(NotificationSentPojo notificationSentPojo) {
        notificationSentDao.insert(notificationSentPojo);
    }

}
