package common.pages.bank;

import common.pages.BasePage;
import common.pages.PagePath;
import config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static common.utils.SeleniumUtils.waitElementIsVisible;

public class CardsPage extends BasePage {


    private final By otpCode = By.cssSelector("[data-testid='otp-code-value']");
    private final By copyCodeButton = By.cssSelector("[title='Копировать код']");
    private final By otpInput = By.cssSelector("[data-testid='otp-input']");
    private final By otpSubmit = By.cssSelector("[data-testid='otp-submit-btn']");

    public CardsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PagePath path() {
        return PagePath.BANK;
    }

    @Step("Получить проверочный код")
    public String getOtpCode() {
        return waitElementIsVisible(driver, otpCode).getText();
    }

    @Step("Скопировать проверочный код")
    public void clickCopyCode() {
        waitElementIsVisible(driver, copyCodeButton).click();
    }

    @Step("Ввести проверочный код")
    public void enterOtpCode(String code) {
        WebElement input = waitElementIsVisible(driver, otpInput);
        input.clear();
        input.sendKeys(code);
    }

    @Step("Активировать поле для ввода проверочного кода")
    public String getOtpInputValue() {
        return driver.findElement(otpInput).getAttribute("value");
    }

    @Step("Подтвердить проверочный код")
    public void submitOtp() {
        waitElementIsVisible(driver, otpSubmit).click();
    }

    @Step("Подождать исчезновение поля")
    public void waitUntilOtpFieldDisappears() {
        new WebDriverWait(driver, Duration.ofSeconds(TestConfig.INSTANCE.explicitWait()))
                .until(ExpectedConditions.invisibilityOfElementLocated(otpSubmit));
    }
}
