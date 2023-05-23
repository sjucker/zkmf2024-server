package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class MailchimpService {

    private static final String BASE_URL = "https://us20.api.mailchimp.com/3.0";

    private static final String STATUS_SUBSCRIBED = "subscribed";
    private static final String TAG_NEWSLETTER = "Newsletter";

    private final ApplicationProperties applicationProperties;
    private final WebClient webClient;

    public MailchimpService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.create(BASE_URL);
    }

    public void addNewsletterMember(String emailAddress, String firstName, String lastName) {
        var body = new AddNewsletterMemberRequest(
                emailAddress,
                STATUS_SUBSCRIBED,
                new MergeFields(firstName, lastName),
                List.of(TAG_NEWSLETTER)
        );

        try {
            var response = webClient.post()
                                    .uri("/lists/%s/members".formatted(applicationProperties.getMailchimpListId()))
                                    .headers(httpHeaders -> httpHeaders.setBearerAuth(applicationProperties.getMailchimpApiKey()))
                                    .contentType(APPLICATION_JSON)
                                    .body(BodyInserters.fromValue(body))
                                    .retrieve()
                                    .toBodilessEntity()
                                    .block();
            if (response != null) {
                log.info("added new member {} to mailchimp with status code {}", body, response.getStatusCode());
            }
        } catch (WebClientException ex) {
            log.error("could not add new member %s to mailchimp".formatted(body), ex);
        }
    }

    private record AddNewsletterMemberRequest(String email_address,
                                              String status,
                                              MergeFields merge_fields,
                                              List<String> tags) {
    }

    private record MergeFields(String FNAME, String LNAME) {
    }
}
