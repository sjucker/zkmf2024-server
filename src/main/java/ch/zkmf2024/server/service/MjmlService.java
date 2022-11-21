package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class MjmlService {

    private static final String BASE_URL = "https://api.mjml.io/v1/render";

    private final ApplicationProperties applicationProperties;
    private final WebClient webClient;

    public MjmlService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.create(BASE_URL);
    }

    public String render(String mjml) {
        var response = webClient.post()
                                .headers(httpHeaders -> httpHeaders.setBasicAuth(applicationProperties.getMjmlAppId(),
                                                                                 applicationProperties.getMjmlPrivateKey()))
                                .contentType(APPLICATION_JSON)
                                .body(BodyInserters.fromValue(new Request(mjml)))
                                .retrieve()
                                .bodyToMono(Response.class)
                                .onErrorResume(throwable -> {
                                    // TODO handle errors
                                    log.info(throwable.getMessage());
                                    return Mono.empty();
                                }).block();

        return response.html();
    }

    private record Request(String mjml) {
    }

    private record Response(List<?> errors, String html, String mjml, String mjml_version) {
    }
}
