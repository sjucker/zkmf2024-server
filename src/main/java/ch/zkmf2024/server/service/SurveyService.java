package ch.zkmf2024.server.service;

import ch.zkmf2024.server.domain.SurveyAnswer;
import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.repository.SurveyAnswerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static ch.zkmf2024.server.service.SurveyService.RegisterSurveyAnswer.INVALID_EMAIL;
import static ch.zkmf2024.server.service.SurveyService.RegisterSurveyAnswer.REGISTERED;
import static ch.zkmf2024.server.service.ValidationUtil.isValidEmail;

@Service
public class SurveyService {

    private final SurveyAnswerRepository surveyAnswerRepository;

    public SurveyService(SurveyAnswerRepository surveyAnswerRepository) {
        this.surveyAnswerRepository = surveyAnswerRepository;
    }

    public RegisterSurveyAnswer register(SurveyAnswerDTO dto) {
        if (!isValidEmail(dto.kontaktEmail())) {
            return INVALID_EMAIL;
        }

        surveyAnswerRepository.save(new SurveyAnswer(
                null,
                LocalDateTime.now(),
                dto.vereinsName(),
                dto.besetzung(),
                dto.staerkeKlasse(),
                dto.anzahlMitglieder(),
                dto.kontaktName(),
                dto.kontaktEmail(),
                dto.kontaktTelefon(),
                String.join(", ", dto.modulAuswahl()),
                dto.absageKommentar(),
                dto.absageKontaktaufnahme(),
                dto.helfer()
        ));

        return REGISTERED;

    }

    public enum RegisterSurveyAnswer {
        REGISTERED,
        INVALID_EMAIL
    }
}
