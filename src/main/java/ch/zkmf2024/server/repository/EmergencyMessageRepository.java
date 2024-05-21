package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.EmergencyMessageDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.EmergencyMessageDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.EmergencyMessagePojo;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmergencyMessageRepository {

    private final EmergencyMessageDao emergencyMessageDao;

    public EmergencyMessageRepository(DefaultConfiguration jooqConfig) {
        this.emergencyMessageDao = new EmergencyMessageDao(jooqConfig);
    }

    public Optional<EmergencyMessageDTO> findEmergencyMessage() {
        return emergencyMessageDao.findAll().stream()
                                  .filter(EmergencyMessagePojo::getActive)
                                  .findFirst()
                                  .map(it -> new EmergencyMessageDTO(it.getHeader(), it.getMessage()));
    }
}
