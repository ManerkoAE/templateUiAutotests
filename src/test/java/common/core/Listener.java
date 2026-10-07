package common.core;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Listener implements TestWatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOGGER.info("Test {} PASSED", context.getDisplayName());
        quitDriver(context);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOGGER.error("Test {} FAILED: {}", context.getDisplayName(), cause.getMessage());
        attachScreenshot(context);
        quitDriver(context);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        quitDriver(context);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        // драйвер не создавался, закрывать нечего
    }

    private void attachScreenshot(ExtensionContext context) {
        Object instance = context.getRequiredTestInstance();
        if (!(instance instanceof BaseTest baseTest)) return;
        WebDriver driver = baseTest.driver;
        if (driver == null) return;
        try {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            attachScreenshotToAllure(png);
        } catch (Exception e) {
            LOGGER.warn("Не удалось сделать скриншот: {}", e.getMessage());
        }
    }

    private void quitDriver(ExtensionContext context) {
        Object instance = context.getRequiredTestInstance();
        if (!(instance instanceof BaseTest baseTest)) return;
        WebDriver driver = baseTest.driver;
        if (driver != null) {
            try { driver.quit(); }
            catch (Exception e) { LOGGER.warn("Не удалось закрыть драйвер: {}", e.getMessage()); }
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshotToAllure(byte[] png) {
        return png;
    }
}