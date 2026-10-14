package common.utils;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class SeleniumUtils {

    private SeleniumUtils() {}

    @Step("Ожидать пока отобразится элемент {locator}")
    public static WebElement waitElementIsVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(Config.INSTANCE.explicitWait()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Проверка наличия элемента {locator}")
    public static boolean isElementPresent(WebDriver driver, By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    @Step("Проверка видимости элемента {locator}")
    public static boolean isElementVisible(WebDriver driver, By locator) {
        try {
            return waitElementIsVisible(driver, locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Ждать появления нужного URL: {urlPart}")
    public static void waitForUrlContains(WebDriver driver, String urlPart) {
        new WebDriverWait(driver, Duration.ofSeconds(Config.INSTANCE.explicitWait()))
                .until(ExpectedConditions.urlContains(urlPart));
    }
}