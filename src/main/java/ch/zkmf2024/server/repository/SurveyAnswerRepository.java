package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.jooq.generated.tables.daos.SurveyAnswerDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.SurveyAnswerPojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyAnswerRepository {

    private final DSLContext jooqDsl;
    private final SurveyAnswerDao surveyAnswerDao;

    public SurveyAnswerRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.surveyAnswerDao = new SurveyAnswerDao(jooqConfig);
    }

    public void persist(SurveyAnswerPojo surveyAnswer) {
        surveyAnswerDao.insert(surveyAnswer);
    }
}
