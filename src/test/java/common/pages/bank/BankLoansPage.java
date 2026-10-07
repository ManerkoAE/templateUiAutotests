package common.pages.bank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.Constant.Timeouts.EXPLICIT_WAIT;

public class BankLoansPage extends BasePage {

    private final By loginError = By.cssSelector("[data-testid='login-error']");
    private final By toggleCardDetails = By.xpath("//button[@data-testid='toggle-card-details']");
    private final By otpCode = By.cssSelector("[data-testid='otp-code-value']");
    private final By copyCodeButton = By.xpath("//button[@title='Копировать код']");
    private final By otpInput = By.xpath("//input[@data-testid='otp-input']");
    private final By otpSubmit = By.xpath("//button[@data-testid='otp-submit-btn']");

    public BankLoansPage(WebDriver driver) {
        super(driver);
    }

    public BankLoansPage checkLoginErrorIsPresent(String text) {
        WebElement errorMsg = waitElementIsVisible(loginError);
      //  Assertions.assertEquals(text, errorMsg.getText());
        return this;
    }
    public void clickShowCardDetails() {
        waitElementIsVisible(toggleCardDetails).click();
    }

    public String getOtpCode() {
        return waitElementIsVisible(otpCode).getText();
    }

    public void clickCopyCode() {
        waitElementIsVisible(copyCodeButton).click();
    }

    public void enterOtpCode(String code) {
        WebElement input = waitElementIsVisible(otpInput);
        input.clear();
        input.sendKeys(code);
    }

    public String getOtpInputValue() {
        return driver.findElement(otpInput).getAttribute("value");
    }

    public void submitOtp() {
        waitElementIsVisible(otpSubmit).click();
    }

    public void waitUntilOtpFieldDisappears() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.invisibilityOfElementLocated(otpSubmit));
    }

    public By getLoginError() {
        return loginError;
    }

    public String getLoginErrorText() {
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(loginError))
                .getText();
    }
}


