package ch.zkmf2024.server.service;

import ch.zkmf2024.server.service.browser.BrowserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Slf4j
@RequiredArgsConstructor
public class PdfGenerationService {

    public static final String DIPLOMA_TEMPLATE = "pdf/diploma/DIPLOMA.tl.html";

    private final SpringTemplateEngine templateEngine;

    private final BrowserService browserService;

    public byte[] generatePdf(String template, Context model) {
        var html = generateHtml(template, model);
        try {
            return browserService.generatePdf(html);
        } catch (Exception e) {
            log.error("Error generating pdf", e);
        }
        return new byte[0];
    }

    protected String generateHtml(String template, Context model) {
        return templateEngine.process(template, model);
    }
}
