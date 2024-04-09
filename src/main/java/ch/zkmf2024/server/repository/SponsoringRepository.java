package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.SponsorDTO;
import ch.zkmf2024.server.dto.SponsorType;
import ch.zkmf2024.server.jooq.generated.tables.daos.SponsorDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.SponsorPojo;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static ch.zkmf2024.server.jooq.generated.Tables.SPONSOR;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.jooq.impl.DSL.rand;

@Slf4j
@Repository
public class SponsoringRepository {

    private final SponsorDao sponsorDao;
    private final DSLContext jooqDsl;

    public SponsoringRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.sponsorDao = new SponsorDao(jooqConfig);
        this.jooqDsl = jooqDsl;
    }

    public Map<SponsorType, List<SponsorDTO>> findAllPerType() {
        return sponsorDao.findAll().stream()
                         .collect(groupingBy(toSponsorType(),
                                             mapping(toDTO(), toList())));
    }

    public SponsorDTO getRandom() {
        return jooqDsl.selectFrom(SPONSOR)
                      .where(SPONSOR.CLOUDFLARE_ID.isNotNull(), SPONSOR.URL.isNotNull())
                      .orderBy(rand())
                      .limit(1)
                      .fetchSingle(it -> new SponsorDTO(it.getName(), it.getCloudflareId(), it.getUrl()));
    }

    private static Function<SponsorPojo, SponsorDTO> toDTO() {
        return pojo -> new SponsorDTO(pojo.getName(), pojo.getCloudflareId(), pojo.getUrl());
    }

    private static Function<SponsorPojo, SponsorType> toSponsorType() {
        return pojo -> SponsorType.valueOf(pojo.getType());
    }
}
