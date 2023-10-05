package ch.zkmf2024.server.mapper;

import ch.zkmf2024.server.dto.NewsletterRecipientDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.NewsletterRecipientPojo;
import ch.zkmf2024.server.service.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Mapper
public interface NewsletterRecipientMapper {

    NewsletterRecipientMapper INSTANCE = Mappers.getMapper(NewsletterRecipientMapper.class);

    NewsletterRecipientDTO toDTO(NewsletterRecipientPojo pojo);

    default LocalDateTime map(OffsetDateTime value) {
        return DateUtil.toLocalDateTime(value);
    }
}
