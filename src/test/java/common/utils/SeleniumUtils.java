package common.utils;

import config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class SeleniumUtils {

    private SeleniumUtils() {
    }

    @Step("Ожидать пока отобразится элемент {locator}")
    public static WebElement waitElementIsVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TestConfig.INSTANCE.explicitWait()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Проверка видимости элемента {locator}")
    public static boolean isElementVisible(WebDriver driver, By locator) {
        try {
            return waitElementIsVisible(driver, locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Дождаться URL, подходящего под шаблон {regex}")
    public static void waitForUrlMatches(WebDriver driver, String regex) {
        new WebDriverWait(driver, Duration.ofSeconds(TestConfig.INSTANCE.explicitWait()))
                .until(ExpectedConditions.urlMatches(regex));
    }
}