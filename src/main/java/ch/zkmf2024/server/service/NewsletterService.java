package ch.zkmf2024.server.service;

import ch.zkmf2024.server.domain.NewsletterRecipient;
import ch.zkmf2024.server.dto.RegisterNewsletterRequestDTO;
import ch.zkmf2024.server.repository.NewsletterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.ALREADY_REGISTERED;
import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.CREATED;
import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.INVALID_EMAIL;

@Service
public class NewsletterService {

    // simple Email verification, just check for the existence of an @
    private static final Pattern emailPattern = Pattern.compile("^(.+)@(\\S+)$");

    private final NewsletterRepository newsletterRepository;

    public NewsletterService(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    public RegisterNewsletterResult register(RegisterNewsletterRequestDTO dto) {
        if (newsletterRepository.existsById(dto.email())) {
            return ALREADY_REGISTERED;
        }

        if (!emailPattern.matcher(dto.email()).matches()) {
            return INVALID_EMAIL;
        }

        newsletterRepository.save(new NewsletterRecipient(
                dto.email(),
                dto.name(),
                LocalDateTime.now(),
                null
        ));

        return CREATED;

    }

    public enum RegisterNewsletterResult {
        CREATED,
        ALREADY_REGISTERED,
        INVALID_EMAIL
    }
}
