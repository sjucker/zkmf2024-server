package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import ch.zkmf2024.server.dto.VereinStageSetupDTO;
import ch.zkmf2024.server.repository.VereinRepository;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

import static com.microsoft.playwright.options.AriaRole.HEADING;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.apache.commons.lang3.StringUtils.replace;

@Slf4j
@Service
public class StageService {

    private final VereinRepository vereinRepository;
    private final ApplicationProperties applicationProperties;

    public StageService(VereinRepository vereinRepository, ApplicationProperties applicationProperties) {
        this.vereinRepository = vereinRepository;
        this.applicationProperties = applicationProperties;
    }

    @Scheduled(timeUnit = MINUTES, initialDelay = 2, fixedRate = 60)
    public void addMissingStageSetupImages() {
        for (var stageSetup : vereinRepository.findMissingStageSetupImages()) {
            doCreateImage(stageSetup);
        }
    }

    @Async
    public void createStageSetupImage(VereinStageSetupDTO stageSetup) {
        doCreateImage(stageSetup);
    }

    private void doCreateImage(VereinStageSetupDTO stageSetup) {
        log.info("Creating stage setup image for ID: {}", stageSetup.vereinId());
        try (var playwright = Playwright.create();
             var browser = playwright.chromium().launch();
             var page = browser.newPage()) {

            page.navigate("%s/#/stage/%s/%s".formatted(applicationProperties.getBaseUrl(),
                                                       stageSetup.locationIdentifier(),
                                                       URLEncoder.encode(replace(stageSetup.stageSetup(), " ", ""), UTF_8)));
            page.getByRole(HEADING).waitFor();
            vereinRepository.updateStageSetupImage(stageSetup.vereinId(), page.screenshot());
        } catch (Exception e) {
            log.error("error creating stage setup image", e);
        }
    }
}
