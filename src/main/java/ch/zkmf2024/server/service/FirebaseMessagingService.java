package ch.zkmf2024.server.service;

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

    public void sendToEmergencyTopic(String title, String body, String route) {
        sendToTopic(EMERGENCY_TOPIC, title, body, route);
    }

    public void sendToGeneralTopic(String title, String body) {
        sendToTopic(GENERAL_TOPIC, title, body, null);
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
