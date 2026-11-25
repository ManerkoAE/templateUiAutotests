package common.core;

import config.TestConfig;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


@Slf4j
public final class DriverHolder {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverHolder() {
    }

    public static void set(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver get() {
        return DRIVER.get();
    }

    public static void quit() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            return;
        }
        DRIVER.remove();

        if (TestConfig.INSTANCE.holdBrowserOpen()) {
            log.info("hold.browser.open=true — браузер оставлен открытым");
            return;
        }

        try {
            driver.quit();
        } catch (WebDriverException e) {
            log.warn("Не удалось закрыть браузер", e);
        }
    }
}
