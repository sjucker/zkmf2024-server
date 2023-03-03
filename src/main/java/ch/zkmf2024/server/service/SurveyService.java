package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.repository.SurveyAnswerRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static ch.zkmf2024.server.service.SurveyService.RegisterSurveyAnswer.REGISTERED;

@Service
@Deprecated
public class SurveyService {

    private final SurveyAnswerRepository surveyAnswerRepository;

    public SurveyService(SurveyAnswerRepository surveyAnswerRepository) {
        this.surveyAnswerRepository = surveyAnswerRepository;
    }

    public RegisterSurveyAnswer register(SurveyAnswerDTO dto) {
        return REGISTERED;
    }

    public List<SurveyAnswerDTO> getAll() {
        return List.of();
    }

    public void delete(Long id) {

    }

    public File export() throws IOException {
        return null;
    }

    public enum RegisterSurveyAnswer {
        REGISTERED,
        INVALID_EMAIL
    }
}
