package ch.zkmf2024.server.service;

import ch.zkmf2024.server.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.Context;

public class PdfGenerationServiceIT extends AbstractIntegrationTest {

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Test
    void testGeneratePdf() {
        var result = pdfGenerationService.generateHtml("pdf/diploma/DIPLOMA.tl.html", new Context());
        pdfGenerationService.generatePdf("pdf/diploma/DIPLOMA.tl.html", new Context());
    }
}
