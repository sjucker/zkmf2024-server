package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.EmergencyMessageDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.EmergencyMessagePojo;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmergencyMessageRepository {

    private final EmergencyMessageDao emergencyMessageDao;

    public EmergencyMessageRepository(DefaultConfiguration jooqConfig) {
        this.emergencyMessageDao = new EmergencyMessageDao(jooqConfig);
    }

    public List<EmergencyMessagePojo> findAll() {
        return emergencyMessageDao.findAll();
    }

    public Optional<EmergencyMessagePojo> findById(Long id) {
        return emergencyMessageDao.findOptionalById(id);
    }

    public Optional<EmergencyMessagePojo> findActiveEmergencyMessage() {
        return emergencyMessageDao.findAll().stream()
                                  .filter(EmergencyMessagePojo::getActive)
                                  .findFirst();
    }

    public void insert(EmergencyMessagePojo pojo) {
        emergencyMessageDao.insert(pojo);
    }

    public void update(EmergencyMessagePojo pojo) {
        emergencyMessageDao.update(pojo);
    }
}
