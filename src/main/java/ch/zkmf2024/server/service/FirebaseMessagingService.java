package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.admin.MessageSendDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
public class FirebaseMessagingService {
    public static final String EMERGENCY_TOPIC = "emergency";
    public static final String GENERAL_TOPIC = "general";

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

            firebaseMessaging.send(message.build());
        } catch (FirebaseMessagingException e) {
            log.error("could not send message", e);
        }
    }
}
