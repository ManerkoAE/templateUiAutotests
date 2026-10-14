package common.pages;

import common.utils.SeleniumUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import static common.utils.SeleniumUtils.waitElementIsVisible;


public class BankPage extends BasePage {

    @Override
    protected String path() {
        return "/bank";
    }

    private final By logoutButton = By.cssSelector("[data-testid='logout-btn']");

    public BankPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить, что пользователь с email {email} залогинен")
    public boolean isUserLoggedIn(String email) {
        By userEmail = By.xpath("//*[@title='" + email + "']");
        try {
            SeleniumUtils.waitElementIsVisible(driver, userEmail);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Выйти из системы")
    public void logout() {
        waitElementIsVisible(driver, logoutButton).click();
    }
}
