package ch.zkmf2024.server.mapper;

import ch.zkmf2024.server.domain.HelperRegistration;
import ch.zkmf2024.server.dto.Aufgaben;
import ch.zkmf2024.server.dto.Einsatzzeit;
import ch.zkmf2024.server.dto.RegisterHelperRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HelperRegistrationMapper {

    HelperRegistrationMapper INSTANCE = Mappers.getMapper(HelperRegistrationMapper.class);

   String LIST_DELIMITER = ",";

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registeredAt", ignore = true)
    HelperRegistration fromDTO(RegisterHelperRequestDTO dto);

    default String mapAufgaben(List<Aufgaben> values) {
        return StringUtils.join(values, LIST_DELIMITER);
    }

    default String mapEinsatzzeit(List<Einsatzzeit> values) {
        return StringUtils.join(values, LIST_DELIMITER);
    }
}
