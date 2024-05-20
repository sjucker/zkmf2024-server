package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.JudgeReportDTO;
import ch.zkmf2024.server.dto.JudgeReportRatingDTO;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.repository.JudgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_A;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_B;
import static ch.zkmf2024.server.dto.JudgeReportModulCategory.MODUL_G_KAT_C;
import static ch.zkmf2024.server.util.FormatUtil.formatDateWritten;
import static ch.zkmf2024.server.util.FormatUtil.formatTime;
import static ch.zkmf2024.server.util.PdfUtil.addPagePortrait;
import static ch.zkmf2024.server.util.PdfUtil.drawLine;
import static ch.zkmf2024.server.util.PdfUtil.drawRectangle;
import static ch.zkmf2024.server.util.PdfUtil.save;
import static ch.zkmf2024.server.util.PdfUtil.textBold;
import static ch.zkmf2024.server.util.PdfUtil.textHeader;
import static ch.zkmf2024.server.util.PdfUtil.textNormal;
import static java.util.Comparator.comparing;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
public class JudgePdfService {

    private final JudgeRepository judgeRepository;

    public JudgePdfService(JudgeRepository judgeRepository) {
        this.judgeRepository = judgeRepository;
    }

    public Optional<File> create() {
        try (var document = new PDDocument()) {
            for (var report : judgeRepository.getAllReports().stream()
                                             .sorted(comparing(JudgeReportDTO::location).thenComparing(JudgeReportDTO::start))
                                             .toList()) {

                if (report.modul() == Modul.G) {
                    printReportModulG(report, document);
                } else {
                    printReport(report, document);
                }
            }
            return Optional.of(save(document));
        } catch (IOException e) {
            log.error("error creating pdf", e);
            return Optional.empty();
        }
    }

    private void printReportModulG(JudgeReportDTO report, PDDocument document) throws IOException {
        try (var contentStream = addPagePortrait(document)) {
            textHeader(contentStream, 10, 285, report.verein());
            textBold(contentStream, 10, 280, "%s - %s".formatted(report.judgeName(), report.roleDescription()));
            textBold(contentStream, 10, 270, "%s / %s, %s - %s".formatted(report.location(),
                                                                          formatDateWritten(report.start().toLocalDate()),
                                                                          formatTime(report.start().toLocalTime()),
                                                                          formatTime(report.end().toLocalTime())));
            textNormal(contentStream, 10, 265, "Modul: %s, Besetzung: %s (%s)".formatted(report.modulDescription(), defaultIfBlank(report.besetzung(), "-"), report.categoryDescription()));
            textNormal(contentStream, 10, 260, "Dirigent/in: %s".formatted(report.dirigent()));

            if (report.category() == MODUL_G_KAT_A) {
                textBold(contentStream, 10, 250, "Grundlage I - " + report.modulGKatA1() + " (min. 5 Punkte, max. 10 Punkte)");
                drawLine(contentStream, 10, 200, 249);

                textBold(contentStream, 10, 240, "Grundlage II - " + report.modulGKatA2() + " (min. 5 Punkte, max. 10 Punkte)");
                drawLine(contentStream, 10, 200, 239);

                var y = 220;
                textBold(contentStream, 10, y, "Frei gewählter Baslermarsch (BM) oder Marsch (M)");
                y -= 5;
                var title1 = report.titles().getFirst();
                textNormal(contentStream, 10, y, "%s, %s, Klasse: %s".formatted(title1.titel().titelName(), title1.titel().composer(), title1.titel().grad()));
                y -= 10;

                for (var rating : title1.ratings()) {
                    textNormal(contentStream, 10, y, rating.categoryDescription());
                    drawLine(contentStream, 10, 200, y - 1);

                    y -= 10;
                }
                y -= 10;

                textBold(contentStream, 10, y, "Frei gewählte Komposition");
                y -= 5;
                var title2 = report.titles().getLast();
                textNormal(contentStream, 10, y, "%s, %s, Klasse: %s".formatted(title2.titel().titelName(), title2.titel().composer(), title2.titel().grad()));
                y -= 10;
                for (var rating : title2.ratings()) {
                    textNormal(contentStream, 10, y, rating.categoryDescription());
                    drawLine(contentStream, 10, 200, y - 1);

                    y -= 10;
                }
                y -= 10;

                textBold(contentStream, 10, y, "Total Punkte");
                drawLine(contentStream, 10, 70, y - 1);

            } else if (report.category() == MODUL_G_KAT_B) {
                var y = 250;
                textBold(contentStream, 10, y, "Kategorie B - Frei gewählter Vortrag");
                y -= 5;
                var title = report.titles().getFirst();
                textNormal(contentStream, 10, y, "%s, %s, Klasse: %s".formatted(title.titel().titelName(), title.titel().composer(), title.titel().grad()));
                y -= 10;

                for (var rating : title.ratings()) {
                    textNormal(contentStream, 10, y, rating.categoryDescription());
                    drawLine(contentStream, 10, 200, y - 1);

                    y -= 10;
                }
                textNormal(contentStream, 10, y, "Begründung Abzug");
                drawLine(contentStream, 10, 200, y - 1);
                y -= 10;

                textBold(contentStream, 10, y, "Total Punkte");
                drawLine(contentStream, 10, 70, y - 1);

            } else if (report.category() == MODUL_G_KAT_C) {
                var y = 250;
                textBold(contentStream, 10, y, "Kategorie C - Frei gewählter Vortrag");
                y -= 5;
                var title = report.titles().getFirst();
                textNormal(contentStream, 10, y, "%s, %s, Klasse: %s".formatted(title.titel().titelName(), title.titel().composer(), title.titel().grad()));
                y -= 10;

                for (var rating : title.ratings()) {
                    textNormal(contentStream, 10, y, rating.categoryDescription());
                    drawLine(contentStream, 10, 200, y - 1);

                    y -= 10;
                }
                y -= 10;

                textBold(contentStream, 10, y, "Total Punkte");
                drawLine(contentStream, 10, 70, y - 1);
            }
        }
    }

    private void printReport(JudgeReportDTO report, PDDocument document) throws IOException {
        var page = 1;
        try (var contentStream = addPagePortrait(document)) {
            textHeader(contentStream, 10, 285, "%s (%s/%s)".formatted(report.verein(), page, report.titles().size() + 1));
            textBold(contentStream, 10, 280, "%s - %s".formatted(report.judgeName(), report.roleDescription()));
            textBold(contentStream, 10, 270, "%s / %s, %s - %s".formatted(report.location(),
                                                                          formatDateWritten(report.start().toLocalDate()),
                                                                          formatTime(report.start().toLocalTime()),
                                                                          formatTime(report.end().toLocalTime())));
            textNormal(contentStream, 10, 265, "Modul: %s, Klasse: %s, Besetzung: %s".formatted(report.modulDescription(),
                                                                                                defaultIfBlank(report.klasse(), "-"),
                                                                                                defaultIfBlank(report.besetzung(), "-")));
            textNormal(contentStream, 10, 260, "Dirigent/in: %s".formatted(report.dirigent()));
            if (isNotBlank(report.programmTitel())) {
                textNormal(contentStream, 10, 255, "Programmtitel: %s".formatted(report.programmTitel()));
            }

            if (!report.overallRatings().isEmpty()) {
                textBold(contentStream, 10, 245, "Gesamteindruck");

                var y = 238;
                for (var rating : report.overallRatings()) {
                    textNormal(contentStream, 10, y, rating.categoryDescription());
                    drawLine(contentStream, 10, 200, y - 1);
                    y -= 12;
                }

                y -= 10;
                textBold(contentStream, 10, y, "Punktzahl");
                drawLine(contentStream, 10, 70, y - 1);
            }
        }

        for (var title : report.titles()) {
            try (var contentStream = addPagePortrait(document)) {
                textHeader(contentStream, 10, 285, "%s (%s/%s)".formatted(report.verein(), ++page, report.titles().size() + 1));
                textNormal(contentStream, 10, 280, "%s - %s".formatted(report.judgeName(), report.roleDescription()));

                var duration = Duration.ofSeconds(title.titel().durationInSeconds());
                textBold(contentStream, 10, 270, "%s%s, %s".formatted(title.titel().titelName(),
                                                                      title.titel().pflichtStueck() ? "*" : "",
                                                                      title.titel().composer()));
                textNormal(contentStream, 10, 265, "Grad: %s, Dauer: %s".formatted(title.titel().grad() != null ? title.titel().grad().toString() : "-",
                                                                                   "%d:%02d".formatted(duration.toMinutesPart(), duration.toSecondsPart())));

                textBold(contentStream, 10, 255, "Kurzbericht");
                drawRectangle(contentStream, 10, 253, 190, 43);

                var y = 200;
                for (var group : title.ratings().stream().map(JudgeReportRatingDTO::group).distinct().toList()) {
                    if (isNotBlank(group)) {
                        textBold(contentStream, 10, y, group);
                        y -= 5;
                    }

                    for (var rating : title.ratings().stream().filter(r -> r.group().equals(group)).toList()) {
                        textNormal(contentStream, 10, y, rating.categoryDescription());
                        drawLine(contentStream, 10, 200, y - 1);
                        y -= 12;
                    }

                    y -= 5;
                }
            }
        }
    }

}
