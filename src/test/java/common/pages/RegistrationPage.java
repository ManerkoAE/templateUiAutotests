package common.pages;

import common.utils.SeleniumUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private final By registrationButton = By.cssSelector("a[data-testid='link-to-register']");

    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("email");
    private final By userNameInput = By.id("username");
    private final By newPasswordInput = By.id("password");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By registerButton = By.xpath("//button[@data-testid='register-submit']");
    private final By registerError = By.cssSelector("[data-testid='register-error']");
    private final By registerForm = By.xpath("//div/form");

    @Override
    protected String path() {
        return "/register";
    }

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Перейти на страницу регистрации")
    public void clickOnRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Заполнить форму регистрации")
    public void registerNewUser(String firstName, String lastName, String email, String randomLogin, String randomPassword, String confirmPassword) {
        SeleniumUtils.waitElementIsVisible(driver, firstNameInput).sendKeys(firstName);
        SeleniumUtils.waitElementIsVisible(driver, lastNameInput).sendKeys(lastName);
        SeleniumUtils.waitElementIsVisible(driver, emailInput).sendKeys(email);
        SeleniumUtils.waitElementIsVisible(driver, userNameInput).sendKeys(randomLogin);
        SeleniumUtils.waitElementIsVisible(driver, newPasswordInput).sendKeys(randomPassword);
        SeleniumUtils.waitElementIsVisible(driver, confirmPasswordInput).sendKeys(confirmPassword);
        SeleniumUtils.waitElementIsVisible(driver, registerButton).click();
    }

    @Step("Получить текст ошибки регистрации")
    public String getRegistrationErrorText() {
        return SeleniumUtils.waitElementIsVisible(driver, registerError).getText();
    }

    @Step("Проверить, что форма регистрации отображается")
    public boolean isRegisterFormVisible() {
        return SeleniumUtils.isElementVisible(driver, registerForm);
    }
}
