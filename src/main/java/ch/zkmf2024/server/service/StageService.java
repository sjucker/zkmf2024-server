package ch.zkmf2024.server.service;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import ch.zkmf2024.server.dto.VereinStageSetupDTO;
import ch.zkmf2024.server.repository.VereinRepository;
import ch.zkmf2024.server.repository.VereinRepository.StageSetupExport;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Optional;

import static ch.zkmf2024.server.util.FormatUtil.formatDateWritten;
import static com.microsoft.playwright.options.AriaRole.HEADING;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA;
import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

@Slf4j
@Service
public class StageService {

    private static final float POINTS_PER_INCH = 72;
    private static final float POINTS_PER_MM = 1 / (10 * 2.54f) * POINTS_PER_INCH;

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
            var font = new PDType1Font(HELVETICA);
            var fontBold = new PDType1Font(HELVETICA_BOLD);

            for (var stageSetupExport : vereinRepository.getAllStageSetupsForExport().stream()
                                                        .filter(stageSetupExport -> locationIdentifier == null || locationIdentifier.equals(stageSetupExport.locationIdentifier()))
                                                        .sorted(comparing(StageSetupExport::location).thenComparing(StageSetupExport::date).thenComparing(StageSetupExport::time))
                                                        .toList()) {

                var page = new PDPage(new PDRectangle(297 * POINTS_PER_MM, 210 * POINTS_PER_MM));
                document.addPage(page);

                var contentStream = new PDPageContentStream(document, page);
                contentStream.beginText();
                contentStream.newLineAtOffset(10 * POINTS_PER_MM, 190 * POINTS_PER_MM);
                contentStream.setFont(fontBold, 20);
                contentStream.showText(stageSetupExport.verein());
                contentStream.endText();

                contentStream.beginText();
                contentStream.newLineAtOffset(10 * POINTS_PER_MM, 185 * POINTS_PER_MM);
                contentStream.setFont(font, 12);
                contentStream.showText("%s, %s - %s (Dirigentenpodest: %s, Anzahl Schlägelablagen: %s)".formatted(formatDateWritten(stageSetupExport.date()),
                                                                                                                  stageSetupExport.time(),
                                                                                                                  stageSetupExport.location(),
                                                                                                                  stageSetupExport.dirigentenpodest() ? "Ja" : "Nein",
                                                                                                                  ofNullable(stageSetupExport.ablagenAmount()).map(Object::toString).orElse("-")));
                contentStream.endText();

                if (isNotBlank(stageSetupExport.comment())) {
                    var lines = StringUtils.replace(stageSetupExport.comment(), "\n", System.lineSeparator()).split(System.lineSeparator());
                    int i = 0;
                    for (var line : lines) {
                        for (var wrapLine : WordUtils.wrap(line, 150).split(System.lineSeparator())) {
                            contentStream.beginText();
                            contentStream.setFont(font, 10);
                            contentStream.newLineAtOffset(10 * POINTS_PER_MM, (35 - (i * 4)) * POINTS_PER_MM);
                            contentStream.showText(wrapLine);
                            contentStream.endText();
                            i++;
                        }
                    }
                }

                var image = PDImageXObject.createFromByteArray(document, stageSetupExport.image(), stageSetupExport.location());
                contentStream.drawImage(image, 10 * POINTS_PER_MM, 39 * POINTS_PER_MM, 256 * POINTS_PER_MM, 144 * POINTS_PER_MM);
                contentStream.close();
            }
            var temp = Files.createTempFile(null, ".pdf").toFile();
            document.save(temp);
            return Optional.of(temp);
        } catch (IOException e) {
            log.error("error creating pdf", e);
            return Optional.empty();
        }
    }
}
