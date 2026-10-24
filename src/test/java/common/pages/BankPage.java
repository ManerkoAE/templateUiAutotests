package common.pages;

import common.utils.SeleniumUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import static common.utils.SeleniumUtils.waitElementIsVisible;


public class BankPage extends BasePage {

    @Override
    protected PagePath path() {
        return PagePath.BANK;
    }

    private static final By TOGGLE_CARD_DETAILS = By.cssSelector("[data-testid='toggle-card-details']");
    private static final By FAST_TRANSFER_BUTTON = By.partialLinkText("Fast transfer/Быстрый перевод");
    private static final By LOGOUT_BUTTON = By.cssSelector("[data-testid='logout-btn']");

    public BankPage(WebDriver driver) {
        super(driver);
    }

    @Step("нажать кнопку Подробной информации о картах")
    public void clickShowCardDetails() {
        waitElementIsVisible(driver, TOGGLE_CARD_DETAILS).click();
    }

    @Step("Нажать кнопку \"Быстрый перевод\"")
    public void clickFastTransferButton() {waitElementIsVisible(driver, FAST_TRANSFER_BUTTON).click();}

    @Step("Проверить, что пользователь с email {email} залогинен")
    public boolean isUserLoggedIn(String email) {
        By userEmail = By.cssSelector("[title='%s']".formatted(email));
        try {
            SeleniumUtils.waitElementIsVisible(driver, userEmail);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Выйти из системы")
    public void logout() {
        waitElementIsVisible(driver, LOGOUT_BUTTON).click();
    }
}
