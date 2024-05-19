package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.UserRole;
import ch.zkmf2024.server.jooq.generated.tables.daos.Zkmf2024UserDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.Zkmf2024UserPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.ZKMF2024_USER;
import static org.apache.commons.lang3.StringUtils.toRootLowerCase;

@Repository
public class UserRepository {

    private final DSLContext jooqDsl;
    private final Zkmf2024UserDao userDao;

    public UserRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.userDao = new Zkmf2024UserDao(jooqConfig);
    }

    public Optional<Zkmf2024UserPojo> findById(String email) {
        return userDao.findOptionalById(toRootLowerCase(email));
    }

    public boolean existsById(String email) {
        return userDao.existsById(toRootLowerCase(email));
    }

    public Optional<Zkmf2024UserPojo> findByIdAndRole(String email, UserRole role) {
        return jooqDsl
                .selectFrom(ZKMF2024_USER)
                .where(
                        ZKMF2024_USER.EMAIL.eq(toRootLowerCase(email)),
                        ZKMF2024_USER.ROLE.eq(role.name())
                )
                .fetchOptionalInto(Zkmf2024UserPojo.class);
    }

    public void insert(Zkmf2024UserPojo user) {
        userDao.insert(user);
    }

    public void update(Zkmf2024UserPojo user) {
        userDao.update(user);
    }

    public void deleteAll() {
        jooqDsl.deleteFrom(ZKMF2024_USER).execute();
    }
}
