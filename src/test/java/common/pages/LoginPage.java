package common.pages;

import config.ConfigLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[contains(text(),'Войти')]");
    private final By loginError = By.cssSelector("[data-testid='login-error']");
    private final By loginForm = By.cssSelector("form");
    public LoginPage(WebDriver driver) {
        super(driver); }
    @Step("Открыть страницу логина")
    public void open() {
        String baseUrl = ConfigLoader.get("BaseBankUrl");
        driver.get(baseUrl + "/login");
    }
    @Step("Войти как {login}")
    public void loginAs(String login, String password) {
        waitElementIsVisible(usernameInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
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
