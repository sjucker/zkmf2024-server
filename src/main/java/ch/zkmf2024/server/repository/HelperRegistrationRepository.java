package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.HelperRegistrationDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.HelperRegistrationPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ch.zkmf2024.server.jooq.generated.tables.HelperRegistration.HELPER_REGISTRATION;

@Repository
public class HelperRegistrationRepository {

    private final DSLContext jooqDsl;
    private final HelperRegistrationDao dao;

    public HelperRegistrationRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.dao = new HelperRegistrationDao(jooqConfig);
    }

    public List<HelperRegistrationPojo> findAll() {
        return dao.findAll();
    }

    public boolean existsByEmail(String email) {
        return jooqDsl.fetchExists(HELPER_REGISTRATION, HELPER_REGISTRATION.EMAIL.equalIgnoreCase(email));
    }

    public void insert(HelperRegistrationPojo helperRegistration) {
        dao.insert(helperRegistration);
    }
}
