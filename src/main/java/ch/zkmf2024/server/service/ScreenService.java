package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.ScreenDTO;
import ch.zkmf2024.server.repository.ScreenRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScreenService {

    private final ScreenRepository screenRepository;

    public ScreenService(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    public Optional<ScreenDTO> findActive(String locationIdentifier) {
        var allActiveInRandomOrder = screenRepository.findActiveInRandomOrder();
        return allActiveInRandomOrder
                .stream()
                // location specific has more priority
                .filter(dto -> StringUtils.equalsIgnoreCase(locationIdentifier, dto.locationIdentifier()))
                .findFirst()
                // fallback to unspecific location
                .or(() -> allActiveInRandomOrder.stream()
                                                .filter(dto -> dto.locationIdentifier() == null)
                                                .findFirst());
    }

    public Optional<String> getWelcomeScreen() {
        return screenRepository.getWelcomeScreen();
    }
}
