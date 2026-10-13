package common.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CardsPage extends BasePage {
    private final By toggleCardDetails = By.xpath("//button[@data-testid='toggle-card-details']");
    private final By otpCode = By.cssSelector("[data-testid='otp-code-value']");
    private final By otpInput = By.xpath("//input[@data-testid='otp-input']");
    private final By otpSubmit = By.xpath("//button[@data-testid='otp-submit-btn']");

    public CardsPage(WebDriver driver) { super(driver); }
    @Step("Нажать кнопку деталей карты")
    public void clickShowCardDetails() {
        waitElementIsVisible(toggleCardDetails).click();
    }
    @Step("Получить проверочный код")
    public String getOtpCode() {
        return waitElementIsVisible(otpCode).getText();
    }
    @Step("Ввести проверочный код")
    public void enterOtpCode(String code) {
        waitElementIsVisible(otpInput).sendKeys(code);
    }
    @Step("Подтвердить проверочный код")
    public void submitOtp() {
        waitElementIsVisible(otpSubmit).click();
    }
}