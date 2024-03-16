package ch.zkmf2024.server;

import ch.zkmf2024.server.service.FirebaseMessagingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

@EnableScheduling
@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    private final FirebaseMessagingService firebaseMessagingService;

    public ServerApplication(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        firebaseMessagingService.sendToEmergencyTopic("test emergency", LocalDateTime.now().toString(), "/zeitplan");
//        firebaseMessagingService.sendToGeneralTopic("test general", LocalDateTime.now().toString());
    }
}
