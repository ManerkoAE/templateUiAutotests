package common.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.Constant.Timeouts.EXPLICIT_WAIT;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    @Step("Получить актуальную страницу сайта")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement waitElementIsVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    @Step("Ждать появления нужного URL")
    public void waitForUrlContains(String urlPart) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.urlContains(urlPart));
    }
    @Step("Проверка наличия элемента")
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
    @Step("Проверка видимости элемента")
    public boolean isElementVisible(By locator) {
        try {
            return waitElementIsVisible(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}