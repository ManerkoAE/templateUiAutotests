package common.pages.bank_loans;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.pages.base.BasePage;
import org.openqa.selenium.WebElement;

public class bank_loans_page extends BasePage {
    private final By loginError = By.cssSelector("[data-testid='login-error']");

    public By getLoginError() {
        return loginError;
    }
    /**
     * @param text
     * */
    public bank_loans_page checkLoginErrorIsPresent (String text) {

        waitElementIsVisible(driver.findElement(loginError));
        WebElement errorMsg = driver.findElement(loginError);
        Assertions.assertEquals(text,errorMsg.getText());
        return this;
    }

    public bank_loans_page(WebDriver driver) {
        super(driver); }
        // ← Просто передаём драйвер в родительский класс


    }


