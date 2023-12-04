package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import ch.zkmf2024.server.jooq.generated.tables.pojos.ImagePojo;
import ch.zkmf2024.server.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Slf4j
@Service
public class CloudflareService {
    private static final String BASE_URL = "https://api.cloudflare.com/client/v4/accounts/%s/images/v1";

    private final ApplicationProperties applicationProperties;
    private final WebClient webClient;
    private final ImageRepository imageRepository;

    public CloudflareService(ApplicationProperties applicationProperties,
                             ImageRepository imageRepository) {
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.create(BASE_URL.formatted(applicationProperties.getCloudflareAccountId()));
        this.imageRepository = imageRepository;
    }

    public void migrateToCloudflare() {
        imageRepository.findAll().stream()
                       .filter(imagePojo -> imagePojo.getCloudflareId() == null)
                       .forEach(this::upload);
    }

    private void upload(ImagePojo imagePojo) {
        try {
            var builder = new MultipartBodyBuilder();
            builder.part("url", "https://zkmf2024-server.herokuapp.com/public/image/%d".formatted(imagePojo.getId()));

            var response = webClient.post()
                                    .headers(headers -> headers.setBearerAuth(applicationProperties.getCloudflareApiToken()))
                                    .contentType(MULTIPART_FORM_DATA)
                                    .body(BodyInserters.fromMultipartData(builder.build()))
                                    .retrieve()
                                    .bodyToMono(Response.class)
                                    .block();
            imagePojo.setCloudflareId(response.result().id());
            imageRepository.update(imagePojo);
        } catch (WebClientException e) {
            log.error("Upload to Cloudflare failed", e);
        }
    }

    private record Response(List<Object> errors, Boolean success, List<Object> messages, Result result) {

    }

    private record Result(String id) {

    }
}
