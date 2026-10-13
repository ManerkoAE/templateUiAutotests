package common.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BankPage  extends BasePage {
    private final By userEmail = By.xpath("//*[@title='test@qabank.com']");
    private final By logoutButton = By.cssSelector("[data-testid='logout-btn']");

    public BankPage(WebDriver driver) { super(driver); }
@Step("Проверяем, залогинен ли пользователь")
    public boolean isUserLoggedIn() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        userEmail));
        return isElementPresent(userEmail);
    }
    public void logout() {
        waitElementIsVisible(logoutButton).click();
    }
}
