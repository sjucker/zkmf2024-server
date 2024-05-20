package ch.zkmf2024.server.util;

import org.apache.commons.text.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA;
import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

public final class PdfUtil {

    public static final float POINTS_PER_INCH = 72;
    public static final float POINTS_PER_MM = 1 / (10 * 2.54f) * POINTS_PER_INCH;
    // for landscape
    public static final int WIDTH_IN_MM = 297;
    public static final int HEIGHT_IN_MM = 210;

    public static final PDType1Font FONT = new PDType1Font(HELVETICA);
    public static final PDType1Font FONT_BOLD = new PDType1Font(HELVETICA_BOLD);

    private PdfUtil() {
    }

    public static void textSmall(PDPageContentStream stream, int x, int y, String text) throws IOException {
        text(stream, x, y, FONT, 10, text);
    }

    public static void textNormal(PDPageContentStream stream, int x, int y, String text) throws IOException {
        text(stream, x, y, FONT, 12, text);
    }

    public static void textBold(PDPageContentStream stream, int x, int y, String text) throws IOException {
        text(stream, x, y, FONT_BOLD, 12, text);
    }

    public static void textHeader(PDPageContentStream stream, int x, int y, String text) throws IOException {
        text(stream, x, y, FONT_BOLD, 20, text);
    }

    public static void text(PDPageContentStream stream, int x, int y, PDFont font, int fontSize, String text) throws IOException {
        stream.beginText();
        stream.newLineAtOffset(x * POINTS_PER_MM, y * POINTS_PER_MM);
        stream.setFont(font, fontSize);
        stream.showText(text);
        stream.endText();
    }

    public static void multiLineSmall(PDPageContentStream stream, int x, int y, String text, int wrapLength) throws IOException {
        var lines = replace(replace(text, "\n", System.lineSeparator()), "\t", " ").split(System.lineSeparator());
        int i = 0;
        for (var line : lines) {
            for (var wrapLine : WordUtils.wrap(line, wrapLength).split(System.lineSeparator())) {
                textSmall(stream, x, y - i * 4, wrapLine);
                i++;
            }
        }
    }

    public static void drawLine(PDPageContentStream stream, int xStart, int xEnd, int y) throws IOException {
        stream.moveTo(xStart * POINTS_PER_MM, y * POINTS_PER_MM);
        stream.lineTo(xEnd * POINTS_PER_MM, y * POINTS_PER_MM);
        stream.stroke();
    }

    public static void drawRectangle(PDPageContentStream stream, int x, int y, int width, int height) throws IOException {
        stream.addRect(x * POINTS_PER_MM, (y - height) * POINTS_PER_MM, width * POINTS_PER_MM, height * POINTS_PER_MM);
        stream.stroke();
    }

    public static PDPageContentStream addPagePortrait(PDDocument document) throws IOException {
        var page = new PDPage(new PDRectangle(HEIGHT_IN_MM * POINTS_PER_MM, WIDTH_IN_MM * POINTS_PER_MM));
        document.addPage(page);
        return new PDPageContentStream(document, page);
    }

    public static PDPageContentStream addPageLandscape(PDDocument document) throws IOException {
        var page = new PDPage(new PDRectangle(WIDTH_IN_MM * POINTS_PER_MM, HEIGHT_IN_MM * POINTS_PER_MM));
        document.addPage(page);
        return new PDPageContentStream(document, page);
    }

    public static File save(PDDocument document) throws IOException {
        var temp = Files.createTempFile(null, ".pdf").toFile();
        document.save(temp);
        return temp;
    }
}
