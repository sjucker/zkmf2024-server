package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.NewsletterRecipientDTO;
import ch.zkmf2024.server.dto.RegisterNewsletterRequestDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.NewsletterRecipientPojo;
import ch.zkmf2024.server.mapper.NewsletterRecipientMapper;
import ch.zkmf2024.server.repository.NewsletterRecipientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.ALREADY_REGISTERED;
import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.INVALID_EMAIL;
import static ch.zkmf2024.server.service.NewsletterService.RegisterNewsletterResult.REGISTERED;
import static ch.zkmf2024.server.service.ValidationUtil.isValidEmail;
import static ch.zkmf2024.server.util.DateUtil.now;

@Service
public class NewsletterService {

    private final MailchimpService mailchimpService;
    private final NewsletterRecipientRepository newsletterRecipientRepository;

    public NewsletterService(MailchimpService mailchimpService,
                             NewsletterRecipientRepository newsletterRecipientRepository) {
        this.mailchimpService = mailchimpService;
        this.newsletterRecipientRepository = newsletterRecipientRepository;
    }

    public RegisterNewsletterResult register(RegisterNewsletterRequestDTO dto) {
        if (newsletterRecipientRepository.existsById(dto.email())) {
            return ALREADY_REGISTERED;
        }

        if (!isValidEmail(dto.email())) {
            return INVALID_EMAIL;
        }

        newsletterRecipientRepository.insert(new NewsletterRecipientPojo(
                dto.email(),
                dto.vorname(),
                dto.name(),
                now(),
                null
        ));

        // TODO make this async?
        mailchimpService.addNewsletterMember(dto.email(), dto.vorname(), dto.name());

        return REGISTERED;
    }

    public List<NewsletterRecipientDTO> getAll() {
        return newsletterRecipientRepository.findAll().stream()
                                            .map(NewsletterRecipientMapper.INSTANCE::toDTO)
                                            .toList();
    }

    public void delete(String email) {
        newsletterRecipientRepository.deleteById(email);
    }

    public void unsubscribe(String email) {
        var newsletterRecipient = newsletterRecipientRepository.findById(email).orElseThrow();
        newsletterRecipient.setUnsubscribedAt(now());
        newsletterRecipientRepository.update(newsletterRecipient);
    }

    public enum RegisterNewsletterResult {
        REGISTERED,
        ALREADY_REGISTERED,
        INVALID_EMAIL
    }
}
