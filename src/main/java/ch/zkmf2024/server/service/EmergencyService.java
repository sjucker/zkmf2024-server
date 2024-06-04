package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.EmergencyMessageDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.EmergencyMessagePojo;
import ch.zkmf2024.server.repository.EmergencyMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmergencyService {

    private final EmergencyMessageRepository emergencyMessageRepository;

    public EmergencyService(EmergencyMessageRepository emergencyMessageRepository) {
        this.emergencyMessageRepository = emergencyMessageRepository;
    }

    public List<EmergencyMessageDTO> findAll() {
        return emergencyMessageRepository.findAll().stream()
                                         .map(EmergencyService::toDTO)
                                         .toList();
    }

    @Cacheable("emergency-message")
    public Optional<EmergencyMessageDTO> findActiveEmergencyMessage() {
        return emergencyMessageRepository.findActiveEmergencyMessage()
                                         .map(EmergencyService::toDTO);
    }

    public void createOrUpdate(EmergencyMessageDTO dto) {
        emergencyMessageRepository.findById(dto.id())
                                  .ifPresentOrElse(pojo -> {
                                                       pojo.setHeader(dto.header());
                                                       pojo.setMessage(dto.message());
                                                       pojo.setActive(dto.active());
                                                       emergencyMessageRepository.update(pojo);
                                                   },
                                                   () -> emergencyMessageRepository.insert(
                                                           new EmergencyMessagePojo(null, dto.header(), dto.message(), dto.active())));
    }

    private static EmergencyMessageDTO toDTO(EmergencyMessagePojo pojo) {
        return new EmergencyMessageDTO(pojo.getId(), pojo.getHeader(), pojo.getMessage(), pojo.getActive());
    }
}
