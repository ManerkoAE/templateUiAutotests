package common.pages;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage {
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[@data-testid='login-submit']");
    private final By loginError = By.cssSelector("[data-testid='login-error']");
    private final By loginForm = By.xpath("//div/form");
    public LoginPage(WebDriver driver) {
        super(driver); }


    @Step("Войти как {login}")
    public void loginAs(String login, String password) {
        waitElementIsVisible(usernameInput).sendKeys(login);
        waitElementIsVisible(passwordInput).sendKeys(password);
        waitElementIsVisible(loginButton).click();
    }
    @Step("Получить текст ошибки логина")
    public String getErrorText() {
        return waitElementIsVisible(loginError).getText();
    }

    @Step("Проверить, что форма логина отображается")
    public boolean isLoginFormVisible() {
        return isElementVisible(loginForm);
    }
}
