package ch.zkmf2024.server.mapper;

import ch.zkmf2024.server.domain.SurveyAnswer;
import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    default List<String> toModulAuswahl(String modulAuswahl) {
        return List.of(modulAuswahl.split(LIST_DELIMITER));
    }

    default String toModulAuswahl(List<String> modulAuswahl) {
        return String.join(", ", modulAuswahl);
    }


}
