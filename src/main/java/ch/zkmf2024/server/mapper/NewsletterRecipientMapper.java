package ch.zkmf2024.server.mapper;

import ch.zkmf2024.server.dto.NewsletterRecipientDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.NewsletterRecipientPojo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsletterRecipientMapper {

    NewsletterRecipientMapper INSTANCE = Mappers.getMapper(NewsletterRecipientMapper.class);

    NewsletterRecipientDTO toDTO(NewsletterRecipientPojo pojo);

}
