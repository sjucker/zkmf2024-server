package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import ch.zkmf2024.server.dto.Aufgaben;
import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Einsatzzeit;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VereinsanmeldungDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.HelperRegistrationPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.Zkmf2024UserPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import static ch.zkmf2024.server.service.HelperRegistrationService.getAufgabenAsList;
import static ch.zkmf2024.server.service.HelperRegistrationService.getEinsatzFreitagAsList;
import static ch.zkmf2024.server.service.HelperRegistrationService.getEinsatzSamstagAsList;
import static ch.zkmf2024.server.service.HelperRegistrationService.getEinsatzSonntagAsList;
import static ch.zkmf2024.server.util.FormatUtil.formatDate;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.GERMAN;
import static java.util.stream.Collectors.joining;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_NO;

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

    public void sendRegistrationEmail(Zkmf2024UserPojo user) {
        log.info("sending registration mail to {}", user.getEmail());
        try {
            var variables = new HashMap<String, Object>();
            variables.put("email", user.getEmail());
            variables.put("loginLink", "%s/login/%s".formatted(applicationProperties.getBaseUrlVereine(),
                                                               user.getEmail()));
            variables.put("verificationLink", "%s/verification/%s/%s".formatted(applicationProperties.getBaseUrlVereine(),
                                                                                user.getEmail(),
                                                                                user.getEmailVerification()));

            var mjml = templateEngine.process("registration", new Context(GERMAN, variables));

            var mimeMessage = mailSender.createMimeMessage();

            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            helper.setReplyTo(applicationProperties.getInfoMail());
            helper.setTo(user.getEmail());
            helper.setCc(applicationProperties.getSekretariatMail());
            helper.setBcc(applicationProperties.getBccMail());
            helper.setSubject("[%s] Vereinsaccount erstellt".formatted(getSubjectPrefix()));
            helper.setText(mjmlService.render(mjml), true);

            mailSender.send(mimeMessage);
        } catch (RuntimeException | MessagingException e) {
            log.error("could not send registration mail for created user %s".formatted(user), e);
        }
    }

    public void sendRegistrationConfirmationEmail(VereinDTO vereinDTO) {
        log.info("sending confirmation mail to {}", vereinDTO.email());
        try {
            var variables = new HashMap<String, Object>();
            variables.put("verein", vereinDTO.angaben().vereinsname());

            variables.put("modul", vereinDTO.anmeldung().getModule().stream()
                                            .map(Modul::getFullDescription)
                                            .collect(joining(", ")));

            variables.put("klasse", getKlassen(vereinDTO.anmeldung()));

            variables.put("besetzung", vereinDTO.anmeldung().getBesetzungen().stream()
                                                .map(Besetzung::getDescription)
                                                .collect(joining(", ")));

            var mjml = templateEngine.process("confirmation", new Context(GERMAN, variables));

            var mimeMessage = mailSender.createMimeMessage();

            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            helper.setReplyTo(applicationProperties.getInfoMail());
            helper.setTo(vereinDTO.email());
            helper.setCc(new String[]{applicationProperties.getMusikMail(), applicationProperties.getSekretariatMail()});
            helper.setBcc(applicationProperties.getBccMail());
            helper.setSubject("[%s] Bestätigung Anmeldung".formatted(getSubjectPrefix()));
            helper.setText(mjmlService.render(mjml), true);

            mailSender.send(mimeMessage);
        } catch (RuntimeException | MessagingException e) {
            log.error("could not send confirmation mail for created verein %s".formatted(vereinDTO.angaben().vereinsname()), e);
        }
    }

    private String getKlassen(VereinsanmeldungDTO anmeldung) {
        var klasse = new StringJoiner(", ");
        if (anmeldung.klasseModulA() != null) {
            klasse.add("Modul A: " + anmeldung.klasseModulA().getDescription());
        }
        if (anmeldung.klasseModulB() != null) {
            klasse.add("Modul B: " + anmeldung.klasseModulB().getDescription());
        }
        if (anmeldung.klasseModulH() != null) {
            klasse.add("Modul H: " + anmeldung.klasseModulH().getDescription());
        }
        return klasse.toString();
    }

    public void sendResetPasswordEmail(Zkmf2024UserPojo user) {
        log.info("sending forgot-password mail to {}", user.getEmail());
        try {
            var variables = new HashMap<String, Object>();
            variables.put("resetLink", "%s/reset-passwort/%s/%s".formatted(applicationProperties.getBaseUrlVereine(),
                                                                           user.getEmail(),
                                                                           user.getPasswordResetToken()));

            var mjml = templateEngine.process("forgot-password", new Context(GERMAN, variables));
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            helper.setReplyTo(applicationProperties.getInfoMail());
            helper.setTo(user.getEmail());
            helper.setBcc(applicationProperties.getBccMail());
            helper.setSubject("[%s] Passwort wiederherstellen".formatted(getSubjectPrefix()));
            helper.setText(mjmlService.render(mjml), true);

            mailSender.send(mimeMessage);
        } catch (RuntimeException | MessagingException e) {
            log.error("could not send reset password mail user %s".formatted(user), e);
        }
    }

    public void sendNewMessageEmail(String to) {
        log.info("sending new-message mail to {}", to);
        try {
            var variables = new HashMap<String, Object>();
            variables.put("link", applicationProperties.getBaseUrlVereine());

            var mjml = templateEngine.process("new-message", new Context(GERMAN, variables));
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            helper.setReplyTo(applicationProperties.getInfoMail());
            helper.setTo(to);
            helper.setBcc(applicationProperties.getBccMail());
            helper.setSubject("[%s] Neue Nachricht".formatted(getSubjectPrefix()));
            helper.setText(mjmlService.render(mjml), true);

            mailSender.send(mimeMessage);
        } catch (RuntimeException | MessagingException e) {
            log.error("could not send new message mail to %s".formatted(to), e);
        }
    }

    public void sendErrataMail(List<String> tos) {
        for (var to : tos) {
            try {
                log.info("sending errata mail to {}", to);
                var variables = new HashMap<String, Object>();
                variables.put("link", applicationProperties.getBaseUrlVereine());

                var mjml = templateEngine.process("errata", new Context(GERMAN, variables));
                var mimeMessage = mailSender.createMimeMessage();
                var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

                helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
                helper.setReplyTo(applicationProperties.getInfoMail());
                if (applicationProperties.isOverrideRecipient()) {
                    helper.setTo(applicationProperties.getBccMail());
                } else {
                    helper.setTo(to);
                    helper.setBcc(applicationProperties.getBccMail());
                }
                helper.setSubject("[%s] Neue Errata".formatted(getSubjectPrefix()));
                helper.setText(mjmlService.render(mjml), true);

                mailSender.send(mimeMessage);
            } catch (RuntimeException | MessagingException e) {
                log.error("could not send new message mail to %s".formatted(to), e);
            }
        }
    }

    public void sendScoresConfirmation(String to) {
        log.info("sending scores-confirmation mail to {}", to);
        try {
            var variables = new HashMap<String, Object>();
            variables.put("link", applicationProperties.getBaseUrlVereine());

            var mjml = templateEngine.process("scores-confirmation", new Context(GERMAN, variables));
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            if (applicationProperties.isOverrideRecipient()) {
                helper.setTo(applicationProperties.getBccMail());
            } else {
                helper.setTo(to);
                helper.setBcc(applicationProperties.getBccMail());
            }
            helper.setSubject("[%s] Neue Bewertung verfügbar".formatted(getSubjectPrefix()));
            helper.setText(mjmlService.render(mjml), true);

            mailSender.send(mimeMessage);
        } catch (RuntimeException | MessagingException e) {
            log.error("could not send scores-confirmation mail to %s".formatted(to), e);
        }
    }

    public void sendNewMessageInternalEmail(VereinPojo verein) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            helper.setReplyTo(applicationProperties.getInfoMail());
            helper.setTo(applicationProperties.getChatMail());
            helper.setSubject("[%s] Neue Nachricht".formatted(getSubjectPrefix()));
            helper.setText("Neue Nachricht vom Verein: %s".formatted(verein.getVereinsname()));
            mailSender.send(mimeMessage);
        } catch (RuntimeException | MessagingException e) {
            log.error("could not send new message mail to %s".formatted(applicationProperties.getChatMail()), e);
        }
    }

    public void sendHelperRegistrationEmail(HelperRegistrationPojo helperRegistration) {
        log.info("sending helper-registration mail to {}", helperRegistration.getEmail());
        try {
            var variables = new HashMap<String, Object>();
            variables.put("email", helperRegistration.getEmail());
            variables.put("vorname", helperRegistration.getVorname());
            variables.put("nachname", helperRegistration.getName());
            variables.put("adresse", helperRegistration.getAdresse());
            variables.put("plzOrt", helperRegistration.getPlzOrt());
            variables.put("geburtsdatum", formatDate(helperRegistration.getGeburtsdatum()));
            variables.put("telefon", helperRegistration.getTelefon());
            variables.put("vereinszugehoerigkeit", helperRegistration.getVereinszugehoerigkeit());
            variables.put("aufgaben", getAufgabenAsList(helperRegistration).stream().map(Aufgaben::getDescription).collect(joining(", ")));
            variables.put("anzahl", helperRegistration.getAnzahlEinsaetze());
            variables.put("freitag", getEinsatzzeit(getEinsatzFreitagAsList(helperRegistration)));
            variables.put("samstag", getEinsatzzeit(getEinsatzSamstagAsList(helperRegistration)));
            variables.put("sonntag", getEinsatzzeit(getEinsatzSonntagAsList(helperRegistration)));
            variables.put("shirt", helperRegistration.getGroesseShirt());
            variables.put("comment", helperRegistration.getComment());

            var mjml = templateEngine.process("helper-registration", new Context(GERMAN, variables));

            var mimeMessage = mailSender.createMimeMessage();

            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            helper.setReplyTo(applicationProperties.getHelferMail());
            helper.setTo(helperRegistration.getEmail());
            helper.setCc(applicationProperties.getHelferMail());
            helper.setBcc(applicationProperties.getBccMail());
            helper.setSubject("[%s] Anmeldung Helfer".formatted(getSubjectPrefix()));
            helper.setText(mjmlService.render(mjml), true);

            mailSender.send(mimeMessage);
        } catch (RuntimeException | MessagingException e) {
            log.error("could not send helper registration mail for %s".formatted(helperRegistration.getEmail()), e);
        }
    }

    public void sendWebhookEmail(String action, String resource, String status, String name, String version) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_NO, UTF_8.name());
            helper.setFrom(environment.getRequiredProperty("spring.mail.username"));
            helper.setTo(applicationProperties.getBccMail());
            helper.setSubject("[%s] Webhook".formatted(getSubjectPrefix()));
            helper.setText("Action: %s %nResource: %s %nStatus: %s %nApp: %s %nCommit: %s %n".formatted(action, resource, status, name, version));
            mailSender.send(mimeMessage);
        } catch (RuntimeException | MessagingException e) {
            log.error("could not send webhook message to %s".formatted(applicationProperties.getBccMail()), e);
        }
    }

    private static String getEinsatzzeit(List<Einsatzzeit> values) {
        return StringUtils.defaultIfBlank(values.stream().map(Einsatzzeit::getDescription).collect(joining(", ")), "-");
    }

    private String getSubjectPrefix() {
        var subject = "ZKMF2024";
        if (!environment.matchesProfiles("prod")) {
            subject += " " + Arrays.toString(environment.getActiveProfiles());
        }
        return subject;
    }
}
