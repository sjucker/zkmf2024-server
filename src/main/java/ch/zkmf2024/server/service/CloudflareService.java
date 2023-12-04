package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import ch.zkmf2024.server.jooq.generated.tables.pojos.ImagePojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Slf4j
@Service
public class CloudflareService {
    private static final String BASE_URL = "https://api.cloudflare.com/client/v4/accounts/%s/images/v1";

    private final ApplicationProperties applicationProperties;
    private final WebClient webClient;

    public CloudflareService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.create(BASE_URL.formatted(applicationProperties.getCloudflareAccountId()));
    }

    public Optional<String> upload(ImagePojo imagePojo) {
        try {
            log.info("uploading image ({}) to Cloudflare", imagePojo);
            var builder = new MultipartBodyBuilder();
            builder.part("url", "%s/public/image/%d".formatted(applicationProperties.getBaseUrl(),
                                                               imagePojo.getId()));

            var response = webClient.post()
                                    .headers(headers -> headers.setBearerAuth(applicationProperties.getCloudflareApiToken()))
                                    .contentType(MULTIPART_FORM_DATA)
                                    .body(BodyInserters.fromMultipartData(builder.build()))
                                    .retrieve()
                                    .bodyToMono(Response.class)
                                    .blockOptional();

            return response.map(r -> r.result.id());
        } catch (WebClientException e) {
            log.error("Upload to Cloudflare failed", e);
            return Optional.empty();
        }
    }

    public void delete(ImagePojo imagePojo) {
        try {
            log.info("deleting image ({}) from Cloudflare", imagePojo.getCloudflareId());
            webClient.delete()
                     .uri("/%s".formatted(imagePojo.getCloudflareId()))
                     .headers(headers -> headers.setBearerAuth(applicationProperties.getCloudflareApiToken()))
                     .retrieve()
                     .bodyToMono(Response.class)
                     .block();
        } catch (WebClientException e) {
            log.error("Delete from Cloudflare failed", e);
        }
    }

    private record Response(List<Object> errors, Boolean success, List<Object> messages, Result result) {

    }

    private record Result(String id) {

    }
}
