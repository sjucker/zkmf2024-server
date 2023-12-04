package ch.zkmf2024.server;

import ch.zkmf2024.server.service.CloudflareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    @Autowired
    private CloudflareService cloudflareService;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cloudflareService.migrateToCloudflare();
    }
}
