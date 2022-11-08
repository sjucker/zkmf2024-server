package ch.zkmf2024.server.mapper;


import ch.zkmf2024.server.domain.HelperRegistration;
import ch.zkmf2024.server.domain.NewsletterRecipient;
import ch.zkmf2024.server.domain.SurveyAnswer;
import ch.zkmf2024.server.domain.Verein;
import ch.zkmf2024.server.dto.HelperRegistrationDTO;
import ch.zkmf2024.server.dto.NewsletterRecipientDTO;
import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    String LIST_DELIMITER = ", ";

    SurveyAnswerDTO toDTO(SurveyAnswer surveyAnswer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    SurveyAnswer fromDTO(SurveyAnswerDTO dto);

    default List<String> toList(String value) {
        return List.of(value.split(LIST_DELIMITER));
    }

    default String toString(List<String> list) {
        return String.join(LIST_DELIMITER, list);
    }

    NewsletterRecipientDTO toDTO(NewsletterRecipient newsletterRecipient);

    HelperRegistrationDTO toDTO(HelperRegistration helperRegistration);

    VereinDTO toDTO(Verein verein);

    @Mapping(target = "email", ignore = true)
    void update(@MappingTarget Verein domain, VereinDTO dto);
}
