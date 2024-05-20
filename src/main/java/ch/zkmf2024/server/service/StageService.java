package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import ch.zkmf2024.server.dto.VereinStageSetupDTO;
import ch.zkmf2024.server.repository.VereinRepository;
import ch.zkmf2024.server.repository.VereinRepository.StageSetupExport;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;

import static ch.zkmf2024.server.util.FormatUtil.formatDateWritten;
import static ch.zkmf2024.server.util.PdfUtil.HEIGHT_IN_MM;
import static ch.zkmf2024.server.util.PdfUtil.POINTS_PER_MM;
import static ch.zkmf2024.server.util.PdfUtil.WIDTH_IN_MM;
import static ch.zkmf2024.server.util.PdfUtil.addPageLandscape;
import static ch.zkmf2024.server.util.PdfUtil.multiLineSmall;
import static ch.zkmf2024.server.util.PdfUtil.save;
import static ch.zkmf2024.server.util.PdfUtil.textHeader;
import static ch.zkmf2024.server.util.PdfUtil.textNormal;
import static com.microsoft.playwright.options.AriaRole.HEADING;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
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

    public Optional<File> createPdf(String locationIdentifier) {
        try (var document = new PDDocument()) {
            for (var stageSetupExport : vereinRepository.getAllStageSetupsForExport().stream()
                                                        .filter(stageSetupExport -> locationIdentifier == null || locationIdentifier.equals(stageSetupExport.locationIdentifier()))
                                                        .sorted(comparing(StageSetupExport::location).thenComparing(StageSetupExport::date).thenComparing(StageSetupExport::time))
                                                        .toList()) {

                var contentStream = addPageLandscape(document);
                textHeader(contentStream, 10, 190, stageSetupExport.verein());
                textNormal(contentStream, 10, 185, "%s, %s - %s (Dirigentenpodest: %s, Anzahl Schlägelablagen: %s)".formatted(formatDateWritten(stageSetupExport.date()),
                                                                                                                              stageSetupExport.time(),
                                                                                                                              stageSetupExport.location(),
                                                                                                                              stageSetupExport.dirigentenpodest() ? "Ja" : "Nein",
                                                                                                                              ofNullable(stageSetupExport.ablagenAmount()).map(Object::toString).orElse("-")));

                if (isNotBlank(stageSetupExport.comment())) {
                    multiLineSmall(contentStream, 10, 35, stageSetupExport.comment(), 150);
                }

                if (stageSetupExport.image() != null) {
                    var image = PDImageXObject.createFromByteArray(document, stageSetupExport.image(), stageSetupExport.verein());
                    contentStream.drawImage(image, 10 * POINTS_PER_MM, 39 * POINTS_PER_MM, 256 * POINTS_PER_MM, 144 * POINTS_PER_MM);
                } else {
                    textNormal(contentStream, 10, 175, stageSetupExport.additionalImage() != null ? "Nur Zusatzbild erfasst" : "Kein Bühnenplan erfasst");
                }
                contentStream.close();

                if (stageSetupExport.additionalImage() != null) {
                    var additionalContentStream = addPageLandscape(document);
                    textHeader(additionalContentStream, 10, 190, stageSetupExport.verein() + " (Zusatzbild)");

                    var additionalImage = PDImageXObject.createFromByteArray(document, stageSetupExport.additionalImage(), stageSetupExport.verein());
                    var scaleWidth = ((float) WIDTH_IN_MM - 20) / additionalImage.getWidth();
                    var scaleHeight = ((float) HEIGHT_IN_MM - 40) / additionalImage.getHeight();
                    var ratio = Math.min(Math.min(scaleWidth, scaleHeight), 1);
                    var height = additionalImage.getHeight() * ratio;
                    additionalContentStream.drawImage(additionalImage,
                                                      10 * POINTS_PER_MM,
                                                      (HEIGHT_IN_MM - height - 30) * POINTS_PER_MM,
                                                      additionalImage.getWidth() * ratio * POINTS_PER_MM,
                                                      height * POINTS_PER_MM);
                    additionalContentStream.close();
                }
            }
            return Optional.of(save(document));
        } catch (IOException e) {
            log.error("error creating pdf", e);
            return Optional.empty();
        }
    }
}
