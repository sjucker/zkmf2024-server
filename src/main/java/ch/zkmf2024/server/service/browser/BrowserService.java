package ch.zkmf2024.server.service.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.SetContentOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.microsoft.playwright.options.WaitUntilState.NETWORKIDLE;

@Slf4j
@Service
public class BrowserService {

    private GenericObjectPool<Browser> pool;

    @PostConstruct
    public void init() {
        var config = new GenericObjectPoolConfig<Browser>();
        config.setMaxIdle(1);
        config.setMaxTotal(1);
        config.setTestOnReturn(true);
        config.setTestOnBorrow(true);
        pool = new GenericObjectPool<>(new BrowserFactory(), config);
    }

    public byte[] generatePdf(String html) throws Exception {
        var browser = this.pool.borrowObject();
        try (var page = browser.newPage()) {
            var options = new Page.PdfOptions();
            options.setFormat("A4");
            options.setPrintBackground(true);
            page.setContent(html, new SetContentOptions().setWaitUntil(NETWORKIDLE));
            return page.pdf(options);
        } finally {
            this.pool.returnObject(browser);
        }
    }
}
