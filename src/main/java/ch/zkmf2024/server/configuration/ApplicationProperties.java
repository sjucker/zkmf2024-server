package ch.zkmf2024.server.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "zkmf2024")
public class ApplicationProperties {
    private String jwtSecret;
    private String mjmlAppId;
    private String mjmlPrivateKey;
    private String baseUrlVereine;
    private String helferMail;
    private String sekretariatMail;
    private String musikMail;
    private String bccMail;
    private String mailchimpApiKey;
    private String mailchimpListId;
}
