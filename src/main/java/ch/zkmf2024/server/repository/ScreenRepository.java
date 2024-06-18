package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.ScreenDTO;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ch.zkmf2024.server.jooq.generated.Tables.SCREEN;
import static org.jooq.impl.DSL.rand;

@Repository
public class ScreenRepository {

    private final DSLContext jooqDsl;

    public ScreenRepository(DSLContext jooqDsl) {
        this.jooqDsl = jooqDsl;
    }

    public List<ScreenDTO> findActiveInRandomOrder() {
        return jooqDsl.selectFrom(SCREEN)
                      .where(SCREEN.ACTIVE.isTrue())
                      .orderBy(rand())
                      .fetch(ScreenDTO::of);
    }

}
