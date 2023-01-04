package ch.zkmf2024.server.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {
    public static final String EMERGENCY_TOPIC = "emergency";
    public static final String GENERAL_TOPIC = "general";

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void sendToEmergencyTopic(String title, String body) throws FirebaseMessagingException {
        sendToTopic(EMERGENCY_TOPIC, title, body);
    }

    public void sendToGeneralTopic(String title, String body) throws FirebaseMessagingException {
        sendToTopic(GENERAL_TOPIC, title, body);
    }

    public void sendToTopic(String topic, String title, String body) throws FirebaseMessagingException {
        var notification = Notification.builder()
                                       .setTitle(title)
                                       .setBody(body)
                                       .build();

        var message = Message.builder()
                             .setTopic(topic)
                             .setNotification(notification)
                             .build();

        firebaseMessaging.send(message);
    }

    public String sendNotification(String title, String body, String token) throws FirebaseMessagingException {

        var notification = Notification.builder()
                                       .setTitle(title)
                                       .setBody(body)
                                       .build();

        var message = Message.builder()
                             .setToken(token)
                             .setNotification(notification)
                             .build();

        return firebaseMessaging.send(message);
    }
}
