package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.SponsorDTO;
import ch.zkmf2024.server.dto.SponsorType;
import ch.zkmf2024.server.jooq.generated.tables.daos.SponsorDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.SponsorPojo;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Slf4j
@Repository
public class SponsoringRepository {

    private final SponsorDao sponsorDao;

    public SponsoringRepository(DefaultConfiguration jooqConfig) {
        this.sponsorDao = new SponsorDao(jooqConfig);
    }

    public Map<SponsorType, List<SponsorDTO>> findAllPerType() {
        return sponsorDao.findAll().stream()
                         .collect(groupingBy(toSponsorType(),
                                             mapping(toDTO(), toList())));
    }

    private static Function<SponsorPojo, SponsorDTO> toDTO() {
        return pojo -> new SponsorDTO(pojo.getName(), pojo.getCloudflareId(), pojo.getUrl());
    }

    private static Function<SponsorPojo, SponsorType> toSponsorType() {
        return pojo -> SponsorType.valueOf(pojo.getType());
    }
}
