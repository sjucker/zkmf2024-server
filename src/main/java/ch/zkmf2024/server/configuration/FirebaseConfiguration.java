package ch.zkmf2024.server.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
@ConditionalOnProperty("firebase.credentials")
public class FirebaseConfiguration {

    private final String firebaseCredentials;

    public FirebaseConfiguration(@Value("${firebase.credentials}") String firebaseCredentials) {
        this.firebaseCredentials = firebaseCredentials;
    }

    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        var googleCredentials = GoogleCredentials.fromStream(new ByteArrayInputStream(firebaseCredentials.getBytes()));

        var firebaseOptions = FirebaseOptions.builder()
                                             .setCredentials(googleCredentials)
                                             .build();

        var firebaseApp = FirebaseApp.initializeApp(firebaseOptions);

        return FirebaseMessaging.getInstance(firebaseApp);
    }

}
