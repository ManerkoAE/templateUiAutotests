package common.pages;

import common.utils.SeleniumUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By REGISTRATION_BUTTON = By.xpath("//a[@data-testid='link-to-register']");
    private static final By USERNAME_INPUT = By.id("username");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("[data-testid='login-submit']");
    private static final By LOGIN_ERROR = By.cssSelector("[data-testid='login-error']");
    private static final By LOGIN_FORM = By.xpath("//div/form");

    @Override
    protected PagePath path() {
        return PagePath.LOGIN;
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Войти как {login}")
    public void loginAs(String login, String password) {
        SeleniumUtils.waitElementIsVisible(driver, USERNAME_INPUT).sendKeys(login);
        SeleniumUtils.waitElementIsVisible(driver, PASSWORD_INPUT).sendKeys(password);
        SeleniumUtils.waitElementIsVisible(driver, LOGIN_BUTTON).click();
    }

    @Step("Получить текст ошибки логина")
    public String getLoginErrorText() {
        return SeleniumUtils.waitElementIsVisible(driver, LOGIN_ERROR).getText();
    }

    @Step("Проверить, что форма логина отображается")
    public boolean isLoginFormVisible() {
        return SeleniumUtils.isElementVisible(driver, LOGIN_FORM);
    }

    @Step("Перейти на страницу регистрации")
    public void clickOnRegistrationButton() {
        SeleniumUtils.waitElementIsVisible(driver, REGISTRATION_BUTTON).click();
    }

}
