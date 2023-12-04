package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.ImageType;
import ch.zkmf2024.server.jooq.generated.tables.daos.ImageDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.ImagePojo;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.tables.Image.IMAGE;

@Repository
public class ImageRepository {

    private final DSLContext jooqDsl;
    private final ImageDao imageDao;

    public ImageRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.imageDao = new ImageDao(jooqConfig);
    }

    public Optional<ImagePojo> findImageByForeignKeyAndType(Long foreignKey, ImageType type) {
        return jooqDsl.selectFrom(IMAGE)
                      .where(
                              IMAGE.FOREIGN_KEY.eq(foreignKey),
                              IMAGE.TYPE.eq(type.name())
                      )
                      .fetchOptionalInto(ImagePojo.class);
    }

    public void insert(ImagePojo image) {
        imageDao.insert(image);
    }

    public void update(ImagePojo image) {
        imageDao.update(image);
    }

    public void delete(ImagePojo image) {
        imageDao.delete(image);
    }

    public Optional<ImagePojo> findById(Long id) {
        return imageDao.findOptionalById(id);
    }

    public List<ImagePojo> findAll() {
        return imageDao.findAll();
    }
}
