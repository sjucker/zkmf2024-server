package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.admin.MessageFavoriteDTO;
import ch.zkmf2024.server.dto.admin.MessageSendDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
@ConditionalOnProperty(value = "firebase.enabled", havingValue = "true")
public class FirebaseMessagingService {
    private static final String EMERGENCY_TOPIC = "emergency";
    private static final String GENERAL_TOPIC = "general";
    private static final String VEREIN_TOPIC = "favorite-%s";

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void send(MessageSendDTO dto) {
        switch (dto.type()) {
            case EMERGENCY -> sendToTopic(EMERGENCY_TOPIC, dto.title(), dto.body(), dto.route());
            case GENERAL -> sendToTopic(GENERAL_TOPIC, dto.title(), dto.body(), dto.route());
        }
    }

    public void send(MessageFavoriteDTO dto) {
        sendToTopic(VEREIN_TOPIC.formatted(dto.identifier()), dto.title(), dto.body(), "/vereine/%s".formatted(dto.identifier()));
    }

    public void sendToTopic(String topic, String title, String body, String route) {
        try {
            var notification = Notification.builder()
                                           .setTitle(title)
                                           .setBody(body)
                                           .build();

            var message = Message.builder()
                                 .setTopic(topic)
                                 .setNotification(notification);

            if (isNotBlank(route)) {
                message.putData("route", route);
            }

            log.info("sending notification {}/{} to topic {}", title, body, topic);
            firebaseMessaging.send(message.build());
        } catch (FirebaseMessagingException e) {
            log.error("could not send message", e);
        }
    }

    public void sendToToken(String token, String title, String body, String route) {
        try {
            var notification = Notification.builder()
                                           .setTitle(title)
                                           .setBody(body)
                                           .build();

            var message = Message.builder()
                                 .setToken(token)
                                 .setNotification(notification);

            if (isNotBlank(route)) {
                message.putData("route", route);
            }
            log.info("sending notification {}/{} to token {}", title, body, token);
            firebaseMessaging.send(message.build());
        } catch (FirebaseMessagingException e) {
            log.error("could not send message", e);
        }
    }
}
