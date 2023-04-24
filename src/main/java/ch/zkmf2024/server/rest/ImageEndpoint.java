package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.IMAGE_JPEG;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@Slf4j
@RestController
@RequestMapping("/public/image")
public class ImageEndpoint {

    private final ImageRepository imageRepository;

    public ImageEndpoint(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping(value = "/{id}", produces = IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getById(@PathVariable Long id) {
        log.info("GET /public/image/{}", id);

        return imageRepository.findById(id)
                              .map(image -> ResponseEntity.ok()
                                                          .contentType(IMAGE_JPEG)
                                                          .body(image.getContent()))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
