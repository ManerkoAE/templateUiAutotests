package common.pages;

import config.Config;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


@RequiredArgsConstructor
public class BasePage {
    protected final WebDriver driver;

    public void goToUrl(String url) {
        driver.get(url);
    }

    @Step("Открыть страницу логина")
    public void openLoginPage() {
        String baseUrl = Config.INSTANCE.baseBankUrl();
        driver.get(baseUrl + "/login");
    }

    @Step("Получить актуальную страницу сайта")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Ожидать пока отобразится элемент {0}")
    public WebElement waitElementIsVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(Config.INSTANCE.explicitWait()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Ждать появления нужного URL")
    public void waitForUrlContains(String urlPart) {
        new WebDriverWait(driver, Duration.ofSeconds(Config.INSTANCE.explicitWait()))
                .until(ExpectedConditions.urlContains(urlPart));
    }

    @Step("Проверка наличия элемента")
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    @Step("Проверка видимости элемента")
    public boolean isElementVisible(By locator) {
       if(true)
            return waitElementIsVisible(locator).isDisplayed();
        else
            return false;
        }
}