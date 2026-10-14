package common.pages;

import common.utils.SeleniumUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[@data-testid='login-submit']");
    private final By loginError = By.cssSelector("[data-testid='login-error']");
    private final By loginForm = By.xpath("//div/form");

    @Override
    protected String path() {
        return "/login";
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Войти как {login}")
    public void loginAs(String login, String password) {
        SeleniumUtils.waitElementIsVisible(driver, usernameInput).sendKeys(login);
        SeleniumUtils.waitElementIsVisible(driver, passwordInput).sendKeys(password);
        SeleniumUtils.waitElementIsVisible(driver, loginButton).click();
    }

    @Step("Получить текст ошибки логина")
    public String getErrorText() {
        return SeleniumUtils.waitElementIsVisible(driver, loginError).getText();
    }

    @Step("Проверить, что форма логина отображается")
    public boolean isLoginFormVisible() {
        return SeleniumUtils.isElementVisible(driver, loginForm);
    }
}
