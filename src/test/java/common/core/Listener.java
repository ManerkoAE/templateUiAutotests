package common.core;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Slf4j
public class Listener implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        DriverHolder.quit();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        attachScreenshot();
        DriverHolder.quit();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        DriverHolder.quit();
    }

    private void attachScreenshot() {
        WebDriver driver = DriverHolder.get();
        if (driver == null) {
            return;
        }
        try {
            attachScreenshotToAllure(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        } catch (RuntimeException e) {
            log.warn("Не удалось прикрепить снимок экрана", e);
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshotToAllure(byte[] png) {
        return png;
    }
}
