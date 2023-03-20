package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.UserRole;
import ch.zkmf2024.server.jooq.generated.tables.daos.UserDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.UserPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.USER;

@Repository
public class UserRepository {

    private final DSLContext jooqDsl;
    private final UserDao userDao;

    public UserRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.userDao = new UserDao(jooqConfig);
    }

    public Optional<UserPojo> findById(String email) {
        return userDao.findOptionalById(email);
    }

    public boolean existsById(String email) {
        return userDao.existsById(email);
    }

    public Optional<UserPojo> findByIdAndRole(String email, UserRole role) {
        return jooqDsl
                .selectFrom(USER)
                .where(
                        USER.EMAIL.eq(email),
                        USER.ROLE.eq(role.toString())
                )
                .fetchOptionalInto(UserPojo.class);
    }

    public void insert(UserPojo user) {
        userDao.insert(user);
    }

    public void update(UserPojo user) {
        userDao.update(user);
    }
}
