package common.pages;

import common.utils.SeleniumUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationPage extends BasePage {


    private static final By FIRST_NAME_INPUT = By.id("firstName");
    private static final By LAST_NAME_INPUT = By.id("lastName");
    private static final By EMAIL_INPUT = By.id("email");
    private static final By USER_NAME_INPUT = By.id("username");
    private static final By NEW_PASSWORD_INPUT = By.id("password");
    private static final By CONFIRM_PASSWORD_INPUT = By.id("confirmPassword");
    private static final By REGISTER_BUTTON = By.cssSelector("[data-testid='register-submit']");
    private static final By REGISTER_ERROR = By.cssSelector("[data-testid='register-error']");

    @Override
    protected PagePath path() {
        return PagePath.REGISTER;
    }

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить форму регистрации")
    public void registerNewUser(String firstName, String lastName, String email, String randomLogin, String randomPassword, String confirmPassword) {
        SeleniumUtils.waitElementIsVisible(driver, FIRST_NAME_INPUT).sendKeys(firstName);
        SeleniumUtils.waitElementIsVisible(driver, LAST_NAME_INPUT).sendKeys(lastName);
        SeleniumUtils.waitElementIsVisible(driver, EMAIL_INPUT).sendKeys(email);
        SeleniumUtils.waitElementIsVisible(driver, USER_NAME_INPUT).sendKeys(randomLogin);
        SeleniumUtils.waitElementIsVisible(driver, NEW_PASSWORD_INPUT).sendKeys(randomPassword);
        SeleniumUtils.waitElementIsVisible(driver, CONFIRM_PASSWORD_INPUT).sendKeys(confirmPassword);
        SeleniumUtils.waitElementIsVisible(driver, REGISTER_BUTTON).click();
    }

    /** Нативное сообщение HTML5-валидации у поля email (то, что видит пользователь). */
    @Step("Получить сообщение HTML5-валидации email")
    public String getEmailValidationMessage() {
        WebElement email = SeleniumUtils.waitElementIsVisible(driver, EMAIL_INPUT);
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", email);
    }

    /** Поле email блокирует отправку (HTML5)? */
    public boolean isEmailInvalidByHtml5() {
        WebElement email = driver.findElement(EMAIL_INPUT);
        return !(Boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].checkValidity();", email);
    }
    @Step("Получить текст ошибки регистрации")
    public String getRegistrationErrorText() {
        return SeleniumUtils.waitElementIsVisible(driver, REGISTER_ERROR).getText();
    }

    @Step("Получить текст ошибки регистрации или null")
    public String getRegistrationErrorTextOrNull() {
        List<WebElement> errors = driver.findElements(REGISTER_ERROR);
        return errors.isEmpty() ? null : errors.get(0).getText();
    }

    /** Быстрая проверка «есть ли ошибка» без ожидания. */
    public boolean isRegistrationErrorDisplayed() {
        return !driver.findElements(REGISTER_ERROR).isEmpty();
    }
}
