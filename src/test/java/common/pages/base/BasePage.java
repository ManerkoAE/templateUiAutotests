package common.pages.base;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.Constant.TimeoutVariables.EXPLISIT_WAIT;

public class BasePage {

public WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver=driver;
    }
    /**
     * The method of navigating to a specific URL
     * */
    public void goToUrl(String url) {
        driver.get(url);
    }
    /**
     * Wait for visibility element in DOM model
     * */
    public WebElement waitElementIsVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLISIT_WAIT)).until(ExpectedConditions.visibilityOf(element));
        return element;

    }

    /**
     * ✅ НОВЫЙ МЕТОД: Проверяет, есть ли элемент на странице (без падения)
     * @param locator - локатор элемента (By.id, By.cssSelector, By.xpath и т.д.)
     * @return true, если элемент есть; false, если его нет
     */
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    /**
     * ✅ ДОПОЛНИТЕЛЬНО: Проверяет, виден ли элемент (более строгая проверка)
     */
    public boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
