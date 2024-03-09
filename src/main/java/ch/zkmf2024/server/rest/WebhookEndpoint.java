package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.service.MailService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/webhook")
public class WebhookEndpoint {

    private final MailService mailService;

    public WebhookEndpoint(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    public ResponseEntity<?> onNotification(@RequestBody String payload) {
        var jsonObject = new GsonBuilder().create().fromJson(payload, JsonElement.class).getAsJsonObject();
        var action = jsonObject.get("action").getAsString();
        var resource = jsonObject.get("resource").getAsString();
        var status = jsonObject.get("data").getAsJsonObject().get("status").getAsString();
        var name = jsonObject.get("data").getAsJsonObject().get("app").getAsJsonObject().get("name").getAsString();
        var version = jsonObject.get("data").getAsJsonObject().get("source_blob").getAsJsonObject().get("version").getAsString();

        mailService.sendWebhookEmail(action, resource, status, name, version);

        return ResponseEntity.noContent().build();
    }
}
