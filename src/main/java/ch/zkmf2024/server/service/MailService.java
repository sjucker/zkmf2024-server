package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import ch.zkmf2024.server.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.GERMAN;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Slf4j
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final MjmlService mjmlService;
    private final SpringTemplateEngine templateEngine;
    private final ApplicationProperties applicationProperties;
    private final Environment environment;

    public MailService(JavaMailSender mailSender,
                       MjmlService mjmlService,
                       SpringTemplateEngine templateEngine,
                       ApplicationProperties applicationProperties,
                       Environment environment) {
        this.mailSender = mailSender;
        this.mjmlService = mjmlService;
        this.templateEngine = templateEngine;
        this.applicationProperties = applicationProperties;
        this.environment = environment;
    }

    public void sendRegistrationEmail(User user) {
        try {
            var variables = new HashMap<String, Object>();
            variables.put("email", user.getEmail());
            variables.put("verificationLink", "%s/verification/%s/%s".formatted(applicationProperties.getBaseUrlVereine(),
                                                                                user.getEmail(),
                                                                                user.getEmailVerification()));

            var mjml = templateEngine.process("registration", new Context(GERMAN, variables));

            var mimeMessage = mailSender.createMimeMessage();

            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            helper.setTo(user.getEmail());
            helper.setSubject("[ZKMF2024] Vereinsaccount erstellt");
            helper.setText(mjmlService.render(mjml), true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // TODO what now?
            throw new RuntimeException(e);
        }
    }

}
