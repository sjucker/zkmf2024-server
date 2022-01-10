package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.repository.SurveyAnswerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static ch.zkmf2024.server.mapper.DTOMapper.INSTANCE;
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

        var surveyAnswer = INSTANCE.fromDTO(dto);
        surveyAnswer.setTimestamp(LocalDateTime.now());
        surveyAnswerRepository.save(surveyAnswer);

        return REGISTERED;
    }

    public List<SurveyAnswerDTO> getAll() {
        return surveyAnswerRepository.findAll().stream()
                                     .map(INSTANCE::toDTO)
                                     .toList();
    }

    public void delete(Long id) {
        surveyAnswerRepository.deleteById(id);
    }

    public enum RegisterSurveyAnswer {
        REGISTERED,
        INVALID_EMAIL
    }
}
