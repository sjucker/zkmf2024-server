package ch.zkmf2024.server.service.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

@Slf4j
public class BrowserFactory extends BasePooledObjectFactory<Browser> {

    @Override
    public Browser create() throws Exception {
        log.info("create new browser");
        return Playwright.create()
                         .chromium()
                         .launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @Override
    public PooledObject<Browser> wrap(Browser browser) {
        return new DefaultPooledObject<>(browser);
    }

    @Override
    public void destroyObject(PooledObject<Browser> p) {
        log.info("destroy browser");
        p.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<Browser> p) {
        var connected = p.getObject().isConnected();
        if (!connected) {
            log.info("browser is not connected");
        }
        return connected;
    }
}
