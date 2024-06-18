package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.ScreenDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.ScreenDao;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ch.zkmf2024.server.jooq.generated.Tables.SCREEN;
import static org.jooq.impl.DSL.rand;

@Repository
public class ScreenRepository {

    private final ScreenDao screenDao;
    private final DSLContext jooqDsl;

    public ScreenRepository(DefaultConfiguration jooqConfig, DSLContext jooqDsl) {
        this.screenDao = new ScreenDao(jooqConfig);
        this.jooqDsl = jooqDsl;
    }

    public List<ScreenDTO> findActiveInRandomOrder() {
        return jooqDsl.selectFrom(SCREEN)
                      .where(SCREEN.ACTIVE.isTrue())
                      .orderBy(rand())
                      .fetch(ScreenDTO::of);
    }

}
