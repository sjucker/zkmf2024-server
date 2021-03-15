package ch.zkmf2024.server.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class InitializeFirebaseApp implements ApplicationListener<ContextRefreshedEvent> {

    private final ConfigProperties configProperties;

    public InitializeFirebaseApp(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            InputStream in = new ByteArrayInputStream(configProperties.getCredentials().getBytes());

            FirebaseOptions options = FirebaseOptions.builder()
                                                     .setCredentials(GoogleCredentials.fromStream(in))
                                                     .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
