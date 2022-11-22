package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.domain.Image;
import ch.zkmf2024.server.domain.Image.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findImageByForeignKeyAndType(Long foreignKey, ImageType type);

}
