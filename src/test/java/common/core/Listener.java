package common.core;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;

import java.util.Optional;


public class Listener implements TestWatcher {

    @Override
    @Step("Тест пройден")
    public void testSuccessful(ExtensionContext context) {
        quitDriver(context);
    }

    @Override
    @Step("Тест провален")
    public void testFailed(ExtensionContext context, Throwable cause) {
        attachScreenshot(context);
        quitDriver(context);
    }

    @Override
    @Step("Тест прерван")
    public void testAborted(ExtensionContext context, Throwable cause) {
        quitDriver(context);
    }

    @Override
    @Step("Тест недоступен")
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
        }
    }

    private void quitDriver(ExtensionContext context) {
        Object instance = context.getRequiredTestInstance();
        if (!(instance instanceof BaseTest baseTest)) return;
        WebDriver driver = baseTest.driver;
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
            }
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshotToAllure(byte[] png) {
        return png;
    }
}