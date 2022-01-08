package ch.zkmf2024.server.service;

import ch.zkmf2024.server.domain.NewsletterRecipient;
import ch.zkmf2024.server.dto.RegisterNewsletterRequestDTO;
import ch.zkmf2024.server.repository.NewsletterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.ALREADY_REGISTERED;
import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.INVALID_EMAIL;
import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.REGISTERED;
import static ch.zkmf2024.server.service.ValidationUtil.isValidEmail;

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterService(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    public RegisterNewsletterResult register(RegisterNewsletterRequestDTO dto) {
        if (newsletterRepository.existsById(dto.email())) {
            return ALREADY_REGISTERED;
        }

        if (!isValidEmail(dto.email())) {
            return INVALID_EMAIL;
        }

        newsletterRepository.save(new NewsletterRecipient(
                dto.email(),
                dto.name(),
                LocalDateTime.now(),
                null
        ));

        return REGISTERED;

    }

    public enum RegisterNewsletterResult {
        REGISTERED,
        ALREADY_REGISTERED,
        INVALID_EMAIL
    }
}
