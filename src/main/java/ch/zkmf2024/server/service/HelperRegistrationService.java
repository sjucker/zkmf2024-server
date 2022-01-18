package ch.zkmf2024.server.service;

import ch.zkmf2024.server.domain.HelperRegistration;
import ch.zkmf2024.server.dto.HelperRegistrationDTO;
import ch.zkmf2024.server.dto.RegisterHelperRequestDTO;
import ch.zkmf2024.server.mapper.DTOMapper;
import ch.zkmf2024.server.repository.HelperRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static ch.zkmf2024.server.dto.EventDays.FRIDAY;
import static ch.zkmf2024.server.dto.EventDays.SATURDAY;
import static ch.zkmf2024.server.dto.EventDays.SUNDAY;
import static ch.zkmf2024.server.service.HelperRegistrationService.RegisterHelperResult.INVALID_EMAIL;
import static ch.zkmf2024.server.service.HelperRegistrationService.RegisterHelperResult.REGISTERED;
import static ch.zkmf2024.server.service.ValidationUtil.isValidEmail;

@Service
public class HelperRegistrationService {

    private final HelperRegistrationRepository helperRegistrationRepository;

    public HelperRegistrationService(HelperRegistrationRepository helperRegistrationRepository) {
        this.helperRegistrationRepository = helperRegistrationRepository;
    }

    public RegisterHelperResult register(RegisterHelperRequestDTO request) {
        if (!isValidEmail(request.email())) {
            return INVALID_EMAIL;
        }

        helperRegistrationRepository.save(new HelperRegistration(
                null,
                request.vorname(),
                request.name(),
                request.email(),
                request.checkedDays().contains(FRIDAY),
                request.checkedDays().contains(SATURDAY),
                request.checkedDays().contains(SUNDAY),
                request.comment()
        ));

        return REGISTERED;
    }

    public List<HelperRegistrationDTO> getAll() {
        return helperRegistrationRepository.findAll().stream()
                                           .map(DTOMapper.INSTANCE::toDTO)
                                           .toList();
    }

    public enum RegisterHelperResult {
        REGISTERED,
        INVALID_EMAIL
    }
}
